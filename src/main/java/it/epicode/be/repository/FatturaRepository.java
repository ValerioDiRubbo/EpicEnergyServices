package it.epicode.be.repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	public Page<Fattura> findByClienteId(Long clienteid, Pageable page);
	public List<Fattura> findByClienteId(Long clienteid);

	public Page<Fattura> findByStatoFattura(StatoFattura statofattura, Pageable page);

	@Query("SELECT f FROM Fattura f WHERE f.data between ?1 AND ?2")
	public Page<Fattura> findByData(Date data1, Date data2, Pageable page);

	@Query("SELECT f FROM Fattura f WHERE f.anno between ?1 AND ?2")
	public Page<Fattura> findByAnno(int annoinizio, int annofine, Pageable page);

	@Query("SELECT f FROM Fattura f WHERE f.importo between ?1 AND ?2")
	public Page<Fattura> findByImporto(BigDecimal daprezzo, BigDecimal aprezzo, Pageable page);
}
