package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.pojo.Foto;
import com.example.demo.serv.FotoService;

import jakarta.validation.Valid;

@Controller
public class FotoController {
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/home")
	public String homeView(Model model) {
	
		List<Foto> foto = fotoService.findAll();
		
		model.addAttribute("foto", foto);
		
		return "FotoIndex";
	}
	
	@GetMapping("/foto/{id}")
	public String showView(Model model,
			@PathVariable("id") int id) {
	
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();

		model.addAttribute("foto", foto);
		
		return "FotoShow";
	}
	
	@GetMapping("/foto/create")
	public String createFoto(Model model) {
		
		model.addAttribute("foto", new Foto());
		
		return "FotoCreate";
	}
	
	@PostMapping("/foto/create")
	public String storeFoto(
			Model model,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			
			return "FotoCreate";
		}
		
		fotoService.save(foto);
		
		return "redirect:/home";
	}
	
	@GetMapping("/foto/update/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		model.addAttribute("foto", foto);
		
		return "FotoUpdate";
	}
	
	@PostMapping("/foto/update/{id}")
	public String updatePizza(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			
			return "FotoUpdate";
		}
		
		foto.setId(id);
		fotoService.save(foto);
		
		return "redirect:/home";
	}
	
	
	

}
