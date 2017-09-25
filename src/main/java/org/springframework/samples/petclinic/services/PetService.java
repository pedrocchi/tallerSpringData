package org.springframework.samples.petclinic.services;

import java.util.Date;
import java.util.List;
import org.springframework.samples.petclinic.model.Pet;

public interface PetService{

	List<Pet> findByBirthDateBetweenOrderByBirthDateAsc(Date d1, Date d2);
	void save(Pet p);
	List<Pet> findAll();
	
}
