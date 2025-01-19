package modelo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

public class GestorAlquileres 
{	
	private static GestorAlquileres miGestorAlquileres = new GestorAlquileres();
	private HashMap<Integer, Alquiler> alquileres; //int=key (idAlquiler) y Alquileres=Value
	
	private GestorAlquileres()
	{
		this.alquileres = new HashMap<Integer, Alquiler>();
	}
	
	public static GestorAlquileres getGestorAlquileres()
	{
		return miGestorAlquileres;
	}

	public void crearYadd(int idUsuario, int idPeli, LocalDateTime ahora)
	{
		// Convertir LocalDateTime a Date
	    Date fecha = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
	    
		Alquiler alqui = new Alquiler(idUsuario, idPeli, fecha);
		
		//buscar usuario y anadir alqui
        GestorUsuarios gesUs = GestorUsuarios.getGestorUsuarios();
        gesUs.getUsuario(idUsuario).anadirAlqui(alqui);
		
		//hacer lo de buscar idmayor+1
		 int nuevaClave;
		 if (alquileres.isEmpty()) 
		 {
		        nuevaClave = 1;
		 } 
		 else 
		 {
		      nuevaClave = Collections.max(alquileres.keySet()) + 1;	    
		 }
		 alquileres.put(nuevaClave, alqui);
		
	}

	public void cargarDatos() 
	{
		
		 GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
		    String query = "SELECT * FROM Alquiler"; // Consulta para obtener todos los alquileres

		    try {
		        ResultadoSQL resultado = gestorBD.consultaSQL(query); // Ejecutar la consulta SQL

		        // Recorrer los resultados
		        while (resultado.next()) {
		            // Leer los datos de la fila actual
		            int idUsuario = resultado.getInt("idUsuario");
		            int idPelicula = resultado.getInt("idPelicula");
		            String fechaStr = resultado.getString("fecha"); // La fecha como String

		            // Convertir la fecha en formato String a un objeto Date
		            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		            Date fecha = dateFormat.parse(fechaStr);

		            // Crear el objeto Alquiler
		            Alquiler alquiler = new Alquiler(idUsuario, idPelicula, fecha);
		            
		            //buscar usuario y anadir alqui
		            GestorUsuarios gesUs = GestorUsuarios.getGestorUsuarios();
		            gesUs.getUsuario(idUsuario).anadirAlqui(alquiler);
		            

		            // Calcular la clave para el HashMap
		            int nuevaClave;
		            if (alquileres.isEmpty()) {
		                nuevaClave = 1;
		            } else {
		                nuevaClave = Collections.max(alquileres.keySet()) + 1;
		            }

		            // Añadir el alquiler al HashMap
		            alquileres.put(nuevaClave, alquiler);
		        }

		        System.out.println("Alquileres cargados exitosamente.");

		        // Imprimir los alquileres para comprobar (opcional, puede eliminarse más adelante)
		        for (Entry<Integer, Alquiler> entry : alquileres.entrySet()) 		        
		        {		            
		        	Integer idAlquiler = entry.getKey();
		            Alquiler alquiler = entry.getValue();
		            System.out.println("ID Alquiler: " + idAlquiler);
		            System.out.println(alquiler); // Asume que la clase Alquiler tiene un método toString()
		        }
		        System.out.println("---------------------------------------------");
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new RuntimeException("Error cargando los alquileres desde la base de datos.", e);
		    }
		
	}
}
