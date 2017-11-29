package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;



@ManagedBean
public class PedidoControler 
{
	private Pedido pedido;
	private List<Pedido> pedidos;
	private int id;
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
	private PedidoDAO pdao;
	
	@PostConstruct
	public void init() {
		pedido= new Pedido();
		loadPedidos();
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

	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadPedidos();
	}
	
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
			/*String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null;*/
		}
		
		return "listado-personas";
	}
	
	public String guardar2() 
	{
		System.out.println(pedido);
		//invoque al DAO y envie la entidad a persistir
		try 
		{
			if(this.id>0)
				pdao.actualizar(pedido);
			else
				pdao.insertar(pedido);
			loadPedidos();
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
		pedido = pdao.leer(codigo);
		
		return "Crearpersonaxhtml";
	}
	
}
