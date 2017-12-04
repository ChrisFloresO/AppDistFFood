package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

@ManagedBean
public class UsuarioControler {
	private String id;
	private List<Usuario> usuarios;
	
	@Inject
	private UsuarioDAO udao;
	
	private Usuario usuario;
	
	
	@PostConstruct		
	public void init() {
		usuario=new Usuario();
		loadUsuarios();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/*
	 * metodos para crud
	 */
	public String Guardar(){
			udao.Insertar(usuario);
		return null;
	}
	public String listadatosEditar(String cedula) 
	{
		usuario = udao.leer(cedula);
		
		return "UsuarioR";
	}
	public void loadUsuarios() {
		usuarios = udao.listadoUsuario();
	}
	//metodo para borrar
	public void Borrar(String cedula) {
		udao.borrar(cedula);
		loadUsuarios();
	}
	
	/*
	 * getters and setters
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
