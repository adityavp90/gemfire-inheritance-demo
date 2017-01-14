package io.pivotal.gemfire.sample.client.builder;

import org.springframework.stereotype.Component;

import io.pivotal.gemfire.sample.common.entity.Dog;

@Component
public class DogBuilder {
	
	public Dog buildDog(int id){
		Dog d = new Dog();
        d.setId(id);
        d.setFirstName("Scooby");
        d.setLastName("Doo" + id);
        d.setAge(5);
        d.setPet(true);
    	return d;
	}
}
