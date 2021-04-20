package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TestHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(20);
		pessoa.setEmail("dori@gmail.com");
		pessoa.setLogin("doritus");
		pessoa.setNome("Doriedson");
		pessoa.setSobrenome("Maranhão");
		pessoa.setSenha("320222");
		
		daoGenerico.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);
		pessoa = daoGenerico.pesquisar(pessoa);
		
		
		
		
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGenerico.pesquisar(pessoa);
		pessoa.setIdade(99);
		pessoa.setNome("Doriscleidson");
		
		pessoa = daoGenerico.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGenerico.pesquisar(pessoa);
		
		daoGenerico.deletarPorId(pessoa);
		
		
	}
	
	@Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGenerico.listar(UsuarioPessoa.class);
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------");
		}
		
		
	}
	
	@Test
	public void testeQueryList() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGenerico.getEntityManager().createQuery("from UsuarioPessoa ").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			
		}
		
	}
	
	
	@Test
	public void testeQueryListMaxResult() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGenerico.getEntityManager().
				createQuery("from UsuarioPessoa order by id ")
				.setMaxResults(1)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
			
		}
		
	}
	
	@Test
	public void testeQueryListParameter() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGenerico.
				getEntityManager().createQuery("from UsuarioPessoa where nome = :nome")
				.setParameter("nome","Doriedson").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
				
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
		
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		
		Double somaIdade = (Double) daoGenerico.getEntityManager().
				createQuery(" select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println(" A soma de todas as idades é ---> " + somaIdade);
		
	}
	
	@Test
	public void testeNamedQuery() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGenerico.getEntityManager().
				createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGenerico.getEntityManager().
				createNamedQuery("UsuarioPessoa.buscaPorNome").
				setParameter("nome", "Doriedson")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeGravarTelefone() {
		DaoGeneric daoGenerico = new DaoGeneric();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(3L);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("casa");
		telefoneUser.setNumero("987123518");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGenerico.salvar(telefoneUser);
		
		}
	
	@Test
	public void testeConsultaTelefone() {
		DaoGeneric daoGenerico = new DaoGeneric();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(3L);
		pessoa = (UsuarioPessoa) daoGenerico.pesquisar(pessoa);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		for (TelefoneUser fone : pessoa.getTelefoneUser()) {
			
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("------------------------------------");
			
		}
		
		
		}

}
