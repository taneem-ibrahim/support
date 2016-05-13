package org.jboss.qa;

import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;


public class RemoteJMSObjectFactory implements ObjectFactory {
	private final static Logger LOGGER = Logger.getLogger(RemoteJMSObjectFactory.class.toString());
	private Context context = null;
	private static final String PROVIDER_URL_PROP =  System.getProperty("tibco.url.port"); 

	public RemoteJMSObjectFactory() {
	}

	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
		if(LOGGER!=null){
			LOGGER.setLevel(Level.INFO);
		}
		try {
			if(PROVIDER_URL_PROP!=null && PROVIDER_URL_PROP.trim().length()>0){
				LOGGER.info("Tibco EMS instance and port:["+PROVIDER_URL_PROP+"]");
				String jndi = (String) obj;   
				final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.tibco.tibjms.naming.TibjmsInitialContextFactory");
				env.put(Context.URL_PKG_PREFIXES, "com.tibco.tibjms.naming");
				//env.put(Context.PROVIDER_URL, "tcp://stlem01d:10000");
				env.put(Context.PROVIDER_URL, PROVIDER_URL_PROP);
				context = new InitialContext(env);
				Object o = context.lookup(jndi);
				return o;
			}else{
				LOGGER.warning("tibco.url.port systemp property is not defined! Please add a JMV argument named <provider.url> with Tibco EMS server name and port value.");
				throw new IllegalArgumentException("tibco.url.port systemp property is not defined! Please add a JMV argument named <provider.url> with Tibco EMS server name and port value.");
			}

		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
