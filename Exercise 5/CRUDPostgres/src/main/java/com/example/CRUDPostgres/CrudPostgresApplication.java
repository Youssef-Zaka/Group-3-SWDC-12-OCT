package com.example.CRUDPostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CrudPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudPostgresApplication.class, args);
	}

}
