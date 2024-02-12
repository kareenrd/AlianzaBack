package alianza.AlianzaBack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PruebaAlianzaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PruebaAlianzaApplication.class, args);
		log.info("-------- ejecuto");
	}

}
