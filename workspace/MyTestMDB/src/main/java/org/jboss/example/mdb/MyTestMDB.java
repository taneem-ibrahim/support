/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>
 */
package org.jboss.example.mdb;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.jboss.ejb3.annotation.ResourceAdapter; 


/**
 * @author taneem
 *
 */
/** Notes to keep in mind: The MDB constructs its own connection factory behind the scenes based on the activation configuration properties.
 *  The only reason the generic JMS JCA RA needs a reference to a connection factory is because it is generic.
 *  HornetQ RA ignores the connectionFactory since it constructs its own connection factory behind the scenes. 
 *  HornetQ JCA RA is actually constructing a HornetQ ServerLocator and ClientSessionFactory, but you can think of it like a JMS connection factory.
 * @author taneem
 *
 */

@MessageDriven(name = "MyTestMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/TestQueue"),
        @ActivationConfigProperty(propertyName = "connectionFactory", propertyValue = "java:/JmsXA"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

@ResourceAdapter("hornetq-ra.rar")
public class MyTestMDB implements MessageListener {
	
	private final static Logger LOGGER = Logger.getLogger(MyTestMDB.class.toString());

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("*** Received Message from queue: " + msg.getText());
            } else {
                LOGGER.warning("*** Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
