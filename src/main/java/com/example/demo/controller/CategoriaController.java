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

import com.example.demo.pojo.Categoria;
import com.example.demo.pojo.Foto;
import com.example.demo.serv.CategoriaServ;
import com.example.demo.serv.FotoService;

import jakarta.validation.Valid;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaServ categoriaServ;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/admin/categorie/index")
	public String categorieIndex(Model model) {
	
		List<Categoria> categorie = categoriaServ.findAll();
		
		model.addAttribute("categorie", categorie);
		
		return "CategoriaIndex";
	}
	
	@GetMapping("/admin/categoria/create")
	public String createCategoria(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		
		return "CategoriaCreate";
	}
	
	@PostMapping("/admin/categoria/create")
	public String storeCategoria(
			Model model,
			@Valid @ModelAttribute Categoria categoria,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("categoria", categoria);
			model.addAttribute("errors", bindingResult);
			
			return "CategoriaCreate";
		}
		
		categoriaServ.save(categoria);
		
		return "redirect:/admin/categorie/index";
	}
	
	@GetMapping("/admin/categoria/update/{id}")
	public String editFoto(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Categoria> optCategoria = categoriaServ.findById(id);
		Categoria categoria = optCategoria.get();
		model.addAttribute("categoria", categoria);
		
		return "CategoriaUpdate";
	}
	
	@PostMapping("/admin/categoria/update/{id}")
	public String updateFoto(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Categoria categoria,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("categoria", categoria);
			model.addAttribute("errors", bindingResult);
			
			return "CategoriaUpdate";
		}
		
		categoria.setId(id);
		categoriaServ.save(categoria);
		
		return "redirect:/admin/categorie/index";
	}
	
	@GetMapping("/admin/categoria/delete/{id}")
	public String deleteFoto(
			@PathVariable Integer id
		) {
		
		Optional<Categoria> optCategoria = categoriaServ.findById(id);
		Categoria categoria = optCategoria.get();
		
		for (Foto f : categoria.getFoto()) {
			
			f.removeCategorie(categoria);
			fotoService.save(f);
		}

		categoriaServ.deleteCategoria(categoria);
		
		return "redirect:/admin/categorie/index";
	}

}
