package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Detalle;

@Stateless
public class DetalleDAO 
{
	@Inject
	private EntityManager em;
	
	public void guardar(Detalle Detalle) {
		Detalle p = leer(Detalle.getCodigo());
		if(p==null)
			insertar(Detalle);
		else
			actualizar(Detalle);
	}
	
	public void insertar (Detalle Detalle) {
		em.persist(Detalle);
	}
	
	public void actualizar (Detalle Detalle) {
		em.merge(Detalle);
	}


	public void borrar (int codigo) {
		System.out.println(codigo);
		em.remove(leer(codigo));
	}


	public Detalle leer (int codigo) {
		return em.find(Detalle.class, codigo);
	}
	
	public List<Detalle> listadoDetalles()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT d FROM Detalle d";
		Query query = em.createQuery(jppql,Detalle.class);
		@SuppressWarnings("unchecked")
		List<Detalle> listado =query.getResultList();
		return listado;
	}

}