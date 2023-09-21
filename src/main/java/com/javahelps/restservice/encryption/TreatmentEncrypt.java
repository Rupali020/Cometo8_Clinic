package com.javahelps.restservice.encryption;

public class TreatmentEncrypt {
	
	private long treatmentId;
	private String patientId;
	private String doctorId;

	private String description;
	private String date;
	private String advice;
	
	
	
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public long getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(long treatmentId) {
		this.treatmentId = treatmentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	
}