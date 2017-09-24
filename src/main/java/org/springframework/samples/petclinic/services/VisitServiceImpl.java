package org.springframework.samples.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService{

	@Autowired
	VisitRepository visitRepository;
	
	@Override
	public List<Visit> findByPetId(Integer petId) {
		// TODO Auto-generated method stub
		return visitRepository.findByPetId(petId);
	}

	@Override
	public List<Visit> findTop3ByOrderByDateDesc() {
		// TODO Auto-generated method stub
		return visitRepository.findTop3ByOrderByDateDesc();
	}

	@Override
	public void save(List<Visit> v) {
		// TODO Auto-generated method stub
		visitRepository.save(v);
	}

}
