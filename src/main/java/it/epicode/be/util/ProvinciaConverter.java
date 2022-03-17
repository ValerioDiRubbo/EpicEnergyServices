package it.epicode.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.Provincia;
import it.epicode.be.service.IndirizzoService;
import it.epicode.be.service.ProvinciaService;

@Component
public class ProvinciaConverter implements Converter<Long, Provincia> {

	@Autowired
	ProvinciaService provinciaService;

	

	@Override
	public Provincia convert(Long idIndirizzo) {

		return provinciaService.findById(idIndirizzo).get();
	}

}
