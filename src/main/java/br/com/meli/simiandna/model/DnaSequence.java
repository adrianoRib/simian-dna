package br.com.meli.simiandna.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="dna")
@Data @EqualsAndHashCode @NoArgsConstructor @ToString
public class DnaSequence implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public DnaSequence(Integer dnaType, String dna) {
		super();
		this.dnaType = dnaType;
		this.dna = dna;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_dna_type", nullable = false, length = 20)
	private Integer dnaType;
	
	@Column(name = "dna_sequence", nullable = false, length = 255)
	private String dna;
	

}
