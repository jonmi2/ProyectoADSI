package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class GestorUsuarios 
{
	private static GestorUsuarios misUsuarios = new GestorUsuarios();
	private HashMap<Integer, Usuario> usuarios; //int=key y Usuario=Value
	
	public GestorUsuarios()
	{
		usuarios = new HashMap<Integer, Usuario>();
	}
	
	public static GestorUsuarios getGestorUsuarios()
	{
		return misUsuarios;
	}

	public void cargarDatos()
	{
		GestorBD gestorBD = GestorBD.getGestorBD(); // Obtener la instancia del Singleton
	    String query = "SELECT * FROM Usuario"; // Consulta para obtener todos los usuarios

	    try 
	    {
	        ResultadoSQL resultado = gestorBD.consultaSQL(query); // Ejecutar la consulta SQL
	        
	        // Recorrer los resultados
	        while (resultado.next()) {
	            // Leer los datos de la fila actual
	            int idUsuario = resultado.getInt("idUsuario");
	            String nombre = resultado.getString("nombre");
	            String email = resultado.getString("email");
	            String rol = resultado.getString("rol");
	            
	            int aceptadoPor;
	            int eliminadoPor;
	            
	            String eliminadoPorStr = resultado.getString("eliminadoPor");
	            if (eliminadoPorStr == null || eliminadoPorStr.isEmpty()) 
	            {
	                //caso en el que "eliminadoPor" sea null en la BD
	            	eliminadoPor=0;
	            } 
	            else 
	            {
	                eliminadoPor = Integer.parseInt(eliminadoPorStr);            
	            }

	            String aceptadoPorStr = resultado.getString("aceptadoPor");
	            if (aceptadoPorStr == null || aceptadoPorStr.isEmpty()) 
	            {
	                //caso en el que "aceptadoPorStr" sea null en la BD
	            	aceptadoPor=0;
	            } 
	            else 
	            {
	                aceptadoPor = Integer.parseInt(aceptadoPorStr);            
	            }

	            // Crear listas vac�as para susListas y susAlquileres
	            ArrayList<ListaPersonalizada> susListas = new ArrayList<>();
	            ArrayList<Alquiler> susAlquileres = new ArrayList<>();
	            ArrayList<Resena> susResenas = new ArrayList<>();

	            // Crear el objeto Usuario
	            Usuario usuario = new Usuario(
	                    idUsuario, nombre, email, rol, susListas, susAlquileres, eliminadoPor, aceptadoPor
	            );
	            
	            // A�adir al HashMap (clave: idUsuario, valor: Usuario)
	            usuarios.put(idUsuario, usuario);
	        }

	        System.out.println("Usuarios cargados exitosamente.");

	        // Imprimir usuarios para comprobar (esto puede eliminarse m�s adelante)
	        for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
	            Integer idUsuario = entry.getKey();
	            Usuario usuario = entry.getValue();
	            System.out.println("ID Usuario: " + idUsuario);
	            System.out.println(usuario); // Asume que la clase Usuario tiene un m�todo toString()
	        }
	        System.out.println("---------------------------------------------");
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        throw new RuntimeException("Error cargando los usuarios desde la base de datos.", e);
	    }
		
	}

	

	
	
	public boolean puedeIniciarsSesion(int pidUsuario) 
	{
		//Como en la vista principal ejecutas la funcion de cargar los datos no hace falta volver a hace runa consulta
		if (usuarios.containsKey(pidUsuario))
		{
			if (usuarios.get(pidUsuario).getRol().equals("admin") || usuarios.get(pidUsuario).getRol().equals("usuario registrado")) 
			{
				return true; //solo se puede iniciar sesion en caso de que el usuario exista y su rol sea administrador o usuario registrado
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	
	public HashMap<Integer, Usuario> getUsuarios() {
		return this.usuarios;
	}

	public String buscarNombreUsuario(int idUsuario) {
		// Imprimir usuarios para comprobar (esto puede eliminarse m�s adelante)
		System.out.println("IMPRIMIR DND DA ERROR");
		System.out.println("IMPRIMIR ID POR PARAMETRO"+idUsuario); 
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
            Integer idU = entry.getKey();
            Usuario usuario = entry.getValue();
            System.out.println("ID Usuario: " + idU);
        }
        
        return usuarios.get(idUsuario).getNombre();
	}

	public String getRolUsuario(int pid) {
		return usuarios.get(pid).getRol();
	}
	
	public Usuario getUsuario(int idUsuario) {
		
		return this.usuarios.get(idUsuario);
		
	}
	
}
