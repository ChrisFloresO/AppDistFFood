package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Plato;

/**
 * 
 */

@Path("platos")
public class PlatosWBSRest {
	
	@Inject
	private PlatoDAO pdao;
	
	@POST
	@Path("/regitrar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta registrarPlato(Plato pla) {
		Respuesta resp = new Respuesta();
		try {
			pdao.guardar(pla);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}
	
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Plato> getPlatos(){
		return pdao.listadoPlatos();
	}
	
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public List<Plato> getPlatosB(@QueryParam("nombre") String nombre){
		return pdao.listadoPlatospr(nombre);
	}
	
	
}
