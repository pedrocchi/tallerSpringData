/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.services.OwnerService;
import org.springframework.samples.petclinic.services.PetService;
import org.springframework.samples.petclinic.services.VisitService;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
    }
      
    @Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			
			//TODO Añade aquí tu código
			
			log.info("Crear objeto");
			Vet vet = new Vet();
			vet.setFirstName("Pedro");
			vet.setLastName("Cordon");
			
			log.info("Persistir objeto");
			vetRepository.save(vet);
			log.info(vet.getId().toString());
			
			log.info("Consultar por id y comprobar insercion");
			vetRepository.findOne(vet.getId());
			Boolean b = vetRepository.exists(vet.getId());
			log.info(b.toString());
			
			log.info("Editar el elemento para añadir speciality");
		    Specialty s = specialityRepository.findOne(1);
		    vet.addSpecialty(s);
		    vet = vetRepository.save(vet);
		    log.info(vet.toString());
			

			log.info("Listar los veterinarios existentes");
			List<Vet> v = vetRepository.findAll();
			log.info(v.toString());
			
			log.info("Lista de vets filtrado por lastName");
			List<Vet> l1 = vetRepository.findByLastName("Cordon");
			log.info(l1.toString());
			
			log.info("firstName and lastName");
			List<Vet> l2 = vetRepository.findByLastNameAndFirstName("Cordon", "Pedro");
			log.info(l2.toString());
			
			log.info("firstName or lastName");
			List<Vet> l3 = vetRepository.findByLastNameOrFirstName("Cordon", "Chiara");
			log.info(l3.toString());
			
			log.info("sacar todos los vet por radiology");
			List<Vet> l4 = vetRepository.findBySpecialtyName("radiology");
			log.info(l4.toString());
			
		};
	}
    
    @Bean
	public CommandLineRunner demoOwnerRepository(OwnerRepository ownerRepository) {
		return (args) -> {
			log.info("*****************************************************");
		    log.info("BOOTCAMP - Spring y Spring Data - OwnerRepository");
		    log.info("*****************************************************");
		    
		    log.info("Buscar por nombre o apellidos");
		    
		    
		    log.info("lista propietarios ordenadas por apellidos");
		    List<Owner> l1 = ownerRepository.findByOrderByLastNameDesc();
		    for(Owner o : l1) {
		    	log.info(o.toString());
		    }
		};
    }
    
    @Bean
	public CommandLineRunner demoOwnerService(OwnerService ownerService) {
		return (args) -> {
			log.info("*****************************************************");
		    log.info("BOOTCAMP - Spring y Spring Data - OwnerService");
		    log.info("*****************************************************");
		    
		    
		    log.info("Filtramos por nombre");
		    for(Owner o: ownerService.findByFirstNameContainingOrLastNameContaining("Edu", "Est")){
		    		log.info("Owner: "+o);
		    }
		    
		    log.info("Ordenamos por apellidos");
		    for(Owner o: ownerService.findByOrderByLastName()){
		    		log.info("Owner: "+o);
		    }
		 
		    
		};
    }
    


	@Bean
	public CommandLineRunner demoPetService(PetService petService) {
		return (args) -> {
			log.info("*****************************************************");
		    log.info("BOOTCAMP - Spring y Spring Data - PetService");
		    log.info("*****************************************************");
		   

		    log.info("Obtener mascotas 2010 ordenadas fecha de nacimiento ascendente");
		    for(Pet p: petService.findByBirthDateBetweenOrderByBirthDateAsc(new DateTime(2010,1,1,0,0).toDate(), new DateTime(2010,12,31,0,0).toDate())){
		    		log.info("Pet: "+p);
		    }
		    
		    
		    log.info("devolver una lista de mascotas con un campo nuevo que indique el num de visitas realizadas en total");
		    for(Pet p: petService.findAll()) {
		    		log.info(p.toString());
		    }

		};
    }
	
	
	@Bean
	public CommandLineRunner demoVisitService(VisitService visitService) {
		return (args) -> {
			log.info("*****************************************************");
		    log.info("BOOTCAMP - Spring y Spring Data - VisitService");
		    log.info("*****************************************************");
		   
		    log.info("crear 5 visitas nuevas para una mascota en meses distintos");

		    List<Visit> visit = visitService.findByPetId(1);
		    	Visit v1 = new Visit();
		    	Visit v2 = new Visit();
		    	Visit v3 = new Visit();
		    	Visit v4 = new Visit();
		    	Visit v5 = new Visit();
		    	v1.setDate(new DateTime(2017,1,1,0,0).toDate());
		    	v2.setDate(new DateTime(2017,2,1,0,0).toDate());
		    	v3.setDate(new DateTime(2017,3,1,0,0).toDate());
		    	v4.setDate(new DateTime(2017,4,1,0,0).toDate());
		    	v5.setDate(new DateTime(2017,5,1,0,0).toDate());
		    	v1.setPetId(1);
		    	v2.setPetId(1);
		    	v3.setPetId(1);
		    	v4.setPetId(1);
		    	v5.setPetId(1);
		    	v1.setDescription("A");
		    	v2.setDescription("A");
		    	v3.setDescription("A");
		    	v4.setDescription("A");
		    	v5.setDescription("A");
		    	visit.add(v1);
		    	visit.add(v2);
		    	visit.add(v3);
		    	visit.add(v4);
		    	visit.add(v5);
		    	log.info(visit.toString());
		    	visitService.save(visit);

		    log.info("obtener todas las visitas para dicha mascota");
		    List<Visit> l1 = visitService.findByPetId(1);
		    for(Visit v: l1) {
		    		log.info(v.toString());
		    }
		    
		    List<Visit> l2 = visitService.findByPetId(7);
		    for(Visit v: l2) {
		    		log.info(v.toString());
		    }
		    
		    log.info("obtener las 3 visitas más recientes de todo el sistema");
		    List<Visit> l3 = visitService.findTop3ByOrderByDateDesc();
		    for(Visit v: l3) {
		    		log.info(v.toString());
		    }
		   
		};
    }
	
	
	
	
	
