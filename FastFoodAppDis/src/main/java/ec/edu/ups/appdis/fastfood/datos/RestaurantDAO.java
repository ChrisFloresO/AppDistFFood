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
import ec.edu.ups.appdis.fastfood.modelo.Restaurante;

/**
 * @author Franklin Villavicencio y Christian Flores
 */

@Stateless
public class RestaurantDAO {
	
	private Part file;
	private Part file2;
	private Imagenes imagen;
	
	@Inject
	private ImagenDAO daoImg;
	
		@Inject	
		private EntityManager em;
		
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
		public Imagenes getImagen() {
			return imagen;
		}
		public void setImagen(Imagenes imagen) {
			this.imagen = imagen;
		}
		
		public void insertar (Restaurante r) {
			em.persist(r);
		}
		
		public void actualizar(Restaurante r) {
			em.merge(r);
		}
		public void borrar(int codigo) {
			em.remove(leer(codigo));
		}
		
		public Restaurante leer(int  codigo) {
			
			return em.find(Restaurante.class, codigo);
		}	
		
		public String guardarImagen() throws IOException{
			
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
		
		
		public void guardar(Restaurante r)
		{
			/*if(huecas.getNombre().equals("")||huecas.getTipoHueca().equals("")||huecas.getUbicacion().equals("")){
				
				System.out.println(" campos vacios");
			}else{	*/
			System.out.println(" campos foto");
			Restaurante r1 = leer(r.getCodigo());
			
			try {
				if(r1==null) {
				int fotoSize = (int)file.getSize();
	        	System.out.println("tamno     "+fotoSize);
	        	byte[] foto;
	        	if(fotoSize>0){
	        		foto = new byte [fotoSize];
	        		
						file.getInputStream().read(foto);
					
						
	        		r.setImagen(foto);		
	        		insertar(r);
	        	}
			} 
			else
				actualizar(r);
				
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		
		
		public List<Restaurante> listadoRestaurantes()
		{
			String jppql = "SELECT r FROM Restaurante r";
			Query query = em.createQuery(jppql,Restaurante.class);
			@SuppressWarnings("unchecked")
			List<Restaurante> listado =query.getResultList();
			return listado;
		}
		
		

}