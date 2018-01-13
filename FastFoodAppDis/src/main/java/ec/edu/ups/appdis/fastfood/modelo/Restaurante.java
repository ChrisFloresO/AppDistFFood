package ec.edu.ups.appdis.fastfood.modelo;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="tbl_restaurante")
@NamedQuery(name="Restaurante.findAll", query="SELECT r FROM Restaurante r")
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="res_codigo")
	private int codigo;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="res_nombre")
	private String nombre;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="res_telefono")
	private String telefono;
	
	@Column(name="res_tipoRes",length=30)
	private String tipo;
	
	@Lob
	@Column(name="res_imagen")
	private byte[] imagen;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="restaurante", referencedColumnName="res_codigo")
	private List<Ubicacion> ubicaciones;
	
	
	//bi-directional many-to-one association to Plato
	@OneToMany(mappedBy="restaurante")
	private List<Plato> platos;
	
	//Getter and Setters

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void addUbicacion(Ubicacion ubicacion) {
		if(ubicaciones==null) {
			ubicaciones = new ArrayList();
		}
		ubicaciones.add(ubicacion);
	}
	
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	@Override
	public String toString() {
		return "Restaurante [codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + ", tipo=" + tipo
				+ ", imagen=" + Arrays.toString(imagen) + ", ubicaciones=" + ubicaciones + "]";
	}

}
