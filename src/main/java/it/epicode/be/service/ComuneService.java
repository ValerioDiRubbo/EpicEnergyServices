package it.epicode.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.epicode.be.model.Comune;
import it.epicode.be.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepo;
	
	public List<Comune> findAll() {
		return comuneRepo.findAll();
	}
}
