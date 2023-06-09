package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Foto;
import com.example.demo.pojo.Messaggio;
import com.example.demo.serv.FotoService;
import com.example.demo.serv.MessaggioServ;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private MessaggioServ messaggioServ;
	
	@GetMapping("/foto")
	public ResponseEntity<List<Foto>> getFoto() {
		
		List<Foto> foto = fotoService.findAll();
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@GetMapping("/foto/by/name")
	public ResponseEntity<List<Foto>> getFotoByName(
			@RequestParam(required = false) String titolo) {
		
		List<Foto> foto = fotoService.findByName(titolo);
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@PostMapping("/messaggio")
	public ResponseEntity<Messaggio> storeMessaggio(
			@RequestBody(required = true) Messaggio messaggio
			) {
		
		messaggio = messaggioServ.save(messaggio);
		
		return new ResponseEntity<>(
				messaggio, 
				HttpStatus.OK);	
	}
	
}
