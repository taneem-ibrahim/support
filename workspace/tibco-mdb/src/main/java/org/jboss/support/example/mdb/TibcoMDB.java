/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>
 */
package org.jboss.support.example.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter; 


/**
 * @author taneem
 *
 */
/** Notes: The MDB constructs its own connection factory behind the scenes based on the activation configuration properties.
 *  The only reason the generic JMS JCA RA needs a reference to a connection factory is because it is generic.
 * @author taneem
 *
 */

@MessageDriven(name = "TibcoMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "jndiParameters", propertyValue = "java.naming.factory.initial=com.tibco.tibjms.naming.TibjmsInitialContextFactory;java.naming.provider.url=tibjmsnaming://ems1_d.centene.com:10000"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "CENTENE.CENPAS.CLAIM.INBOUND.PEND.SUBMIT.QV1"),
        @ActivationConfigProperty(propertyName = "connectionFactory", propertyValue = "FTQueueConnectionFactory"),
        @ActivationConfigProperty(propertyName= "user",propertyValue="CenPasClms_app_stl_user_d"),
        @ActivationConfigProperty(propertyName= "password", propertyValue="c3np@$5Jx")})
        
@ResourceAdapter("org.jboss.genericjms")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TibcoMDB implements MessageListener {
	
	private final static Logger LOGGER = Logger.getLogger(TibcoMDB.class.toString());

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
