package it.epicode.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.Provincia;
import it.epicode.be.repository.IndirizzoRepository;
import it.epicode.be.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Optional<Provincia> findById(Long idProvincia) {

		return provinciaRepo.findById(idProvincia);
	}
}
