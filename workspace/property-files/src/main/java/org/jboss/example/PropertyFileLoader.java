/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>
 */
package org.jboss.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PropertyFileLoader")
public class PropertyFileLoader extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(PropertyFileLoader.class.toString());

	public PropertyFileLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		try {
			Properties props = this.loadPropertiesFile("test.properties");
			out.write("<h2>Content of the property file:</h2>");
			if(props!=null){
				for(String key : props.stringPropertyNames()) {
					String value = props.getProperty(key);
					out.print("key=["+key+"]->value=["+value+"]"+"</br>");
				}
			}else{
				logger.warning("Failed to load properties file!");
				out.write("<h2>A problem occurred during reading the propery file, property object is NULL.</h2>");
				out.write("</br>");
				out.write("<p><i>Go your the JBoss EAP server console or log to see the error stack trace</i></p>");
				throw new RuntimeException("Failed to load properties file!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning(e.getMessage());
			out.write("<h2>A problem occurred during reading the propery file</h2>");
			out.write("</br>");
			out.write("<p><i>Go your the JBoss EAP server console or log to see the error stack trace</i></p>");
		}finally{
			if (out != null) {
                out.close();
            }
		}


	}

	private Properties loadPropertiesFile(String propertiesFile) throws Exception {
		URL url = Thread.currentThread().getContextClassLoader().getResource(propertiesFile);

		if(url == null) {
			logger.warning(propertiesFile + " does not exist");
			throw new Exception(propertiesFile + " does not exist");
		}

		InputStream is = url.openStream();

		Properties properties = new Properties();
		properties.load(is);

		if(is!=null) {
			is.close();
		}

		return properties;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}


}
