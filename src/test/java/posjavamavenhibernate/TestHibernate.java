package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TestHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setEmail("allankleber83@gmail.com");
		pessoa.setLogin("allankm1983");
		pessoa.setNome("Allan Kleber");
		pessoa.setSobrenome("Maranhão");
		pessoa.setSenha("320222");
		
		daoGenerico.salvar(pessoa);
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGenerico.pesquisar(1l, UsuarioPessoa.class);
		
		
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGenerico = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGenerico.pesquisar(1l, UsuarioPessoa.class);
		pessoa.setIdade(99);
		pessoa.setNome("Doriscleidson");
		
		pessoa = daoGenerico.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}

}
