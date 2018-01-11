package ec.edu.ups.appdis.fastfood.crud.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Franklin Villavicencio y Christian Flores
 * @version 1.0
 *
 */
public class FCM {
	
	String filename, dirBase;
    double[][] centroides;
    int[][] centroidesVecinosporUsuario;
    double[][] pesoscentroidesVecinosporUsuario;
     
    public int k = 0;
    public int usuarios = 0;
    public int items = 0;

    public double[][] votosTrain;
    public double[][] votos;

    public int iteraciones;
    @SuppressWarnings("rawtypes")
	List<Hashtable> ListaVotosUsuarios;
    
    public int totalTrain, totalTest;
    double R;
    public boolean conVacios;
    double ERROR;
    Random rand = new Random();
    
    BufferedReader lectura;
    FileReader f;
    
    int L;//num centroides más cercanos a tomar en cuenta para realizar la predicción
    
	public FCM (String direccion,String filename, String dirBase, boolean bandera, int ka, int iter, double err, double Mm) 
	{
		System.out.println(direccion);
		System.out.println(dirBase+filename);
		System.out.println(bandera);
		System.out.println(ka);
		System.out.println(iter);
		System.out.println(err);
		System.out.println(Mm);
		//Algoritmo de Sistema de Recomendacion
        //1. Entrada de Datos y parámetros
		
		this.filename = filename;
		this.dirBase = dirBase; 
		
		try 
		{
			lectura = new BufferedReader(new FileReader(direccion));
			//indica si el conjunto de datos tiene vacios o esta completo. Si convacios es false, los huecos se tomaran como ceros.
	        conVacios = bandera;
	        k=ka;
	        iteraciones = iter;
	        ERROR = err;
	        m = Mm;
	      //m = 1.01;
	        
	        R = 4.0;
	        
	        //votos
	        minVoto = 1.0;
	        maxVoto = 5.0;
	        //para movielens
	        items = 3952;
	        //items = 12;
	        usuarios = 6040; //modificar de acuerdo al dataset a utilizar
	       // usuarios = 12;
	        
	        creaM();
	        creaTest();
	        iniciarListas();
            iniciarListas();
            

	        //2. Clustering de usuarios
	        SoftClustering();

	        //3. Cálculo de Predicciones y medición MAE
	        System.out.println("calculo predicciones Mae");
	        L = k;
	        double MAE = calcularMAEFCMeans(L);
	        System.out.println("k;" + k + ";MAE;" + MAE);

	        
	        //4. Realizar Recomendaciones -> ordenar N predicciones para usuario objetivo (el que hace el login)
	        System.out.println("prediccion");
	        determinarCentroidesVecinosFCMeans(L);
	        System.out.println(predecirVotoFCM(2, 4, L));
	        		
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void creaM() {
		
		try
        {
			Path path = Paths.get("D:/Trabajos_Matlab/PracticaFCM/Dataset1M", "train.dat");
            totalTrain = Files.readAllLines(path).size();
            votosTrain = new double[totalTrain][3];//total de datos en dataset * 3 campos (usuario, item, voto) 
            String fila;
            int cont = 0;
            while ((fila = lectura.readLine()) != null)
            {
                String[] parametros;
                parametros = fila.split("::");
                votosTrain[cont][0] = Integer.parseInt(parametros[0])-1;
                votosTrain[cont][1] = Integer.parseInt(parametros[1])-1;
                votosTrain[cont][2] = Double.parseDouble(parametros[2]);

                parametros = null;
                cont++;
            }
            lectura.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
		
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public void creaTest()
    {
        int campos = 4;
        try
        {         
            BufferedReader fsIn = new BufferedReader(new FileReader(dirBase + "test.dat"));            					 
            String fila;
            Path path = Paths.get(dirBase, "test.dat");
            totalTest = Files.readAllLines(path).size();
                       
            votos = new double[totalTest][campos];
            
            int row = 0;
            int cont = 0;
            while ((fila = fsIn.readLine()) != null)
            {
                String[] parametros;
                int usuario;
                int item;
                parametros = fila.split("::");
                votos[cont][0] = Integer.parseInt(parametros[0]) - 1;
                votos[cont][1] =Integer.parseInt(parametros[1]) - 1;
                
                votos[cont][2] = Double.parseDouble(parametros[2]);
                
                cont++;
                row++;
                parametros = null;
               
            }
            lectura.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
	
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public void iniciarListas()
     {
         ListaVotosUsuarios = new ArrayList<Hashtable>();
         Hashtable votosUsuario;
         for (int u = 0; u < usuarios; u++)
         {
             votosUsuario = new Hashtable();
             ListaVotosUsuarios.add(votosUsuario);
         }

         if (!conVacios) //la matriz no tiene vacios o los vacios son tomados como ceros.
         {
             for (int u = 0; u < usuarios; u++)
             {
                 for (int i = 0; i < items; i++)
                 {
                     ListaVotosUsuarios.get(u).put(i, 0);
                 }
             }
             for (int v = 0; v < totalTrain; v++)
             {
                 int usuario = (int)(votosTrain[v][0]);
                 int item = (int)( votosTrain[v][1]);
                 double voto = votosTrain[v][2];
                 ListaVotosUsuarios.get(usuario).replace(item, voto);
             }
         }
         else// indica que la matriz tiene vacios, sparse
         {
             for (int v = 0; v < totalTrain; v++)
             {
                 int usuario = (int)(votosTrain[v][0]);
                 int item = (int)(votosTrain[v][1]);
                 double voto = votosTrain[v][2];
                 ListaVotosUsuarios.get(usuario).put(item, voto);
             }
         }
//         mostrarHashTable(ListaVotosUsuarios[1]);
     }
	 
	 // Fuzzy CMeans *********************************************************************************************************************
     //***********************************************************************************************************************************
     double[][] Yuk;
     double m;//controla overlapping en Fuzzy cmeans
     double JM;
     double mMin;
     double mMax;
     int cMax;
     int saltoK;
     
     
     @SuppressWarnings("unused")
	public void SoftClustering() {
    	 
     	//a. inicializar
          iteraciones = 100;
          int numIntentos = 0;
          numIntentos++;
          //b. inicializar fuzzy membership Yij
          inicializarMatrizCentroidesconVacios();
          inicializarRandomicamenteYuk();
          double JMant = Double.MAX_VALUE;
          
          //c. actualizar centroides y membership Yij
          for (int it = 0; it < iteraciones; it++) //4. Probar Convergencia con numIteraciones o con error 0.001 con JM.
          {
              if (!conVacios)
            	  calcularCentroidesFCM();
              else
            	  calcularCentroidesFCM2();
              
              System.out.println("actualizado centroides ");
              actualizarYuk();
              System.out.println("actualizado YUK ");
              JM = calcularJM();
              //double errorJM = Math.Abs((JM - JMant) / JM);
              double errorJM = Math.abs(JM - JMant);
              
              System.out.println("it;"+(it +1) + ";JM;" + JM + ";errorJM;" + errorJM);
              if (errorJM < ERROR) break;
              JMant = JM;
          }
     	 
      }
     
     public void inicializarMatrizCentroidesconVacios() //reinicia a 0 todos los centroides
     {

         centroides = new double[k][items];
         for (int j = 0; j < k; j++)
         {
             for (int i = 0; i < items; i++)
             {
                 centroides[j][i] = 0.0;
             }
         }
         //mostrarMatriz(centroides);
     }
     
     public void inicializarRandomicamenteYuk() {
    	 //La sumatoria de cada vector de usuarios es igual a 1.
         Yuk = new double[usuarios][k];
         double sumaYuk;
         for (int u = 0; u < usuarios; u++)
         {
             sumaYuk = 0.0;
             for (int j = 0; j < k; j++)
             {
                 Yuk[u][j] = rand.nextDouble();
                 sumaYuk = sumaYuk + Yuk[u][j];
             }
             for (int j = 0; j < k; j++) {
                 Yuk[u][j] = Yuk[u][j] / sumaYuk;	//se normaliza para que la sumatoria sea igual a 1.
             }
         }
     }
    
     public void calcularCentroidesFCM()
     {
         double[][] sumatoriaAuk = new double[k][items];
         centroides = null;
         centroides = new double[k][items];
         for (int i = 0; i < items; i++)
         {
             for (int u = 0; u < usuarios; u++)
             {
                 if (!conVacios)
                 {
                     double votoenItem = Double.parseDouble("" + ListaVotosUsuarios.get(u).get(i));
                     for (int j = 0; j < k; j++)
                     {
                         centroides[j][i] = centroides[j][i] + (Math.pow(Yuk[u][j], m) * votoenItem);
                         sumatoriaAuk[j][i] = sumatoriaAuk[j][i] + Math.pow(Yuk[u][j], m);
                     }
                 }
                 else
                 {
                     if ( ListaVotosUsuarios.get(u).get(i) != null)
                     {
                         double votoenItem = Double.parseDouble("" +  ListaVotosUsuarios.get(u).get(i));
                         for (int j = 0; j < k; j++)
                         {
                             centroides[j][i] = centroides[j][i] + (Math.pow(Yuk[u][j], m) * votoenItem);
                             sumatoriaAuk[j][i] = sumatoriaAuk[j][i] + Math.pow(Yuk[u][j], m);
                         }
                     }
                 }
             }
         }
         for (int i = 0; i < items; i++)
         {
             for (int j = 0; j < k; j++)
             {
                 centroides[j][i] = centroides[j][i] / sumatoriaAuk[j][i];
                 if (sumatoriaAuk[j][i] == 0) centroides[j][i] = 0.0;//si ningun usuario aporta entonces queda en 0.
             }
         }
         sumatoriaAuk = null;
     }
     
     
     public void calcularCentroidesFCM2()
     {
         double[][] sumatoriaAuk = new double[k][items];
         centroides = null;
         centroides = new double[k][items];
         //Console.WriteLine("totalTrain: "+ totalTrain);
         for (int r = 0; r < totalTrain; r++)
         {
             int u = (int)(votosTrain[r][0]);
             int i = (int)(votosTrain[r][1]);
             double votoenItem = votosTrain[r][2];
             for (int j = 0; j < k; j++)
             {
                 centroides[j][i] = centroides[j][i] + (Math.pow(Yuk[u][j], m) * votoenItem);
                 sumatoriaAuk[j][i] = sumatoriaAuk[j][i] + Math.pow(Yuk[u][j], m);
             }
         }
         for (int i = 0; i < items; i++)
         {
             for (int j = 0; j < k; j++)
             {
                 centroides[j][i] = centroides[j][i] / sumatoriaAuk[j][i];
                 if (sumatoriaAuk[j][i] == 0) centroides[j][i] = 0.0;
                 //if (sumatoriaAuk[j, i] == 0) centroides[j, i] = ((maxVoto+minVoto)/2.0);//si ningun usuario aporta entonces queda en 0.
                 //if(centroides[j, i]==0) centroides[j, i] = (maxVoto + minVoto) / 2.0;
             }
         }
         sumatoriaAuk = null;
     }
     
     double[][] normas;
     
     public void actualizarYuk()
     {
         normas = new double[usuarios][k];
         for (int u = 0; u < usuarios; u++)
         {
             for (int j = 0; j < k; j++)
             {
                 normas[u][j] = Norma(u, j);
             }
         }
         double p = (2.0 / (m - 1.0));

         for (int u = 0; u < usuarios; u++)
         {
             for (int j = 0; j < k; j++)
             {
                 double sumatoria = 0.0;
                 for (int c = 0; c < k; c++)
                 {
                     sumatoria = sumatoria + Math.pow((normas[u][j]) / normas[u][c], p);
//                     System.out.println(sumatoria);
                     
                 }
                 Yuk[u][j] = 1.0 / sumatoria;
                 if (Double.isNaN(sumatoria) || sumatoria == 0) Yuk[u][j] = (1.0 / k) + rand.nextDouble() / 100.0;
             }

         }
         //mostrarMatriz(Yuk);
     }
     
     
     @SuppressWarnings("unchecked")
	public double Norma(int u, int v)// v es el centroide. Se calcula la razi cuadrada
     {
         double distancia = 0.0;
         
         Set<Integer> keys = ListaVotosUsuarios.get(u).keySet();
         
         for(int key : keys){
        	 double voto = Double.parseDouble("" + ListaVotosUsuarios.get(u).get(key));
             double votoC = centroides[v][key];
             if (!conVacios)
             {
                 distancia = distancia + Math.pow(voto - votoC, 2.0);
             }
             else
             {
                 if (votoC > 0.0)//votosComun++;
                     distancia = distancia + Math.pow(voto - votoC, 2.0);
             }
         }
         return Math.sqrt(distancia); 
     }
     
     @SuppressWarnings({ "unused", "unchecked" })
	public double Norma2(int u, int v)// v es el centroide
     {
         double distancia = 0.0;
         double votosComun = 0.0;
         
         Set<Integer> keys = ListaVotosUsuarios.get(u).keySet();
         
         for(int key : keys){

        	 double voto = Double.parseDouble("" + ListaVotosUsuarios.get(u).get(key));
             if (!conVacios)
             {
                 votosComun++;
                 distancia = distancia + Math.pow(voto - centroides[v][key], 2.0);
             }
             else
             {
                 if (centroides[v][key] > 0.0)
                 {
                     votosComun++;
                     distancia = distancia + Math.pow(voto - centroides[v][key], 2.0);
                 }
             }
         }
         return distancia;
     }
         
     public double calcularJM()
     {
         double JM = 0.0;
         for (int u = 0; u < usuarios; u++)
         {
             for (int j = 0; j < k; j++)
             {
                 JM = JM + (Math.pow(Yuk[u][j], m) * Math.pow(normas[u][j], 2.0));
             }
         }
         return JM;
     }
     
     @SuppressWarnings("unused")
	public double calcularMAE_FCMeans(int l)
     {
         determinarCentroidesVecinosFCMeans(l);
         double MAE = 0.0;
         int campoUsuario = 0;
         int campoItem = 1;
         int campoVoto = 2;
         int numVotosceros = 0;
         for (int r = 0; r < totalTest; r++)
         {
             int u = (int)(votos[r][campoUsuario]);
             int i = (int)(votos[r][campoItem]);
             double votoReal = votos[r][campoVoto];
             double prediccion = predecirVotoFCM(u, i, l);
             if (prediccion == 0) numVotosceros++;
             MAE = MAE + Math.abs(votoReal - prediccion);
         }
         MAE = MAE / totalTest;
         return MAE;
     }
     
     public double calcularMAEFCMeans(int L)
     {
         double MAE = calcularMAE_FCMeans(L);
         return MAE;
     }
     
     @SuppressWarnings("unused")
	public double predecirVotoFCM(int u, int i, int l)
     {
         double prediccion = 0.0;
         int cantVotosTomados = l;
         double sumatoriaPesos = 0.0;
         for (int v = 0; v < l; v++)
         {
             double votoVecino = 0.0;
             int vecino = centroidesVecinosporUsuario[u][v];
             votoVecino = centroides[vecino][i];
             sumatoriaPesos = sumatoriaPesos + pesoscentroidesVecinosporUsuario[u][v];
             prediccion = prediccion + (votoVecino * pesoscentroidesVecinosporUsuario[u][v]);
         }
         prediccion = prediccion / sumatoriaPesos;  //media ponderada
         if (sumatoriaPesos == 0.0) { prediccion = ((minVoto + maxVoto) / 2.0); }
         if (prediccion == 0.0) { prediccion = ((minVoto + maxVoto) / 2.0); }//dejar como indiferente en el caso de no tener info para predecir.

         double pui = 0.0;
         pui = (prediccion - 1.0) / R;
         if (pui >= 0 && pui < 0.2)
         {
             prediccion = 1;
         }
         else if (pui >= 0.2 && pui < 0.4)
         {
             prediccion = 2;
         }
         else if (pui >= 0.4 && pui < 0.6)
         {
             prediccion = 3;
         }
         else if (pui >= 0.6 && pui < 0.8)
         {
             prediccion = 4;
         }
         else if (pui >= 0.8)
         {
             prediccion = 5;
         }
         return prediccion;
     }
     double minVoto;
     double maxVoto;
     
     
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public void determinarCentroidesVecinosFCMeans(int l)
     {
         centroidesVecinosporUsuario = new int[usuarios][l];
         pesoscentroidesVecinosporUsuario = new double[usuarios][l];
         for (int u = 0; u < Yuk.length; u++) 
         {
				ArrayList vecinos= new ArrayList<>();
				for (int c = 0; c < k; c++) 
				{
					double sim = Norma(u,c);
					vecinos.add(new Part(c,sim));	
				}
				
				//ordena de menor a mayor
				Collections.sort(vecinos, new Comparator<Part>() 
				{
					@Override
					public int compare(Part parte1, Part parte2) 
					{	
						String num1=""+parte1.getPartProb()*1000;
						String num2=""+parte2.getPartProb()*1000;
						
						int v1=Integer.parseInt(num1.substring(0,num1.indexOf(".")));
						int v2=Integer.parseInt(num2.substring(0,num2.indexOf(".")));;
						
						return new Integer(v1).compareTo(new Integer(v2));
					}
				});
				
				int numVecino = 0;
				
				List<Part> temp = vecinos.subList(0, l);
				
				
				for(Part vecino : temp)
				{
					centroidesVecinosporUsuario[u][numVecino] = vecino.PartId;
					pesoscentroidesVecinosporUsuario[u][numVecino] = vecino.PartProb;
					numVecino++;
					//System.out.println("El cont="+numVecino+" "+vecino.PartProb);
				}
         	}
     	}
}