package pro.sky.java.course3.unihogvards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class UniHogvardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniHogvardsApplication.class, args);
    }

}
