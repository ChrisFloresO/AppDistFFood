package ec.edu.ups.appdis.fastfood.datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
		public void borrar(String  nombre) {
			em.remove(leer(nombre));
		}
		public Restaurante leer(String  nombre) {
			em.find(Restaurante.class, nombre);
			return null;
		}	

}
