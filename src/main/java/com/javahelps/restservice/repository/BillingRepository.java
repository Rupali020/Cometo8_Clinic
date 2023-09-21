package com.javahelps.restservice.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import com.javahelps.restservice.entity.Billing;

@RestResource(exported = false)
public interface BillingRepository extends RevisionRepository<Billing, Long, Integer>, JpaRepository<Billing, Long> {
	

	Billing findByBillingId(long billingId);
	Billing findByPatientId(long patientId);
	Billing findByDoctorId(long doctorId);

	
	
	@Query("SELECT s FROM Billing s WHERE s.billingId= :billingId")
	public List<Billing> findAllByBillingId(@Param("billingId") long billingId);
	




	
	

}
