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
	
	private GestorUsuarios()
	{
		usuarios = new HashMap<Integer, Usuario>();
	}
	
	public static GestorUsuarios getGestorUsuarios()
	{
		return misUsuarios;
	}

	public void cargarDatos() {
		// metodo para cargar los datos de la base de datos
		
		String url = "jdbc:mysql://localhost:3306/adsibd";
        String user = "root"; //Usuario por defecto en XAMPP

        String password = "";  // Contrase�a vac�a por defecto en XAMPP

        //establecer conexi�n
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Cargar Usuarios");
         // Consulta para obtener todos los usuarios
            String query = "SELECT * FROM Usuario";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Recorrer los resultados
                while (resultSet.next()) {
                    // Leer los datos de la fila actual
                    int idUsuario = resultSet.getInt("idUsuario");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    String rol = resultSet.getString("rol");
                    int eliminadoPor = resultSet.getInt("eliminadoPor");
                    int aceptadoPor = resultSet.getInt("aceptadoPor");

                    // Crear listas vac�as para susListas y susAlquileres
                    // ---falta terminar
                    // Estas listas deben llenarse desde otras tablas de la base de datos 
                    ArrayList<ListaPersonalizada> susListas = new ArrayList<>();
                    ArrayList<Alquiler> susAlquileres = new ArrayList<>();

                    // Crear el objeto Usuario
                    Usuario usuario = new Usuario(
                            idUsuario, nombre, email, rol, susListas, susAlquileres, eliminadoPor, aceptadoPor
                    );

                    // A�adir al HashMap (clave: idUsuario, valor: Usuario)
                    usuarios.put(idUsuario, usuario);
                    System.out.println(usuarios);
                }
            }

            System.out.println("Usuarios cargados exitosamente.");
          //IMPRIMIR USUARIOS PARA COMPROBAR-- ESTO LO QUITAMOS LUEGO
                for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
                    Integer idUsuario = entry.getKey(); // Clave (idUsuario)
                    Usuario usuario = entry.getValue(); // Valor (Usuario)

                    System.out.println("ID Usuario: " + idUsuario);
                    System.out.println(usuario); // Llamar� al m�todo toString() de Usuario
                }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	public boolean puedeIniciarsSesion(int pidUsuario) 
	{
//		boolean rdo = false;
//		
//		String url = "jdbc:mysql://localhost:3306/adsibd";
//        String user = "root"; //Usuario por defecto en XAMPP
//
//        String password = "";  // Contrase�a vac�a por defecto en XAMPP
//
//        //establecer conexi�n
//        try (Connection connection = DriverManager.getConnection(url, user, password)) 
//        {
//            System.out.println("�Conexi�n exitosa a MySQL!");
//         // Consulta SQL para verificar la existencia del usuario y su rol
//            String query = "SELECT rol FROM Usuario WHERE idUsuario = ?";
//
//            try (PreparedStatement statement = connection.prepareStatement(query)) 
//            {
//            	// Establecer el par�metro del idUsuario en la consulta
//                statement.setInt(1, Integer.parseInt(pidUsuario));
//
//                // Ejecutar la consulta
//                try (ResultSet resultSet = statement.executeQuery()) 
//                {
//                    // Si se encuentra el usuario
//                    if (resultSet.next()) 
//                    {
//                        String rol = resultSet.getString("rol");
//                        // Comprobar si el rol es "usuario registrado"
//                        if ("usuario registrado".equalsIgnoreCase(rol)) 
//                        {
//                            rdo= true;
//                        }
//                    }
//                    else
//                    {
//                    	rdo= false;
//                    }            
//                }                   
//            } 
//            catch (Exception e) 
//            {
//            	e.printStackTrace();
//            	rdo = false;
//            }
//        } 
//        catch (SQLException e1) 
//        {
//        	rdo=false;
//        	e1.printStackTrace();
//        }
//        
//        return rdo;
		
		//Como en la vista principal ejecutas la funcion de cargar los datos no hace falta volver a hace runa consulta
		return usuarios.containsKey(pidUsuario);
		
	}
	
	
	public HashMap<Integer, Usuario> getUsuarios() {
		return this.usuarios;
	}
	
}
