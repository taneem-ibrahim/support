/**
* To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.
*/
package org.jboss.samples.spring.ws.mtom.service;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import org.jboss.samples.spring.ws.mtom.schema.Attachment;
import org.jboss.samples.spring.ws.mtom.schema.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AttachmentRepositoryEndpoint {
	private AttachmentRepository attachmentRepository;
	private ObjectFactory objectFactory;
	
	public AttachmentRepositoryEndpoint(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
		this.objectFactory = new ObjectFactory();
	}
	
	@PayloadRoot(localPart = "StoreAttachmentRequest", namespace = "http://www.springframework.org/spring-ws/samples/mtom")
    @ResponsePayload
    public void store(@RequestPayload JAXBElement<Attachment> requestElement) throws IOException {
		Attachment request = requestElement.getValue();
		attachmentRepository.storeAttachment(request.getName(),request.getPayLoad().getAttachmentPayLoad());
    }
	@PayloadRoot(localPart = "DownloadAttachmentRequest", namespace = "http://www.springframework.org/spring-ws/samples/mtom")
    @ResponsePayload
    public JAXBElement<Attachment> load(@RequestPayload JAXBElement<String> requestElement) throws IOException {
        String name = requestElement.getValue();
        Attachment response = new Attachment();
        Attachment.PayLoad payLoad = new Attachment.PayLoad();
        payLoad.setAttachmentPayLoad(attachmentRepository.readAttachment(name));
        response.setName(name);
        response.setPayLoad(payLoad);
        return objectFactory.createDownloadAttachmentResponse(response);
    }

	
	
	
    
}
