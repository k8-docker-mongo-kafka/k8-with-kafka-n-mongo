package com.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * used to Binds the stream to topics
 * @author IBMADMIN
 *
 */
@EnableBinding(EventSource.class)
public class EventSourceBinder {

}
