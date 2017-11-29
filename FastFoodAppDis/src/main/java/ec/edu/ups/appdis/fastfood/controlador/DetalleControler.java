package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.CalificacionDAO;
import ec.edu.ups.appdis.fastfood.modelo.Calificacion;
import ec.edu.ups.appdis.fastfood.modelo.Detalle;

public class DetalleControler 
{
	private Detalle detalle;
	private List<Detalle> detalles;
	private int id;
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
	private DetalleDAO pdao;
	
	@PostConstruct
	public void init() {
		detalle= new Detalle();
		loadDetalles();
	}
	
	public void loadDetalles() {
		detalles = pdao.listadoDetalles();
	}


	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public DetalleDAO getPdao() {
		return pdao;
	}

	public void setPdao(DetalleDAO pdao) {
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
		loadDetalles();
	}
	
	public String guardar() 
	{
		System.out.println(detalle);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			pdao.guardar(detalle);
			loadDetalles();
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
		detalle = pdao.leer(codigo);
		
		return "Crearpersonaxhtml";
	}

}
