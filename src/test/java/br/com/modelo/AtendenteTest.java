package br.com.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import br.com.util.Util;

public class AtendenteTest {

	private EntityManager em;

	
	@Test
	public void consultaMaiorId(){
	
		gravaAtendente();
		gravaAtendente();
		gravaAtendente();
			
		Criteria criteria = createCriteria(Atendente.class, "a").setProjection(Projections.max("a.id"));
		
		Long maxId = (Long) criteria.setResultTransformer(Criteria.PROJECTION).uniqueResult();
		
		assertTrue("Verificar se é o maior Id", maxId >=3);
		
		
	}
		
	private Criteria createCriteria(Class<Atendente> class1, String string) {
	
		return null;
	}

	@Test
	public void consultaAtendente() {
		gravaAtendente();
		gravaAtendente();
		gravaAtendente();
		gravaAtendente();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT COUNT (a.id) ");
		jpql.append(" FROM Atendente a ");
		jpql.append(" WHERE a.matricula = :matricula ");

		Query query = em.createQuery(jpql.toString());
		query.setParameter("matricula", "000001");

		Long qtdAtend = (Long) query.getSingleResult();

		assertEquals("quantidade de produtos deve ser igual a quantidade de lista de produtos", qtdAtend.intValue());

	}

	@Test
	public void gravaAtendente() {
		Atendente atendentes = new Atendente();
		atendentes.setNome("Isaias Silva");
		atendentes.setMatricula(000001);
		atendentes.setSegmento("SAC");

		// assertTrue("entidade não tem ID ainda", atendentes.isTransient());

		em.getTransaction().begin();
		em.persist(atendentes);
		em.getTransaction().commit();

		// assertFalse("entidade agora tem ID", atendentes.isTransient());

	}

	@Before
	public void instanciarEntityManager() {
		em = Util.INSTANCE.getEntityManager();
	}

	@After
	public void fecharEntityManager() {

		if (em.isOpen()) {
			em.close();
		}
	}

	@AfterClass
	public static void deveLimparBase() {
		EntityManager entityManager = Util.INSTANCE.getEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("DELETE FROM Atendente a");
		int registrosExcluidos = query.executeUpdate();

		entityManager.getTransaction().commit();

		assertTrue("deve ter excluido registros", registrosExcluidos > 0);
	}

}
