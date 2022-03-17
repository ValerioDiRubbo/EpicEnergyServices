package it.epicode.be.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.common.util.exception.NewException;
import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;
import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.repository.FatturaRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	FatturaRepository fatturaRepo;

	public Cliente save(Cliente cliente) {
		clienteRepo.save(cliente);
		return cliente;

	}

	public void delete(Long id) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);
		List<Fattura> fattureCliente = fatturaRepo.findByClienteId(id);

		if (clienteResult.isPresent()) {
			for (Fattura f : fattureCliente) {
				f.setCliente(null);
				fatturaRepo.delete(f);
			}
			clienteRepo.deleteById(id);
		} else {
			throw new NewException("Cliente non trovato con id: " + id + " , inserire un ID di un cliente presente");
		}

	}

	public Cliente update(Cliente cliente, Long id) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);
		if (clienteResult.isPresent()) {
			Cliente clienteUpdate = clienteResult.get();
			clienteUpdate.setRagioneSociale(cliente.getRagioneSociale());
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setEmailContatto(cliente.getEmailContatto());
			clienteUpdate.setNome(cliente.getNome());
			clienteUpdate.setCognome(cliente.getCognome());
			clienteUpdate.setTelefonoContatto(cliente.getTelefonoContatto());
			clienteUpdate.setTipoCliente(cliente.getTipoCliente());
			clienteUpdate.setIndirizzoSedeOperativa(cliente.getIndirizzoSedeOperativa());
			clienteUpdate.setIndirizzoSedeLegale(cliente.getIndirizzoSedeLegale());

			return clienteRepo.save(clienteUpdate);

		} else {
			throw new NewException("Impossibile aggiornare Cliente con id : " + id
					+ "Inserire un ID valido o validare i campi necessari.");
		}

	}

	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);

		if (clienteResult.isPresent()) {

			return clienteResult;
		} else {
			throw new NewException("Cliente non trovato con id: " + id + " , inserire un ID di un cliente presente.");
		}
	}

	// ***Ricerca e ordinamento ***
	public Page<Cliente> findByOrderByNomeAsc(Pageable page) {
		return clienteRepo.findByOrderByNomeAsc(page);
	}

	public Page<Cliente> findByOrderByFatturatoAnnualeDesc(Pageable page) {
		return clienteRepo.findByOrderByFatturatoAnnualeDesc(page);
	}

	public Page<Cliente> findByOrderByDataInserimentoAsc(Pageable page) {
		return clienteRepo.findByOrderByDataInserimentoAsc(page);
	}

	public Page<Cliente> findByOrderByDataUltimoContattoAsc(Pageable page) {
		return clienteRepo.findByOrderByDataUltimoContattoAsc(page);
	}

	public Page<Cliente> findAllByIndirizzoSedeLegaleComuneProvinciaSigla(Pageable page) {
		return clienteRepo.findByOrderByIndirizzoSedeLegaleComuneProvinciaSiglaAsc(page);
	}

	// Filtri
	public Page<Cliente> findFatturato(double uno, double due, Pageable page) {
		return clienteRepo.findByFatturatoAnnuale(uno, due, page);
	}

	public Page<Cliente> findUltimoContatto(LocalDate data1, LocalDate data2, Pageable page) {
		return clienteRepo.findByDataUltimoContatto(data1, data2, page);
	}

	public Page<Cliente> findDataInserimento(LocalDate data1, LocalDate data2, Pageable page) {
		return clienteRepo.findByDataInserimentoContatto(data1, data2, page);
	}

	public Page<Cliente> findNomeParziale(String nomeParziale, Pageable page) {
		return clienteRepo.findByNomeContainingIgnoreCase(nomeParziale, page);
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}

	public Page<Cliente> findAll(Pageable page) {
		return clienteRepo.findAll(page);
	}

}
