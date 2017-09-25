package org.springframework.samples.petclinic.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	PetRepository petRepository;

	@Override
	public List<Pet> findByBirthDateBetweenOrderByBirthDateAsc(Date d1, Date d2) {
		return petRepository.findByBirthDateBetweenOrderByBirthDateAsc(d1, d2);
	}

	@Override
	public void save(Pet p) {
		petRepository.save(p);
	}

	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return petRepository.findAll();
	}
	
}
