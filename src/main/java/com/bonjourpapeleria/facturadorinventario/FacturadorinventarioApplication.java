package com.bonjourpapeleria.facturadorinventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class FacturadorinventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturadorinventarioApplication.class, args);
	}

}
