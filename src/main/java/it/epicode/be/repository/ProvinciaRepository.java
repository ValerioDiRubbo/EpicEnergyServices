package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	public Optional<Provincia> findByNome (String string);

	public Optional<Provincia> getByNome(String string);

	

	
}
