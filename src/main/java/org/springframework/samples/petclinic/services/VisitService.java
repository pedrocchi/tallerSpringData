package org.springframework.samples.petclinic.services;

import java.util.List;

import org.springframework.samples.petclinic.model.Visit;

public interface VisitService {

	
    List<Visit> findByPetId(Integer petId);
    List<Visit> findTop3ByOrderByDateDesc();
	void save (List<Visit> v);
	
}
