package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Entity
@Table(name="tbl_ubicacion")
public class Ubicacion {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ubi_codigo")
	private int codigo;
	
	@NotNull
	@Size(min=10,max=50)
	@Column(name="ubi_direccion")
	private String direccion;
	
	
	@Column(name="ubi_latitud")
	private double latitud;
	
	@Column(name="user_longitud")
	private double longitud;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	@Override
	public String toString() {
		return "Ubicacion [codigo=" + codigo + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}

}
