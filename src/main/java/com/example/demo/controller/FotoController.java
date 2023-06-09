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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojo.Categoria;
import com.example.demo.pojo.Foto;
import com.example.demo.serv.CategoriaServ;
import com.example.demo.serv.FotoService;

import jakarta.validation.Valid;

@Controller
public class FotoController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoriaServ categoriaServ;
	
	@GetMapping("/home")
	public String homeView(Model model) {
	
		List<Foto> foto = fotoService.findAll();
		
		model.addAttribute("foto", foto);
		
		return "FotoIndex";
	}
	
	@PostMapping("/foto/by/name")
	public String search(Model model,
			@RequestParam(required = false) String titolo) {
	
		List<Foto> foto = fotoService.findByName(titolo);
		
		model.addAttribute("foto", foto);
		model.addAttribute("titolo", titolo);
		
		return "FotoSearch";
	}
	
	@GetMapping("/foto/{id}")
	public String showView(Model model,
			@PathVariable("id") int id) {
	
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		List<Categoria> categorie = foto.getCategorie();

		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		
		return "FotoShow";
	}
	
	@GetMapping("/admin/foto/create")
	public String createFoto(Model model) {
		
		model.addAttribute("foto", new Foto());
		
		return "FotoCreate";
	}
	
	@PostMapping("/admin/foto/create")
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
	
	@GetMapping("/admin/foto/update/{id}")
	public String editFoto(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		
		List<Categoria> categorie = categoriaServ.findAll();

		model.addAttribute("foto", foto);
		model.addAttribute("categorie", categorie);
		
		return "FotoUpdate";
	}
	
	@PostMapping("/admin/foto/update/{id}")
	public String updateFoto(
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
	
	@GetMapping("/admin/foto/delete/{id}")
	public String deleteFoto(
			@PathVariable Integer id
		) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		fotoService.deleteFoto(foto);
		
		return "redirect:/home";
	}
	
	
	

}
