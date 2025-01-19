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
	private static final String URL = "jdbc:sqlite:adsidb.db"; // Cambiar por la ruta de tu archivo SQLite
	
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
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(this.URL)) 
	    {
			return conn.prepareStatement(sql);
	    }
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Error ejecutando la consulta: " + sql, e);
		}
        
    }
	
	// Metodo para inicializar la base de datos con unos datos para poder utilizar la aplicaci�n
	public void inicializarBaseDeDatos() 
	{
	    try (Connection conn = DriverManager.getConnection(this.URL)) 
	    {
	        if (conn != null)
	        {
	            // Verificar si la tabla 'Usuario' ya existe (con esto hacemos que solo se cree la BD la primera vez que ejecutemos)
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
	                
	                String admin = "INSERT INTO Usuario (" +
	                        "idUsuario, nombre, email, rol, eliminadoPor, aceptadoPor" +
	                        ") VALUES (" +
	                        "1, 'Mbappe', 'admin@example.com', 'admin', NULL, NULL" +
	                        ");";
	                execSQL(admin);
	                
	                // Insertar usuarios con rol 'usuario registrado', todos aceptados por el admin (idUsuario = 1)
                    String insertUsuarios = "INSERT INTO Usuario (idUsuario, nombre, email, rol, aceptadoPor) "
                            + "VALUES (2, 'Maria Lopez', 'maria@example.com', 'usuario registrado', 1), "
                            + "(3, 'Carlos Garcia', 'carlos@example.com', 'usuario registrado', 1)";
                    execSQL(insertUsuarios);
	                
	                
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
	                
	                // Insertar peliculas con el idAceptador del admin (idUsuario = 1)
                    String insertPeliculas = "INSERT INTO Pelicula (idPelicula, titulo, reparto, anio, idAceptador) "
                            + "VALUES (1, 'Inception', 'Leonardo DiCaprio, Joseph Gordon-Levitt', 2010, 1), "
                            + "(2, 'The Matrix', 'Keanu Reeves, Laurence Fishburne', 1999, 1), "
                            + "(3, 'The Dark Knight', 'Christian Bale, Heath Ledger', 2008, 1)";
                    execSQL(insertPeliculas);

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
	                
	             // Insertar rese�as para la pel�cula con idPelicula = 1
	                String insertResenas = "INSERT INTO Resena (idUsuario, idPelicula, comentario, puntuacion) " +
	                        "VALUES " +
	                        "(2, 1, 'Excelente pel�cula, tiene un guion incre�ble y actuaciones espectaculares.', 9.0), " +
	                        "(3, 1, 'Una obra maestra visual y narrativa, simplemente brillante.', 8.5);";
	                execSQL(insertResenas);


	                // Crear la tabla 'Alquiler'
	                String createAlquilerTable = "CREATE TABLE Alquiler (" +
	                        "idUsuario INTEGER," +
	                        "idPelicula INTEGER," +
	                        "fecha DATE," +
	                        "PRIMARY KEY (idUsuario, idPelicula, fecha)," +
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
	        } 
	    } 
	    catch (SQLException e)
	    {
	        System.out.println("Error de conexion o SQL: " + e.getMessage());
	    }
	}
}
