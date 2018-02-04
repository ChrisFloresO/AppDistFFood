package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;

/**
 * 
 */

@Path("platos")
public class PlatosWBSRest 
{
	private Restaurante restaurante;
	
	@Inject
	private PlatoDAO pdao;
	
	@Inject
	private RestaurantDAO rdao;
	
	@Inject
	private PedidoDAO pedao;
	
	@POST
	@Path("/registrar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta registrarPlato(@FormParam("codigo") int codigo,Plato p) {
		Respuesta resp = new Respuesta();
		try {
			restaurante = rdao.leer(codigo);
			p.setRestaurante(restaurante);
			System.out.println(p.getRestaurante().getCodigo());
			pdao.guardar(p);
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
	public List<Pedido> getPedidos(){
		return pedao.listadoPedidos();
	}
	
	@GET
	@Path("/listarp")
	@Produces("application/json")
	public List<Plato> getPlatos(){		 
		List<Plato> lista = pdao.listadoPlatos();
		for (Plato plato : lista) {
			plato.setCalificaciones(null);
			plato.setPedidos(null);	
		}
		return lista;
	}
	
	
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public List<Plato> getPlatosB(@QueryParam("nombre") String nombre){
		return pdao.listadoPlatospr(nombre);
	}
	
	
}
