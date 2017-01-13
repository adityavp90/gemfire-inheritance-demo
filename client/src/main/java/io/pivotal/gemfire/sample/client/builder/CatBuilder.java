package io.pivotal.gemfire.sample.client.builder;

import org.springframework.stereotype.Component;

import io.pivotal.gemfire.sample.common.entity.Cat;

@Component
public class CatBuilder {
	
	public Cat buildCat(int id){
		Cat c = new Cat();
		
		c.setId(id);
	    c.setName("Garfield" + id);
	    c.setAge(12);
	    c.setPet(true);        
		return c;
	}
	
}
