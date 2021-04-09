package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<T> {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
	public T pesquisar(Long id,Class<T> entidade) {
		@SuppressWarnings("unchecked")
		T e = (T) entityManager.find(entidade.getClass(),id);
		
		return e;
	}
	
	public T updateMerge(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		T entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		
		return entidadeSalva;
		
		
	}

}