//  @Bean
//	public CommandLineRunner demoPetRepository(PetRepository petRepository, PetTypeRepository petTypeRepository, OwnerRepository ownerRepository) {
//		return (args) -> {
//			log.info("*****************************************************");
//		    log.info("BOOTCAMP - Spring y Spring Data - PetRepository");
//		    log.info("*****************************************************");
//		    
//		    PetType pt1 = petTypeRepository.findOne(1);
//		    PetType pt2 = petTypeRepository.findOne(2);
//		    PetType pt3 = petTypeRepository.findOne(3);
//
//		    Owner o1 = ownerRepository.findOne(1);
//		    Owner o2 = ownerRepository.findOne(2);
//		    Owner o3 = ownerRepository.findOne(3);
//		    
//		    Pet p1 = new Pet();
//		    p1.setBirthDate(new Date(2010, 2, 17));
//		    p1.setType(pt1);
//		    p1.setName("A");
//		    p1.setOwner(o1);
//		    
//		    Pet p2 = new Pet();
//		    p2.setBirthDate(new Date(2010, 7, 18));
//		    p2.setType(pt2);
//		    p2.setName("B");
//		    p2.setOwner(o2);
//		    
//		    Pet p3 = new Pet();
//		    p3.setBirthDate(new Date(2011, 2, 17));
//		    p3.setType(pt3);
//		    p3.setName("C");
//		    p3.setOwner(o3);
//		    
//			log.info("Persistir objetos");
//			petRepository.save(p1);
//			petRepository.save(p2);
//			petRepository.save(p3);
//			for(Pet p: petRepository.findAll()){
//				log.info(p.toString());
//			}
//		    
//		};
//  }
}
