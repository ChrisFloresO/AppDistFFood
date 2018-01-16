package ec.edu.ups.appdis.fastfood.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * 
 * @author frn14k
 *
 */

@Path("/usuarios")
public class UsuarioWBSRest 
{
	
	@Inject
	private UsuarioDAO udao;
	
	@POST
	@Path("/guardar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta resgitrarUsuario(Usuario usu) {
		Respuesta resp = new Respuesta();
		try {
			udao.insertar(usu);
			resp.setCodigo(1);
			resp.setMensaje("Registro satisfactorio");
		}catch(Exception e) {
			resp.setCodigo(-1);
			resp.setMensaje("Error en registro");
		}			
		return resp;
	}
	
	
	@GET
	@Path("/perfil")
	@Produces("application/json")
	public List<Usuario> getUsuario (@QueryParam("correo") String correo,@QueryParam("clave") String clave){
		return udao.getUsuariosLogin(correo, clave);
	}
	


}
