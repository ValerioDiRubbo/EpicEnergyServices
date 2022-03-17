package it.epicode.be.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.common.util.exception.NewException;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;
import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	public Fattura save(Fattura fattura) {

		if (!fatturaRepo.save(fattura).equals(null)) {
			return fattura;
		} else {
			throw new NewException("Impossibile creare una nuova fattura.");
		}

	}

	public void delete(Long id) {
		Optional<Fattura> fatturaResult = fatturaRepo.findById(id);

		if (fatturaResult.isPresent()) {
			fatturaRepo.deleteById(id);
		} else {
			throw new NewException("Fattura non trovata con id: " + id + " , inserire un ID di una fattura presente.");
		}

	}

	public Fattura update(Fattura fattura, Long id) {
		Optional<Fattura> fatturaResult = fatturaRepo.findById(id);
		if (fatturaResult.isPresent()) {
			Fattura fatturaUpdate = fatturaResult.get();

			fatturaUpdate.setAnno(fattura.getAnno());
			fatturaUpdate.setData(fattura.getData());
			fatturaUpdate.setImporto(fattura.getImporto());
			fatturaUpdate.setNumeroFattura(fattura.getNumeroFattura());
			fatturaUpdate.setStatoFattura(fattura.getStatoFattura());
			

			return fatturaRepo.save(fatturaUpdate);

		} else {
			throw new NewException("Impossibile aggiornare Fattura con id : " + id
					+ "Inserire un ID valido o validare i campi necessari.");
		}

	}

	// Metodi per filtrare.
	public Page<Fattura> findByCliente(Long clienteid, Pageable page) {
		Optional<Fattura> fatturaResult = fatturaRepo.findById(clienteid);

		if (fatturaResult.isPresent()) {

			return fatturaRepo.findByClienteId(clienteid, page);
		} else {
			throw new NewException("Fattura non trovate presso l'id Cliente: " + clienteid
					+ " , inserire un ID di un cliente che possiede fatture..");
		}

	}

	public Page<Fattura> findByStatoFattura(StatoFattura statofattura, Pageable page) {
		return fatturaRepo.findByStatoFattura(statofattura, page);
	}

	public Page<Fattura> findByData(Date data1, Date data2, Pageable page) {
		return fatturaRepo.findByData(data1, data2, page);
	}

	public Page<Fattura> findByAnno(int annoinizio, int annofine, Pageable page) {
		return fatturaRepo.findByAnno(annoinizio, annofine, page);
	}

	public Page<Fattura> findByImporto(BigDecimal daprezzo, BigDecimal aprezzo, Pageable page) {
		return fatturaRepo.findByImporto(daprezzo, aprezzo, page);
	}

	public List<Fattura> findAll() {
		return fatturaRepo.findAll();
	}

	public List<Fattura> findByCliente(Long id) {
		return fatturaRepo.findByClienteId(id);
	}

	public Optional<Fattura> findById(Long id) {

		return fatturaRepo.findById(id);
	}

}
