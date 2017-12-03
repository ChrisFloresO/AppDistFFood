package ec.edu.ups.appdis.fastfood.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ec.ups.edu.appdis.crud.modelo.Telefono;

@Entity
@Table(name="tbl_pedido")
public class Pedido 
{
	@Id
	@Column (name = "ped_codigo",nullable = false) 
	private int codigo;
	
	@NotNull
	@Size (min=10, max=13)
	@Column (name="pes_ruc")
	private String ruc;
		
	
	@Temporal(TemporalType.DATE)
	@Column (name="ped_fechaNa")
	private Date fechaNa;
	
	@NotNull
	@Column (name="ped_iva")
	private String iva;

	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="det_ped_codigo", referencedColumnName="ped_codigo")
	private List<Detalle> detalles;

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

	public Date getFechaNa() {
		return fechaNa;
	}

	public void setFechaNa(Date fechaNa) {
		this.fechaNa = fechaNa;
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
		return "Pedido [codigo=" + codigo + ", ruc=" + ruc + ", fechaNa=" + fechaNa + ", iva=" + iva + ", detalles="
				+ detalles + "]";
	}

}
