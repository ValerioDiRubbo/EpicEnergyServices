package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.IndirizzoOperativo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

	public Optional<Indirizzo> findById(Long id);

	public Indirizzo save(IndirizzoOperativo indirizzooperativo);
}
