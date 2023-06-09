package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import javax.management.modelmbean.RequiredModelMBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Foto;
import com.example.demo.serv.FotoService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/pizze")
	public ResponseEntity<List<Foto>> getPizze() {
		
		List<Foto> foto = fotoService.findAll();
		
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
}
