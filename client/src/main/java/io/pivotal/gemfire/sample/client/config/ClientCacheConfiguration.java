package io.pivotal.gemfire.sample.client.config;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.gemfire.sample.common.entity.Cat;
import io.pivotal.gemfire.sample.common.entity.Dog;

@Configuration
public class ClientCacheConfiguration {
    
    /*
     * Connection parameter members (TODO - @Profile should adjust these *only*)
     */
    @Value("${gemfire.locator.host}")
    private String locatorHost;

    @Value("${gemfire.locator.port}")
    private Integer locatorPort;

    /*
     * Create a connection - client/server topology (TODO - maybe change this to use a connection Pool)
     */
    @Bean
    public ClientCache cache() {
        ClientCacheFactory ccf = new ClientCacheFactory();

        ccf.addPoolLocator(locatorHost, locatorPort);

        ccf.setPdxPersistent(true);
        ccf.setPdxReadSerialized(false);
        ccf.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.gemfire.sample.common.entity.*"));

        return ccf.create();
    }

    /*
     * Get a region called "Brand", configure as a non-caching proxy (i.e. data remains remote; a pure client-server topology)
     */
    @Bean
    public Region<Integer, Cat> catRegion(ClientCache cache) {
        ClientRegionFactory<Integer, Cat> crf = cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
        return crf.create("Cat");
    }
    
    @Bean
    public Region<Integer, Dog> dogRegion(ClientCache cache) {
        ClientRegionFactory<Integer, Dog> crf = cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
        return crf.create("Dog");
    }

}
