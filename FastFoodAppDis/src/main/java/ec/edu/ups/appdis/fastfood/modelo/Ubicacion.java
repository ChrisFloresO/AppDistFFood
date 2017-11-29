package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Ubicacion {
	@Id
	@Column(name="ubi_codigo")
	private String codigo;
	
	@NotNull
	@Size(min=20,max=40)
	@Column(name="ubi_direccion")
	private String direccion;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="ubi_latitud")
	private String latitud;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="user_longitud")
	private String longitud;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	@Override
	public String toString() {
		return "Ubicacion [codigo=" + codigo + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}

}
