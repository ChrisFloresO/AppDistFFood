package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_predicciones")
@NamedQuery(name="Predicciones.findAll", query="SELECT pre FROM Predicciones pre")
public class Predicciones {

	@Id
	@Column(name="cal_id")
	private int id;
	
	@NotNull
	@Column(name= "cal_usuario")
	private int usuario;
	
	@NotNull
	@Column(name= "cal_item")
	private int item;
	
	@NotNull
	@Column(name= "cal_prediccion")
	private int prediccion;
	
	@Lob
	@Column(name="plt_imagen")
	private byte[] imagen;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getPrediccion() {
		return prediccion;
	}

	public void setPrediccion(int prediccion) {
		this.prediccion = prediccion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}
