package com.devsuperior.bds02.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {	
		Optional<Event> optional = repository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Event not found");
		}
		Event entity = optional.get();
		entity.setCity(new City(dto.getCityId(), null));
		entity.setDate(dto.getDate());
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		repository.save(entity);
		
		return new EventDTO(entity);	
	}
	
	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		
		Optional<Event> obj = repository.findById(id);
		if(obj.isEmpty()) {
			return null;
		}
		else {
			Event entity = obj.get();
			return new EventDTO(entity);
		}
	}
}
