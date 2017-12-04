package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

public class UbicacionDAO {
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
	public Ubicacion leer(String  cedula) {
		em.find(Ubicacion.class, cedula);
		return null;
	}
	public List<Ubicacion> listadoUbicacion()
	{
		//selects contra las entidades mapeadas ubicacion
		String jppql = "SELECT ub FROM Ubicacion ub";
		Query query = em.createQuery(jppql,Ubicacion.class);
		@SuppressWarnings("unchecked")
		List<Ubicacion> listado=query.getResultList();
		return listado;
	}

}
