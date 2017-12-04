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

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="tbl_usuario")
public class Usuario {
	
	@Id
	@Size(min=9,max=10)
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
	@Size(min=4,max=20)
	@Column(name="user_telefono")
	private String telefono;
	
	@Email
	@Column (name="user_email")
	private String email;
	
	@Email
	@Column (name="user_contrasena")
	private String contrasena;
	
//Getters and Setters

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

public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContrasena() {
	return contrasena;
}
public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
}
@Override
public String toString() {
	return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
			+ ", telefono=" + telefono + ", email=" + email + ", contrasena=" + contrasena
			+ "]";
}



}
