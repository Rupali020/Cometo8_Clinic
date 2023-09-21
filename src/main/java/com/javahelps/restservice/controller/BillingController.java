package com.javahelps.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javahelps.restservice.Crypt;
import com.javahelps.restservice.RandomString;
import com.javahelps.restservice.encryption.BillingEncrypt;
import com.javahelps.restservice.entity.Billing;
import com.javahelps.restservice.repository.BillingRepository;
import javassist.tools.web.BadHttpRequest;

@EntityScan("com.javahelps.restservice.entity")
@Component
@SpringBootApplication(scanBasePackages = { "com.javahelps.restservice.entity",
		"com.javahelps.restservice.repository" })
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = "com.javahelps.restservice")
@CrossOrigin(origins = { "http://localhost:4200", "http://www.jobiors.com","https://www.jobiors.com" })
@RequestMapping(path = "/billings")
public class BillingController {



	@Autowired
	private BillingRepository repository;

	@GetMapping
	public Iterable<Billing> findAll() {
		return repository.findAll();
	}

//    @GetMapping(path = "billingId/{billingId}")
//    public Billing findBybillingId(@PathVariable("billingId") long billingId) {
//        return repository.findByBillingId(billingId);
//    }
//    
	 @GetMapping(path = "billingId/{billingId}")
		public BillingEncrypt findByBillingId(@PathVariable("billingId") long billingId) {
	    Billing billingDecoded = repository.findByBillingId(billingId);
			Crypt newEncypt = new Crypt();
			Crypt newDecrypt = new Crypt();

		String patientId;
		String doctorId;
		String date;
		String amount;
		String modeOfpayment;
		
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		BillingEncrypt billingEncrypt = new BillingEncrypt();

		Long patientId1 = billingDecoded.getPatientId();
		Long doctorId1 = billingDecoded.getDoctorId();
		try {
			patientId = newEncypt.encrypt(patientId1.toString(), encodedBase64Key);
			doctorId = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
			date = newEncypt.encrypt(billingDecoded.getDate(), encodedBase64Key);
			amount = newEncypt.encrypt(billingDecoded.getAmount(), encodedBase64Key);
			modeOfpayment = newEncypt.encrypt(billingDecoded.getModeOfpayment(), encodedBase64Key);


			billingEncrypt.setPatientId(patientId);
			billingEncrypt.setDoctorId(doctorId);
			billingEncrypt.setDate(date);
			billingEncrypt.setAmount(amount);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return billingEncrypt;
	}

//    @GetMapping(path = "userAccountId/{userAccountId}")
//    public Company findByUserAccountId(@PathVariable("userAccountId") long userAccountId) {
//        return repository.findByUserAccountId(userAccountId);
//    }

	@GetMapping(path = "doctorId/{doctorId}")
	
	public BillingEncrypt findByDoctorId(@PathVariable("doctorId") long doctorId) {
		Billing BillingDecoded = repository.findByDoctorId(doctorId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();

		String patientId;
		String doctorId2;
		String date;
		String amount;
		String modeOfpayment;
		
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		BillingEncrypt billingEncrypt = new BillingEncrypt();

		Long doctorId1 = BillingDecoded.getDoctorId();
		Long patientId1 = BillingDecoded.getPatientId();
		Long billingId1 =BillingDecoded.getBillingId();

		try {
			patientId = newEncypt.encrypt(patientId1.toString(), encodedBase64Key);
			doctorId2 = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
			date= newEncypt.encrypt(BillingDecoded.getDate(), encodedBase64Key);
			amount= newEncypt.encrypt(BillingDecoded.getAmount(), encodedBase64Key);
			modeOfpayment = newEncypt.encrypt(BillingDecoded.getModeOfpayment(), encodedBase64Key);
			
			billingEncrypt.setDoctorId(patientId);
			billingEncrypt.setDoctorId(doctorId2);
			billingEncrypt.setDate(date);
			billingEncrypt.setAmount(amount);
			billingEncrypt.setModeOfpayment(modeOfpayment);
		
		
			// TODO Auto-generated catch block
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return billingEncrypt;
	}

	@GetMapping(path = "allPatientId/{patientId}")
	public BillingEncrypt findAllByPatientId(@PathVariable("patientId") long patientId) {
		Billing BillingDecoded = repository.findByPatientId(patientId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();
		


		String doctorId;
		String date;
		String amount;
		String modeOfpayment;
		
		

		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		BillingEncrypt billingEncrypt = new BillingEncrypt();

		Long doctorId1 = BillingDecoded.getDoctorId();
		Long PatientId1 = BillingDecoded.getPatientId();
		try {
		
		
		doctorId = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
		date= newEncypt.encrypt(BillingDecoded.getDate(), encodedBase64Key);
		amount= newEncypt.encrypt(BillingDecoded.getAmount(), encodedBase64Key);
		modeOfpayment = newEncypt.encrypt(BillingDecoded.getModeOfpayment(), encodedBase64Key);
		
		
		billingEncrypt.setBillingId(BillingDecoded.getBillingId());
		billingEncrypt.setDoctorId(doctorId);
		billingEncrypt.setDate(date);
		billingEncrypt.setAmount(amount);
		billingEncrypt.setModeOfpayment(modeOfpayment);
	
	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return billingEncrypt;
	}



//@PostMapping(consumes = "application/json")
//public HiringManager create(@RequestBody HiringManager hiringManager) {
//	        return repository.save(hiringManager);
//}


	@PostMapping(consumes = "application/json")
	public Billing create(@RequestBody BillingEncrypt billing) {

		String patientId;
		String doctorId;
		String date;
		String amount;
		String modeOfpayment;
		
		
		Billing billingSave = new Billing();

		Crypt newDecypt = new Crypt();
		try {
			System.out.println("Inside post of billing try......................." + billing.getDate());
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);
			
			patientId = newDecypt.decrypt(billing.getPatientId(), encodedBase64Key);
			doctorId = newDecypt.decrypt(billing.getDoctorId(), encodedBase64Key);
			date = newDecypt.decrypt(billing.getDate(), encodedBase64Key);
			amount = newDecypt.decrypt(billing.getAmount(), encodedBase64Key);
			modeOfpayment = newDecypt.decrypt(billing.getModeOfpayment(), encodedBase64Key);
			
	
			billingSave.setPatientId(Long.parseLong(patientId));  
			billingSave.setDate(date);
			billingSave.setAmount(amount);
			billingSave.setModeOfpayment(modeOfpayment);
			billingSave.setDoctorId(Long.parseLong(doctorId));  			
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Inside post of billing before save.......................");
		 return repository.save(billingSave);
		

	}

	@DeleteMapping(path = "/{billingId}")
	public void delete(@PathVariable("billingId") long billingId) {
		repository.deleteById(billingId);
	}

//    @PutMapping(path = "/{billingId}")
//    public patient update(@PathVariable("billingId") long billingId, @RequestBody Patient patient) throws BadHttpRequest {
//        if (repository.exists(patientId)) {
//        	patient.setPatientId(patientId);
//            return repository.save(patient);
//        } else {
//            throw new BadHttpRequest();
//        }
//    }

	@PutMapping(path = "/{billingId}")
	public Billing update(@PathVariable("billingId") long billingId, @RequestBody BillingEncrypt billing)
			throws BadHttpRequest {
		if (repository.existsById(billingId)) {

			String patientId;
			String doctorId;
			String date;
			String amount;
			String modeOfpayment;
			String doctortId;
			
			
			Billing billingSave = new Billing();

			Crypt newDecypt = new Crypt();
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);

			Crypt newEncypt = new Crypt();
			String key1 = "SanjayNKhalnayak";
			String encodedCredentials = newEncypt.encodeKey(key1);
			try {

				date = newDecypt.decrypt(billing.getDate(), encodedBase64Key);
				amount = newDecypt.decrypt(billing.getAmount(), encodedBase64Key);
				modeOfpayment = newDecypt.decrypt(billing.getModeOfpayment(), encodedBase64Key);
				doctortId = newDecypt.decrypt(billing.getDoctorId(), encodedBase64Key);
				patientId = newDecypt.decrypt(billing.getPatientId(), encodedBase64Key);
				
				
				
				
				billingSave.setDate(date);
				billingSave.setAmount(amount);
				billingSave.setModeOfpayment(modeOfpayment);
				billingSave.setBillingId(billingId);
				billingSave.setDoctorId(Long.parseLong(doctortId));  			
				billingSave.setPatientId(Long.parseLong(patientId));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return repository.save(billingSave);
		} else {
			throw new BadHttpRequest();
		}
	}

}