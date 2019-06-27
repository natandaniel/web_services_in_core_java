package cats;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.Source;
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

@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class CatsServiceImpl implements Provider<Source> {

	@Resource
	protected WebServiceContext ctx;

	private static final String fileName = "cats.xml";
	private byte[] catsByteArray;
	private List<Cat> catsList;
	private Map<String, Cat> catsMap;

	public CatsServiceImpl() {
		catsMap = Collections.synchronizedMap(new HashMap<String, Cat>());
		readCatsXmlFileToByteArray();
		deserializeFromXmlBytesToCats();
	}

	@Override
	public Source invoke(Source request) {

		if (ctx == null) {
			throw new RuntimeException("Dependancy Injection failed on ctx");
		}

		MessageContext msgCtx = ctx.getMessageContext();
		String httpVerb = (String) MessageContext.HTTP_REQUEST_METHOD;
		httpVerb = httpVerb.trim().toUpperCase();

		if ("GET".equals(httpVerb)) {
			return doGet(msgCtx);
		} else {
			throw new HTTPException(405);
		}
	}

	private void readCatsXmlFileToByteArray() {

		String currentWorkingDir = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String pathToFile = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "java" + seperator
				+ "cats" + seperator + fileName;

		int length = (int) new File(pathToFile).length();
		catsByteArray = new byte[length];

		try (FileInputStream fileInputStream = new FileInputStream(pathToFile)) {
			fileInputStream.read(catsByteArray);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void deserializeFromXmlBytesToCats() {
		try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(catsByteArray))) {
			catsList = (List<Cat>) decoder.readObject();
			catsList.forEach(cat -> catsMap.put(cat.getName(), cat));
		}
	}

	private Source doGet(MessageContext msgCtx) {

		String queryString = (String) msgCtx.get(MessageContext.QUERY_STRING);

		if (queryString == null) {
			return new StreamSource(new ByteArrayInputStream(catsByteArray));
		} else {

			String nameValue = getValueFromQueryKey(queryString, "name");

			Cat cat = catsMap.get(nameValue);

			if (cat == null) {
				throw new HTTPException(404);
			} else {
				return new StreamSource(serializeFromCatToXmlBytes(cat));
			}
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

	private ByteArrayInputStream serializeFromCatToXmlBytes(Object cat) {

		try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); XMLEncoder encoder = new XMLEncoder(stream);) {
			encoder.writeObject(cat);
			return new ByteArrayInputStream(stream.toByteArray());
		} catch (IOException e) {
			throw new HTTPException(500);
		}
	}
}
