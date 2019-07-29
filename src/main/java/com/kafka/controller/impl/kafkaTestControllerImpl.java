package com.kafka.controller.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kafka.controller.KafkaTestController;
import com.kafka.event.processor.EventProcessor;
import com.kafka.model.DomainEvent;
import com.kafka.model.KafkaTestModel;
import com.kafka.model.ResumeEvent;
import com.kafka.model.ResumeModel;
import com.kafka.repository.KafkaRepository;
import com.kafka.repository.ResumeRepository;

@RestController
public class kafkaTestControllerImpl implements KafkaTestController {

	@Autowired
	EventProcessor eventProcessor;

	@Autowired
	KafkaRepository kafkaRepository;

	@Autowired
	ResumeRepository resumeRepository;

	/**
	 * Method send event to kafka and also saves the event to repo
	 */
	@Override
	public void testkafka(KafkaTestModel requestModel) {

		DomainEvent event = new DomainEvent<KafkaTestModel>(requestModel);

		eventProcessor.testKafka(event);
		kafkaRepository.save(event);
		
		/*
		 * MessageChannel outboundMessage = eventSource.outboundTopic();
		 * outboundMessage.send(MessageBuilder.withPayload(requestModel)
		 * .setHeader(MessageHeaders.CONTENT_TYPE,
		 * MimeTypeUtils.APPLICATION_JSON).build());
		 */

	}

	@Override
	public String dummyTest(String name) {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String resumeUpload(MultipartFile multipart, String empId, String name) throws IOException {

		try {
			ResumeModel model = new ResumeModel();
			model.setFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
			model.setName(name);

			ResumeEvent resumeEvent = new ResumeEvent<ResumeModel>(model, empId);

			eventProcessor.testKafka(resumeEvent);

			resumeRepository.save(resumeEvent);

			System.out.println(model.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "Success";
	}

	@Override
	public Optional<DomainEvent> getDomainEvent(UUID eventId) {
		Optional<DomainEvent> domainEvent= kafkaRepository.findById(eventId);
		
		System.out.println(" Payload:::"+domainEvent.get().getPayload().toString());
		
		return domainEvent;
	}

	@Override
	public String getResume(String empId) {
		List<ResumeEvent> resumeEvent=resumeRepository.getByEmpId(empId);
	
		//System.out.println(resumeEvent.get().getPayload().toString());
		
		resumeEvent.forEach( z -> System.out.println("Resume Evnet ::"+z.getPayload()));
		
		/*Binary file= resumeEvent.get().get;
		
		if(file != null) {
	        FileOutputStream fileOuputStream = null;
	        try {
	        	String path="C:\\Users\\IBMADMIN\\Desktop\\dummy";
	            fileOuputStream = new FileOutputStream(path + "abc");
	            fileOuputStream.write(file.getData());
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "failure";
	        } finally {
	            if (fileOuputStream != null) {
	                try {
	                    fileOuputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    return "failure";
	                }
	            }
	        }
	    }*/
		
		return "success";
	}
}
