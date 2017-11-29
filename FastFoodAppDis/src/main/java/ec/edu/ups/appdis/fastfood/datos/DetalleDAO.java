package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Detalle;


@Stateless
public class DetalleDAO {

	@Inject
	private EntityManager em;
	
	public void guardar(Detalle detalle) {
		Detalle c = leer(detalle.getCodigo());
		if(c==null)
			insertar(detalle);
		else
			actualizar(detalle);
	}
	
	public void insertar (Detalle detalle) {
		em.persist(detalle);
	}
	
	public void actualizar (Detalle detalle) {
		em.merge(detalle);
	}


	public void borrar (int codigo) {
		em.remove(leer(codigo));
	}


	public Detalle leer (int codigo) {
		Detalle d =em.find(Detalle.class, codigo);
		
		return d;	
	}
	
	public List<Detalle> listadoDetalle()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT d FROM Detalle d";
		Query query = em.createQuery(jppql,Detalle.class);
		@SuppressWarnings("unchecked")
		List<Detalle> listado =query.getResultList();
		return listado;
	}


}
