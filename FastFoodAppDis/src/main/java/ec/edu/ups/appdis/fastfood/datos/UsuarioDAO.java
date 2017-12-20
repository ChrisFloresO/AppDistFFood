package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class UsuarioDAO {
	
	@Inject	
	private EntityManager em;
	
	public void guardar(Usuario u) {
		Usuario u1 = leer(u.getId());
		if(u1==null)
			insertar(u);
		else
			actualizar(u);
			
	}
	public void insertar (Usuario u) {
		em.persist(u);
	}
	
	public void actualizar(Usuario u) {
		em.merge(u);
	}
	public void borrar(int id) {
		em.remove(leer(id));
	}
	public Usuario leer(int id) {
		
		return em.find(Usuario.class, id);
	}

	public List<Usuario> listadoUsuario()
	{
		//selects  entidades mapeadas usuario
		String jppql = "SELECT u FROM Usuario u";
		Query query = em.createQuery(jppql,Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listado =query.getResultList();
		return listado;
	}
public List<Usuario> getUsuariosLogin(String correo,String clave){
		
		
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? "
				+" AND contrasena = ?";
	
	Query q = em.createQuery(sql,Usuario.class);
	q.setParameter(1, correo);
	q.setParameter(2, clave);
	List<Usuario> personas = q.getResultList();
	return personas;
	}
	
    public List<Usuario> getUsuariosLoginRC(String correo){
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? ";
	Query q = em.createQuery(sql,Usuario.class);
	q.setParameter(1, correo);
	List<Usuario> personas = q.getResultList();
	return personas;
	}
    

}