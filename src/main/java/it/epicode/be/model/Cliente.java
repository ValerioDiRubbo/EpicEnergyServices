package it.epicode.be.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ragioneSociale;
	private String partitaIva;
	private @Email String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInserimento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	private @Email String pec;
	private String telefono;
	private @Email String emailContatto;
	private String nome;
	private String cognome;
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	
	

	@OneToOne(cascade = CascadeType.ALL )
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Indirizzo indirizzoSedeLegale;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Indirizzo indirizzoSedeOperativa;

	@Override
	public String toString() {
		return ragioneSociale;
	}
	

	
	
}
