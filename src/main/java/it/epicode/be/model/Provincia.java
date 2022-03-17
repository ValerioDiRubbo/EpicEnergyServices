package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sigla;
	private String regione;
	private String nome;

	public Provincia(String sigla, String provincia, String regione) {
		this.sigla = sigla;
		this.nome = provincia;
		this.regione = regione;
	}

	@Override
	public String toString() {
		return "" + sigla + "";
	}

	
}
