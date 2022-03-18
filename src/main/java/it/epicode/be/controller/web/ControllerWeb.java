package it.epicode.be.controller.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.service.ClienteService;
import it.epicode.be.service.ComuneService;
import it.epicode.be.service.FatturaService;
import it.epicode.be.service.IndirizzoService;
import it.epicode.be.util.CaricaDB;

@Controller
@RequestMapping("/clienti")
public class ControllerWeb {

	@Autowired
	CaricaDB loader;

	@Autowired
	ClienteService clienteService;

	@Autowired
	FatturaService fatturaService;

	@Autowired
	ComuneService comuneService;

	@Autowired
	IndirizzoService indirizzoService;

	@GetMapping("/index")
	public ModelAndView mostraIndex() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}

	@GetMapping("/loader")
	public String caricaDb() {
		loader.caricaDB();
		return "index";
	}

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoClienti() {
		ModelAndView view = new ModelAndView("elencoclienti");
		view.addObject("clienti", clienteService.findAll());
		return view;
	}
	

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Indirizzo indirizzoLeg, Indirizzo indirizzoOp, Model model) {

		model.addAttribute("listaComuni", comuneService.findAll());

		return "formCliente";
	}

	@PostMapping("/addCliente")
	public String aggiungiCliente(Cliente cliente, Indirizzo indirizzoLeg, Indirizzo indirizzoOp, BindingResult result,
			Model model) {
		cliente.setDataInserimento(LocalDate.now());
		if (result.hasErrors()) {
			model.addAttribute("listaClienti", clienteService.findAll());

			return "formCliente";
		}

		clienteService.save(cliente);

		return "redirect:/clienti/mostraelenco";

	}

	
	
	@GetMapping("/eliminacliente/{id}")
	public ModelAndView eliminaCliente(@PathVariable Long id, Model model) {
		Optional<Cliente> clienteDelete = clienteService.findById(id);
		if (clienteDelete.isPresent()) {
			clienteService.delete(id); 
			ModelAndView view = new ModelAndView("elencoclienti");
			view.addObject("clienti", clienteService.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", id + " non trovato.");
		}

	}
	
	@GetMapping("fatture/eliminafattura/{id}")
	public ModelAndView eliminafattura(@PathVariable Long id, Model model) {
		Optional<Fattura> fatturaDelete = fatturaService.findById(id);
		if (fatturaDelete.isPresent()) {
			fatturaService.delete(id); 
			ModelAndView view = new ModelAndView("elencofatture");
			view.addObject("fatture", fatturaService.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", id + " non trovato.");
		}

	}

	// **** METODI PER LE FATTURE ****
	@GetMapping("/fatture/mostraelenco")
	public ModelAndView mostraElencoFatture() {
		ModelAndView view = new ModelAndView("elencofatture");
		view.addObject("fatture", fatturaService.findAll());
		return view;
	}

	@GetMapping("/fatture/mostraformaggiungi")
	public String mostraFormAggiungiFatture(Cliente cliente, Fattura fattura, Model model) {

		model.addAttribute("listaClienti", clienteService.findAll());

		return "formFattura";
	}

	@PostMapping("fatture/addFattura")
	public String aggiungiFattura(Cliente cliente, Fattura fattura, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("listaFatture", fatturaService.findAll());
			return "formFattura";
		}

		fatturaService.save(fattura);

		return "redirect:/clienti/fatture/mostraelenco";

	}
	
	@GetMapping("/fatture/trovafatturepercliente/{id}")
	public ModelAndView trovaFatturePerCliente(@PathVariable Long id, Model model) {
		List<Fattura> fattura = fatturaService.findByCliente(id);
		if (!fattura.isEmpty()) {
			ModelAndView view = new ModelAndView("elencofatture");
			view.addObject("fatture", fattura);
			return view;
		} else {
			return new ModelAndView("error").addObject("Fattura appartenenti ai clienti con ", id + " non trovate.");
		}
	}



}
