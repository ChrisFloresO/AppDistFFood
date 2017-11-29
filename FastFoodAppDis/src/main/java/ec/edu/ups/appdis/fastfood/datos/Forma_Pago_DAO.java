package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Forma_Pago;


@Stateless
public class Forma_Pago_DAO {
	
	@Inject
	private EntityManager em;
	
	public void guardar(Forma_Pago forma_p) {
		Forma_Pago p = leer(forma_p.getCodigo());
		if(p==null)
			insertar(forma_p);
		else
			actualizar(forma_p);
	}
	
	public void insertar (Forma_Pago forma_p) {
		em.persist(forma_p);
	}
	
	public void actualizar (Forma_Pago forma_p) {
		em.merge(forma_p);
	}


	public void borrar (int codigo) {
		em.remove(leer(codigo));
	}


	public Forma_Pago leer (int codigo) {
		Forma_Pago fp =em.find(Forma_Pago.class, codigo);
		
		return fp;	
	}
	
	public List<Forma_Pago> listadoForma_Pagos()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT fp FROM Forma_Pago fp";
		Query query = em.createQuery(jppql,Forma_Pago.class);
		@SuppressWarnings("unchecked")
		List<Forma_Pago> listado =query.getResultList();
		return listado;
	}

}
