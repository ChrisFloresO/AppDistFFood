package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;

@Path("huecas")
public class RestauranteWBSRest {
	
	
	@Inject
	private RestaurantDAO rdao;
	
	@POST
	@Path("/regitrar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta registrarRestaurante(Restaurante res) {
		Respuesta resp = new Respuesta();
		try {
			rdao.guardar(res);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}

	@GET
	@Path("listar")
	@Produces("application/json")
	public List<Restaurante> getRestaurante(){
		return rdao.listadoRestaurantes();
	}
	


}

