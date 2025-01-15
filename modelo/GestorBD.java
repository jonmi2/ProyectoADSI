package modelo;
import java.util.*;
import java.sql.*;

public class GestorBD 
{	
	private static GestorBD miGestorBD = new GestorBD();
	private static final String URL = "jdbc:mysql://localhost:3306/adsibd";
	private static final String USER = "root";
    private static final String PASSWORD = "";
	
	
	private GestorBD()
	{
		
	}
	
	public static GestorBD getGestorBD()
	{
		return miGestorBD;
	}
	
	//para ejecutar llamadas a la BD que no devuelven nada (INSERT, DELETE, UPDATE)
	
	public void execSQL(String sentencia)
	{
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = connection.prepareStatement(sentencia)) 
		{
	            statement.executeUpdate();
	    } 
		catch (SQLException e) 
		{
	            e.printStackTrace();
	            throw new RuntimeException("Error ejecutando la sentencia: " + sentencia, e);
	    }
	}
	
	//para ejecutar llamadas consultas (SELECT)	
	public ResultadoSQL consultaSQL(String sentencia)
	{
	      try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = connection.prepareStatement(sentencia); ResultSet resultSet = statement.executeQuery()) 
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
}
