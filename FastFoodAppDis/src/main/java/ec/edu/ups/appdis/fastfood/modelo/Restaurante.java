package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_restaurante")
public class Restaurante {
	
	@Id
	@Column(name="res_codigo")
	private String codigo;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="res_nombre")
	private String nombre;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="res_telefono")
	private String telefono;
	
	//Getter and Setters
	public String mostrarinformacion() {
		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Restaurante [codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}
	

}
