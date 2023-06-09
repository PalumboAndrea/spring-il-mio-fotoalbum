package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Categoria;
import com.example.demo.repo.CategoriaRepo;

@Service
public class CategoriaServ {
	
	@Autowired
	private CategoriaRepo categoriaRepo;

	public List<Categoria> findAll() {
		
		return categoriaRepo.findAll();
	}
	
	public Categoria save(Categoria categoria) {
		
		return categoriaRepo.save(categoria);
	}
	
	public Optional<Categoria> findById(int id) {
		
		return categoriaRepo.findById(id);
	}
	
	public void deleteCategoria(Categoria categoria) {
		
		categoriaRepo.delete(categoria);
	}
	
}
