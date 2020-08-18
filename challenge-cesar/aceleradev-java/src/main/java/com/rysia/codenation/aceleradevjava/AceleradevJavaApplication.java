package com.rysia.codenation.aceleradevjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rysia.codenation.aceleradevjava.model.Quote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.rysia.codenation.aceleradevjava.util.HandlesCaesarCryptography.*;
import static com.rysia.codenation.aceleradevjava.shared.Constants.API.URL_GET_DATA;

@SpringBootApplication
public class AceleradevJavaApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(AceleradevJavaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AceleradevJavaApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			Quote quote = encryptOrDecrypt(restTemplate.getForObject(URL_GET_DATA, Quote.class));
			quote.setResumoCriptografico(cryptographicHash(quote.getDecifrado()));
			String answer = mapper.writeValueAsString(quote);
			log.info(answer);
		};
	}
}
