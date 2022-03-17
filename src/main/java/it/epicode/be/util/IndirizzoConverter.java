package it.epicode.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import it.epicode.be.model.Indirizzo;

import it.epicode.be.service.IndirizzoService;

@Component
public class IndirizzoConverter implements Converter<Long, Indirizzo> {

	@Autowired
	IndirizzoService indirizzoService;

	

	@Override
	public Indirizzo convert(Long idIndirizzo) {

		return indirizzoService.findById(idIndirizzo).get();
	}

}
