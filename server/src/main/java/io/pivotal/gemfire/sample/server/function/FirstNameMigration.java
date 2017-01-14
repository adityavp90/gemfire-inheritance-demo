package io.pivotal.gemfire.sample.server.function;

import java.util.Properties;
import java.util.Set;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.pdx.PdxInstance;

public class FirstNameMigration implements Function, Declarable {

	@Override
	public void init(Properties arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(FunctionContext ctx) {

		try {
			Cache cache = CacheFactory.getAnyInstance();
			Region<Integer, PdxInstance> theRegion = cache.getRegion("Dog");
			Set<Integer> regionKeys=theRegion.keySet();
			for (Integer id : regionKeys) {
				PdxInstance p = theRegion.get(id);
				System.out.println(p.getField("firstName"));
				System.out.println(p.getField("firstName"));
			}
		} catch (Exception x) {
			throw new FunctionException(x.toString(), x);
		}

	}

}
