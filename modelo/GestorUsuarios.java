package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String password = "";  // Contraseña vacía por defecto en XAMPP

        //establecer conexión
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

                    // Crear listas vacías para susListas y susAlquileres
                    // ---falta terminar
                    // Estas listas deben llenarse desde otras tablas de la base de datos 
                    ArrayList<ListaPersonalizada> susListas = new ArrayList<>();
                    ArrayList<Alquiler> susAlquileres = new ArrayList<>();

                    // Crear el objeto Usuario
                    Usuario usuario = new Usuario(
                            idUsuario, nombre, email, rol, susListas, susAlquileres, eliminadoPor, aceptadoPor
                    );

                    // Añadir al HashMap (clave: idUsuario, valor: Usuario)
                    usuarios.put(idUsuario, usuario);
                }
            }

            System.out.println("Usuarios cargados exitosamente.");
          //IMPRIMIR USUARIOS PARA COMPROBAR-- ESTO LO QUITAMOS LUEGO
                for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
                    Integer idUsuario = entry.getKey(); // Clave (idUsuario)
                    Usuario usuario = entry.getValue(); // Valor (Usuario)

                    System.out.println("ID Usuario: " + idUsuario);
                    System.out.println(usuario); // Llamará al método toString() de Usuario
                }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
