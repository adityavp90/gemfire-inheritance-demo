package io.pivotal.gemfire.sample.client.controller;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.execute.FunctionService;
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
import io.pivotal.gemfire.sample.common.entity.Dog;

@RestController
class DogController {

	@Autowired
	ClientCache cache;

	@Autowired
	DogBuilder dogBuilder;

	@Autowired
	private Region<Integer, Dog> dogRegion;

	@RequestMapping(value = "/dog/{id}", method = GET)
	public Dog getDog(@PathVariable("id") Integer id) {
		return (Dog) dogRegion.get(id);
	}

	@RequestMapping(value = "/dog/{id}", method = PUT)
	public Dog newDog(@PathVariable("id") Integer id) {
		Dog d = dogBuilder.buildDog(id);
		dogRegion.put(id, d);
		return d;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dog/byFirstName/{firstName}", method = GET)
	public String getDogByFirstName(@PathVariable("firstName") String firstName) throws Exception {
		Gson dogGson = new Gson();
		List<String> dogList = new ArrayList<>();
		Object[] params = new Object[1];
		params[0] = firstName;
		
		String queryString = "SELECT * FROM /Dog WHERE firstName = $1";

		QueryService queryService = cache.getQueryService();
		Query query = queryService.newQuery(queryString);

		SelectResults results = (SelectResults) query.execute(params);

		if (results.isEmpty()) {
			throw new Exception();
		} else {
			for (Iterator iter = results.iterator(); iter.hasNext();) {
				Dog dog = (Dog) iter.next();
				dogList.add(dogGson.toJson(dog));
			}
			return dogList.toString();
		}
	}
}
//	@RequestMapping(value = "/dog/setLastNameToFirstName", method = GET)
//	public String setLastNameToFirstName(){
//		FunctionService.registerFunction(FirstNameMigration);		
//		return null;		
//	}
// nmki, 
//}