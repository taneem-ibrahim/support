/**
* To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.
*/
package org.jboss.samples.spring.ws.mtom.service;

import java.io.IOException;

import javax.activation.DataHandler;

public interface AttachmentRepository {
	DataHandler readAttachment(String name) throws IOException;
	void storeAttachment(String name, DataHandler attachment) throws IOException;

}
