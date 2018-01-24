package ec.edu.ups.appdis.fastfood.datos;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class UsuarioDAO 
{
	
	@Inject	
	private EntityManager em;
	
	//getters and setters
	
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
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosLogin(String correo,String clave)
	{
		
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? "
				+" AND contrasena = ?";
	
		Query q = em.createQuery(sql,Usuario.class);
		q.setParameter(1, correo);
		q.setParameter(2, clave);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	
    @SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosLoginRC(String correo)
    {
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? ";
		Query q = em.createQuery(sql,Usuario.class);
		q.setParameter(1, correo);
		List<Usuario> personas = q.getResultList();
		return personas;
	}
    
	public boolean ValidarCedula(String cedula)
    {
		Usuario usuario = em.find(Usuario.class, cedula);
		if(usuario == null) {
			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * este metodo permite guardar un plato resiviendo como parametro el plato y verificando que los campos que resiva sena diferentes de nulos.
	 * @param plato
	 */
	public void guardar(Usuario u)
	{
		Usuario u1 = leer(u.getId());
		try {
		if (u1 == null) {
        		insertar(u);
        	
		} 
		else
			actualizar(u);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		}
}