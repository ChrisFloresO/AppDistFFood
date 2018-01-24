package ec.edu.ups.appdis.fastfood.datos;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.fastfood.modelo.Predicciones;


@Stateless
public class PrediccionesDao {

	
	@Inject
	private EntityManager EM;
	
	public void Insertar(Predicciones pre) {
		EM.persist(pre);
	}
	public void Actualizar(Predicciones pre) {
		EM.merge(pre);
	}
	public void Borrar(Predicciones pred) {
		EM.remove(pred);
	}
	//este metodo nos sirve para buscar una Marca en base a un id
	public Predicciones Leer(int id) {
		Predicciones centroide = EM.find(Predicciones.class, id);
		return centroide;
	}
	//Este metodo nos sirve para hacer un select y obtener todo las marcas
	public List<Predicciones> listadoPredicciones() {
		String  jpql = "SELECT pre FROM Predicciones pre";
		Query query = EM.createQuery(jpql, Predicciones.class);
		List<Predicciones> predicciones= query.getResultList(); 
		return predicciones;
	}
	
	//Este metodo nos sirve para hacer un select y obtener todo las marcas
	public List<Predicciones> listPrediccionesUsua(int idUsuario) {
		String  jpql = "SELECT pre FROM Predicciones pre WHERE pre.usuario= :idUsuario";
		Query query = EM.createQuery(jpql, Predicciones.class);
		query.setParameter("idUsuario", idUsuario);
		
		List<Predicciones> predicciones= query.getResultList(); 
		return predicciones;
	}
	
}