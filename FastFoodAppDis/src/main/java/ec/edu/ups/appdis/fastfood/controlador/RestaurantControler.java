package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@ManagedBean
@ViewScoped
public class RestaurantControler {
	private Restaurante restaurante;
	private List<Restaurante> restaurantes;
	private int id;
	

	@Inject
	private RestaurantDAO rdao;
	
	@Inject
    private FacesContext facesContext;
	
	
	@PostConstruct		
	public void init() {
		restaurante= new Restaurante();
		restaurante.addUbicacion(new Ubicacion());
		loadRestaurantes();
	}

	public Restaurante getUsuario() {
		return restaurante;
	}
	

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public void loadRestaurantes() {
		restaurantes = rdao.listadoRestaurantes();
	}
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	public int getId() {
		return id;
	}
	public String listadatosEditar(int codigo) 
	{
		
		restaurante = rdao.leer(codigo);
		System.out.println("Restaurante " + restaurante);
		
		return "RestauranteE";
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}
	public void Borrar(int codigo) {
		rdao.borrar(codigo);
		loadRestaurantes();
	}
	
	public String guardar() 
	{
		//System.out.println(restaurante);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			rdao.guardar(restaurante);
			
			loadRestaurantes();
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null;
		}
		
		return "RestauranteL";
	}
	public String addUbicacione(){
		System.out.println("Aqui paso");
		restaurante.addUbicacion(new Ubicacion());
		return null;
	}
	
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
	
}
