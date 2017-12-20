package ec.ups.edu.appdis.crud.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Plato;

//@Path("/pedidos")
@Path("/crear")
public class CrearService 
{

	@Inject
	 private PlatoDAO dao;

	//get recuperar datos
	//pos mandar a guardar datos o pasar al servidor
	@GET
	@Path("/saludo")
	@Produces("application/json")
	public String saludo(@QueryParam("n") String nombre) 
	{
		return "hola"+nombre;
		
	}
	
	@GET
	@Path("/consultar")
	@Produces("application/json")
	public List<Plato> listado( @QueryParam("usr") String usuario)
	{
		
		List<Plato> listado =dao.listadoPlatospr(usuario);
		return listado;
	    }
}
