package it.epicode.be.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Comune;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.Provincia;
import it.epicode.be.model.TipoCliente;
import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.repository.ComuneRepository;
import it.epicode.be.repository.IndirizzoRepository;
import it.epicode.be.repository.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CaricaDB {

	@Autowired
	ComuneRepository comuneRepo;

	@Autowired
	ProvinciaRepository provinciaRepo;

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	IndirizzoRepository indirizzoRepo;

	public void caricaDB() {
		if (provinciaRepo.findAll().size() == 0) {
			caricaProvince();
			caricaComuni();
			caricaCliente();
			caricaCliente2();
			caricaCliente3();
		}

	}

	public void caricaComuni() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("comuni-italiani.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;

			while ((values = reader.readNext()) != null) {

				Optional<Provincia> provinciaTmp = provinciaRepo.findByNome(values[3]);
				if (provinciaTmp.isPresent()) {
					comuneRepo.save(new Comune(values[0], values[1], values[2], provinciaTmp.get()));
				}

			}
		} catch (FileNotFoundException e) {
			log.error("File non trovato.");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Errore IO.");
			e.printStackTrace();
		}
	}

	public List<Provincia> caricaProvince() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		List<Provincia> listaProv = new ArrayList<>();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("province-italiane.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;

			while ((values = reader.readNext()) != null) {
				Provincia p = new Provincia(values[0], values[1], values[2]);
				provinciaRepo.save(p);
				listaProv.add(p);
			}

		} catch (FileNotFoundException e) {
			log.error("File non trovato.");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Errore IO.");
			e.printStackTrace();
		}
		return listaProv;
	}

	public void caricaCliente() {

		Cliente cliente = new Cliente();
		cliente.setRagioneSociale("Trapani PA");
		cliente.setPartitaIva("12345678901");
		cliente.setEmail("gianni@dd.it");
		cliente.setDataInserimento(LocalDate.of(2001, 10, 10));
		cliente.setDataUltimoContatto(LocalDate.of(2020, 10, 10));
		cliente.setFatturatoAnnuale(120000.00);
		cliente.setPec("giannipec@pec.it");
		cliente.setTelefono("3317345321");
		cliente.setEmailContatto("giannigianni@e.it");
		cliente.setNome("Gianni");
		cliente.setCognome("Percoco");
		cliente.setTelefonoContatto(cliente.getTelefono());
		cliente.setTipoCliente(TipoCliente.PA);
		cliente.setIndirizzoSedeLegale(caricaIndirizzo(5l));
		cliente.setIndirizzoSedeOperativa(caricaIndirizzo(6l));

		clienteRepo.save(cliente);
	}

	public void caricaCliente2() {
		Cliente cliente = new Cliente();
		cliente.setRagioneSociale("Martelli SRL");
		cliente.setPartitaIva("00023678901");
		cliente.setEmail("anto@dd.it");
		cliente.setDataInserimento(LocalDate.of(2015, 10, 10));
		cliente.setDataUltimoContatto(LocalDate.of(2020, 10, 10));
		cliente.setFatturatoAnnuale(160000.00);
		cliente.setPec("antopec@pec.it");
		cliente.setTelefono("3317345321");
		cliente.setEmailContatto("giannigianni@e.it");
		cliente.setNome("Antonio");
		cliente.setCognome("Ciolla");
		cliente.setTelefonoContatto(cliente.getTelefono());
		cliente.setTipoCliente(TipoCliente.SRL);
		cliente.setIndirizzoSedeLegale(caricaIndirizzo(10l));
		cliente.setIndirizzoSedeOperativa(caricaIndirizzo(11l));

		clienteRepo.save(cliente);
	}

	public void caricaCliente3() {
		Cliente cliente = new Cliente();
		cliente.setRagioneSociale("Gommiere SAS");
		cliente.setPartitaIva("00345778901");
		cliente.setEmail("fede@dd.it");
		cliente.setDataInserimento(LocalDate.of(2001, 10, 10));
		cliente.setDataUltimoContatto(LocalDate.of(2022, 01, 10));
		cliente.setFatturatoAnnuale(200000.00);
		cliente.setPec("fedepec@pec.it");
		cliente.setTelefono("3317345321");
		cliente.setEmailContatto("fede@e.it");
		cliente.setNome("Federico");
		cliente.setCognome("Mastro");
		cliente.setTelefonoContatto(cliente.getTelefono());
		cliente.setTipoCliente(TipoCliente.SAS);
		cliente.setIndirizzoSedeLegale(caricaIndirizzo(35l));
		cliente.setIndirizzoSedeOperativa(caricaIndirizzo(36l));

		clienteRepo.save(cliente);
	}

	public Indirizzo caricaIndirizzo(Long id) {
		Indirizzo i = new Indirizzo();
		i.setVia("Via dei cedri");
		i.setCivico("7");
		i.setCap("51017");
		i.setComune(comuneRepo.getById(id));
		// indirizzoRepo.save(i);
		return i;

	}
}
