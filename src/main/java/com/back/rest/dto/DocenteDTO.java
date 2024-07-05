package com.back.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DocenteDTO {
	private Integer codigo;

	@NotNull(message = "Debe proporcionar un nombre")
	@NotBlank(message = "El nombre no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre debe contener entre 3 y 20 caracteres")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede incluir letras")
	private String nombres;

	@NotNull(message = "Debe proporcionar un apellido")
	@NotBlank(message = "El apellido no debe estar vacío")
	@Size(min = 4, max = 20, message = "El apellido debe contener entre 4 y 20 caracteres")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido solo puede incluir letras")
	private String apellidos;

	@NotNull(message = "Debe proporcionar un sueldo")
	@Min(value = 1000, message = "El sueldo mínimo es 1000")
	@Max(value = 5000, message = "El sueldo máximo es 5000")
	private double sueldo;

}
