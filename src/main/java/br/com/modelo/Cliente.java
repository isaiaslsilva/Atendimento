package br.com.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente", unique = true, nullable = false)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String cpf;
	
	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name = "cliente",
			joinColumns = @JoinColumn(name = "id_cliente"),
			inverseJoinColumns = @JoinColumn(name = "id_atendente"))
	private List<Atendente> atendente;
	

}
