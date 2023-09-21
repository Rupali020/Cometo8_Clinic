package com.javahelps.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.javahelps.restservice.entity.Authorise;

@RestResource(exported = false)
public interface AuthoriseRepository extends JpaRepository<Authorise, String> {

}
