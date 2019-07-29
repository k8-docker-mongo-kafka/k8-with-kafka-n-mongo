package com.kafka.model;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ResumeModel {

	private String name;
	private Binary file;

	
	public ResumeModel() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Binary getFile() {
		return file;
	}

	public void setFile(Binary file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "ResumeModel [name=" + name + ", file=" + file + "]";
	}

	
}
