package ec.edu.ups.appdis.fastfood.modelo;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tbl_plato")
@NamedQuery(name="Plato.findAll", query="SELECT p FROM Plato p")
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
	
	@Lob
	@Column(name="plt_imagen")
	private byte[] imagen;
	
	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="res_codigo" ,nullable=false)
	@JsonIgnore
	private Restaurante restaurante; 
	
	
	//bi-directional many-to-one association to DetalleOrdene
	@OneToMany(mappedBy="plato")
	private List<Detalle> detalles;
	
	//bi-directional many-to-one association to DetalleOrdene
	@OneToMany(mappedBy="plato")
	private List<Calificacion> calificaciones;
	
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		return "Plato [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", imagen=" + Arrays.toString(imagen) + ", restaurante=" + restaurante + "]";
	}
	
	

}
