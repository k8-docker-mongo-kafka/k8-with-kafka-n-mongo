package com.kafka.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 *  Used to declare the spring cloud  Streams
 * @author Abu Siddiqi
 *
 */
public interface EventSource {

	@Output("output")
	MessageChannel outboundTopic();
}
