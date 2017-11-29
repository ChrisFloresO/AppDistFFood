package ec.edu.ups.appdis.fastfood.modelo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Usuario {

	private String codigo;
	private String cedula;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private Date fechaRegis;
public String guardar() {
	
	return null;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getCedula() {
	return cedula;
}
public void setCedula(String cedula) {
	this.cedula = cedula;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public Date getFechaRegis() {
	return fechaRegis;
}
public void setFechaRegis(Date fechaRegis) {
	this.fechaRegis = fechaRegis;
}

}
