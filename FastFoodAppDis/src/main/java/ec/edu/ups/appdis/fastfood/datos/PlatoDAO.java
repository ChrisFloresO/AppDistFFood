package ec.edu.ups.appdis.fastfood.datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Plato;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class PlatoDAO {
	@Inject
	private EntityManager em;

	public void guardar(Plato plato) {
		Plato pl = leer(plato.getCodigo());
		if (pl == null)
			insertar(plato);
		else
			actualizar(plato);
	}

	public void insertar(Plato plato) {
		em.persist(plato);
	}

	public void actualizar(Plato plato) {
		em.merge(plato);
	}

	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}

	public Plato leer(int codigo) {
		Plato pl = em.find(Plato.class, codigo);
		return pl;
	}

	public List<Plato> listadoPlatos() {
		// selects contra las entidades mapeadas
		String jppql = "SELECT p FROM Plato p";
		Query query = em.createQuery(jppql, Plato.class);
		@SuppressWarnings("unchecked")
		List<Plato> listado = query.getResultList();
		return listado;
	}

	public List<Plato> listadoPlatospr (String nombre) {
		// selects contra las entidades mapeadas select * from tbl_plato p where
		System.out.println(nombre="dao");
		String jppql = "SELECT pl FROM Plato pl where pl.nombre = :nombre";
		Query query = em.createQuery(jppql, Plato.class);
		query.setParameter("nombre", nombre);
		@SuppressWarnings("unchecked")
		List<Plato> listado1 = query.getResultList();
		return listado1;

	}

}