package com.devsuperior.bds02.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> list = repository.findAll(Sort.by("name"));
		
		return list.stream().map(x ->  new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new CityDTO(entity);	
	}
	
	public void delete(Long id) {
			repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> city = repository.findById(id);
		if(city.isPresent()) {
			return new CityDTO(city.get());
		}else {
			return null;
		}
	}
}
