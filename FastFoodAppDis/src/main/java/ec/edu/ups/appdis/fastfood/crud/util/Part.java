package ec.edu.ups.appdis.fastfood.crud.util;
/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 * @version 1.0
 *
 */
public class Part 
{
	/**
	 * parametros
	 */
	 public double PartProb;
     public int PartId;
     
     
     /**
      * contructor
      * @param id
      * @param prob
      */
	 public Part(int id, double prob) 
	 {
         this.PartId = id;
         this.PartProb = prob;
     }
     
	 /**
	  * Metodos get and set 
	  */
	 
	 public double getPartProb() 
     {
		return PartProb;
     }
	
     public void setPartProb(double partProb) 
     {
		PartProb = partProb;
     }
	
     public int getPartId() 
     {
		return PartId;
     }
	
     public void setPartId(int partId) 
     {
		PartId = partId;
	}
}