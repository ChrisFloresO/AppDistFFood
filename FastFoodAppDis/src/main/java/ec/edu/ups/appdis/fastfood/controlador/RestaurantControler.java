package ec.edu.ups.appdis.fastfood.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;

@ManagedBean
public class RestaurantControler {
	
	
	

	@Inject
	private RestaurantDAO rdao;
	private Restaurante restaurante;
	
	
	
	@PostConstruct		
	public void init() {
		restaurante=new Restaurante();
	}

	public Restaurante getUsuario() {
		return restaurante;
	}

	public String Guardar(){
		
		//invoque al DAO
			rdao.Insertar(restaurante);
		return null;
	}

	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

}
