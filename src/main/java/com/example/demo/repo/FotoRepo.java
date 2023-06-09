package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Foto;

@Repository
public interface FotoRepo extends JpaRepository<Foto, Integer>{

}
