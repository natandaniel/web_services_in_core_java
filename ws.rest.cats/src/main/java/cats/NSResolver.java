package cats;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

public class NSResolver implements NamespaceContext {

	private Map<String, String> prefix2uri;
	private Map<String, String> uri2prefix;

	public NSResolver() {
		prefix2uri = Collections.synchronizedMap(new HashMap<String, String>());
		uri2prefix = Collections.synchronizedMap(new HashMap<String, String>());
	}

	public NSResolver(String prefix, String uri) {
		this();
		prefix2uri.put(prefix, uri);
		uri2prefix.put(uri, prefix);
	}

	public String getNamespaceURI(String prefix) {
		return prefix2uri.get(prefix);
	}

	public String getPrefix(String uri) {
		return uri2prefix.get(uri);
	}

	public Iterator getPrefixes(String uri) {
		return uri2prefix.keySet().iterator();
	}

}
