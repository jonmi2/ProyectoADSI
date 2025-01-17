package modelo;
import java.util.*;
import java.io.InputStream;
import java.sql.*;

import java.util.*;
import java.io.InputStream;
import java.sql.*;

public class GestorBD 
{	
	private static GestorBD miGestorBD = new GestorBD();
	private static final String URL = "jdbc:sqlite:test.db"; // Cambiar por la ruta de tu archivo SQLite
	
	private GestorBD() { }
	
	public static GestorBD getGestorBD()
	{
		return miGestorBD;
	}
	
	// Para ejecutar llamadas a la BD que no devuelven nada (INSERT, DELETE, UPDATE)
	public void execSQL(String sentencia)
	{
		try (Connection connection = DriverManager.getConnection(URL); PreparedStatement statement = connection.prepareStatement(sentencia)) 
		{
			statement.executeUpdate();        
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando la sentencia: " + sentencia, e);
		}
	}
	
	// Para ejecutar consultas (SELECT)	
	public ResultadoSQL consultaSQL(String sentencia)
	{
		try (Connection connection = DriverManager.getConnection(URL); PreparedStatement statement = connection.prepareStatement(sentencia); ResultSet resultSet = statement.executeQuery()) 
		{
			// Procesar el ResultSet en una estructura de filas y columnas
			List<Map<String, Object>> filas = new ArrayList<>();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) 
			{
				Map<String, Object> fila = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) 
				{
					fila.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				filas.add(fila);
			}
			return new ResultadoSQL(filas);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando la consulta: " + sentencia, e);
		}
	}
	
	// Método para inicializar la base de datos
	public void inicializarBaseDeDatos() 
	{
	    try (Connection conn = DriverManager.getConnection(this.URL)) 
	    {
	        if (conn != null)
	        {
	            // Verificar si la tabla 'Usuario' ya existe
	            String checkTableSQL = "SELECT name FROM sqlite_master WHERE type='table' AND name='Usuario';";
	            ResultadoSQL resultado = consultaSQL(checkTableSQL);
	            
	            // Si la tabla 'Usuario' no existe, creamos las tablas
	            if (resultado.next() == false) 
	            {
	                // Crear las tablas (Usuario, Pelicula, ListaPersonalizada, Resena, Alquiler, Pertenece)
	                // Crear la tabla 'Usuario'
	                String createUsuarioTable = "CREATE TABLE Usuario (" +
	                        "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
	                        "nombre VARCHAR(100) NOT NULL," +
	                        "email VARCHAR(100) NOT NULL," +
	                        "rol VARCHAR(50)," +
	                        "eliminadoPor INTEGER," +
	                        "aceptadoPor INTEGER," +
	                        "FOREIGN KEY (eliminadoPor) REFERENCES Usuario(idUsuario)," +
	                        "FOREIGN KEY (aceptadoPor) REFERENCES Usuario(idUsuario)" +
	                        ");";
	                execSQL(createUsuarioTable);
	                
	                // Crear la tabla 'Pelicula'
	                String createPeliculaTable = "CREATE TABLE Pelicula (" +
	                        "idPelicula INTEGER PRIMARY KEY AUTOINCREMENT," +
	                        "titulo VARCHAR(255) NOT NULL," +
	                        "reparto TEXT," +
	                        "anio INTEGER," +
	                        "puntuacion DECIMAL(3,2)," +
	                        "idAceptador INTEGER," +
	                        "FOREIGN KEY (idAceptador) REFERENCES Usuario(idUsuario)" +
	                        ");";
	                execSQL(createPeliculaTable);

	                // Crear la tabla 'Lista Personalizada'
	                String createListaPersonalizadaTable = "CREATE TABLE ListaPersonalizada (" +
	                        "idLista INTEGER PRIMARY KEY AUTOINCREMENT," +
	                        "nombreLista VARCHAR(255) NOT NULL," +
	                        "estado VARCHAR(50)," +
	                        "idUsuario INTEGER," +
	                        "FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)" +
	                        ");";
	                execSQL(createListaPersonalizadaTable);

	                // Crear la tabla 'Resena'
	                String createResenaTable = "CREATE TABLE Resena (" +
	                        "idUsuario INTEGER," +
	                        "idPelicula INTEGER," +
	                        "comentario TEXT," +
	                        "puntuacion DECIMAL(3,2)," +
	                        "PRIMARY KEY (idUsuario, idPelicula)," +
	                        "FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)," +
	                        "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula)" +
	                        ");";
	                execSQL(createResenaTable);

	                // Crear la tabla 'Alquiler'
	                String createAlquilerTable = "CREATE TABLE Alquiler (" +
	                        "idUsuario INTEGER," +
	                        "idPelicula INTEGER," +
	                        "fecha DATE," +
	                        "PRIMARY KEY (idUsuario, idPelicula)," +
	                        "FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)," +
	                        "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula)" +
	                        ");";
	                execSQL(createAlquilerTable);

	                // Crear la tabla 'Pertenece'
	                String createPerteneceTable = "CREATE TABLE Pertenece (" +
	                        "idPelicula INTEGER," +
	                        "idLista INTEGER," +
	                        "PRIMARY KEY (idPelicula, idLista)," +
	                        "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula)," +
	                        "FOREIGN KEY (idLista) REFERENCES ListaPersonalizada(idLista)" +
	                        ");";
	                execSQL(createPerteneceTable);
	            }

	            // Verificar si la tabla 'Usuario' tiene datos
	            String checkUsuarioDataSQL = "SELECT COUNT(*) AS count FROM Usuario;";
	            ResultadoSQL resultadoUsuario = consultaSQL(checkUsuarioDataSQL);

	            // Si no hay registros en la tabla 'Usuario', insertar los datos
	            if (resultadoUsuario.next()) 
	            {
	                int usuarioCount = Integer.parseInt(resultadoUsuario.getString("count"));
	                // Verificar si la tabla 'Pelicula' tiene datos
	                String checkPeliculaDataSQL = "SELECT COUNT(*) AS count FROM Pelicula;";
	                ResultadoSQL resultadoPelicula = consultaSQL(checkPeliculaDataSQL);
	                
	                if (resultadoPelicula.next()) 
	                {
	                    int peliculaCount = Integer.parseInt(resultadoPelicula.getString("count"));
	                    
	                    // Si ambas tablas están vacías, insertar los datos iniciales
	                    if (usuarioCount == 0 && peliculaCount == 0) 
	                    {
	                        // Insertar un usuario con rol admin
	                        String insertAdmin = "INSERT INTO Usuario (nombre, email, rol, aceptadoPor) VALUES ('Juan Pérez', 'juan@example.com', 'admin', NULL)";
	                        execSQL(insertAdmin);

	                        // Insertar usuarios con rol 'usuario registrado', todos aceptados por el admin (idUsuario = 1)
	                        String insertUsuarios = "INSERT INTO Usuario (nombre, email, rol, aceptadoPor) "
	                                + "VALUES ('María López', 'maria@example.com', 'usuario registrado', 1), "
	                                + "('Carlos García', 'carlos@example.com', 'usuario registrado', 1)";
	                        execSQL(insertUsuarios);

	                        // Insertar películas con el idAceptador del admin (idUsuario = 1)
	                        String insertPeliculas = "INSERT INTO Pelicula (titulo, reparto, anio, puntuacion, idAceptador) "
	                                + "VALUES ('Inception', 'Leonardo DiCaprio, Joseph Gordon-Levitt', 2010, 8.8, 1), "
	                                + "('The Matrix', 'Keanu Reeves, Laurence Fishburne', 1999, 8.7, 1), "
	                                + "('The Dark Knight', 'Christian Bale, Heath Ledger', 2008, 9.0, 1)";
	                        execSQL(insertPeliculas);
	                    }
	                }
	            }
	        } 
	    } 
	    catch (SQLException e)
	    {
	        System.out.println("Error de conexión o SQL: " + e.getMessage());
	    }
	}




	
	private boolean tablasExisten(Connection connection) 
	{
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT COUNT(*) AS cantidad FROM sqlite_master WHERE type='table' AND name='Usuario'"); 
			 ResultSet resultSet = statement.executeQuery()) 
		{
			if (resultSet.next()) 
			{
				return resultSet.getInt("cantidad") > 0;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Error verificando las tablas existentes", e);
		}
		return false;
	}
	
	// Método que verifica si el admin ya existe en la base de datos
	public boolean adminExiste() {
		String query = "SELECT COUNT(*) FROM Usuario WHERE rol = 'admin'";
		try (Connection connection = DriverManager.getConnection(URL); 
			 PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet resultSet = statement.executeQuery()) {
			
			if (resultSet.next()) {
				return resultSet.getInt(1) > 0; // Si el conteo es mayor que 0, el admin ya existe
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
