package ec.edu.ups.appdis.fastfood.controlador;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.crud.util.FCM;
import ec.edu.ups.appdis.fastfood.datos.CalificacionDAO;
import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.datos.PrediccionesDao;
import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Calificacion;
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Predicciones;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@ManagedBean
public class CalificacionControler 
{

	private Calificacion calificacion;
	private List<Calificacion> calificaciones;
	private List<Usuario> usuarios;
	private List<Plato> platos;
	private int id;
	private int codigop;
	private int codigou;
	private Usuario usuario;
	private Plato plato;
	private String nplatos;
	private String n1;
	private String n2;
	private String n3;
	private String n4;
	private String n5;
	private String n6;
	
	@Inject
	private UsuarioDAO udao;
	
	@Inject
	private Sesion sesion;
	
	
	@Inject
	private PlatoDAO padao;
	
	@Inject
	private PrediccionesDao prdao;
	
	@Inject
	private CalificacionDAO pdao;
	
	@PostConstruct
	public void init() {
		calificacion= new Calificacion();
		plato = new Plato();
		loadCalificaciones();
	}
	
	public void loadCalificaciones() {
		calificaciones = pdao.listadoCalificaciones();
	}
	

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public void setPdao(CalificacionDAO pdao) {
		this.pdao = pdao;
	}

	public int getId() {
		return id;
	}

	public int getCodigop() {
		return codigop;
	}

	public void setCodigop(int codigop) {
		this.codigop = codigop;
	}

	public int getCodigou() {
		return codigou;
	}

	public void setCodigou(int codigou) {
		this.codigou = codigou;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public UsuarioDAO getUdao() {
		return udao;
	}

	public void setUdao(UsuarioDAO udao) {
		this.udao = udao;
	}

	public PlatoDAO getPadao() {
		return padao;
	}

	public void setPadao(PlatoDAO padao) {
		this.padao = padao;
	}

	public CalificacionDAO getPdao() {
		return pdao;
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}

	public String getNplatos() {
		return nplatos;
	}

	public void setNplatos(String nplatos) {
		this.nplatos = nplatos;
	}

	public String getN1() {
		return n1;
	}

	public void setN1(String n1) {
		this.n1 = n1;
	}

	public String getN2() {
		return n2;
	}

	public void setN2(String n2) {
		this.n2 = n2;
	}

	public String getN3() {
		return n3;
	}

	public void setN3(String n3) {
		this.n3 = n3;
	}

	public String getN4() {
		return n4;
	}

	public void setN4(String n4) {
		this.n4 = n4;
	}

	public String getN5() {
		return n5;
	}

	public void setN5(String n5) {
		this.n5 = n5;
	}

	public String getN6() {
		return n6;
	}

	public void setN6(String n6) {
		this.n6 = n6;
	}

	public List<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	/**
	 * Este metodo recibe un parametro (codigo de la calificiacion)
	 * y este llama al objeto pdao(pdao de la clase CalificacionDao)
	 * y se le pase el parametro codigo y recarga el metodo loadCalificaciones
	 * @param codigo
	 */
	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadCalificaciones();
	}
	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto pdao
	 * que tiene el metodo guardar que se le pasa el parametro calificacion (objeto de la clase
	 * calificacion) y recarga el metodo loadCalificaciones, retornando un String (Inico), siendo este
	 * un nombre de un archivo html.
	 * 
	 * @return Inicio
	 */
	public String guardar() 
	{
		usuario = sesion.getUsuario();
		plato = sesion.getPlato();
		System.out.println(usuario.getNombre());
		calificacion.setPlato(plato);
		calificacion.setUsuario(usuario);
		try 
		{
			pdao.guardar(calificacion);
			loadCalificaciones();
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
          //  facesContext.addMessage(null, m);
            return null;
		}
		
		return "Buscar";
	}
	
	/**
	 * este metodo se genera cuando al invocar el metodo guardar no se puede guardar;
	 * este metodo nos mostrara la causa por que no se guardo
	 * reciviendo un parametro de excepcion (e) y retornara un String con la informacion del error
	 * @param e
	 * @return
	 */
	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (codigo)
	 * y nos retornara un String (Calificacion) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	
	public String listadatosEditar(int codigo) 
	{
		calificacion = pdao.leer(codigo);
		return "Calificacion";
	}
	
	public String encuesta() 
	{
		System.out.println(n1);
		plato=padao.leer(Integer.parseInt(n1));
		//platos = padao.listadoPlatospr(n1);
		System.out.println(plato.getNombre());
		usuario = sesion.getUsuario();
		System.out.println(usuario.getNombre());
		calificacion.setPlato(plato);
		calificacion.setUsuario(usuario);
		calificacion.setVoto(5);
		calificacion.setComentario("muy buena");
		try 
		{
			usuarios = udao.listadoUsuario();
			pdao.guardar(calificacion);
			loadCalificaciones();
			
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
          //  facesContext.addMessage(null, m);
            return null;
		}
		
		return "Logeo";
		
		
	}

}