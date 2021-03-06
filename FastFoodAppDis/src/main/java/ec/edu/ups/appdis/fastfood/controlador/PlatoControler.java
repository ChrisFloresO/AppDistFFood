package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.DetalleDAO;
import ec.edu.ups.appdis.fastfood.datos.PedidoDAO;
import ec.edu.ups.appdis.fastfood.datos.PlatoDAO;
import ec.edu.ups.appdis.fastfood.datos.PrediccionesDao;
import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Detalle;
import ec.edu.ups.appdis.fastfood.modelo.Pedido;
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.modelo.Predicciones;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

@ManagedBean
public class PlatoControler 
{
	//variables
	private Plato plato;
	private List<Plato> platos;
	private List<Plato> platosR;
	private List<Pedido> pedidos;
	private List<Detalle> detalles;
	private List<Predicciones> predicciones;
	private String id;
	private String nombre;
	private String tipo;
	private int codigo;
	private Restaurante restaurante;
	private Pedido pedido;
	private Usuario usuario;
	private Detalle detalle;

	@Inject
	private PlatoDAO pdao;
	
	@Inject
	private DetalleDAO dedao;
	
	@Inject
	private RestaurantDAO rdao;
	
	@Inject
	private PrediccionesDao prerdao;
	
	@Inject
	private PedidoDAO pedao;
	
	@Inject
	private UsuarioDAO usdao;
	
	@Inject
	private Sesion sesion;
	
	/**
	 * metodo para inicializar las variables
	 */
	@PostConstruct
	public void init() {
		plato = new Plato();
		pedido= new Pedido();
		usuario = new Usuario();
		detalle= new Detalle();
		loadPlatos();
		loadPrediccion();
		loadDetalles();
	}

	//getters and setters
	public void loadPlatos() {
		platos = pdao.listadoPlatos();
	}
	
	public void loadDetalles() {
		detalles = dedao.listadoDetalles();
	}
	
	public void loadPedidos() {
		pedidos = pedao.listadoPedidos();
		for (int i = 0; i < pedidos.size(); i++) {
			System.out.println(pedidos.get(i).getCodigo());
		}
	}
	
	public void loadPrediccion() {
		
		predicciones = prerdao.listadoPredicciones();
		for (int i = 0; i < predicciones.size(); i++) 
		{
			int codigo =predicciones.get(i).getItem();
			System.out.println(codigo);
			platosR =pdao.listadoPlatospr(codigo);
			
		}
		
	}

	public void BorrarD(int cedula) {
		dedao.borrar(cedula);
		loadDetalles();
	}

