package org.springframework.samples.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerServicesImpl implements OwnerService{

	@Autowired
	OwnerRepository ownerRepository;

	public OwnerRepository getOwnerRepository() {
		return ownerRepository;
	}
	
	public void setRepository(OwnerRepository or) {
		ownerRepository = or;
	}

	@Override
	public List<Owner> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName) {
		return ownerRepository.findByFirstNameContainingOrLastNameContaining(firstName, lastName);
	}

	@Override
	public List<Owner> findByOrderByLastName() {
		return ownerRepository.findByOrderByLastNameDesc();
	}
	
	
	
}
