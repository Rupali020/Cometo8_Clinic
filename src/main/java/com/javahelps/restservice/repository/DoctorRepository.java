
package com.javahelps.restservice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.javahelps.restservice.entity.Doctor;

@RestResource(exported = false)
public interface DoctorRepository extends RevisionRepository<Doctor, Long, Integer>, JpaRepository<Doctor, Long> {
	

	Doctor findByDoctorId(long doctorId);
	Doctor findByFirstName(String firstName);
	
	
	@Query("SELECT s FROM Doctor s WHERE s.doctorId= :doctorId")
	public List<Doctor> findAllByDoctorId(@Param("doctorId") long doctorId);

	
	

	

}
