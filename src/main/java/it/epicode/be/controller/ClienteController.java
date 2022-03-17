package it.epicode.be.controller;

import java.time.LocalDate;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.model.Cliente;
import it.epicode.be.service.ClienteService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	
	@GetMapping(path = "/clienti")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Trova tutti i clienti.")
	public ResponseEntity<Page<Cliente>> trovaTutti(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/cliente/ordina/nome")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Ordina tutti i clienti per nome, in ordine ascendente.")
	public ResponseEntity<Page<Cliente>> ordinaPerNome(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findByOrderByNomeAsc(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/ordina/fatturato")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Ordina tutti i clienti per fatturato annuo, partendo dal più alto.")
	public ResponseEntity<Page<Cliente>> ordinaPerFatturato(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findByOrderByFatturatoAnnualeDesc(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/ordina/datainserimento")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Ordina tutti i clienti per data di inserimento, in ordine ascendente.")
	public ResponseEntity<Page<Cliente>> ordinaPerDataInserimento(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findByOrderByDataInserimentoAsc(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/ordina/dataultimocontatto")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Ordina tutti i clienti per data di ultimo contatto, in ordine ascendente.")
	public ResponseEntity<Page<Cliente>> ordinaPerDataUltimoContatto(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findByOrderByDataUltimoContattoAsc(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/ordina/provinciasedelegale")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Ordina tutti i clienti per provincia.")
	public ResponseEntity<Page<Cliente>> ordinaPerProvinciaSedeLegale(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findAllByIndirizzoSedeLegaleComuneProvinciaSigla(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/cliente/crea")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Crea un nuovo cliente.")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente save = clienteService.save(cliente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/cliente/modifica/{id}")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Modifica un cliente tramite json body, selezionando il suo id.")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente save = clienteService.update(cliente, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/cliente/elimina/{id}")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Cancella un cliente con l'ID associato.")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>("Cliente cancellato", HttpStatus.OK);

	}

	// Filtri
	@GetMapping(path = "/cliente/filtra/fatturatoannualecustom/{valore1}/{valore2}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Specificare il range di importi tra cui ricercare i fatturati dei clienti. (Tra valore 1 e valore 2.)")
	public ResponseEntity<Page<Cliente>> findFatturato(@PathVariable double valore1, @PathVariable double valore2,
			Pageable pageable) {
		Page<Cliente> findAll = clienteService.findFatturato(valore1, valore2, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/filtra/dataultimocontatto/{data1}/{data2}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Specificare il range di date tra cui ricercare.")
	public ResponseEntity<Page<Cliente>> findDataUltimoContatto(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data1,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data2, Pageable pageable) {
		Page<Cliente> findAll = clienteService.findUltimoContatto(data1, data2, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/filtra/datainserimento/{data1}/{data2}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Specificare il range di date tra cui ricercare.")
	public ResponseEntity<Page<Cliente>> findDataInserimento(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data1,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data2, Pageable pageable) {
		Page<Cliente> findAll = clienteService.findDataInserimento(data1, data2, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/filtra/filtranomeparziale/{nomeparziale}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Inserire parte del nome del cliente da ricercare.")
	public ResponseEntity<Page<Cliente>> filtraNomeParziale(@PathVariable String nomeparziale, Pageable pageable) {
		Page<Cliente> findAll = clienteService.findNomeParziale( nomeparziale,pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
