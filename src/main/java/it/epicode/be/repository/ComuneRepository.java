package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.epicode.be.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Long> {

	
}
