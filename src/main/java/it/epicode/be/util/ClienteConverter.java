package it.epicode.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.Provincia;
import it.epicode.be.service.ClienteService;
import it.epicode.be.service.IndirizzoService;
import it.epicode.be.service.ProvinciaService;

@Component
public class ClienteConverter implements Converter<Long, Cliente> {

	@Autowired
	ClienteService clienteService;

	

	@Override
	public Cliente convert(Long idCliente) {

		return clienteService.findById(idCliente).get();
	}

}
