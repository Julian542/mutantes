package com.example.demo.controllers;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.PDto;
import com.example.demo.services.PService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE})
@RequestMapping(path = "api/v1/mutante")
public class PController {
	private PService service;

	public PController(PService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Error en getAll\"}");
		}
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Error en getOne\"}");
		}
	}
	
	@GetMapping("/stats")
	@Transactional
	public ResponseEntity stats() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.stats());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Error en STATS\"}");
		}
	}
	
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody PDto dto) {
		try {
			
			if(service.save(dto) == true) {
				return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"El adn ingresado es MUTANTE\"}");
			}else {
				
				return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"El adn ingresado es NORMAL\"}");
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody PDto dto) {
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Error en delete\"}");
		}
	}
}
