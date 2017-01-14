package io.pivotal.gemfire.sample.client.builder;

import org.springframework.stereotype.Component;

import io.pivotal.gemfire.sample.common.entity.Cat;

@Component
public class CatBuilder {
	
	public Cat buildCat(int id){
		Cat c = new Cat();
		
		c.setId(id);
	    c.setFirstName("Garfield" + id);
	    c.setLastName("TheCat" + id);
	    c.setAge(2);
	    c.setPet(true);        
		return c;
	}
	
}
