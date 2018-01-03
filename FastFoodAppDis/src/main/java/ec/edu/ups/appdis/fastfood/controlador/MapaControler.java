package ec.edu.ups.appdis.fastfood.controlador;





import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import ec.edu.ups.appdis.fastfood.datos.RestaurantDAO;
import ec.edu.ups.appdis.fastfood.datos.UsuarioDAO;
import ec.edu.ups.appdis.fastfood.modelo.Ubicacion;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;






@ManagedBean
@SessionScoped
public class MapaControler {
private String latitud="-1";
private String longitud="-1";
private String descripcion;
private String eubelegida;
private String latituddes;
private String longituddes;



private List <MapaControler> ub = new ArrayList<MapaControler>();
private List<SelectItem> ubicaciones=new ArrayList<SelectItem>();

private List<Ubicacion> listadoUb;
private List<Usuario> listadoUsu;






@Inject
private UsuarioDAO use;

public List<Usuario> getListadoUsu() {
	return listadoUsu;
}

public void setListadoUsu(List<Usuario> listadoUsu) {
	this.listadoUsu = listadoUsu;
}

public UsuarioDAO getUse() {
	return use;
}

public void setUse(UsuarioDAO use) {
	this.use = use;
}



public List<Ubicacion> getListadoUb() {
	return listadoUb;
}

public void setListadoUb(List<Ubicacion> listadoUb) {
	this.listadoUb = listadoUb;
}

public MapaControler(String latitud, String longitud, String descripcion) {
	this.latitud=latitud;
	this.longitud=longitud;
	this.descripcion=descripcion;
	
}

 public MapaControler() {
	// TODO Auto-generated constructor stub
}
	
	

@PostConstruct
public void init(){
	
	cargarCampos();

}
public String getLongitud() {

		return longitud;


}
public void setLongitud(String longitud) {
	this.longitud = longitud;
}

public String getLatitud() {

		return latitud;

}
public void setLatitud(String latitud) {
	this.latitud = latitud;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public List<MapaControler> getUb() {
	return ub;
}
public void setUb(List<MapaControler> ub) {
	this.ub = ub;
}

public String getEubelegida() {
	return eubelegida;
}

public void setEubelegida(String eubelegida) {
	this.eubelegida = eubelegida;
}

public List<SelectItem> getUbicaciones() {
	return ubicaciones;
}

public void setUbicaciones(List<SelectItem> ubicaciones) {
	this.ubicaciones = ubicaciones;
}

public String getLatituddes() {
	return latituddes;
}

public void setLatituddes(String latituddes) {
	this.latituddes = latituddes;
}

public String getLongituddes() {
	return longituddes;
}

public void setLongituddes(String longituddes) {
	this.longituddes = longituddes;
}





private void cargarCampos()
{
	for (MapaControler ubi:ub)
		
	{
		ubicaciones.add(new SelectItem(ubi.descripcion,ubi.descripcion));
		
	}

}

public String action(){

	setDescripcion("Mi Ubicacion");
	return null;
}

public String calcular(String descripcion,String lat,String lon){

	for (MapaControler ubicar: ub)
	{

		if (eubelegida.equals(ubicar.descripcion))
		{
			this.latituddes=ubicar.latitud;
			this.longituddes=ubicar.longitud;
			System.out.println("calculando"+" "+ubicar.descripcion+"  latitud  "+this.latituddes+" "+"longitud  "+this.longituddes+"--->"+descripcion+"  latitud"+latitud+" "+"  longitud"+longitud );
		}
	}
	return "null";
}


	
}
