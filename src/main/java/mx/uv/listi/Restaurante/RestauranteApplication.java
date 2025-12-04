package mx.uv.listi.Restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;  

@SpringBootApplication
@ComponentScan(basePackages = "mx.uv.listi.Restaurante")
public class RestauranteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteApplication.class, args);
    }
}