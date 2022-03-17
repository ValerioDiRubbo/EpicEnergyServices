package it.epicode.be.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


	// Ordinamento
	public Page<Cliente> findByOrderByNomeAsc (Pageable page);
	public Page<Cliente> findByOrderByFatturatoAnnualeDesc (Pageable page);
	public Page<Cliente> findByOrderByDataInserimentoAsc (Pageable page);
	public Page<Cliente> findByOrderByDataUltimoContattoAsc (Pageable page);
	public Page<Cliente> findByOrderByIndirizzoSedeLegaleComuneProvinciaSiglaAsc (Pageable page);
	

	// Filtri
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale between ?1 AND ?2")
	public Page<Cliente> findByFatturatoAnnuale(double valore1, double valore2, Pageable page);
	
	@Query("SELECT c FROM Cliente c WHERE c.dataInserimento between ?1 AND ?2")
	public Page<Cliente> findByDataInserimentoContatto(LocalDate data1, LocalDate data2, Pageable page);
	
	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto between ?1 AND ?2")
	public Page<Cliente> findByDataUltimoContatto(LocalDate data1, LocalDate data2, Pageable page);
	
	public Page<Cliente> findByNomeContainingIgnoreCase(String nomeParziale, Pageable page);
}
