package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorListaPersonalizada {

    private static GestorListaPersonalizada miGestorListaPerso = new GestorListaPersonalizada();

    private GestorListaPersonalizada() { }

    public static GestorListaPersonalizada getGestorListaPersonalizada() {
        return miGestorListaPerso;
    }

    public List<List<String>> buscarPelicula(String nombrePelicula) {
        try {
        	URL url = new URL("https://www.omdbapi.com/?apikey=a578d477&s=" + urlEncode(nombrePelicula) + "&type=movie");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                System.out.println(content.toString());
                return obtenerDatosPeliculas(content.toString());
            } else {
                System.out.println("GET request failed");
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String urlEncode(String pelicula) {
        String encodedPelicula = "";
        try {
            encodedPelicula = URLEncoder.encode(pelicula, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodedPelicula;
    }
    
    public List<List<String>> obtenerDatosPeliculas(String jsonResponse) {
        List<List<String>> datosPeliculas = new ArrayList<>(); // Lista principal que contiene listas de datos de películas

        try {
            // Extraer la parte del array "Search"
            String searchKey = "\"Search\":";
            int startIndex = jsonResponse.indexOf(searchKey);
            if (startIndex == -1) {
                return datosPeliculas; // No se encuentra la clave "Search"
            }

            // Obtener solo el contenido del array después de "Search"
            String searchArray = jsonResponse.substring(startIndex + searchKey.length()).trim();

            // Eliminar los corchetes que encierran el array
            if (searchArray.startsWith("[")) {
                searchArray = searchArray.substring(1, searchArray.length() - 1).trim();
            }

            // Dividir el array de objetos de películas por los objetos separados por "},{"
            String[] peliculas = searchArray.split("},\\{");

            // Recorrer cada objeto de película y extraer los datos
            for (String pelicula : peliculas) {
                List<String> peliInfo = new ArrayList<>(); // Crear una nueva lista para cada película

                // Buscar la clave "Title" y extraer su valor
                String titleKey = "\"Title\":\"";
                int titleStartIndex = pelicula.indexOf(titleKey);
                if (titleStartIndex != -1) {
                    int titleEndIndex = pelicula.indexOf("\"", titleStartIndex + titleKey.length());
                    if (titleEndIndex != -1) {
                        String titulo = pelicula.substring(titleStartIndex + titleKey.length(), titleEndIndex);
                        peliInfo.add(titulo); // Agregar el título a la lista de datos de la película
                    }
                }

                // Buscar el año
                String yearKey = "\"Year\":\"";
                int yearStartIndex = pelicula.indexOf(yearKey);
                if (yearStartIndex != -1) {
                    int yearEndIndex = pelicula.indexOf("\"", yearStartIndex + yearKey.length());
                    if (yearEndIndex != -1) {
                        String year = pelicula.substring(yearStartIndex + yearKey.length(), yearEndIndex);
                        peliInfo.add(year); // Agregar el año a la lista de datos de la película
                    }
                }

                // Buscar el poster
                String posterKey = "\"Poster\":\"";
                int posterStartIndex = pelicula.indexOf(posterKey);
                if (posterStartIndex != -1) {
                    int posterEndIndex = pelicula.indexOf("\"", posterStartIndex + posterKey.length());
                    if (posterEndIndex != -1) {
                        String poster = pelicula.substring(posterStartIndex + posterKey.length(), posterEndIndex);
                        peliInfo.add(poster); // Agregar el poster a la lista de datos de la película
                    }
                }

                // Agregar la lista de datos de la película a la lista principal
                datosPeliculas.add(peliInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datosPeliculas; // Devolver la lista de listas
    }


    // Nueva función para obtener películas aleatorias
    public List<String> obtenerPeliculasRandom(int cantidad) {
        // Lista de películas predefinidas para selección aleatoria
        List<String> peliculas = new ArrayList<>();
        peliculas.add("Inception");
        peliculas.add("The Matrix");
        peliculas.add("Interstellar");
        peliculas.add("The Dark Knight");
        peliculas.add("Pulp Fiction");
        peliculas.add("Fight Club");
        peliculas.add("Forrest Gump");
        peliculas.add("The Shawshank Redemption");
        peliculas.add("The Godfather");
        peliculas.add("The Lord of the Rings");

        // Mezclar y seleccionar las primeras `cantidad`
        Collections.shuffle(peliculas);
        return peliculas.subList(0, Math.min(cantidad, peliculas.size()));
    }

    // Método para buscar películas aleatorias en la API (opcional)
//    public List<String> buscarPeliculasRandomEnAPI(int cantidad) {
//        List<String> nombresAleatorios = generarNombresAleatorios(cantidad);
//        List<String> resultados = new ArrayList<>();
//
//        for (String nombre : nombresAleatorios) {
//            try {
//                String encodedNombre = urlEncode(nombre);
//                URL url = new URL("https://omdbapi.com/?apikey=a578d477&t=" + encodedNombre + "&r=json");
//                String resultado = buscarPelicula(url);
//
//                if (!resultado.isEmpty()) {
//                    resultados.add(resultado);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return resultados;
//    }

    private List<String> generarNombresAleatorios(int cantidad) {
        // Lista de términos base para generar búsquedas aleatorias
        String[] palabrasClave = {"Star", "Dark", "Love", "Dream", "War", "Life", "Adventure", "Moon", "Night", "Hero"};
        List<String> nombresAleatorios = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = palabrasClave[(int) (Math.random() * palabrasClave.length)];
            nombresAleatorios.add(nombre);
        }

        return nombresAleatorios;
    }
}
