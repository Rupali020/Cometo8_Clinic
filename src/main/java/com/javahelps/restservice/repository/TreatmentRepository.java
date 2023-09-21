package com.javahelps.restservice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import com.javahelps.restservice.entity.Treatment;

@RestResource(exported = false)
public interface TreatmentRepository extends RevisionRepository<Treatment, Long, Integer>, JpaRepository<Treatment, Long> {
	

	Treatment findByPatientId(long patientId);
	Treatment findByDoctorId(long doctorId);
	Treatment findByTreatmentId(long treatmentId);
	
	
	
	@Query("SELECT s FROM Treatment s WHERE s.treatmentId= :treatmentId")
	public List<Treatment> findAllByTreatmentId(@Param("treatmentId") long treatmentId);

	

}
