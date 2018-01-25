package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

@Path("pedidos")
public class OrdenWBSRest 
{
	private Usuario usuario;
	private Plato plato;
	
	@Inject
	private PlatoDAO pdao;
	
	@Inject
	private UsuarioDAO udao;
	
	@Inject
	private PedidoDAO pedao;
	
	
	@POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta registrarPedido(@FormParam("codigo") int codigo,Plato plato) {
		Respuesta resp = new Respuesta();
		try {
			Pedido p = new Pedido();
			System.out.println("aqui");
			usuario = udao.leer(codigo);
			p.setPlato(plato);
			p.setUsuario(usuario);
			pedao.insertar(p);
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
	public List<Pedido> getPedido(){
		return pedao.listadoPedidos();
	}
	

}
