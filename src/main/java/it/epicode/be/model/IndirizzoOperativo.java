package it.epicode.be.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndirizzoOperativo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String via;
	private String civico;
	private String cap;
	private String localita;
	
	@ManyToOne
	private Comune comune;

	@Override
	public String toString() {
		return   via + ", Civico " + civico + ", CAP " + cap + " Comune " /*+ comune.getNomeComune() + " - ("
				+ comune.getProvincia() + ") "*/ ;
	}
	
	
	
	
}
