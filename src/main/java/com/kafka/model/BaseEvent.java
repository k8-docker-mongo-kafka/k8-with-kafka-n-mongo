package com.kafka.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * Generic event to take the payload of any type
 * @author AbuSiddiqi
 *
 * @param <T>
 */
public class BaseEvent<T> {

	@Id
	private UUID eventId;
	private T payload;

	
	public BaseEvent(T payload) {
		this.setPayload(payload);
		this.setEventId(UUID.randomUUID());
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "BaseEvent [eventId=" + eventId + ", payload=" + payload + "]";
	}
	
	
}
