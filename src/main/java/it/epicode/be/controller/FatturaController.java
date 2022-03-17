package it.epicode.be.controller;

import java.math.BigDecimal;

import java.util.Date;

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

import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;

import it.epicode.be.service.FatturaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;

	@GetMapping(path = "/fattura/ordina/cliente/{clienteid}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Restituisce le fatture relative all'ID cliente inserito.")
	public ResponseEntity<Page<Fattura>> filtraPerCliente(@PathVariable Long clienteid, Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findByCliente(clienteid, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fattura/ordina/data/{data1}/{data2}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Restituisce le fatture relative al range tra le date inserite.")
	public ResponseEntity<Page<Fattura>> filtraPerData(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2, Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findByData(data1, data2, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fattura/ordina/stato/{statofattura}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Restituisce tutte le fatture con lo Stato selezionato.")
	public ResponseEntity<Page<Fattura>> filtraPerStatoFattura(@PathVariable StatoFattura statofattura,
			Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findByStatoFattura(statofattura, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fattura/ordina/anno/{annoinizio}/{annofine}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Restituisce le fatture per gli anni compresi nell'intervallo.")
	public ResponseEntity<Page<Fattura>> filtraPerAnno(@PathVariable int annoinizio, @PathVariable int annofine,
			Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findByAnno(annoinizio, annofine, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fattura/ordina/importo/{daprezzo}/{aprezzo}")
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	@Operation(description = "Restituisce le fatture che hanno importi compresi nel range.")
	public ResponseEntity<Page<Fattura>> filtraPerImporto(@PathVariable BigDecimal daprezzo,
			@PathVariable BigDecimal aprezzo, Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findByImporto(daprezzo, aprezzo, pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/fattura/crea")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Crea una nuova fattura. Si può abbinare al cliente scelto passando il suo ID.")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura save = fatturaService.save(fattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/fattura/modifica/{id}")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Modifica unafattura con ID inserito. Se non si vuole modificare il cliente, cancellare la parte json relativa.")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		Fattura save = fatturaService.update(fattura, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/fattura/elimina/{id}")
	@RolesAllowed("ROLE_ADMIN")
	@Operation(description = "Elimina la fattura con ID immesso.")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fatturaService.delete(id);
		return new ResponseEntity<>("Fattura cancellata", HttpStatus.OK);

	}

}
