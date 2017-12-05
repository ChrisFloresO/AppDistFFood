package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Plato;

@ManagedBean
public class PlatoControler 
{
	private Plato plato;
	private List<Plato> platos;
	private int id;

	
	@Inject
	private PlatoDAO pdao;
	
	@PostConstruct
	public void init() {
		plato= new Plato();
		//plato.addDetalle(new Detalle());
		loadPlatos();
	}
	
	public void loadPlatos() {
		platos = pdao.listadoPlatos();
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}


	public List<Plato> getPlatos() {
		return platos;
	}


	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}

	public PlatoDAO getPdao() {
		return pdao;
	}

	public void setPdao(PlatoDAO pdao) {
		this.pdao = pdao;
	}

	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadPlatos();
	}
	
	/*public String addDetalle() {
		System.out.println("aqui");
		pedido.addDetalle(new Detalle());
		return null;
	}
	*/
	public String guardar() 
	{
		System.out.println(plato);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			pdao.guardar(plato);
			loadPlatos();
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
          //  facesContext.addMessage(null, m);
            return null;
		}
		
		return "Lista_Plato";
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
		plato = pdao.leer(codigo);
		return "Editar_Plato";
	}
	
	public String listadatosB(int codigo) 
	{
		plato = pdao.leer(codigo);
		return "Pedido";
	}
	
	
	

}
