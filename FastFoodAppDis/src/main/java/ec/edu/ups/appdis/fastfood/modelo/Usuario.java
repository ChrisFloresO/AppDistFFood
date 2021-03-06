package ec.edu.ups.appdis.fastfood.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name="tbl_usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",length=10)
	private int id;
	
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

	
	@Email
	@Column (name="user_email")
	private String email;
	
	@Size(min=4,max=10)
	@Column (name="user_contrasena")
	private String contrasena;
	
	@Column(name = "use_rol",length = 20)
	private int rol;
	
	//bi-directional many-to-one association to Ordene
	@OneToMany(mappedBy="usuario")
	private List<Pedido> pedidos;
	
	//bi-directional many-to-one association to Ordene
	@OneToMany(mappedBy="usuario")
	private List<Calificacion> calificaciones;
	
	
	//Getters and Setters	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getRol() {
		return rol;
	}
	
	public void setRol(int rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}
	
}
