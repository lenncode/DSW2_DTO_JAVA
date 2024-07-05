package com.back.rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "docente")
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Integer codigo;
	@Column(name = "nombres")
	private String nombres;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "sueldo")
	private double sueldo;

}



