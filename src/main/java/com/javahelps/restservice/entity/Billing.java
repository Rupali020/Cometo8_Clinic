package com.javahelps.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
@Entity
public class Billing {

	private long billingId;
	private long patientId;
	private long doctorId;
	private String date;
	private String amount; 
	private String modeOfpayment;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public long getBillingId() {
		return billingId;
	}
	public void setBillingId(long billingId) {
		this.billingId = billingId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
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