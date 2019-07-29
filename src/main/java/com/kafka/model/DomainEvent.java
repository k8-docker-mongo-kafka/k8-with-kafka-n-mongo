package com.kafka.model;

public class DomainEvent<T> extends BaseEvent {

	public DomainEvent(T payload) {
		super(payload);
		
	}
 
	
	
}
