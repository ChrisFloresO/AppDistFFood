package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

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
	private List<Usuario> usuarios;
	
	
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
	public void loadPersonas() {
		usuarios = udao.listadoPersonas();
	}
	public String listadatosEditar(String cedula) 
	{
		usuario = udao.leer(cedula);
		
		return "Crearpersonaxhtml";
	}

	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
