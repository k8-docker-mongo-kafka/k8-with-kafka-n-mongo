package com.kafka.event.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.kafka.config.EventSource;
import com.kafka.model.BaseEvent;

@Component
public class EventProcessor<T extends BaseEvent> {

	@Autowired
	EventSource eventSource;

	/**
	 * Sending the payload to topic 
	 * @param t
	 */
	public void testKafka(T t) {
		MessageChannel outboundMessage = eventSource.outboundTopic();
		outboundMessage.send(MessageBuilder.withPayload(t)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());

	}

}
