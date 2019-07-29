package com.kafka.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kafka.model.DomainEvent;
import com.kafka.model.KafkaTestModel;

@RequestMapping("/kafka")
public interface KafkaTestController {

	/**
	 * Used to send event to kafka using spring cloud stream and also the event is
	 * persisted to mongo db
	 * 
	 * @param requestModel
	 */
	@PostMapping("/test")
	public void testkafka(@RequestBody KafkaTestModel requestModel);

	/**
	 * Used for dummy url in order to add the exception for ssl/tls in browser as
	 * the certificate is self signed
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/dummy/{name}")
	public String dummyTest(@PathVariable String name);

	/**
	 * Used to test uploading file to mongo db by converting the file to binary
	 * 
	 * @param multipart
	 * @param empId
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/resume/upload/{empId}")
	public String resumeUpload(@RequestParam("file") MultipartFile multipart, @PathVariable String empId,
			@RequestParam String name) throws IOException;

	/**
	 * used to fetch the event from mongo db
	 * 
	 * @param eventId
	 * @return
	 */
	@GetMapping("/domainevent/get/{eventId}")
	public Optional<DomainEvent> getDomainEvent(@PathVariable UUID eventId);

	/**
	 * Used to fetch the file fromm mongo db
	 * 
	 * @param empId
	 * @return
	 */
	@GetMapping("/resumeevent/get/{empId}")
	public String getResume(@PathVariable String empId);
}
