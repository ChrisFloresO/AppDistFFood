package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Usuario;


@Stateless
public class UsuarioDAO {
	
	@Inject	
	private EntityManager em;
	
	public void Insertar(Usuario u) {
		em.persist(u);	
	}
	public void actualizar(Usuario u) {
		em.merge(u);
	}
	public void borrar(String  cedula) {
		em.remove(leer(cedula));
	}
	public Usuario leer(String  cedula) {
		em.find(Usuario.class, cedula);
		return null;
	}
	public List<Usuario> listadoPersonas()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT p FROM Persona p";
		Query query = em.createQuery(jppql,Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listado =query.getResultList();
		return listado;
	}

}
