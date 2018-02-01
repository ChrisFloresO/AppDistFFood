package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.edu.ups.appdis.fastfood.datos.PrediccionesDao;
import ec.edu.ups.appdis.fastfood.modelo.Predicciones;

@Path("recomendado")
public class RecomendadoWBSRest 
{
	@Inject
	private PrediccionesDao predao;
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Predicciones> getPredicciones(){
		return predao.listadoPredicciones();
	}
	

}
