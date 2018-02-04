package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="tbl_detalle")
@NamedQuery(name="Detalle.findAll", query="SELECT d FROM Detalle d")
public class Detalle 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "det_codigo",nullable = false) 
	private int codigo;

	@NotNull
	@Column (name="det_usuario")
	private String usuario;
	
	
	@NotNull
	@Column (name="det_detalle")
	private String detalle;
	
	@NotNull
	@Column (name="det_cantidad")
	private double cantidad;

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Detalle [codigo=" + codigo + ", usuario=" + usuario + ", detalle=" + detalle + ", cantidad=" + cantidad
				+ "]";
	}



}