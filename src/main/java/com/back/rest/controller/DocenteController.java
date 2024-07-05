package com.back.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.rest.dto.DocenteDTO;
import com.back.rest.entity.Docente;
import com.back.rest.servicesImpl.DocenteServices;
import com.back.rest.utils.MensajeResponse;
import com.back.rest.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private DocenteServices docenteServices;
	@Autowired
	private ModelMapper mapper;

	@PostMapping("/grabar") // endpoint ====>INSERT INTO
	public ResponseEntity<?> grabar(@Valid @RequestBody DocenteDTO lab) {
		try {

			Docente docente = mapper.map(lab, Docente.class);
			Docente salida = docenteServices.registrar(docente);
			DocenteDTO dto = mapper.map(salida, DocenteDTO.class);

			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado correctamente")
					.object(dto).build(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(e.getMessage())
					.object(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actualizar") // endpoint ====>UPDATE
	public ResponseEntity<?> actualizar(@Valid @RequestBody DocenteDTO bean) {
		// buscar si existe el còdigo
		Docente docenteEncontrado = docenteServices.buscarPorID(bean.getCodigo());
		// validar
		if (docenteEncontrado == null)
			throw new ModeloNotFoundException("Còdigo del Docente : " + bean.getCodigo() + " no existe");
		else {
			Docente docente = mapper.map(bean, Docente.class);
			Docente m = docenteServices.actualizar(docente);
			DocenteDTO dto = mapper.map(m, DocenteDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro actualizado")
					.object(dto).build(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/eliminar/{codigo}") // endpoint /eliminar/3 ====>DELETE
	public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
		Docente docenteEncontrado = docenteServices.buscarPorID(codigo);
		if (docenteEncontrado == null)
			throw new ModeloNotFoundException("Còdigo del Docente : " + codigo + " no existe");
		else
			docenteServices.eliminar(codigo);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/consulta/{codigo}") // endpoint /consulta/2 ===>SELECT *FROM TABLA WHWERE COD=1
	public ResponseEntity<?> consulta(@PathVariable String codigo) {
		// buscar si existe el còdigo
		Docente docenteEncontrado = docenteServices.buscarPorID(Integer.parseInt(codigo));
		// validar
		if (docenteEncontrado == null) {
			DocenteDTO dto = mapper.map(docenteEncontrado, DocenteDTO.class);
			List<DocenteDTO> listDTO = new ArrayList<>();
			listDTO.add(dto);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No existe el Docente ")
					.object(listDTO).build(), HttpStatus.OK);
		} else {
			DocenteDTO dto = mapper.map(docenteEncontrado, DocenteDTO.class);
			List<DocenteDTO> listDTO = new ArrayList<>();
			listDTO.add(dto);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("")
					.object(listDTO).build(), HttpStatus.OK);
		}
	}

	@GetMapping("/listar") // endpoint ====>SELECT *FROM TABLA
	// @GetMapping retorna JSON
	public ResponseEntity<?> listar() {
		List<Docente> lista = docenteServices.listarTodos();
		// validar lista
		if (lista == null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.object(null).build(), HttpStatus.OK);
		} else {
			List<DocenteDTO> data = lista.stream().map(m -> mapper.map(m, DocenteDTO.class))
					.collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("registros")
					.object(data).build(), HttpStatus.OK);
		}

	}

}
