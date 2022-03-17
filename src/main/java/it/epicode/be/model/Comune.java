package it.epicode.be.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codiceProvincia;
	private String progressivoComune;
	private String nomeComune;
	@ManyToOne
	private Provincia provincia;
	
	public Comune(String codiceProvincia, String progressivoComune, String nomeComune, Provincia provincia) {
		this.codiceProvincia = codiceProvincia;
		this.progressivoComune = progressivoComune;
		this.nomeComune = nomeComune;
		this.provincia = provincia;
	}

	
	
	
}
