package ec.edu.ups.appdis.fastfood.modelo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_usuario")
public class Usuario {
	
	@Id
	@Column(name="user_codigo")
	private String codigo;
	
	
	@Column(name="user_cedula",length=10)
	private String cedula;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="user_nombre")
	private String nombre;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="user_apellido")
	private String apellido;
	
	@NotNull
	@Size(min=20,max=40)
	@Column(name="user_direccion")
	private String direccion;
	
	@NotNull
	@Size(min=4,max=20)
	@Column(name="user_telefono")
	private String telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="user_fechaRegis")
	private Date fechaRegis;
	
//Getters and Setters
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
@Override
public String toString() {
	return "Usuario [codigo=" + codigo + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
			+ ", direccion=" + direccion + ", telefono=" + telefono + ", fechaRegis=" + fechaRegis + "]";
}



}
