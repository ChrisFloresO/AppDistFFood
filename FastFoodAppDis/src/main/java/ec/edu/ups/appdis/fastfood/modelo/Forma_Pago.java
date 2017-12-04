package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_forma_pago")
public class Forma_Pago 
{
	@Id
	@Column (name = "frp_codigo",nullable = false) 
	private int codigo;

	@NotNull
	@Column (name="frp_cantidad")
	private int cantidad;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Forma_Pago [codigo=" + codigo + ", cantidad=" + cantidad + "]";
	}

}
