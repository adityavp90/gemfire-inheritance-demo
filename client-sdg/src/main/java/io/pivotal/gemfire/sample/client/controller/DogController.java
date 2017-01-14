package io.pivotal.gemfire.sample.client.controller;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.pivotal.gemfire.sample.client.builder.DogBuilder;
import io.pivotal.gemfire.sample.client.repository.DogRepository;
import io.pivotal.gemfire.sample.common.entity.Dog;

@RestController
class DogController {

	@Autowired
	private DogRepository dogRepo;

	@Autowired
	DogBuilder dogBuilder;

	@Autowired
	private Region<Integer, Dog> dogRegion;

	@RequestMapping(value = "/dog/{id}", method = GET)
	public Dog getDog(@PathVariable("id") Integer id) {
		return dogRepo.findById(id);
	}

	@RequestMapping(value = "/dog/{id}", method = PUT)
	public Dog newDog(@PathVariable("id") Integer id) {
		Dog d = dogBuilder.buildDog(id);
		dogRegion.put(id, d);
		return d;
	}

	@RequestMapping(value = "/dog/byFirstName/{firstName}", method = GET)
	public String getDogByFirstName(@PathVariable("firstName") String firstName) {
		Gson dogGson = new Gson();
		List<String> dogStringList = new ArrayList<>();
		List<Dog> dogList = dogRepo.findByFirstName(firstName);
		
		for (Dog d : dogList){
			dogStringList.add(dogGson.toJson(d));
		}		
		return dogStringList.toString();
	}
	

}