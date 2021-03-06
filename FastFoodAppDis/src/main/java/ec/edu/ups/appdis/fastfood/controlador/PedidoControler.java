package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;

/**
 * @author Franklin Villavicencio y Christian Flores
 */

@ManagedBean
public class PedidoControler 
{
	private Pedido pedido;
	private List<Pedido> pedidos;
	private int id;
	private double cantidad;


	@Inject
	private PedidoDAO pdao;
	
	@PostConstruct
	public void init() {
		pedido= new Pedido();
		loadPedidos();
		cantidad();
	}
	
	public void loadPedidos() {
		pedidos = pdao.listadoPedidos();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		listadatosEditar(id);
	}

	public PedidoDAO getPdao() {
		return pdao;
	}

	public void setPdao(PedidoDAO pdao) {
		this.pdao = pdao;
	}

	public void Borrar(int codigo) {
		System.out.println("aqui en el borrar"+codigo);
		pdao.borrar(codigo);
		loadPedidos();
	}
	
	
	public void name() {
		System.out.println("aqui");
	}
	

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto pdao
	 * que tiene el metodo guardar que se le pasa el parametro calificacion (objeto de la clase
	 * pedido) y recarga el metodo loadCalificaciones, retornando un String (Lista_P), siendo este
	 * un nombre de un archivo html.
	 * 
	 * @return Lista_P
	 */
	@SuppressWarnings("unused")
	public String guardar() 
	{
		
		System.out.println(pedido);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			pdao.guardar(pedido);
			loadPedidos();
		}
		catch(Exception e)
		{
			String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
          //  facesContext.addMessage(null, m);
            return null;
		}
		
		return "Lista_P";
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
	 * y nos retornara un String (Pedido) que es un nombre de una pagina Xhtml
	 * @param codigo
	 * @return
	 */
	public String listadatosEditar(int codigo) 
	{
		pedido = pdao.leer(codigo);
		return "Pedido";
	}
	public void cantidad() 
	{
		for (int i = 0; i < pedidos.size(); i++) 
		{
			double x= pedidos.get(i).getPlato().getPrecio();
			cantidad=cantidad+x;
			
		}
	}
	
    @PreDestroy
	public void close(){
		System.out.println("Cerrando Pedido");
	}
	
}