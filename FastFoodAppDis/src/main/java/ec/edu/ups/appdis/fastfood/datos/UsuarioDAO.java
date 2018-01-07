package ec.edu.ups.appdis.fastfood.datos;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;

import ec.edu.ups.appdis.fastfood.modelo.Imagenes;
import ec.edu.ups.appdis.fastfood.modelo.Usuario;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class UsuarioDAO 
{
	//variables
	private Part file;
	private Part file2;
	private Imagenes imagen;
	
	@Inject
	private ImagenDAO daoImg;
	
	@Inject	
	private EntityManager em;
	
	//getters and setters
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public Part getFile2() {
		return file2;
	}
	public void setFile2(Part file2) {
		this.file2 = file2;
	}
	
	public void insertar (Usuario u) {
		em.persist(u);
	}
	
	public void actualizar(Usuario u) {
		em.merge(u);
	}
	public void borrar(int id) {
		em.remove(leer(id));
	}
	public Usuario leer(int id) {
		
		return em.find(Usuario.class, id);
	}

	public List<Usuario> listadoUsuario()
	{
		//selects  entidades mapeadas usuario
		String jppql = "SELECT u FROM Usuario u";
		Query query = em.createQuery(jppql,Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listado =query.getResultList();
		return listado;
	}
	
	public List<Usuario> getUsuariosLogin(String correo,String clave)
	{
		
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? "
				+" AND contrasena = ?";
	
		Query q = em.createQuery(sql,Usuario.class);
		q.setParameter(1, correo);
		q.setParameter(2, clave);
		List<Usuario> personas = q.getResultList();
		return personas;
	}
	
    public List<Usuario> getUsuariosLoginRC(String correo)
    {
		String sql = "SELECT u FROM Usuario u "
				+ "WHERE email = ? ";
		Query q = em.createQuery(sql,Usuario.class);
		q.setParameter(1, correo);
		List<Usuario> personas = q.getResultList();
		return personas;
	}
    
    /**
	 * Este metodo permite guardar la imagen como tal en la base de datos
	 * @return
	 * @throws IOException
	 */
	public String guardarImagen() throws IOException
	{
		int fotoSize = (int)file2.getSize();
           System.out.println("tamno     "+fotoSize);
           byte[] foto;
            if(fotoSize>0){
            	foto = new byte [fotoSize];
            	file2.getInputStream().read(foto);
            	imagen.setImagen(foto);		
    			daoImg.save(imagen);
            
   		}
            return null;
	}
	
	
	/**
	 * este metodo permite guardar un plato resiviendo como parametro el plato y verificando que los campos que resiva sena diferentes de nulos.
	 * @param plato
	 */
	public void guardar(Usuario u)
	{
		/*if(plato.getNombre().equals("")||plato.getDescripcion().equals("")||plato.getImagen().equals("")||plato.getPrecio()<=0.0)
		{
			
			System.out.println("campos vacios");
		}
		else
		{	*/
		Usuario u1 = leer(u.getId());
		try {
		if (u1 == null) {
			int fotoSize = (int)file.getSize();
        	System.out.println("tamno     "+fotoSize);
        	byte[] foto;
        	if(fotoSize>0){
        		foto = new byte [fotoSize];
        		
					file.getInputStream().read(foto);
				
					
        		u.setImagen(foto);		
        		insertar(u);
        	}
		} 
		else
			actualizar(u);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				
		}
	//}
	
	/**
	 * Este metodo permite transformar un arreglo de byte a string para poder mostrar la foto al cliente resiviendo como parametro el arreglo de byte.
	 * Y retorna el string con el nombre de la imagen.
	 * @param photo
	 * @return
	 */
	public String convertir (byte[] photo ) 
	{
		String bphoto = Base64.getEncoder().encodeToString(photo);
		return bphoto;
		
	}
    

}