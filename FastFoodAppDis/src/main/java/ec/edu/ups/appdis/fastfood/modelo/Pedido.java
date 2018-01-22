package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="tbl_pedido")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "codigo",nullable = false) 
	private int codigo;
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Usuario usuario;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="plt_codigo")
	private Plato plato;
	
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", plato=" + plato + ", usuario=" + usuario + "]";
	}


}
