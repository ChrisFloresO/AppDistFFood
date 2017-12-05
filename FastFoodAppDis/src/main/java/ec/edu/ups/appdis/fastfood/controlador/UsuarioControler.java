package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

@ManagedBean
public class UsuarioControler {
	private String id;
	private List<Usuario> usuarios;
	private String correoI;
	private String claveI;
	private List<Usuario> listadoLogin;
	private String contraseñaA;
	private String contraseñaN;
	
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
	public String listadatosEditar(int codigo) 
	{
		usuario = udao.leer(codigo);
		System.out.println("Cuenca " + usuario);
		
		return "UsuarioR";
	}
	public void loadUsuarios() {
		usuarios = udao.listadoUsuario();
	}
	public void Borrar(int codigo) {
		udao.borrar(codigo);
		loadUsuarios();
	}
	public String listar(){
		listadoLogin = udao.getUsuariosLogin(correoI,claveI);
		for(int i=0;i<listadoLogin.size();i++){
			System.out.println(listadoLogin.get(i).getEmail());
			System.out.println(listadoLogin.get(i).getRol());
			if(listadoLogin.get(i).getRol()==1){
				System.out.println("administrador");
				return "RestauranteR";
			}else
				if(listadoLogin.get(i).getRol()==2){
					return "RestauranteL";
				}else
					if(listadoLogin.get(i).getRol()==3) {
					return "Inicio";
				}
		}
		return null;
	}
	public String perfilUsuario(){
		
		listadoLogin = udao.getUsuariosLogin(correoI,claveI);
		init();
		for(int i=0;i<listadoLogin.size();i++){
			System.out.println(listadoLogin.get(i).getEmail());
			System.out.println(listadoLogin.get(i).getRol());
			
		}
		return "perfil_usuario";
	}
	
 public String contraseñaCambiada(){
    	 
    	 if(getContraseñaA().equals(usuario.getContrasena())){
    		 usuario.setContrasena(getContraseñaN());
    		 udao.Insertar(this.usuario);
    	 }
    	 return "dialogo_clave";
     }
 public String cambiarContraseña(int correo){
		
		Usuario usuario = udao.leer(correo);
		this.usuario  = usuario;
		//System.out.println(this.usuario.getClave());
		return "cambiar_contrasenia";
	}
    public String editarPerfil(int correo){
		Usuario usuario = udao.leer(correo);
		this.usuario  = usuario;
		return "UsuarioE";
	}
    @PreDestroy
	public void close(){
		System.out.println("Cerrando");
	}
	
	public String logout() {
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      return "logueo?faces-redirect=true";
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

	public String getCorreoI() {
		return correoI;
	}

	public void setCorreoI(String correoI) {
		this.correoI = correoI;
	}

	public String getClaveI() {
		return claveI;
	}

	public void setClaveI(String claveI) {
		this.claveI = claveI;
	}

	public String getContraseñaA() {
		return contraseñaA;
	}

	public void setContraseñaA(String contraseñaA) {
		this.contraseñaA = contraseñaA;
	}

	public String getContraseñaN() {
		return contraseñaN;
	}

	public void setContraseñaN(String contraseñaN) {
		this.contraseñaN = contraseñaN;
	}

}
