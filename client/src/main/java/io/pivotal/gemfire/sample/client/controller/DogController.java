package io.pivotal.gemfire.sample.client.controller;

import org.apache.geode.cache.Region;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import io.pivotal.gemfire.sample.common.entity.Dog;

@RestController
class DogController {
    
	@Autowired private Region<Integer, Dog> dogRegion;

    @RequestMapping(value = "/dog/{id}", method = GET)
    public Dog getDog(@PathVariable("id") Integer id) {
        return (Dog) dogRegion.get(id);
    }
    
    @RequestMapping(value = "/dog/{id}", method = PUT)
    public Dog newDog(@PathVariable("id") Integer id) {
        return null;
    }
}