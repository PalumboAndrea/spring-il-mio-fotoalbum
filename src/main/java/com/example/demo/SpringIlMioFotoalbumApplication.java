package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.pojo.Foto;
import com.example.demo.serv.FotoService;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Autowired
	private FotoService fotoService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Foto foto1 = new Foto("Foto a Bali",
							"Ecco la mia vacanza a Bali",
							"https://storage.googleapis.com/fvallimages/uploads/blog/1582707516_blog_bannr_34.jpg",
							true);
		Foto foto2 = new Foto("Foto Costa Rica",
				"Ecco la mia vacanza in Costa Rica",
				"https://totalsurfcamp.com/sites/default/files/surfcamps/%5Btitle-raw%5D/playa-dominical-en-costa-rica.jpg",
				true);
		
		fotoService.save(foto1);
		fotoService.save(foto2);
		
		
	}

}
