package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Categoria;
import com.example.demo.pojo.Messaggio;
import com.example.demo.repo.CategoriaRepo;
import com.example.demo.repo.MessaggioRepo;

@Service
public class MessaggioServ {
	
	@Autowired
	private MessaggioRepo messaggioRepo;

	public List<Messaggio> findAll() {
		
		return messaggioRepo.findAll();
	}
	
	public Messaggio save(Messaggio messaggio) {
		
		return messaggioRepo.save(messaggio);
	}
	
	public Optional<Messaggio> findById(int id) {
		
		return messaggioRepo.findById(id);
	}
	
	public void deleteMessaggio(Messaggio messaggio) {
		
		messaggioRepo.delete(messaggio);
	}
	
}
