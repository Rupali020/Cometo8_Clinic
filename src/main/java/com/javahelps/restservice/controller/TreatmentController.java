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
import com.javahelps.restservice.encryption.TreatmentEncrypt;
import com.javahelps.restservice.entity.Treatment;
import com.javahelps.restservice.repository.TreatmentRepository;
import javassist.tools.web.BadHttpRequest;

@EntityScan("com.javahelps.restservice.entity")
@Component
@SpringBootApplication(scanBasePackages = { "com.javahelps.restservice.entity",
		"com.javahelps.restservice.repository" })
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = "com.javahelps.restservice")
@CrossOrigin(origins = { "http://localhost:4200", "http://www.jobiors.com","https://www.jobiors.com" })
@RequestMapping(path = "/treatments")
public class TreatmentController {



	@Autowired
	private TreatmentRepository repository;

	@GetMapping
	public Iterable<Treatment> findAll() {
		return repository.findAll();
	}

//    @GetMapping(path = "treatmentId/{treatmentId}")
//    public Treatment findBytreatmentId(@PathVariable("treatmentId") long treatmentId) {
//        return repository.findByTreatmentId(treatmentId);
//    }
//    

	@GetMapping(path = "treatmentId/{treatmentId}")
	public TreatmentEncrypt findByTreatmentId(@PathVariable("treatmentId") long treatmentId) {
		Treatment TreatmentDecoded = (Treatment) repository.findAllByTreatmentId(treatmentId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();
		
		
		String doctorId;
		String patientId;
		String description;
		String date;
		String advice;
		
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		TreatmentEncrypt treatmentEncrypt = new TreatmentEncrypt();

		Long doctorId1 = TreatmentDecoded.getDoctorId();
		Long patientId1 = TreatmentDecoded.getPatientId();
		
		try {
			doctorId = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
			patientId = newEncypt.encrypt(patientId1.toString(), encodedBase64Key);
			description = newEncypt.encrypt(TreatmentDecoded.getDescription(), encodedBase64Key);
			date = newEncypt.encrypt(TreatmentDecoded.getDate(), encodedBase64Key);
			advice = newEncypt.encrypt(TreatmentDecoded.getAdvice(), encodedBase64Key);
			
			treatmentEncrypt.setDoctorId(doctorId);
			treatmentEncrypt.setPatientId(patientId);
			treatmentEncrypt.setDescription(description);
			treatmentEncrypt.setDate(date);
			treatmentEncrypt.setAdvice(advice);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return treatmentEncrypt;
	}

//    @GetMapping(path = "userAccountId/{userAccountId}")
//    public Company findByUserAccountId(@PathVariable("userAccountId") long userAccountId) {
//        return repository.findByUserAccountId(userAccountId);
//    }
	 
 

	@GetMapping(path = "AllDoctorId/{doctorId}")
	public TreatmentEncrypt findByDoctorId(@PathVariable("doctorId") long doctorId) {
		Treatment TreatmentDecoded = repository.findByDoctorId(doctorId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();
		
		String patientId2;
		String doctorId2;
		String description;
		String date;
		String advice;
		

		
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		TreatmentEncrypt treatmentEncrypt = new TreatmentEncrypt();

		Long doctorId1 = TreatmentDecoded.getDoctorId();
		Long patientId1 = TreatmentDecoded.getPatientId();
		try {
			doctorId2 = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
			patientId2 = newEncypt.encrypt(patientId1.toString(), encodedBase64Key);
			description = newEncypt.encrypt(TreatmentDecoded.getDescription(), encodedBase64Key);
			date = newEncypt.encrypt(TreatmentDecoded.getDate(), encodedBase64Key);
			advice = newEncypt.encrypt(TreatmentDecoded.getAdvice(), encodedBase64Key);
			
			
			treatmentEncrypt.setDoctorId(doctorId2);
			treatmentEncrypt.setPatientId(patientId2);
			// treatmentEncrypt.setdoctorId(doctortId);
			treatmentEncrypt.setDescription(description);
			treatmentEncrypt.setDate(date);
			treatmentEncrypt.setAdvice(advice);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return treatmentEncrypt;
	}

	 @GetMapping(path = "patientId/{patientId}")
	 public TreatmentEncrypt findByPatientId(@PathVariable("patientId") long patientId) {
			Treatment TreatmentDecoded = repository.findByPatientId(patientId);
			Crypt newEncypt = new Crypt();
			Crypt newDecrypt = new Crypt();

	   		 String doctorId;
	   		String description;
			String date;
			String advice;

	   		String key = "mustbe16byteskey";
	   		String encodedBase64Key = newEncypt.encodeKey(key);

	   		String key1 = "SanjayNKhalnayak";
	   		String encodedCredentials = newEncypt.encodeKey(key1);

	   		TreatmentEncrypt treatmentEncrypt = new TreatmentEncrypt();


	   		Long patientId1 = TreatmentDecoded.getPatientId();
	   		Long doctorId1 = TreatmentDecoded.getDoctorId();
	   		try {
	   			
	   			doctorId = newEncypt.encrypt(doctorId1.toString(), encodedBase64Key);
				description = newEncypt.encrypt(TreatmentDecoded.getDescription(), encodedBase64Key);
				date = newEncypt.encrypt(TreatmentDecoded.getDate(), encodedBase64Key);
				advice = newEncypt.encrypt(TreatmentDecoded.getAdvice(), encodedBase64Key);
	   			
				treatmentEncrypt.setTreatmentId(TreatmentDecoded.getTreatmentId());
	   			treatmentEncrypt.setDoctorId(doctorId);
				
				// treatmentEncrypt.setdoctorId(doctortId);
				treatmentEncrypt.setDescription(description);
				treatmentEncrypt.setDate(date);
				treatmentEncrypt.setAdvice(advice);

	   		} catch (Exception e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}

	   		return treatmentEncrypt;
	   	}
	  

//    @PostMapping(consumes = "application/json")
//    public Company create(@RequestBody Company company) {
//        return repository.save(company);
//    }

//    @PostMapping(consumes = "application/json")
//	public Company create(@RequestBody CompanyEncrypt company) {
//


	@PostMapping(consumes = "application/json")
	public Treatment create(@RequestBody TreatmentEncrypt treatment) {

		String doctorId;
		String patientId;
		String description;
		String date;
		String advice;
		
		
		Treatment treatmentSave = new Treatment();

		Crypt newDecypt = new Crypt();
		try {
			System.out.println("Inside post of treatment try......................." + treatment.getPatientId());
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);
			
			patientId = newDecypt.decrypt(treatment.getPatientId(), encodedBase64Key);
   			doctorId = newDecypt.decrypt(treatment.getDoctorId(), encodedBase64Key);
			description = newDecypt.decrypt(treatment.getDescription(), encodedBase64Key);
			date = newDecypt.decrypt(treatment.getDate(), encodedBase64Key);
			advice = newDecypt.decrypt(treatment.getAdvice(), encodedBase64Key);
			doctorId = newDecypt.decrypt(treatment.getDoctorId(), encodedBase64Key);
		

			
			treatmentSave.setDescription(description);
			treatmentSave.setDate(date);
			treatmentSave.setAdvice(advice);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Inside post of treatment before save.......................");
		 return repository.save(treatmentSave);
		

	}

	@DeleteMapping(path = "/{treatmentId}")
	public void delete(@PathVariable("treatmentId") long treatmentId) {
		repository.deleteById(treatmentId);
	}

//    @PutMapping(path = "/{treatmentId}")
//    public Treatment update(@PathVariable("treatmentId") long treatmentId, @RequestBody Treatment treatment) throws BadHttpRequest {
//        if (repository.exists(treatmentId)) {
//        	treatment.setTreatmentId(treatmentId);
//            return repository.save(treatment);
//        } else {
//            throw new BadHttpRequest();
//        }
//    }

	@PutMapping(path = "/{treatmentId}")
	public Treatment update(@PathVariable("treatmentId") long treatmentId, @RequestBody TreatmentEncrypt treatment)
			throws BadHttpRequest {
		if (repository.existsById(treatmentId)) {

			String description;
			String date;
			String advice;
			String doctorId;
			String patientId;
		
			
			Treatment treatmentSave = new Treatment();

			Crypt newDecypt = new Crypt();
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);

			Crypt newEncypt = new Crypt();
			String key1 = "SanjayNKhalnayak";
			String encodedCredentials = newEncypt.encodeKey(key1);
			try {

				description = newDecypt.decrypt(treatment.getDescription(), encodedBase64Key);
				date = newDecypt.decrypt(treatment.getDate(), encodedBase64Key);
				advice = newDecypt.decrypt(treatment.getAdvice(), encodedBase64Key);
				doctorId = newDecypt.decrypt(treatment.getDoctorId(), encodedBase64Key);
				patientId = newDecypt.decrypt(treatment.getPatientId(), encodedBase64Key);
				
				treatmentSave.setTreatmentId(treatmentId);
				treatmentSave.setPatientId(Long.parseLong(patientId));
				treatmentSave.setDescription(description);
				treatmentSave.setTreatmentId(treatmentId);
				treatmentSave.setDate(date);
				treatmentSave.setDoctorId(Long.parseLong(doctorId));
			
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return repository.save(treatmentSave);
		} else {
			throw new BadHttpRequest();
		}
	}

}