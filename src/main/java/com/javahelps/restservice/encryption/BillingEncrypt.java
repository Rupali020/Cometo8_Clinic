package com.javahelps.restservice.encryption;

public class BillingEncrypt {
	
	private long billingId;
	private String patientId;
	private String doctorId;
	private String date;
	private String amount; 
	private String modeOfpayment;
	
	
	
	public long getBillingId() {
		return billingId;
	}
	public void setBillingId(long billingId) {
		this.billingId = billingId;
	}
	

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getModeOfpayment() {
		return modeOfpayment;
	}
	public void setModeOfpayment(String modeOfpayment) {
		this.modeOfpayment = modeOfpayment;
	}
	
	
	
	
}