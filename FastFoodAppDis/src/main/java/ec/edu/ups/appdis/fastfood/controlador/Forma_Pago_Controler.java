package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.Forma_Pago_DAO;
import ec.edu.ups.appdis.fastfood.modelo.Forma_Pago;

@ManagedBean
public class Forma_Pago_Controler {

	private Forma_Pago forma_p;
	private List<Forma_Pago> forma_ps;
	private int id;
	
	
	
	@Inject
	private Forma_Pago_DAO fpdao;
	
	@PostConstruct
	public void init() {
		forma_p= new Forma_Pago();
		loadForma_Pagos();
	}
	
	public Forma_Pago getForma_p() {
		return forma_p;
	}

	public void setForma_p(Forma_Pago forma_p) {
		this.forma_p = forma_p;
	}

	public List<Forma_Pago> getForma_ps() {
		return forma_ps;
	}

	public void setForma_ps(List<Forma_Pago> forma_ps) {
		this.forma_ps = forma_ps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}
	
	public void loadForma_Pagos() {
		forma_ps = fpdao.listadoForma_Pagos();
	}
	

	public void Boorar(int cedula) {
		fpdao.borrar(cedula);
		loadForma_Pagos();
	}
	
	public String guardar() 
	{
		System.out.println(forma_p);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			fpdao.guardar(forma_p);
			loadForma_Pagos();
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            //facesContext.addMessage(null, m);
            return null;
		}
		
		return "listado-personas";
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
		forma_p = fpdao.leer(codigo);
		
		return "Crearpersonaxhtml";
	}
	
}
