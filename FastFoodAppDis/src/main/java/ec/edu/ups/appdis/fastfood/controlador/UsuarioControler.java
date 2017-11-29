package ec.edu.ups.appdis.fastfood.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;


@ManagedBean
public class UsuarioControler {
	
	@Inject
	private UsuarioDAO udao;
	
	private Usuario usuario;
	
	
	@PostConstruct		
	public void init() {
		usuario=new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String Guardar(){
		
		//invoque al DAO
			udao.Insertar(usuario);
		return null;
	}

}
