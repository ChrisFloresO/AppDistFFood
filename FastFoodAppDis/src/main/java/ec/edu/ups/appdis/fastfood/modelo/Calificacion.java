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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_calificacion")
@NamedQuery(name="Calificacion.findAll", query="SELECT cal FROM Calificacion cal")
public class Calificacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="cal_codigo")
	private int codigo;
	
	@NotNull
	@Column (name="cal_voto")
	private int voto;
	
	@NotNull
	@Column (name="cal_comentario")
	private String comentario;
	
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

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
	

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	@Override
	public String toString() {
		return "Calificacion [codigo=" + codigo + ", voto=" + voto + ", comentario=" + comentario + ", usuario="
				+ usuario + ", plato=" + plato + "]";
	}
	
	

}
