
package com.javahelps.restservice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import com.javahelps.restservice.entity.Patient;

@RestResource(exported = false)
public interface PatientRepository extends RevisionRepository<Patient, Long, Integer>, JpaRepository<Patient, Long> {
	

	Patient findByPatientId(long patientId);
	Patient findByFirstName(String firstName);
	
	
	@Query("SELECT s FROM Patient s WHERE s.patientId= :patientId")
	public List<Patient> findAllByPatientId(@Param("patientId") long patientId);

	

}
