package ec.edu.ups.appdis.fastfood.controlador;

import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.crud.util.FCM;
import ec.edu.ups.appdis.fastfood.crud.util.Validar;
import ec.edu.ups.appdis.fastfood.datos.CalificacionDAO;
import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.datos.PrediccionesDao;
import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Calificacion;
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Predicciones;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * @author Franklin Villavicencio y Christian Flores
 */

@ManagedBean
@SessionScoped
public class UsuarioControler 
{
	private int codigo;
	private Validar v;
	private String id;
	private List<Usuario> usuarios;
	private String correoI;
	private String claveI;
	private List<Usuario> listadoLogin;
	private String contraseñaA;
	private String contraseñaN;
	private List<Plato> platos;
	private List<Calificacion> calificaciones;
	
	@Inject
	private CalificacionDAO cdao;
	
	@Inject
	private PrediccionesDao prdao;
	
	@Inject
	private PlatoDAO pdao;
	
	@Inject
	private UsuarioDAO udao;
	
	@Inject
	private Sesion sesion;
	
	
	private Usuario usuario;
	
	
	@PostConstruct		
	public void init() {
		usuario=new Usuario();
		loadUsuarios();
		v = new Validar();
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
	@SuppressWarnings("static-access")
	public String Guardar()
	{
		boolean t =v.validacionCedula(usuario.getCedula());
		System.out.println(t+"y");
		if(t==false) {
			return "Logeo";
		} else 
		{
			usuario.setRol(3);
			udao.guardar(usuario);
			sesion.setUsuario(usuario);
			loadUsuarios();
			return "Encuesta";
		}
	}
	
	@SuppressWarnings("static-access")
	public String Guardar1()
	{
			usuario.setRol(2);
			udao.guardar(usuario);
			loadUsuarios();
			return "UsuarioL";
		
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
		System.out.println("Cuenca " + usuario.getNombre());
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
	
	public void loadPlatos() {
		platos = pdao.listadoPlatos();
	}
	
	public void loadCalificacion() {
		calificaciones = cdao.listadoCalificaciones();
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
	@SuppressWarnings({"rawtypes" })
	public String listar(){
		
		usuarios = udao.listadoUsuario();
		listadoLogin = udao.getUsuariosLogin(correoI,claveI);
		calificaciones = cdao.listadoCalificaciones();
		platos =pdao.listadoPlatos();
		
		
		for(int i=0;i<listadoLogin.size();i++){
			System.out.println(listadoLogin.get(i).getEmail());
			System.out.println(listadoLogin.get(i).getRol());
			codigo = listadoLogin.get(i).getId();
			System.out.println(codigo);
			if(listadoLogin.get(i).getRol()==1){
				System.out.println("administrador");
				return "RestauranteR";
			}else
				if(listadoLogin.get(i).getRol()==2){
					sesion.setUsuario(listadoLogin.get(i));
					return "Lista_Plato";
				}else
					if(listadoLogin.get(i).getRol()==3) 
					{
						int c = 1;
						sesion.setUsuario(listadoLogin.get(i));
						FCM fcm = new FCM(calificaciones, usuarios, platos);
						Hashtable predicciones=  fcm.prediccionesUsuarios();
						Predicciones pre;
						List<Predicciones> listapredcciones = prdao.listadoPredicciones();
						for (int k = 0; k < listapredcciones.size(); k++) 
						{
							System.out.println("1"+listapredcciones.get(k));
							prdao.Borrar(listapredcciones.get(k).getId());
						}
						for (int k = 0; k < predicciones.size(); k++) 
						{
							int us = usuarios.get(k).getId();
							Hashtable voto = (Hashtable) predicciones.get(us);
							for (int j = 0; j < calificaciones.size(); j++) {
								int plat = platos.get(j).getCodigo();
								byte[] imagen = platos.get(j).getImagen();
								if ((int)voto.get(plat)!=0) {
									pre = new Predicciones();
									pre.setId(c);
									pre.setItem(plat);
									pre.setUsuario(us);
									pre.setImagen(imagen);
									pre.setPrediccion((int) voto.get(plat));
									prdao.Insertar(pre);
									c++;
									System.out.println("hola"+c);
								}
								
							}
							
						}
					return "Home";
					
				}
		}
		return null;
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

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
	}

}