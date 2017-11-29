package ec.edu.ups.appdis.fastfood.controlador;

import javax.faces.bean.ManagedBean;

import ec.edu.ups.appdis.fastfood.modelo.Usuario;
@ManagedBean
public class UsuarioControler {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
