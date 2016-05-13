/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>
 */
package org.jboss.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyTestMDBServletClient
 */
@WebServlet("/MyTestMDBServletClient")
public class MyTestMDBServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int MSG_COUNT = 5;
	
	@Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/TestQueue")
    private Queue queue;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTestMDBServletClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection connection = null;
        out.write("<h1> This is an example <strong>JMS 1.1</strong> producer to test MyTestMDB</h1>");
        try{
        	Destination destination = queue;
        	out.write("<p>Sending messages to <em>" + destination + "</em></p>");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            connection.start();
            out.write("<h2>Following messages will be send to the destination:</h2>");
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < MSG_COUNT; i++) {
                message.setText("This is message " + (i + 1));
                messageProducer.send(message);
                out.write("Message (" + i + "): " + message.getText() + "</br>");
            }
            out.write("<p><i>Go to your JBoss EAP server console or log to see the result of messages processing</i></p>");   	
        }catch (JMSException e){
        	e.printStackTrace();
            out.write("<h2>A problem occurred during the delivery of this message</h2>");
            out.write("</br>");
            out.write("<p><i>Go your the JBoss EAP server console or log to see the error stack trace</i></p>");
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
