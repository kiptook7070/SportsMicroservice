package microservice.ItemCatalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ItemCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemCatalogApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("SPORTS ITEMS CATALOG INITIALIZED SUCCESSFULLY AT " + new Date());
        };
    }

}
