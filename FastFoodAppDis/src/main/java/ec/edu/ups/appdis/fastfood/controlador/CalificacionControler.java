package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.CalificacionDAO;
import ec.edu.ups.appdis.fastfood.modelo.Calificacion;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}

	public CalificacionDAO getPdao() {
		return pdao;
	}

	public void setPdao(CalificacionDAO pdao) {
		this.pdao = pdao;
	}

	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadCalificaciones();
	}
	
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
			/*String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null;*/
		}
		
		return "listado-personas";
	}
	
	public String listadatosEditar(int codigo) 
	{
		calificacion = pdao.leer(codigo);
		
		return "Crearpersonaxhtml";
	}

}
