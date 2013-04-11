/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package org.jboss.samples.spring.ws.mtom.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author taneem
 *
 */
public class StubAttachmentRepository implements AttachmentRepository{
	private static final Log logger = LogFactory.getLog(StubAttachmentRepository.class);
	private Map<String,DataHandler> attachments = new HashMap<String, DataHandler>();

	@Override
	public DataHandler readAttachment(String name) throws IOException {
		logger.info("Loading attachment "+name);
		if(attachments.containsKey(name)){
			return attachments.get(name);
		}else{
			return null;
		}
	}

	@Override
	public void storeAttachment(String name, DataHandler attachment) throws IOException {
		logger.info("Stroing attachment "+name);
		attachments.put(name, attachment);
	}

}
