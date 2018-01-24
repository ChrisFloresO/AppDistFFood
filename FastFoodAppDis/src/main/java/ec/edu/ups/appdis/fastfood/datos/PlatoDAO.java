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
import ec.edu.ups.appdis.fastfood.modelo.Plato;
import ec.edu.ups.appdis.fastfood.datos.ImagenDAO;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class PlatoDAO {
	//variables
	private Part file;
	private Part file2;
	private Imagenes imagen;
	
	@Inject
	private ImagenDAO daoImg;
	
	@Inject
	private EntityManager em;

	
	//getters an setters
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
	
	//metodos de crud
	public void insertar(Plato plato) {
		em.persist(plato);
	}

	public void actualizar(Plato plato) {
		em.merge(plato);
	}

	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}

	public Plato leer(int codigo) {
		return em.find(Plato.class, codigo);
	}
	

	public List<Plato> listadoPlatos() {
		String jppql = "SELECT p FROM Plato p";
		Query query = em.createQuery(jppql, Plato.class);
		@SuppressWarnings("unchecked")
		List<Plato> listado = query.getResultList();
		return listado;
	}

	/**
	 * Este metodo permite listar todos los paltos que se mande an buscar con el parametro nombre y retorna un arreglo de platos
	 * @param nombre
	 * @return
	 */
	public List<Plato> listadoPlatospr (String nombre) 
	{
		String jppql = "SELECT pl FROM Plato pl where lower(pl.nombre) like :nombre";
		Query query = em.createQuery(jppql, Plato.class);
		query.setParameter("nombre", "%"+nombre.toLowerCase()+"%");
		@SuppressWarnings("unchecked")
		List<Plato> listado1 = query.getResultList();
		return listado1;
	}
	
	public List<Plato> listadoPlatospr (int codigo) 
	{
		String jppql = "SELECT pl FROM Plato pl where pl.codigo = :codigo";
		Query query = em.createQuery(jppql, Plato.class);
		query.setParameter("codigo", codigo);
		@SuppressWarnings("unchecked")
		List<Plato> listado1 = query.getResultList();
		return listado1;
	}
	
	public List<Plato> listadoPlatosprT (String nombre) 
	{
		String jppql = "SELECT  distinct pl FROM tbl_plato pl, tbl_restaurante r where pl.res_codigo = r.res_codigo and lower(r.res_tipores) like :nombre";
		Query query = em.createQuery(jppql, Plato.class);
		query.setParameter("nombre", "%"+nombre.toLowerCase()+"%");
		@SuppressWarnings("unchecked")
		List<Plato> listado1 = query.getResultList();
		return listado1;
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
	public void guardar(Plato plato)
	{
		/*if(plato.getNombre().equals("")||plato.getDescripcion().equals("")||plato.getImagen().equals("")||plato.getPrecio()<=0.0)
		{
			
			System.out.println("campos vacios");
		}
		else
		{	*/
		Plato pl = leer(plato.getCodigo());
		try {
		if (pl == null) {
			int fotoSize = (int)file.getSize();
        	System.out.println("tamno     "+fotoSize);
        	byte[] foto;
        	if(fotoSize>0){
        		foto = new byte [fotoSize];
        		
					file.getInputStream().read(foto);
				
					
        		plato.setImagen(foto);		
        		insertar(plato);
        	}
		} 
		else
			actualizar(plato);
			
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