package ec.edu.ups.appdis.fastfood.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_pedido")
public class Pedido 
{
	@Id
	private int codigo;
	
	
	private String ruc;
		
	
	@Temporal(TemporalType.DATE)
	@Column (name="per_fechaNa")
	private Date fechaNa;
	
	
	private String iva;


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
	

}
