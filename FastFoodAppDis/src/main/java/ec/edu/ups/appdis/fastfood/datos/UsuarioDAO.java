package ec.edu.ups.appdis.fastfood.datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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

}
