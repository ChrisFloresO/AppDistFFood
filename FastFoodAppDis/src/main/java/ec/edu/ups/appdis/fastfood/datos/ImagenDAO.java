package ec.edu.ups.appdis.fastfood.datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.appdis.fastfood.modelo.Imagenes;

@Stateless
public class ImagenDAO {

	@Inject
	private EntityManager em;
	
	public void save(Imagenes archivo){
		if(em.find(Imagenes.class,archivo.getId())==null)
			insertar(archivo);
		else
			actualizar(archivo);
	}
	
	public void insertar(Imagenes archivo){
		em.persist(archivo);
	}
	
	public void actualizar(Imagenes archivo){
		em.merge(archivo);
	}
	
}
