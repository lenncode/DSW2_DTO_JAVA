package com.back.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.rest.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer>{
	
	
}
