package it.epicode.be.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer anno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	private BigDecimal importo;
	//@Column(unique=true)
	private Integer numeroFattura;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	@ManyToOne
	private Cliente cliente;
}
