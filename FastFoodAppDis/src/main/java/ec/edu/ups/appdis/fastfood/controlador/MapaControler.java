package ec.edu.ups.appdis.fastfood.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;
import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;



@ManagedBean
@SessionScoped
public class MapaControler {
	private String latitud="-1";
	private String longitud="-1";
	private String descripcion;
	private String eubelegida;
	private String latituddes;
	private String longituddes;
	private Ubicacion ubicacion;

	private Restaurante restaurante;

	

	public MapaControler(String latitud, String longitud, String descripcion) {
		this.latitud=latitud;
		this.longitud=longitud;
		this.descripcion=descripcion;
		
	}

	public MapaControler() {
		// TODO Auto-generated constructor stub
	}	
	
	

	@PostConstruct
	public void init()
	{	
	}
	public String getLongitud() 
	{
		return longitud;
	}
	
	public void setLongitud(String longitud) 
	{
		this.longitud = longitud;
	}
	
	public String getLatitud() 
	{
		return latitud;
	}
		
	public void setLatitud(String latitud) 
	{
		this.latitud = latitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
	}
		

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getEubelegida() {
		return eubelegida;
	}
		
	public void setEubelegida(String eubelegida) {
			this.eubelegida = eubelegida;
		}
		
		
		public String getLatituddes() {
			return latituddes;
		}
		
		public void setLatituddes(String latituddes) {
			this.latituddes = latituddes;
		}
		
		public String getLongituddes() {
			return longituddes;
		}
		
		public void setLongituddes(String longituddes) {
			this.longituddes = longituddes;
		}

		public Ubicacion getUbicacion() {
			return ubicacion;
		}

		public void setUbicacion(Ubicacion ubicacion) {
			this.ubicacion = ubicacion;
		}

	
}
