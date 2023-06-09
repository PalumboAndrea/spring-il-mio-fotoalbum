package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Foto;
import com.example.demo.repo.FotoRepo;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepo fotoRepo;
	
	public List<Foto> findAll() {
		
		return fotoRepo.findAll();
	}
	
	public Foto save(Foto foto) {
		
		return fotoRepo.save(foto);
	}
	
	public Optional<Foto> findById(int id) {
		
		return fotoRepo.findById(id);
	}
	
	public void deleteFoto(Foto foto) {
		
		fotoRepo.delete(foto);
	}

}
