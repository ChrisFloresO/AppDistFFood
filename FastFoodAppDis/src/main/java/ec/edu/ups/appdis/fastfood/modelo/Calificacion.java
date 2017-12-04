package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_calificacion")
public class Calificacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column (name="cal_codigo")
	private int codigo;
	
	@NotNull
	@Column (name="cal_voto")
	private String voto;


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "Calificacion [codigo=" + codigo + ", voto=" + voto + "]";
	}
	
	

}
