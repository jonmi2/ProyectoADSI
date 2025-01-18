package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class GestorResenas 
{
	private static GestorResenas miGestorResenas = new GestorResenas();
	private HashMap<Integer, Resena> resenas; //int=key (idResena) y Resena=Value
	
	private GestorResenas()
	{
		this.resenas = new HashMap<Integer, Resena>();
	}
	
	public static GestorResenas getGestorResenas()
	{
		return miGestorResenas;
	}
	
	public void anadirResena(Resena res)
	{
		int nuevaClave;
		if (resenas.isEmpty()) 
		{
		        nuevaClave = 1;		    
		} 
		else 
		{
				nuevaClave = Collections.max(resenas.keySet()) + 1;
		}
		resenas.put(nuevaClave, res);
	}

	public JSON3 getResenasPeli(int idPeli) 
	{
		JSON3 json = new JSON3(idPeli);
		
		// Recorremos el HashMap de nuestras resenas
	    for (Resena resena : resenas.values()) 
	    {
	        // Comparar el id de la peli de la resena con el id recibido
	        if (resena.getIdPelicula()==idPeli) 
	        {
	            // cuando encontremos la peli rellenarlo con los datos de la resena
	           json.anadirDatosResena(resena.getPuntuacion(), resena.getComentario());
	        }
	    }
	    
	    return json;
	}

	public void cargarDatos() 
	{
		GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Resena"; // Consulta para obtener todas las resenas	    
	    
	    try {
	        ResultadoSQL resultado = gestorBD.consultaSQL(query); // Ejecutar la consulta SQL

	        // Recorrer los resultados
	        while (resultado.next()) {            
	        	// Leer los datos de la fila actual
	            int idUsuario = resultado.getInt("idUsuario");
	            int idPelicula = resultado.getInt("idPelicula");
	            String coment = resultado.getString("comentario");
	            float puntuacion = resultado.getFloat("puntuacion");

	           
	            // Crear el objeto Resena
	            Resena resena = new Resena(
	            		idUsuario, idPelicula, puntuacion, coment
	            );

	            
	            // Añadir al HashMap
	            this.anadirResena(resena);
	        }

	        System.out.println("Resenas cargadas exitosamente.");

	        // Imprimir resenas para comprobar (esto puede eliminarse más adelante)
	        for (Entry<Integer, Resena> entry : resenas.entrySet()) {
	        	Integer idRes = entry.getKey();
	            Resena resena = entry.getValue();
	            System.out.println("ID resena: " + idRes);
	            System.out.println(resena); // Asume que la clase resena tiene un método toString()
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando las resenaas desde la base de datos.", e);
	    }
	}
}
