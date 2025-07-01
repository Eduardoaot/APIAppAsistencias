package wf.apiasistencias.appasistencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "wf.apiasistencias.appasistencias")
public class AppasistenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppasistenciasApplication.class, args);
	}

}
