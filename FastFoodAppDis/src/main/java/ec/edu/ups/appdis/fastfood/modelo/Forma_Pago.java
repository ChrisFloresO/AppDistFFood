package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_forma_pago")
public class Forma_Pago 
{
	@Id
	@Column (name = "fp_codigo",nullable = false) 
	private int codigo;
	
	@NotNull
	@Size (min=4, max=20)
	@Column (name="fp_descripcion")
	private String descripcion;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Forma_Pago [codigo=" + codigo + ", nombre=" + descripcion + "]";
	}

}
