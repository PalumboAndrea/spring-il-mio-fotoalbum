package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Categoria;
import com.example.demo.pojo.Messaggio;

@Repository
public interface MessaggioRepo extends JpaRepository<Messaggio, Integer>{

}
