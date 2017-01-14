package io.pivotal.gemfire.sample.client.repository;


import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.stereotype.Repository;

import io.pivotal.gemfire.sample.common.entity.Cat;

@Repository
public interface CatRepository extends GemfireRepository<Cat, Integer> {

	Cat findCatById(Integer id);

}