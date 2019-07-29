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

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	
	private static final String EMPTY = "";

	private enum KEYS {
		name, newName, size, colour, avgWeight, avgAgeExpectancy, coatLength, grooming, lifestyle
	}

	private byte[] catsByteArray;
	private List<Cat> catsList;
	private Map<String, Cat> catsMap;

	public CatsServiceImpl() throws IOException {
		catsMap = Collections.synchronizedMap(new HashMap<String, Cat>());
		readCatsXmlFileToByteArray();
		deserializeFromXmlBytesToCatsList();
	}

	@Override
	public Source invoke(Source request) {

		if (ctx == null) {
			throw new RuntimeException("Dependancy Injection failed on ctx");
		}

		MessageContext msgCtx = ctx.getMessageContext();
		String httpVerb = (String) msgCtx.get(MessageContext.HTTP_REQUEST_METHOD);
		httpVerb = httpVerb.trim().toUpperCase();

		if (GET.equals(httpVerb)) {
			return doGet(msgCtx);
		} else if (POST.equals(httpVerb)) {
			return doPost(msgCtx);
		} else if (PUT.equals(httpVerb)) {
			return doPut(msgCtx);
		} else if (DELETE.equals(httpVerb)) {
			return doDelete(msgCtx);
		} else {
			throw new HTTPException(405);
		}
	}

	private Source doGet(MessageContext msgCtx) throws HTTPException {

		System.out.println("Handling GET request ...");

		String queryString = (String) msgCtx.get(MessageContext.QUERY_STRING);

		System.out.println("input query : " + queryString);

		if (queryString == null) {
			return new StreamSource(new ByteArrayInputStream(catsByteArray));
		} else {

			Cat cat = catsMap.get(getValueFromQueryKey(queryString, KEYS.name.toString()));

			if (cat == null) {
				throw new HTTPException(404);
			} else {
				return new StreamSource(serializeFromCatToXmlBytes(cat));
			}
		}
	}

	private Source doPost(MessageContext msgCtx) {

		System.out.println("Handling POST request ...");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Map<String, List> request = (Map<String, List>) msgCtx.get(MessageContext.HTTP_REQUEST_HEADERS);

		@SuppressWarnings("unchecked")
		List<String> payload = request.get(PAYLOAD);

		if (payload == null) {
			throw new HTTPException(400);
		}

		// System.out.println("payload : " + payload);

		String xml = EMPTY;

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

			xp.setNamespaceContext(new NSResolver(EMPTY, nsUri.toString()));

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

			if (catsMap.containsKey(cat.getName())) {
				throw new HTTPException(400);
			}

			catsList.add(cat);
			catsMap.put(cat.getName(), cat);
			// catsMap.forEach((k, v) -> System.out.println(v));

			serializeCatsListToXmlFile(catsList);
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

	private Source doPut(MessageContext msgCtx) {

		System.out.println("Handling PUT request ...");

		String queryString = (String) msgCtx.get(MessageContext.QUERY_STRING);

		System.out.println("input query : " + queryString);

		if (queryString == null) {
			System.out.println("Bad Request : expecting query string");
			throw new HTTPException(400);
		} else {

			String name = getValueFromQueryKey(queryString, KEYS.name.toString());
			System.out.println("Updating " + name);

			if (name == null) {
				System.out.println("Bad Request : expecting 'name' parameter");
				throw new HTTPException(400);
			}

			if (!catsMap.containsKey(name)) {
				System.out.println("Not found : " + name + " unknown");
				throw new HTTPException(404);
			}

			Cat cat = catsMap.get(name);

			String newName = getValueFromQueryKey(queryString, KEYS.newName.toString());
			String size = getValueFromQueryKey(queryString, KEYS.size.toString());
			String colour = getValueFromQueryKey(queryString, KEYS.colour.toString());
			String avgWeight = getValueFromQueryKey(queryString, KEYS.avgWeight.toString());
			String avgAgeExpectancy = getValueFromQueryKey(queryString, KEYS.avgAgeExpectancy.toString());
			String coatLength = getValueFromQueryKey(queryString, KEYS.coatLength.toString());
			String grooming = getValueFromQueryKey(queryString, KEYS.grooming.toString());
			String lifestyle = getValueFromQueryKey(queryString, KEYS.lifestyle.toString());

			cat.setName(EMPTY.equals(newName)? name : newName);

			if (!EMPTY.equals(size)) {
				System.out.println("hello");
				cat.setSize(size);
			}

			if (!EMPTY.equals(colour)) {
				cat.setColour(colour);
			}

			if (!EMPTY.equals(avgWeight)) {
				System.out.println(avgWeight);
				cat.setAvgWeight(Integer.valueOf(avgWeight));
			}

			if (!EMPTY.equals(avgAgeExpectancy)) {
				cat.setAvgAgeExpectancy(Integer.valueOf(avgAgeExpectancy));
			}

			if (!EMPTY.equals(coatLength)) {
				cat.setCoatLength(coatLength);
			}

			if (!EMPTY.equals(grooming)) {
				cat.setGrooming(grooming);
			}

			if (!EMPTY.equals(lifestyle)) {
				cat.setLifestyle(lifestyle);
			}

			catsMap.put(cat.getName(), cat);

			try {
				serializeCatsListToXmlFile(catsList);
				readCatsXmlFileToByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				throw new HTTPException(500);
			}

			return responseToClient("updated cat " + cat.getName());
		}

	}

	private Source doDelete(MessageContext msgCtx) {

		System.out.println("Handling DELETE request ...");

		String query = (String) msgCtx.get(MessageContext.QUERY_STRING);

		System.out.println("input query : " + query);

		if (query == null) {
			throw new HTTPException(403);
		} else {
			String name = getValueFromQueryKey(query, KEYS.name.toString());

			if (!catsMap.containsKey(name)) {
				throw new HTTPException(404);
			}

			Cat cat = catsMap.get(name);
			catsList.remove(cat);
			catsMap.remove(name);

			try {
				serializeCatsListToXmlFile(catsList);
				readCatsXmlFileToByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				throw new HTTPException(500);
			}

			return responseToClient(name + " deleted from server");
		}

	}

	private StreamSource responseToClient(String msg) {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(stream);
		enc.writeObject(msg);
		enc.close();

		return new StreamSource(new ByteArrayInputStream(stream.toByteArray()));
	}

	private void readCatsXmlFileToByteArray() throws IOException {

		int length = (int) new File(getPathToCatsXmlFile()).length();
		catsByteArray = new byte[length];

		try (FileInputStream fileInputStream = new FileInputStream(getPathToCatsXmlFile())) {
			fileInputStream.read(catsByteArray);
		}
	}

	@SuppressWarnings("unchecked")
	private void deserializeFromXmlBytesToCatsList() {

		try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(catsByteArray))) {
			catsList = (List<Cat>) decoder.readObject();
			catsList.forEach(cat -> catsMap.put(cat.getName(), cat));
		}
	}

	private String getValueFromQueryKey(String queryString, String key) {

		String[] keyValuePairs = queryString.split("&");

		String value = null;

		for (int i = 0; i < keyValuePairs.length; i++) {
			if (key.equals(keyValuePairs[i].split("=")[0])) {
				
				if(keyValuePairs[i].split("=").length != 2) {
					value=EMPTY;
				}else {
					value = keyValuePairs[i].split("=")[1];
				}
				break;
			}
		}

		return value;
	}

	private ByteArrayInputStream serializeFromCatToXmlBytes(Object cat) {

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

	private void serializeCatsListToXmlFile(List<Cat> cats) throws IOException {

		String path = getPathToCatsXmlFile();

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));

		XMLEncoder enc = new XMLEncoder(out);
		enc.writeObject(cats);
		enc.close();
		out.close();

	}

	private String getPathToCatsXmlFile() {

		String currentWorkingDir = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String path = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "resources" + seperator
				+ FILE_NAME;
		return path;
	}
}
