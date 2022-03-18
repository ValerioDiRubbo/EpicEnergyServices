package it.epicode.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.be.util.CaricaDB;

@RestController
@RequestMapping("/public")
public class CaricaDBController {

	@Autowired
	CaricaDB loader;

	@GetMapping("/caricaDB")
	@Operation(description = "Carica il Database con le liste di comuni e province e 3 clienti prova.")
	public void caricaDatabase() {
		loader.caricaDB();
	}

}
