package nl.tvdven.ligatum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class Config {

//    @Bean
//    public Vertx vertx() {
//        return Vertx.vertx(new VertxOptions().setWorkerPoolSize(40)); // TODO: Make WorkerPoolSize configurable
////        return Vertx.vertx();
//    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Resource resource = new FileSystemResource(System.getProperty("user.home") + "/.ligatum/application-" + System.getenv("env") + ".properties");
        configurer.setLocations(resource);
        configurer.setLocalOverride(true);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}

