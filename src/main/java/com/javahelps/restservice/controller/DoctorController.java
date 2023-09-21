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
import com.javahelps.restservice.encryption.DoctorEncrypt;
import com.javahelps.restservice.entity.Doctor;
import com.javahelps.restservice.repository.DoctorRepository;
import javassist.tools.web.BadHttpRequest;

@EntityScan("com.javahelps.restservice.entity")
@Component
@SpringBootApplication(scanBasePackages = { "com.javahelps.restservice.entity",
		"com.javahelps.restservice.repository" })
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = "com.javahelps.restservice")
@CrossOrigin(origins = { "http://localhost:4200", "http://www.jobiors.com","https://www.jobiors.com" })
@RequestMapping(path = "/doctors")
public class DoctorController {



	@Autowired
	private DoctorRepository repository;

	@GetMapping
	public Iterable<Doctor> findAll() {
		return repository.findAll();
	}

//    @GetMapping(path = "doctorId/{doctorId}")
//    public Doctor findByDoctorId(@PathVariable("doctorId") long doctorId) {
//        return repository.findBydoctorId(doctorId);
//    }
//    

	@GetMapping(path = "doctorId/{doctorId}")
	public DoctorEncrypt findByDoctorId(@PathVariable("doctorId") long doctorId) {
		Doctor DoctorDecoded = repository.findByDoctorId(doctorId);
		Crypt newEncypt = new Crypt();
		Crypt newDecrypt = new Crypt();

		String firstName;
		String middleName;
		String lastName;
		String clinicName;
		String address;
		String emailId;
		String contact;
		String userName;
		String passWord;
		
		String key = "mustbe16byteskey";
		String encodedBase64Key = newEncypt.encodeKey(key);

		String key1 = "SanjayNKhalnayak";
		String encodedCredentials = newEncypt.encodeKey(key1);

		DoctorEncrypt doctorEncrypt = new DoctorEncrypt();

	
		try {
		
			firstName = newEncypt.encrypt(DoctorDecoded.getFirstName(), encodedBase64Key);
			middleName = newEncypt.encrypt(DoctorDecoded.getMiddleName(), encodedBase64Key);
			lastName = newEncypt.encrypt(DoctorDecoded.getLastName(), encodedBase64Key);
			clinicName = newEncypt.encrypt(DoctorDecoded.getClinicName(), encodedBase64Key);
			address = newEncypt.encrypt(DoctorDecoded.getAddress(), encodedBase64Key);
			emailId = newEncypt.encrypt(DoctorDecoded.getEmailId(), encodedBase64Key);
			contact = newEncypt.encrypt(DoctorDecoded.getContact(), encodedBase64Key);
			userName = newEncypt.encrypt(DoctorDecoded.getUserName(), encodedBase64Key);
			passWord = newEncypt.encrypt(DoctorDecoded.getPassWord(), encodedBase64Key);
			

			doctorEncrypt.setDoctorId(DoctorDecoded.getDoctorId());
		
			doctorEncrypt.setFirstName(firstName);
			doctorEncrypt.setMiddleName(middleName);
			doctorEncrypt.setLastName(lastName);
			doctorEncrypt.setClinicName(clinicName);
			doctorEncrypt.setAddress(address);
			doctorEncrypt.setEmailId(emailId);
			doctorEncrypt.setContact(contact);
			doctorEncrypt.setUserName(userName);
			doctorEncrypt.setPassWord(passWord);
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doctorEncrypt;
	}



	@GetMapping(path = "allDoctorId/{doctorId}")
	public List<Doctor> findAllByDoctorId(@PathVariable("doctorId") long doctorId) {
		return repository.findAllByDoctorId(doctorId);
	}

//  @PostMapping(consumes = "application/json")
//	public Doctor create(@RequestBody DoctorEncrypt doctor) {

//		String doctorId;
//		String firstName;
//		String middleName;
//		String lastName;
//		String clinicName;
//		String address;
//		String emailId;
//		String contact;
//		String userName;
//		String passWord;
		
	//	Doctor doctorSave = new Doctor();

	//	Crypt newDecypt = new Crypt();
	//	try {
	//		System.out.println("Inside post of doctor try......................." + doctor.getFirstName());
	//		String key = "mustbe16byteskey";
	//		String encodedBase64Key = newDecypt.encodeKey(key);
	//		
//			firstName = newDecypt.decrypt(doctor.getFirstName(), encodedBase64Key);
//			middleName = newDecypt.decrypt(doctor.getMiddleName(), encodedBase64Key);
//			lastName= newDecypt.decrypt(doctor.getLastName(), encodedBase64Key);
//			clinicName = newDecypt.decrypt(doctor.getClinicName(), encodedBase64Key);
//			address = newDecypt.decrypt(doctor.getAddress(), encodedBase64Key);
//			emailId = newDecypt.decrypt(doctor.getEmailId(), encodedBase64Key);
//			contact = newDecypt.decrypt(doctor.getContact(), encodedBase64Key);
//			userName= newDecypt.decrypt(doctor.getUserName(), encodedBase64Key);
//			passWord= newDecypt.decrypt(doctor.getPassWord(), encodedBase64Key);
//
	//		doctorSave.setFirstName(firstName);
	//		doctorSave.setMiddleName(middleName);
		//	doctorSave.setLastName(lastName);
			///doctorSave.setClinicName(clinicName);
		//	doctorSave.setAddress(address);
//			doctorSave.setEmailId(emailId);
//			doctorSave.setContact(contact); 			
//			doctorSave.setUserName(userName);
//			doctorSave.setPassWord(passWord);
//			
	//	} catch (Exception e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
	//	}
//		System.out.println("Inside post of company before save.......................");
	//	 return repository.save(doctorSave);
		
//
//	}

	@PostMapping(consumes = "application/json")
	public Doctor create(@RequestBody DoctorEncrypt doctor) {

		String doctorId;
		String firstName;
		String middleName;
		String lastName;
		String clinicName;
		String address;
		String emailId;
		String contact;
		String userName;
		String passWord;
		
		Doctor doctorSave = new Doctor();

		Crypt newDecypt = new Crypt();
		try {
			System.out.println("Inside post of doctor try......................." + doctor.getFirstName());
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);
			
			firstName = newDecypt.decrypt(doctor.getFirstName(), encodedBase64Key);
			middleName = newDecypt.decrypt(doctor.getMiddleName(), encodedBase64Key);
			lastName= newDecypt.decrypt(doctor.getLastName(), encodedBase64Key);
			clinicName = newDecypt.decrypt(doctor.getClinicName(), encodedBase64Key);
			address = newDecypt.decrypt(doctor.getAddress(), encodedBase64Key);
			emailId = newDecypt.decrypt(doctor.getEmailId(), encodedBase64Key);
			contact = newDecypt.decrypt(doctor.getContact(), encodedBase64Key);
			userName= newDecypt.decrypt(doctor.getUserName(), encodedBase64Key);
			passWord= newDecypt.decrypt(doctor.getPassWord(), encodedBase64Key);

			doctorSave.setFirstName(firstName);
			doctorSave.setMiddleName(middleName);
			doctorSave.setLastName(lastName);
			doctorSave.setClinicName(clinicName);
			doctorSave.setAddress(address);
			doctorSave.setEmailId(emailId);
			doctorSave.setContact(contact); 			
			doctorSave.setUserName(userName);
			doctorSave.setPassWord(passWord);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Inside post of company before save.......................");
		 return repository.save(doctorSave);
		

	}

	@DeleteMapping(path = "/{doctorId}")
	public void delete(@PathVariable("doctorId") long doctorId) {
		repository.deleteById(doctorId);
	}

//    @PutMapping(path = "/{doctorId}")
//    public Doctor update(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) throws BadHttpRequest {
//        if (repository.exists(doctorId)) {
//        	doctor.setDoctorId(doctorId);
//            return repository.save(doctor);
//        } else {
//            throw new BadHttpRequest();
//        }
//    }

	@PutMapping(path = "/{doctorId}")
	public Doctor update(@PathVariable("doctorId") long doctorId, @RequestBody DoctorEncrypt doctor)
			throws BadHttpRequest {
		if (repository.existsById(doctorId)) {

			String firstName;
			String middleName;
			String lastName;
			String clinicName;
			String address;
			String emailId;
			String contact;
			String userName;
			String passWord;
			
			Doctor doctorSave = new Doctor();

			Crypt newDecypt = new Crypt();
			String key = "mustbe16byteskey";
			String encodedBase64Key = newDecypt.encodeKey(key);

			Crypt newEncypt = new Crypt();
			String key1 = "SanjayNKhalnayak";
			String encodedCredentials = newEncypt.encodeKey(key1);
			try {

				firstName = newDecypt.decrypt(doctor.getFirstName(), encodedBase64Key);
				middleName = newDecypt.decrypt(doctor.getMiddleName(), encodedBase64Key);
				lastName= newDecypt.decrypt(doctor.getLastName(), encodedBase64Key);
				clinicName = newDecypt.decrypt(doctor.getClinicName(), encodedBase64Key);
				address = newDecypt.decrypt(doctor.getAddress(), encodedBase64Key);
				emailId = newDecypt.decrypt(doctor.getEmailId(), encodedBase64Key);
				contact = newDecypt.decrypt(doctor.getContact(), encodedBase64Key);
				userName= newDecypt.decrypt(doctor.getUserName(), encodedBase64Key);
				passWord= newDecypt.decrypt(doctor.getPassWord(), encodedBase64Key);
				
				
				doctorSave.setFirstName(firstName);
				doctorSave.setMiddleName(middleName);
				doctorSave.setLastName(lastName);
				doctorSave.setDoctorId(doctorId);
				doctorSave.setClinicName(clinicName);
				doctorSave.setAddress(address);
				doctorSave.setEmailId(emailId);
				doctorSave.setContact(contact); 			
				doctorSave.setUserName(userName);
				doctorSave.setPassWord(passWord);
				
			
	
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return repository.save(doctorSave);
		} else {
			throw new BadHttpRequest();
		}
	}

}