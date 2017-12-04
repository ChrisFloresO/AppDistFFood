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
	public void Insertar(Ubicacion ub) {
		em.persist(ub);	
	}
	public void actualizar(Ubicacion ub) {
		em.merge(ub);
	}
	public void borrar(String  codigo) {
		em.remove(leer(codigo));
	}
	public Ubicacion leer(String  codigo) {
		em.find(Ubicacion.class, codigo);
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
