package ec.edu.ups.appdis.fastfood.datos;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;


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
}
