package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Calificacion;

@Stateless
public class CalificacionDAO 
{
	@Inject
	private EntityManager em;
	
	public void guardar(Calificacion calificacion) {
		Calificacion c = leer(calificacion.getCodigo());
		if(c==null)
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
		Calificacion c =em.find(Calificacion.class, codigo);
		
		return c;	
	}
	
	public List<Calificacion> listadoCalificaciones()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT c FROM Calificaciones c";
		Query query = em.createQuery(jppql,Calificacion.class);
		@SuppressWarnings("unchecked")
		List<Calificacion> listado =query.getResultList();
		return listado;
	}


}
