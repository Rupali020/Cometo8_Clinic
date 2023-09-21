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
import com.javahelps.restservice.encryption.PatientEncrypt;
import com.javahelps.restservice.entity.Patient;
import com.javahelps.restservice.repository.PatientRepository;
import javassist.tools.web.BadHttpRequest;

@EntityScan("com.javahelps.restservice.entity")
@Component
@SpringBootApplication(scanBasePackages = { "com.javahelps.restservice.entity",
		"com.javahelps.restservice.repository" })
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = "com.javahelps.restservice")
@CrossOrigin(origins = { "http://localhost:4200", "http://www.jobiors.com","https://www.jobiors.com" })
@RequestMapping(path = "/patients")
public class PatientController {

	@Autowired
	private PatientRepository repository;

	@GetMapping
	public Iterable<Patient> findAll() {
		return repository.findAll();
	}

//    @GetMapping(path = "patientId/{patientId}")
//    public Patient findByPatientId(@PathVariable("patientId") long patientId) {
//        return repository.findByPatientId(patientId);
//    }
//    

	@GetMapping(path = "patientId/{patientId}")
	public PatientEncrypt findByPatientId(@PathVariable("patientId") long patientId) {
		Patient PatientDecoded = repository.findByPatientId(patientId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();

		String firstName;
		String middleName;
		String lastName;
		String dateOfBirth;
		String address;
		String emailId;
		String contact;	
		String bloodGroup;	
		String profession;
		String addiction;
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		PatientEncrypt patientEncrypt = new PatientEncrypt();

	
		try {
		
			firstName = newEncypt.encrypt(PatientDecoded.getFirstName(), encodedBase64Key);
			middleName = newEncypt.encrypt(PatientDecoded.getMiddleName(), encodedBase64Key);
			lastName = newEncypt.encrypt(PatientDecoded.getLastName(), encodedBase64Key);
			dateOfBirth= newEncypt.encrypt(PatientDecoded.getDateOfBirth(), encodedBase64Key);
			address = newEncypt.encrypt(PatientDecoded.getAddress(), encodedBase64Key);
			emailId = newEncypt.encrypt(PatientDecoded.getEmailId(), encodedBase64Key);
			contact = newEncypt.encrypt(PatientDecoded.getContact(), encodedBase64Key);
			bloodGroup = newEncypt.encrypt(PatientDecoded.getBloodGroup(), encodedBase64Key);
			profession = newEncypt.encrypt(PatientDecoded.getProfession(), encodedBase64Key);
			addiction = newEncypt.encrypt(PatientDecoded.getAddiction(), encodedBase64Key);
			

			patientEncrypt.setPatientId(PatientDecoded.getpatientId());
		
			
			patientEncrypt.setFirstName(firstName);
			patientEncrypt.setMiddleName(middleName);
			patientEncrypt.setLastName(lastName);
			patientEncrypt.setDateOfBirth(dateOfBirth);
			patientEncrypt.setAddress(address);
			patientEncrypt.setEmailId(emailId);
			patientEncrypt.setContact(contact);
			patientEncrypt.setBloodGroup(bloodGroup);
			patientEncrypt.setProfession(profession);
			patientEncrypt.setAddiction(addiction);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return patientEncrypt;
	}



	@GetMapping(path = "allPatientId/{patientId}")
	public List<Patient> findAllByPatientId(@PathVariable("patientId") long patientId) {
		return repository.findAllByPatientId(patientId);
	}

//    @PostMapping(consumes = "application/json")
//    public Company create(@RequestBody Company company) {
//        return repository.save(company);
//    }

//   @PostMapping(consumes = "application/json")
//	public Patient create(@RequestBody PatientEncrypt patient) {

//		String patientId;
//		String firstName;
//		String middleName;
//		String lastName;
//		String dateOfBirth;
//		String address;
//		String emailId;
//		String contact;	
//		String bloodGroup;	
//		String profession;
//		String addiction;
		
//		Patient patientSave = new Patient();

//		Crypt newDecypt = new Crypt();
//		try {
//			System.out.println("Inside post of patient try......................." + patient.getFirstName());
//			String key = "mustbe16byteskey";
//			String encodedBase64Key = newDecypt.encodeKey(key);
			
//			firstName = newDecypt.decrypt(patient.getFirstName(), encodedBase64Key);
//			middleName = newDecypt.decrypt(patient.getMiddleName(), encodedBase64Key);
//			lastName= newDecypt.decrypt(patient.getLastName(), encodedBase64Key);
//			dateOfBirth = newDecypt.decrypt(patient.getDateOfBirth(), encodedBase64Key);
//			address = newDecypt.decrypt(patient.getAddress(), encodedBase64Key);
//			emailId = newDecypt.decrypt(patient.getEmailId(), encodedBase64Key);
//			contact = newDecypt.decrypt(patient.getContact(), encodedBase64Key);
//			bloodGroup= newDecypt.decrypt(patient.getBloodGroup(), encodedBase64Key);
//			profession= newDecypt.decrypt(patient.getProfession(), encodedBase64Key);
//	addiction = newEncypt.encrypt(PatientDecoded.getAddiction(), encodedBase64Key);
	

//			patientSave.setFirstName(firstName);
//			patientSave.setMiddleName(middleName);
//			patientSave.setLastName(lastName);
//			patientSave.setDateOfBirth(dateOfBirth);
//			patientSave.setAddress(address);
//			patientSave.setEmailId(emailId);
//			patientSave.setContact(contact); 			
//			patientSave.setBloodGroup(bloodGroup);
//			patientSave.setProfession(profession);
//				patientEncrypt.setAddiction(addiction);
	
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println("Inside post of patient before save.......................");
//		 return repository.save(patientSave);
		

//
//	}

	@PostMapping(consumes = "application/json")
	public Patient create(@RequestBody PatientEncrypt patient) {

		String patientId;
		String firstName;
		String middleName;
		String lastName;
		String dateOfBirth;
		String address;
		String emailId;
		String contact;	
		String bloodGroup;	
		String profession;
		String addiction;
		
		Patient patientSave = new Patient();

		Crypt newDecypt = new Crypt();
		try {
			System.out.println("Inside post of patient try......................." + patient.getFirstName());
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);
			
			firstName = newDecypt.decrypt(patient.getFirstName(), encodedBase64Key);
			middleName = newDecypt.decrypt(patient.getMiddleName(), encodedBase64Key);
			lastName= newDecypt.decrypt(patient.getLastName(), encodedBase64Key);
			dateOfBirth = newDecypt.decrypt(patient.getDateOfBirth(), encodedBase64Key);
			address = newDecypt.decrypt(patient.getAddress(), encodedBase64Key);
			emailId = newDecypt.decrypt(patient.getEmailId(), encodedBase64Key);
			contact = newDecypt.decrypt(patient.getContact(), encodedBase64Key);
			bloodGroup= newDecypt.decrypt(patient.getBloodGroup(), encodedBase64Key);
			profession= newDecypt.decrypt(patient.getProfession(), encodedBase64Key);
			addiction= newDecypt.decrypt(patient.getAddiction(), encodedBase64Key);


			patientSave.setFirstName(firstName);
			patientSave.setMiddleName(middleName);
			patientSave.setLastName(lastName);
			patientSave.setDateOfBirth(dateOfBirth);
			patientSave.setAddress(address);
			patientSave.setEmailId(emailId);
			patientSave.setContact(contact); 			
			patientSave.setBloodGroup(bloodGroup);
			patientSave.setProfession(profession);
			patientSave.setAddiction(addiction);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Inside post of patient before save.......................");
		 return repository.save(patientSave);
		

	}

	@DeleteMapping(path = "/{patientId}")
	public void delete(@PathVariable("patientId") long patientId) {
		repository.deleteById(patientId);
	}

//    @PutMapping(path = "/{companyId}")
//    public Company update(@PathVariable("companyId") long companyId, @RequestBody Company company) throws BadHttpRequest {
//        if (repository.exists(companyId)) {
//        	company.setCompanyId(companyId);
//            return repository.save(company);
//        } else {
//            throw new BadHttpRequest();
//        }
//    }

	@PutMapping(path = "/{patientId}")
	public Patient update(@PathVariable("patientId") long patientId, @RequestBody PatientEncrypt patient)
			throws BadHttpRequest {
		if (repository.existsById(patientId)) {

			String firstName;
			String middleName;
			String lastName;
			String dateOfBirth;
			String address;
			String emailId;
			String contact;	
			String bloodGroup;	
			String profession;
			String addiction;
			
			Patient patientSave = new Patient();

			Crypt newDecypt = new Crypt();
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);

			Crypt newEncypt = new Crypt();
			String key1 = "SanjayNKhalnayak";
			String encodedCredentials = newEncypt.encodeKey(key1);
			try {
				firstName = newDecypt.decrypt(patient.getFirstName(), encodedBase64Key);
				middleName = newDecypt.decrypt(patient.getMiddleName(), encodedBase64Key);
				lastName= newDecypt.decrypt(patient.getLastName(), encodedBase64Key);
				dateOfBirth = newDecypt.decrypt(patient.getDateOfBirth(), encodedBase64Key);
				address = newDecypt.decrypt(patient.getAddress(), encodedBase64Key);
				emailId = newDecypt.decrypt(patient.getEmailId(), encodedBase64Key);
				contact = newDecypt.decrypt(patient.getContact(), encodedBase64Key);
				bloodGroup= newDecypt.decrypt(patient.getBloodGroup(), encodedBase64Key);
				profession= newDecypt.decrypt(patient.getProfession(), encodedBase64Key);
				addiction= newDecypt.decrypt(patient.getAddiction(), encodedBase64Key);
				
				
				patientSave.setFirstName(firstName);
				patientSave.setMiddleName(middleName);
				patientSave.setLastName(lastName);
				patientSave.setDateOfBirth(dateOfBirth);
				patientSave.setAddress(address);
				patientSave.setEmailId(emailId);
				patientSave.setContact(contact); 			
				patientSave.setBloodGroup(bloodGroup);
				patientSave.setProfession(profession);
				patientSave.setAddiction(addiction);
			
	
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return repository.save(patientSave);
		} else {
			throw new BadHttpRequest();
		}
	}

}




































