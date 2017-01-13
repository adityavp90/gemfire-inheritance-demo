package io.pivotal.gemfire.sample.client.controller;

import org.apache.geode.cache.Region;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import io.pivotal.gemfire.sample.client.builder.CatBuilder;
import io.pivotal.gemfire.sample.common.entity.Cat;

@RestController()
class CatController {
    
	@Autowired
    CatBuilder catBuilder;
	
	@Autowired 
	private Region<Integer, Cat> catRegion;	
	
    @RequestMapping(value = "/cat/{id}", method = GET)
    public Cat getCat(@PathVariable("id") Integer id) {
        return (Cat) catRegion.get(id);
    }
    
    @RequestMapping(value = "/cat/{id}", method = PUT)
    public Cat newCat(@PathVariable("id") Integer id) {
    	Cat c = catBuilder.buildCat(id);
    	catRegion.put(id, c);
    	return c;    
    }
}