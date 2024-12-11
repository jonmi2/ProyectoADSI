package modelo;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL 
{
	  public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/adsibd";
	        String user = "root"; //Usuario por defecto en XAMPP

	        String password = "";  // Contraseña vacía por defecto en XAMPP

	        //establecer conexión
	        try (Connection connection = DriverManager.getConnection(url, user, password)) {
	            System.out.println("¡Conexión exitosa a MySQL!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
