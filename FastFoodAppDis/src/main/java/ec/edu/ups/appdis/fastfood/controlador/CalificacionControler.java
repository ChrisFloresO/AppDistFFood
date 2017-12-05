package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.CalificacionDAO;
import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Calificacion;
import ec.edu.ups.appdis.fastfood.modelo.Detalle;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;

@ManagedBean
public class CalificacionControler 
{
	private Calificacion calificacion;
	private List<Calificacion> calificaciones;
	private int id;

	
	@Inject
	private CalificacionDAO pdao;
	
	@PostConstruct
	public void init() {
		calificacion= new Calificacion();
		//pedido.addDetalle(new Detalle());
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

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}


	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadCalificaciones();
	}
	/*
	public String addDetalle() {
		System.out.println("aqui");
		calificacion.addDetalle(new Detalle());
		return null;
	}
	*/
	public String guardar() 
	{
		System.out.println(calificacion);
		//invoque al DAO y envie la entidad a persistir
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
		
		return "Inicio";
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
	
	
	public String listadatosEditar(int codigo) 
	{
		calificacion = pdao.leer(codigo);
		return "Calificacion";
	}

}
