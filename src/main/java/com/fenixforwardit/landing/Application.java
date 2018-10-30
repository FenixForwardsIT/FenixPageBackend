package com.fenixforwardit.landing;

import com.fenixforwardit.landing.configuration.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Walter Finkbeiner
 */
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
