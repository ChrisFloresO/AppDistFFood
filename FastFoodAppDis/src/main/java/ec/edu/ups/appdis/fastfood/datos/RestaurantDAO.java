package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

@Stateless
public class RestaurantDAO {
	
		@Inject	
		private EntityManager em;
		
		public void Insertar(Restaurante r) {
			em.persist(r);	
		}
		public void actualizar(Restaurante r) {
			em.merge(r);
		}
		public void borrar(int codigo) {
			em.remove(leer(codigo));
		}
		public Restaurante leer(int  codigo) {
			em.find(Restaurante.class, codigo);
			return null;
		}	
		public List<Restaurante> listadoRestaurantes()
		{
			//selects contra las entidades mapeadas
			String jppql = "SELECT r FROM Restaurante r";
			Query query = em.createQuery(jppql,Restaurante.class);
			@SuppressWarnings("unchecked")
			List<Restaurante> listado =query.getResultList();
			return listado;
		}

}
