package org.springframework.samples.petclinic.services;

import java.util.List;
import org.springframework.samples.petclinic.model.Owner;

public interface OwnerService{

	List<Owner> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

	List<Owner> findByOrderByLastName();

	
}
