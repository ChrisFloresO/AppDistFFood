package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_restaurante")
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
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
	
	@Column(name="res_tipoRes",length=30)
	private String tipo;
	
	@OneToOne(cascade= {CascadeType.ALL},fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="restaurante", referencedColumnName="res_codigo")
	private Ubicacion ubicacion;
	
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

	

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	//To String para sacar datos
	@Override
	public String toString() {
		return "Restaurante [codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + ", tipo=" + tipo
				+ ", ubicacion=" + ubicacion + "]";
	}

}
