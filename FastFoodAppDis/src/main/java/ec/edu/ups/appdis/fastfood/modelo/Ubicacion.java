package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="tbl_ubicacion")
public class Ubicacion {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ubi_codigo")
	private String codigo;
	
	@NotNull
	@Size(min=20,max=40)
	@Column(name="ubi_direccion")
	private String direccion;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="ubi_latitud")
	private double latitud;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="user_longitud")
	private double longitud;
	
	
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
	
	@Override
	public String toString() {
		return "Ubicacion [codigo=" + codigo + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}

}
