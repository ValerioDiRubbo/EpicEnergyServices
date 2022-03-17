package it.epicode.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.IndirizzoOperativo;
import it.epicode.be.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepo;

	public Optional<Indirizzo> findById(Long idIndirizzo) {

		return indirizzoRepo.findById(idIndirizzo);
	}

	public Indirizzo save(Indirizzo indirizzo) {
		indirizzoRepo.save(indirizzo);
		return indirizzo;

	}

	public IndirizzoOperativo save(IndirizzoOperativo indirizzooperativo) {
		indirizzoRepo.save(indirizzooperativo);
		return indirizzooperativo;
		
	}
}
