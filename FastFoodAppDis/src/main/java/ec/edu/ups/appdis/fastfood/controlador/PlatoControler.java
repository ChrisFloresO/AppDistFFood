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
	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto pdao
	 * que tiene el metodo guardar que se le pasa el parametro calificacion (objeto de la clase
	 * plato) y recarga el metodo loadCalificaciones, retornando un String (Lista_Plato), siendo este
	 * un nombre de un archivo html.
	 * 
	 * @return Lista_Plato
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
	 * y nos retornara un String (Editar_Plato) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	public String listadatosEditar(int codigo) 
	{
		plato = pdao.leer(codigo);
		return "Editar_Plato";
	}
	
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (codigo)
	 * y nos retornara un String (Pedido) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	public String listadatosB(int codigo) 
	{
		plato = pdao.leer(codigo);
		return "Pedido";
	}
	
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (codigo)
	 * y nos retornara un String (Calificacion) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	public String listadatosC(int codigo) 
	{
		plato = pdao.leer(codigo);
		return "Calificacion";
	}
	
	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda (codigo)
	 * y nos retornara un String (Calificacion) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	public String listadatosU() 
	{
		//plato = pdao.leer(codigo);
		return "UbicacionP";
	}
	
	
}
