package com.back.rest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.back.rest.entity.Docente;
import com.back.rest.repository.DocenteRepository;

@Service
public class DocenteServices extends IDocenteImpl<Docente, Integer> {
	@Autowired
	private DocenteRepository docenteRepository;

	@Override
	public JpaRepository<Docente, Integer> getRepository() {
		return docenteRepository;
	}

}
