package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.auth.Serv.RoleService;
import com.example.demo.auth.Serv.UserService;
import com.example.demo.auth.pojo.Role;
import com.example.demo.auth.pojo.User;
import com.example.demo.pojo.Foto;
import com.example.demo.pojo.Categoria;
import com.example.demo.serv.CategoriaServ;
import com.example.demo.serv.FotoService;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaServ categoriaServ;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userUser = new User("user", pws, roleUser);
		User userAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		
		Categoria categoria1 = new Categoria("Mare");
		Categoria categoria2 = new Categoria("Montagna");
		Categoria categoria3 = new Categoria("Collina");
		Categoria categoria4 = new Categoria("Lago");
		
		categoriaServ.save(categoria1);
		categoriaServ.save(categoria2);
		categoriaServ.save(categoria3);
		categoriaServ.save(categoria4);
		
		Foto foto1 = new Foto("Foto a Bali",
							"Ecco la mia vacanza a Bali",
							"https://storage.googleapis.com/fvallimages/uploads/blog/1582707516_blog_bannr_34.jpg",
							true, new Categoria[] {categoria1, categoria2});
		Foto foto2 = new Foto("Foto Costa Rica",
				"Ecco la mia vacanza in Costa Rica",
				"https://totalsurfcamp.com/sites/default/files/surfcamps/%5Btitle-raw%5D/playa-dominical-en-costa-rica.jpg",
				true, new Categoria[] {categoria3, categoria2});
		
		fotoService.save(foto1);
		fotoService.save(foto2);
		
		
	}

}
