package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer>{

}
