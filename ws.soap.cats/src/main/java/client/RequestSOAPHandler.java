package client;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class RequestSOAPHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String LOGGER_NAME = "CLIENT_LOGGER";
	private Logger logger;

	public RequestSOAPHandler() {
		logger = Logger.getLogger(LOGGER_NAME);
	}

	public void close(MessageContext ctx) {
		logger.info("close");
	}

	public boolean handleFault(SOAPMessageContext ctx) {

		logger.info("handleFault");

		try {
			ctx.getMessage().writeTo(System.out);
		} catch (SOAPException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}

		return true;
	}

	public boolean handleMessage(SOAPMessageContext ctx) {

		logger.info("handleMessage");

		Boolean isRequest = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (isRequest) {
			try {
				ctx.getMessage().writeTo(System.out);
			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}

		return true;
	}

	public Set<QName> getHeaders() {

		logger.info("getHeaders");

		return null;
	}

}
