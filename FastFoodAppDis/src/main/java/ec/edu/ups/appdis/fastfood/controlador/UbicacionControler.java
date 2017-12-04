package ec.edu.ups.appdis.fastfood.controlador;

import java.util.List;

import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;


public class UbicacionControler {
	private String id;
	private List<Ubicacion> ubicaciones;
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
}
