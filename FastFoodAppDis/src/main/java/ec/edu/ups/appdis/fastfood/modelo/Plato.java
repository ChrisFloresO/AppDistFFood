package ec.edu.ups.appdis.fastfood.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

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
	@Column (name="ped_descripcion")
	private String descripcion;
	
	@NotNull
	@Column (name="plt_precio")
	private double precio;
	
	
	@OneToOne
	private Restaurante restaurante;
	
	//SELECT p FROM Plato p WHERE p.resturante.nombre = '' 

	
	//gets and sets
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
