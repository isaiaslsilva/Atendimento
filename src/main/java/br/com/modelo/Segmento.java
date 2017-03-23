package br.com.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Segmento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_segmento", unique = true, nullable = false)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String tipo;
	
	@OneToMany(mappedBy = "segmento", fetch = FetchType.LAZY)
	private List<Atendente> atendente;
	

}
