package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Pedido;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class PedidoDAO 
{
	@Inject
	private EntityManager em;
	
	public void guardar(Pedido pedido) {
		Pedido p = leer(pedido.getCodigo());
		if(p==null)
			insertar(pedido);
		else
			actualizar(pedido);
	}
	
	public void insertar (Pedido pedido) {
		em.persist(pedido);
	}
	
	public void actualizar (Pedido pedido) {
		em.merge(pedido);
	}


	public void borrar (int codigo) {
		System.out.println(codigo);
		em.remove(leer(codigo));
	}


	public Pedido leer (int codigo) {
		return em.find(Pedido.class, codigo);
	}
	
	public List<Pedido> listadoPedidos()
	{
		//selects contra las entidades mapeadas
		String jppql = "SELECT p FROM Pedido p";
		Query query = em.createQuery(jppql,Pedido.class);
		@SuppressWarnings("unchecked")
		List<Pedido> listado =query.getResultList();
		return listado;
	}

}