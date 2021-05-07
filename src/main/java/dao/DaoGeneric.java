package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<T> {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
	public T pesquisar(T entidade) {
		
		Object id = HibernateUtil.getPrimarykey(entidade);
		
		@SuppressWarnings("unchecked")
		T e = (T) entityManager.find(entidade.getClass(), id);
		
		return e;
	}
	
	public T updateMerge(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		T entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		
		return entidadeSalva;
		
		
	}
	
	public void deletarPorId(T entidade) throws Exception {
		Object id = HibernateUtil.getPrimarykey(entidade);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.createNativeQuery(" delete from " + 
		entidade.getClass().getSimpleName().toLowerCase()+ " where id = "+ id).executeUpdate();
		transaction.commit();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar (Class<T> entidade){
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<T> lista = entityManager.createQuery(" from " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
