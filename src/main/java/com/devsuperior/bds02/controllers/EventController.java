package com.devsuperior.bds02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService service;
	
	@PutMapping("/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto){
		try {
			EventDTO updatedDto = service.update(id, dto);
			return ResponseEntity.ok().body(updatedDto);
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			}	
		
	}
}
