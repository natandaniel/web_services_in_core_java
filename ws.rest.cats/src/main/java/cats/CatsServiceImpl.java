package cats;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class CatsServiceImpl implements Provider<Source> {

	@Resource
	protected WebServiceContext ctx;

	private static final String FILE_NAME = "cats.xml";
	private static final String PAYLOAD = "payload";

	private byte[] catsByteArray;
	private List<Cat> catsList;
	private Map<String, Cat> catsMap;

	public CatsServiceImpl() throws IOException {
		catsMap = Collections.synchronizedMap(new HashMap<String, Cat>());
		readCatsXmlFileToByteArray();
		deserializeFromXmlBytesToCatsObject();
	}

	@Override
	public Source invoke(Source request) {

		if (ctx == null) {
			throw new RuntimeException("Dependancy Injection failed on ctx");
		}

		MessageContext msgCtx = ctx.getMessageContext();
		String httpVerb = (String) msgCtx.get(MessageContext.HTTP_REQUEST_METHOD);
		httpVerb = httpVerb.trim().toUpperCase();

		if ("GET".equals(httpVerb)) {
			return doGet(msgCtx);
		} else if ("POST".equals(httpVerb)) {
			return doPost(msgCtx);
		} else {
			throw new HTTPException(405);
		}
	}

	private Source doGet(MessageContext msgCtx) throws HTTPException {

		String queryString = (String) msgCtx.get(MessageContext.QUERY_STRING);

		if (queryString == null) {
			return new StreamSource(new ByteArrayInputStream(catsByteArray));
		} else {

			Cat cat = catsMap.get(getValueFromQueryKey(queryString, "name"));

			if (cat == null) {
				throw new HTTPException(404);
			} else {
				return new StreamSource(serializeFromCatObjectToXmlBytes(cat));
			}
		}
	}

	private Source doPost(MessageContext msgCtx) {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Map<String, List> request = (Map<String, List>) msgCtx.get(MessageContext.HTTP_REQUEST_HEADERS);

		@SuppressWarnings("unchecked")
		List<String> payload = request.get(PAYLOAD);

		if (payload == null) {
			throw new HTTPException(400);
		}

		// System.out.println("payload : " + payload);

		String xml = "";

		for (String next : payload) {
			xml += next.trim();
		}

		// System.out.println("input xml : " + xml);

		ByteArrayInputStream xmlStream = new ByteArrayInputStream(xml.getBytes());

		String name = null;
		String size = null;
		String colour = null;
		String avgWeight = null;
		String avgAgeExpectancy = null;
		String coatLength = null;
		String grooming = null;
		String lifestyle = null;

		DOMResult dom = new DOMResult();
		Transformer trans;

			try {
				trans = TransformerFactory.newInstance().newTransformer();
				trans.transform(new StreamSource(xmlStream), dom);
				URI nsUri = new URI("create_cat");

				XPathFactory xpf = XPathFactory.newInstance();
				XPath xp = xpf.newXPath();

				xp.setNamespaceContext(new NSResolver("", nsUri.toString()));

				name = xp.evaluate("/create_cat/name", dom.getNode());
				size = xp.evaluate("/create_cat/size", dom.getNode());
				colour = xp.evaluate("/create_cat/colour", dom.getNode());
				avgWeight = xp.evaluate("/create_cat/avgWeight", dom.getNode());
				avgAgeExpectancy = xp.evaluate("/create_cat/avgAgeExpectancy", dom.getNode());
				coatLength = xp.evaluate("/create_cat/coatLength", dom.getNode());
				grooming = xp.evaluate("/create_cat/grooming", dom.getNode());
				lifestyle = xp.evaluate("/create_cat/lifestyle", dom.getNode());
				Cat cat = new Cat(name, size, colour, coatLength, Integer.valueOf(avgAgeExpectancy),
						Integer.valueOf(avgWeight), grooming, lifestyle);
				
				if(catsMap.containsKey(cat.getName())) {
					throw new HTTPException(400);
				}

				catsList.add(cat);
				catsMap.put(cat.getName(), cat);
				//catsMap.forEach((k, v) -> System.out.println(v));
				
				serializeFromCatsObjectToXmlResourceFile(catsList);
				readCatsXmlFileToByteArray();
			} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
				e.printStackTrace();
				throw new HTTPException(500);
			} catch (TransformerException e) {
				e.printStackTrace();
				throw new HTTPException(500);
			} catch (IOException e) {
				e.printStackTrace();
				throw new HTTPException(500);
			} catch (XPathExpressionException e) {				
				e.printStackTrace();
				throw new HTTPException(500);
			} catch (URISyntaxException e) {				
				e.printStackTrace();
				throw new HTTPException(500);
			}

		return responseToClient("Cat created!");
	}
	
	private StreamSource responseToClient(String msg) {
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(stream);
		enc.writeObject(msg);
		enc.close();
		
		return new StreamSource(new ByteArrayInputStream(stream.toByteArray()));
	}

	private void readCatsXmlFileToByteArray() throws IOException {

		int length = (int) new File(getPathToCatsXmlResourceFile()).length();
		catsByteArray = new byte[length];

		try (FileInputStream fileInputStream = new FileInputStream(getPathToCatsXmlResourceFile())) {
			fileInputStream.read(catsByteArray);
		} 
	}

	@SuppressWarnings("unchecked")
	private void deserializeFromXmlBytesToCatsObject() {

		//System.out.println("Deserializing cats.xml resource file into in memory Java object ...");

		try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(catsByteArray))) {
			catsList = (List<Cat>) decoder.readObject();
			catsList.forEach(cat -> catsMap.put(cat.getName(), cat));
		}
	}

	private String getValueFromQueryKey(String queryString, String key) {

		String[] keyValuePairs = queryString.split("=");

		if (!key.equalsIgnoreCase(keyValuePairs[0])) {
			throw new HTTPException(400);
		} else {
			return keyValuePairs[1].trim();
		}
	}

	private ByteArrayInputStream serializeFromCatObjectToXmlBytes(Object cat) {

		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			XMLEncoder encoder = new XMLEncoder(stream);
			Cat myCat = (Cat) cat;
			List<Cat> list = new ArrayList<Cat>();
			list.add(myCat);
			encoder.writeObject(list);
			encoder.close();
			return new ByteArrayInputStream(stream.toByteArray());
		} catch (IOException e) {
			throw new HTTPException(500);
		}
	}

	private void serializeFromCatsObjectToXmlResourceFile(List<Cat> cats) throws IOException {

		String path = getPathToCatsXmlResourceFile();

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));

		XMLEncoder enc = new XMLEncoder(out);
		enc.writeObject(cats);
		enc.close();
		out.close();

	}

	private String getPathToCatsXmlResourceFile() {

		String currentWorkingDir = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String path = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "resources" + seperator
				+ FILE_NAME;

		// System.out.println(path);
		return path;
	}
}
