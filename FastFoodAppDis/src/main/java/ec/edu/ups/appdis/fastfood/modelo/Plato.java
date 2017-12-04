package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="tbl_plato")
public class Plato 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column (name = "plt_codigo",nullable = false) 
	private int codigo;
	
	@NotNull
	@Column (name="plt_nombre")
	private String nombre;
		
	@NotNull
	@Column (name="ped_iva")
	private String descripcion;
	
	@NotNull
	@Column (name="plt_precio")
	private double precio;
	
	//private Restaurante restaurante;
	
	
	//SELECT p FROM Plato p WHERE p.resturante.nombre = '' 

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Plato [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
	}
	
	

}