	public List<Plato> getPlatosR() {
		return platosR;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setPlatosR(List<Plato> platosR) {
		this.platosR = platosR;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PlatoDAO getPdao() {
		return pdao;
	}

	public void setPdao(PlatoDAO pdao) {
		this.pdao = pdao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void Boorar(int codigo) {
		pdao.borrar(codigo);
		loadPlatos();
	}
	
	public void Borrar1(int codigo) {
		System.out.println("aqui en el borrar"+codigo);
		pedao.borrar(codigo);
	}
	
	public void name() {
		System.out.println("aqui");
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public PedidoDAO getPedao() {
		return pedao;
	}

	public void setPedao(PedidoDAO pedao) {
		this.pedao = pedao;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//getters and setters
	

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public RestaurantDAO getRdao() {
		return rdao;
	}

	public void setRdao(RestaurantDAO rdao) {
		this.rdao = rdao;
	}

	public List<Predicciones> getPredicciones() {
		return predicciones;
	}

	public void setPredicciones(List<Predicciones> predicciones) {
		this.predicciones = predicciones;
	}

	public PrediccionesDao getPrerdao() {
		return prerdao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsdao() {
		return usdao;
	}

	public void setUsdao(UsuarioDAO usdao) {
		this.usdao = usdao;
	}

	public void setPrerdao(PrediccionesDao prerdao) {
		this.prerdao = prerdao;
	}

	@PreDestroy
	public void close(){
		System.out.println("Cerrando");
	}
	/**
	 * este metod permite guardar una calificacion al momento de llamar al objeto
	 * pdao que tiene el metodo guardar que se le pasa el parametro calificacion
	 * (objeto de la clase plato) y recarga el metodo loadCalificaciones, retornando
	 * un String (Lista_Plato), siendo este un nombre de un archivo html.
	 * 
	 * @return Lista_Plato
	 */
	@SuppressWarnings("unused")
	public String guardar() 
	{
		restaurante = rdao.leer(codigo);
		plato.setRestaurante(restaurante);
		System.out.println(plato);
		// invoque al DAO y envie la entidad a persistir
		try {
			pdao.guardar(plato);
			loadPlatos();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			// facesContext.addMessage(null, m);
			return null;
		}

		return "Lista_Plato";
	}

	/**
	 * este metodo se genera cuando al invocar el metodo guardar no se puede
	 * guardar; este metodo nos mostrara la causa por que no se guardo reciviendo un
	 * parametro de excepcion (e) y retornara un String con la informacion del error
	 * 
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
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Editar_Plato) que es un nombre de una
	 * pagina Xhtml
	 * 
	 * @param codigo
	 * @return
	 */
	public String listadatosEditar(int codigo) {
		plato = pdao.leer(codigo);
		return "Editar_Plato";
	}

	/**
	 * 
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Pedido) que es un nombre de una pagina
	 * Xhtml
	 * 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unused")
	public String listadatosB(int codigo , int codus) 
	{
		if(codus>0) 
		{
			System.out.println(codigo);
			System.out.println(codus);
			plato = pdao.leer(codigo);
			System.out.println(plato.getNombre());
			usuario = usdao.leer(codus);
			System.out.println(usuario.getNombre());
			pedido.setPlato(plato);
			pedido.setUsuario(usuario);
			try {
				pedao.guardar(pedido);
				loadPedidos();
			} catch (Exception e) {
				String errorMessage = getRootErrorMessage(e);
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
				// facesContext.addMessage(null, m);
				return null;
			}

			return "Carrito";
			
		}else
			return "Logeo";
		
	}

	public String doRead() 
	{
		platos = pdao.listadoPlatospr(nombre);
		System.out.println(platos.size());
		
		return null;
	}

	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Calificacion) que es un nombre de una
	 * pagina Xhtml
	 * 
	 * @param codigo
	 * @return
	 */
	public String listadatosC(int codigo, int codus) 
	{
		if(codus>0) {
			plato = pdao.leer(codigo);
			usuario = usdao.leer(codus);
			sesion.setPlato(plato);
			return "Calificacion";
		}else
			return "Logeo";
		
	}

	/**
	 * este metodo permite encontrar un objeto a partir de un parametro de busqueda
	 * (codigo) y nos retornara un String (Calificacion) que es un nombre de una
	 * pagina Xhtml
	 * 
	 * @param codigo
	 * @return
	 */
	public String listadatosU(int codigo) {
		plato = pdao.leer(codigo);
		restaurante = rdao.leer(plato.getRestaurante().getCodigo());
		System.out.println(restaurante.getCodigo());
		return "IrLugar";
	}
	
	public String registrar () 
	{
		String nusuario = null;
		String descr = null;
		double cantidadd = 0;
		List<Pedido> listapredcciones = pedao.listadoPedidos();
		for (int k = 0; k < listapredcciones.size(); k++) 
		{
			nusuario=listapredcciones.get(k).getUsuario().getNombre();
			descr =descr+listapredcciones.get(k).getPlato().getNombre();
			cantidadd =cantidadd+listapredcciones.get(k).getPlato().getPrecio();
			System.out.println("1"+listapredcciones.get(k));
			pedao.borrar(listapredcciones.get(k).getCodigo());
		}
		System.out.println(nusuario);
		System.out.println(descr);
		System.out.println(cantidadd);
		detalle.setUsuario(nusuario);
		detalle.setDetalle(descr);
		detalle.setCantidad(cantidadd);
		dedao.guardar(detalle);
		return "Buscar";
		
		
	}
/*	public String salir () 
	{
		List<Pedido> listapredcciones = pedao.listadoPedidos();
		for (int k = 0; k < listapredcciones.size(); k++) 
		{
			System.out.println("1"+listapredcciones.get(k));
			pedao.borrar(listapredcciones.get(k).getCodigo());
		}
		return "Home";
	}
*/
}