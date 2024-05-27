package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {

	@Autowired 
	UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario=usuarioService.getById(1);
		
		
		System.out.println("HOLAAAA "+usuario.getNombre());
		
	}

}
