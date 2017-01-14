package io.pivotal.gemfire.sample.client.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import io.pivotal.gemfire.sample.common.entity.Dog;;

public interface DogRepository extends CrudRepository<Dog, Integer>{

	Dog findById(int id);
	
	List<Dog>findByFirstName(String firstName);
}
