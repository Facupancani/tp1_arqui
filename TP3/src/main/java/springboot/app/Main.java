package springboot.app;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.app.helper.CargarDatos;
import java.io.IOException;

@SpringBootApplication
public class Main {

    @Autowired
    private CargarDatos cargarDatos;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        cargarDatos.cargarDB();
    }

}
