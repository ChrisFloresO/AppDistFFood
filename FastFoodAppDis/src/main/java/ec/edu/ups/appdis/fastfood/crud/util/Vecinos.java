package ec.edu.ups.appdis.fastfood.crud.util;
/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 * @version 1.0
 *
 */

public class Vecinos 
{
	public int item;

    public int prediccion;

    public Vecinos(int item, int prediccion) {
        this.item = item;
        this.prediccion = prediccion;
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

	@Override
	public String toString() {
		return "Recomendacion [ item=" + item + ", prediccion=" + prediccion + "]";
	}


	}

