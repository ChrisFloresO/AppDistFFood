package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * @author Franklin Villavicencio y Christian Flores
 */

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
	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto pdao
	 * que tiene el metodo guardar que se le pasa el parametro calificacion (objeto de la clase
	 * usuari) y recarga el metodo loadUsuario, retornando un String (Logueo), siendo este
	 * un nombre de un archivo html.
	 * 
	 * @return Lista_Plato
	 */
	public String Guardar(){
			udao.guardar(usuario);
			loadUsuarios();
		return "Logeo";
	}
	
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (cedula)
	 * y nos retornara un String (UsuarioE que es un nombre de una pagina Xhtml
	 * @param cedula
	 * @return
	 */
	public String listadatosEditar(int cedula) 
	{
		usuario = udao.leer(cedula);
		///System.out.println("Cuenca " + usuario);
		return "UsuarioE";
	}
	
	/**
	 * este metodo permite agregar los usuarios a una lista para luego mostrarlos.
	 * @param 
	 * @return
	 */
	public void loadUsuarios() {
		usuarios = udao.listadoUsuario();
	}
	
	/**
	 * Este metodo recibe un parametro (cedula de Usuario)
	 * y este llama al objeto pdao(pdao de la clase UsuarioDao)
	 * y se le pase el parametro cedula y recarga el metodo loadUsuarios
	 * @param cedula
	 */
	public void Borrar(int cedula) {
		udao.borrar(cedula);
		loadUsuarios();
	}
	
	/**
	 * este metodo carga el usuario dado el parametro de correo y clave de inico 
	 * luego hace un for para verificar que tipo de usuario es con el campo rol
	 * este campo rol permitira ver si el usuario que se registra es administrador, empleado o cliente
	 * y retorna un String (RestauranteR, RestauranteL, Inicio, null).
	 * @return
	 */
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
    		 udao.guardar(this.usuario);
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
