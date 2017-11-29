package ec.edu.ups.appdis.fastfood.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_calificacion")
public class Calificacion {
	
	@Id
	private int codigo;
	
	
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
	
	

}
