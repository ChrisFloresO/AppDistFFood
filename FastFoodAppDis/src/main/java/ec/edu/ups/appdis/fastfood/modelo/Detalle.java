package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="tbl_detalle")
@NamedQuery(name="Detalle.findAll", query="SELECT d FROM Detalle d")
public class Detalle 
{
	@Id
	@Column (name = "det_codigo",nullable = false) 
	private int codigo;

	@NotNull
	@Column (name="det_cantidad")
	private int cantidad;
	
	//bi-directional many-to-one association to Ordene
	@ManyToOne
	@JoinColumn(name="codigo", insertable=false, updatable=false)
	private Pedido pedido;
	
	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="plt_codigo")
	private Plato plato;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "Detalle [codigo=" + codigo + ", cantidad=" + cantidad + ", pedido=" + pedido + "]";
	}	
	
	public String aumentar() 
	{
		this.cantidad=cantidad +1;
		return null;
		
	}
	
	public String disminuir() 
	{
		if(cantidad>0) {
		this.cantidad=cantidad -1;
		}else
			this.cantidad=0;
		return null;
		
	}

}