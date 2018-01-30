package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Calificacion;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class CalificacionDAO 
{
	@Inject
	private EntityManager em;
	
	public void guardar(Calificacion calificacion) {
		Calificacion cal = leer(calificacion.getCodigo());
		if(cal==null)
			insertar(calificacion);
		else
			actualizar(calificacion);
	}
	
	public void insertar (Calificacion calificacion) {
		em.persist(calificacion);
	}
	
	public void actualizar (Calificacion calificacion) {
		em.merge(calificacion);
	}


	public void borrar (int codigo) {
		em.remove(leer(codigo));
	}


	public Calificacion leer (int codigo) {
		Calificacion cal =em.find(Calificacion.class, codigo);
		return cal;	
	}
	
	public List<Calificacion> listadoCalificaciones()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT c FROM Calificacion c";
		Query query = em.createQuery(jppql,Calificacion.class);
		@SuppressWarnings("unchecked")
		List<Calificacion> listado =query.getResultList();
		return listado;
	}

}