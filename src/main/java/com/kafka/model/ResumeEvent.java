package com.kafka.model;

public class ResumeEvent<ResumeModel> extends DomainEvent{

	
	private String empId;
	
	public ResumeEvent(ResumeModel payload,String empId) {
		super(payload);
		this.setEmpId(empId);
		
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "ResumeEvent [empId=" + empId + "]";
	}
	
	
	
	
	
	
}
