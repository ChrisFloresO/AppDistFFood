package ec.edu.ups.appdis.fastfood.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="tbl_pedido")
@NamedQuery(name="Pedido.findAll", query="SELECT o FROM Pedido o")
public class Pedido 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column (name = "codigo",nullable = false) 
	private int codigo;
	
	@NotNull
	@Size (min=10, max=13)
	@Column (name="ped_ruc")
	private String ruc;
		
	@NotNull
	@Column (name="ped_iva")
	private String iva;

	@OneToMany(mappedBy="pedido", fetch=FetchType.LAZY)
	private List<Detalle> detalles;
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Usuario usuario;
	
	


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	
	public void addDetalle(Detalle detalle) {
		if(detalles==null) {
			detalles = new ArrayList();
		}
		detalles.add(detalle);
	}


	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", ruc=" + ruc + ", iva=" + iva + ", detalles=" + detalles + "]";
	}

}
