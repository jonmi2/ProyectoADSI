package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.GestorListaPersonalizada;
import modelo.GestorUsuarios;
import modelo.ListaPersonalizada;
import modelo.Pelicula;
import modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListaPersonalizadaVista extends JFrame {

    // JPanels
    private JPanel panelListaPersonalizada;
    private JPanel panelTitulo;
    private JPanel panelListas;
    private JPanel panelBotonesPeliculas;

    // JLabels
    private JLabel tituloLabel;

    // JButtons
    private JButton crearListaButton;
    
    // JTextArea
    private JTextArea nombreListaArea;
    private JTextField nombreBuscarPelicula;
    
    //Modelo
    int idUsuario;
    static GestorUsuarios gUsuario;
    Usuario usuario;
    ArrayList<ListaPersonalizada> listasPersonalizadaUsuario;
    ArrayList<Pelicula> tempPeliculas = new ArrayList<>(); // Array para que se agregen las peliculas al hacer click en ellas
  
    
//	public void actualizar(int idUsuario) {
//		System.out.println(idUsuario);
//		this.idUsuario=idUsuario;
//	}

    public ListaPersonalizadaVista(int idUsuario) {
    	
    	this.idUsuario = idUsuario;
    	//CREAMOS EL GESTOR Y EL USUARIO PARA INTERACTUAR CON SUS LISTAS PERSONALIZADAS
    	System.out.println("FUNCIONA");
    	this.gUsuario = new GestorUsuarios().getGestorUsuarios();
        this.usuario = gUsuario.getUsuario(idUsuario);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Listas Personalizadas");

        // Configuración del panel principal
        panelListaPersonalizada = new JPanel();
        panelListaPersonalizada.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelListaPersonalizada.setLayout(new BorderLayout());
        setContentPane(panelListaPersonalizada);

        panelListaPersonalizada.add(getPanelTitulo(), BorderLayout.NORTH);
        
        //AQUIE CREAR UN PANEL CON LAS MEDIDAS PERSONALIZADAS Y AGREGARLO PARA QUE LUEGO PUEDA PONER LAS QUE YO QUIERA 
        
        panelListaPersonalizada.add(getPanelListas(), BorderLayout.CENTER);
    }
 

    private JPanel getPanelTitulo() {
        if (panelTitulo == null) {
            panelTitulo = new JPanel();
            panelTitulo.setLayout(new BorderLayout());
            panelTitulo.setPreferredSize(new Dimension(800, 60)); // Tamano preferido
            panelTitulo.setBackground(Color.LIGHT_GRAY);

            // Configurar el titulo
            tituloLabel = new JLabel("Listas Personalizadas", SwingConstants.CENTER);
            tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
            panelTitulo.add(tituloLabel, BorderLayout.CENTER);

            // Configurar el boton para crear una lista
            crearListaButton = new JButton("Crear Lista");
            crearListaButton.setPreferredSize(new Dimension(120, 40));
            crearListaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarEntradaNombreLista();
                }
            });
            panelTitulo.add(crearListaButton, BorderLayout.EAST);
        }
        return panelTitulo;
    }
    
    private void mostrarDetallesLista(ListaPersonalizada lista) {
        JOptionPane.showMessageDialog(
            this,
            "Detalles de la lista: " + lista.getNombreLista() + "\nNúmero de películas: " + lista.getPelis().size(),
            "Detalles de la Lista",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    
    private void mostrarDetallesPelicula(Pelicula pelicula) {
        // Crear el mensaje de detalles de la película
        String mensaje = "Nombre: " + pelicula.getTitulo() + 
                         "\nFecha de estreno: " + pelicula.getAnio() + 
                         "\nPuntuación: " + pelicula.getPuntuacionMedia();
        
        // Mostrar el mensaje usando JOptionPane con la ventana principal como referencia
        JOptionPane.showMessageDialog(
            this,  // 'this' hace referencia al JFrame actual (ventana principal)
            mensaje,  // El mensaje a mostrar
            "Detalles de la Película",  // Título de la ventana emergente
            JOptionPane.INFORMATION_MESSAGE  // Tipo de mensaje (informativo)
        );
    }


    private JPanel getPanelListas() {
        if (panelListas == null) {
            panelListas = new JPanel();
//            panelListas.setLayout(new BoxLayout(panelListas, BoxLayout.Y_AXIS)); // Cambiado a BoxLayout para un flujo vertical
            panelListas.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Fijamos el tamaño de panelListas a lo que deseamos
            panelListas.setPreferredSize(new Dimension(700, 200));  // Fijar tamaño deseado

            // Llamamos a la función para actualizar las listas
            actualizarVistaListas();
        }
        return panelListas;
    }

    private void actualizarVistaListas() {
        // Limpiar el panel de listas
        panelListas.removeAll();

        // Obtener las listas personalizadas del usuario
        listasPersonalizadaUsuario = usuario.getSusListas(); // Asegúrate de tener la lista de listas actualizada

        // Verificar si las listas están vacías
        if (listasPersonalizadaUsuario.isEmpty()) {
            JTextArea mensajeVacio = new JTextArea("No tienes listas personalizadas.");
            mensajeVacio.setEditable(false);
            mensajeVacio.setBackground(panelListas.getBackground());
            mensajeVacio.setFont(new Font("Arial", Font.ITALIC, 16));
            mensajeVacio.setForeground(Color.RED);
            mensajeVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelListas.add(mensajeVacio);
        } else {
            // Si hay listas, iteramos para mostrar los botones
            for (ListaPersonalizada lista : listasPersonalizadaUsuario) {
                // Crear el panel de la lista
                JPanel listaPanel = new JPanel();
                listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.X_AXIS)); // Layout en eje horizontal

                // Establecer un borde rojo alrededor del panel de la lista
                listaPanel.setBackground(Color.LIGHT_GRAY);
//                listaPanel.setBorder(new LineBorder(Color.BLACK, 1));  // Borde rojo con grosor de 2 píxeles

                // Fijar tamaño fijo para cada panel de lista (sin que se expanda)
                listaPanel.setPreferredSize(new Dimension(700, 100));  // Fijar tamaño (ancho 780px, altura 100px)

                // Panel izquierdo para el título de la lista y el botón "Ver más"
                JPanel izquierdaPanel = new JPanel();
                izquierdaPanel.setLayout(new BoxLayout(izquierdaPanel, BoxLayout.Y_AXIS)); // Layout vertical para el título y botón
                JTextArea listaNombreArea = new JTextArea(lista.getNombreLista());
                listaNombreArea.setEditable(false);
                listaNombreArea.setFont(new Font("Arial", Font.BOLD, 16));
                listaNombreArea.setBackground(izquierdaPanel.getBackground());
                listaNombreArea.setForeground(Color.BLACK);
                listaNombreArea.setLineWrap(true);
                listaNombreArea.setWrapStyleWord(true);
                listaNombreArea.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Crear el botón para ver más información
                JButton listaButton = new JButton("Ver más");
                listaButton.setPreferredSize(new Dimension(200, 40));  // Tamaño del botón
                listaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrarDetallesLista(lista);  // Método para mostrar detalles de la lista seleccionada
                    }
                });

                // Agregar el nombre de la lista y el botón al panel izquierdo
                izquierdaPanel.add(listaNombreArea);
                izquierdaPanel.add(listaButton);

                // Panel derecho para mostrar las películas
                JPanel derechaPanel = new JPanel();
                derechaPanel.setLayout(new BoxLayout(derechaPanel, BoxLayout.Y_AXIS)); // Layout vertical para el título y botones de películas
                JTextArea listaPeliculasNombre = new JTextArea("PELÍCULAS");
                listaPeliculasNombre.setEditable(false);
                listaPeliculasNombre.setFont(new Font("Arial", Font.BOLD, 16));
                listaPeliculasNombre.setBackground(derechaPanel.getBackground());
                listaPeliculasNombre.setForeground(Color.BLACK);
                listaPeliculasNombre.setLineWrap(true);
                listaPeliculasNombre.setWrapStyleWord(true);
                listaPeliculasNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Agregar el título "PELÍCULAS"
                derechaPanel.add(listaPeliculasNombre);

                // Agregar las películas al panel de películas
                for (Pelicula pelicula : lista.getPelis()) {
                    JButton peliculaButton = new JButton(pelicula.getTitulo());  // Mostrar título de la película
                    peliculaButton.setPreferredSize(new Dimension(150, 30)); // Ajusta el tamaño de los botones de películas
                    peliculaButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Acción para ver más detalles de la película
                            mostrarDetallesPelicula(pelicula);
                        }
                    });
                    derechaPanel.add(peliculaButton); // Agregar el botón de la película
                }

                // Agregar los dos paneles (izquierda y derecha) al panel de la lista
                listaPanel.add(izquierdaPanel); // Panel izquierdo (título + botón)
                listaPanel.add(derechaPanel);   // Panel derecho (PELÍCULAS + botones de películas)

                // Agregar el panel de la lista al panel principal
                panelListas.add(listaPanel);
            }
        }

        // Refrescar la vista para aplicar los cambios
        panelListas.revalidate();
        panelListas.repaint();
    }




    
    private void mostrarEntradaNombreLista() {
        // Crear el cuadro de diálogo personalizado
        JDialog dialog = new JDialog((JFrame) null, "Crear Lista y Buscar Película", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Panel principal para el diálogo
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Entrada para el nombre de la lista
        JPanel nombreListaPanel = new JPanel(new BorderLayout(10, 10));
        JLabel promptLabel = new JLabel("Nombre de la nueva lista:");
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        nombreListaArea = new JTextArea(1, 20);
        JScrollPane scrollPaneNombreLista = new JScrollPane(nombreListaArea);

        nombreListaPanel.add(promptLabel, BorderLayout.WEST);
        nombreListaPanel.add(scrollPaneNombreLista, BorderLayout.CENTER);

        // Entrada para buscar película
        JPanel buscarPeliculaPanel = new JPanel(new BorderLayout(10, 10));
        JLabel buscarLabel = new JLabel("Buscar película:");
        buscarLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        nombreBuscarPelicula = new JTextField(20);
        JButton buscarButton = new JButton("Buscar");
        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePelicula = nombreBuscarPelicula.getText().trim();
                List<List<String>> infoPeliculas = GestorListaPersonalizada.getGestorListaPersonalizada().buscarPelicula(nombrePelicula);

                mostrarBotonesConNombresPeliculas(infoPeliculas);
            }
        });

        buscarPeliculaPanel.add(buscarLabel, BorderLayout.WEST);
        buscarPeliculaPanel.add(nombreBuscarPelicula, BorderLayout.CENTER);
        buscarPeliculaPanel.add(buscarButton, BorderLayout.EAST);

        // Área para mostrar detalles de la película
        JPanel detallesPeliculaPanel = new JPanel(new BorderLayout(10, 10));
        JLabel detallesLabel = new JLabel("Detalles de la película:");
        detallesLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        panelBotonesPeliculas = new JPanel();
        panelBotonesPeliculas.setLayout(new GridLayout(0, 3, 10, 10));

        JScrollPane scrollPane = new JScrollPane(panelBotonesPeliculas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        detallesPeliculaPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para el botón "Crear" debajo del todo
        JPanel botonCrearPanel = new JPanel();
        JButton confirmarButton = new JButton("Crear");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreLista = nombreListaArea.getText().trim();
                if (!nombreLista.isEmpty()) {
                    
                    ListaPersonalizada lp = new ListaPersonalizada(nombreLista, "privada", idUsuario, tempPeliculas);
              
                    listasPersonalizadaUsuario.add(lp);
                    dialog.dispose(); // Cerrar el cuadro de diálogo después de crear la lista
                    
                    actualizarVistaListas();
                } else {
                    JOptionPane.showMessageDialog(dialog, "El nombre de la lista no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        botonCrearPanel.add(confirmarButton); // Añade el botón al panel

        // Agregar los paneles al panel principal
        inputPanel.add(nombreListaPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Espaciado entre componentes
        inputPanel.add(buscarPeliculaPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Espaciado entre componentes
        inputPanel.add(detallesPeliculaPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Espaciado entre detalles y botón
        inputPanel.add(botonCrearPanel); // Agrega el panel con el botón al final

        // Configurar el cuadro de diálogo
        dialog.getContentPane().add(inputPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    
    private void mostrarBotonesConNombresPeliculas(List<List<String>> infoPeliculas) {
    	panelBotonesPeliculas.removeAll();
    	tempPeliculas.clear();
    	
    	System.out.println("INFORMACION DE LAS PELICULAS");
    	System.out.println(infoPeliculas);
    	
        // Generar botones para las peliculas encontradas
        for (List<String> datosLista : infoPeliculas) {
            
        	String p = datosLista.get(0);
        	
        	JLabel labelPelicula = new JLabel(p);
            labelPelicula.setFont(new Font("Arial", Font.PLAIN, 12));
            labelPelicula.setHorizontalAlignment(SwingConstants.CENTER);
            labelPelicula.setVerticalAlignment(SwingConstants.CENTER);
            labelPelicula.setPreferredSize(new Dimension(150, 50)); // Tamano de los botones

            // Crear un JButton con el JLabel como contenido
            JButton botonPelicula = new JButton();
            botonPelicula.setLayout(new BorderLayout());
            botonPelicula.add(labelPelicula, BorderLayout.CENTER);
            botonPelicula.setPreferredSize(new Dimension(150, 50)); // Tamano de los botones

            // Configurar el boton para que no se corte el texto y se ajuste a multiples lineas
            labelPelicula.setText("<html>" + p.replaceAll("\n", "<br>") + "</html>");
        	
            botonPelicula.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Accion cuando el usuario hace clic en un boton
                	
                	System.out.printf("Peli agregada -> " + p);
                	System.out.println();
                	
                	//SEGUIR AQUI CON EL REPARTO DE LA PELICULA
//                	 public Pelicula(int idPelicula, String titulo, ArrayList<String> reparto, int anio,
//                             float puntuacionMedia, ArrayList<ListaPersonalizada> perteneceA, int quienLaHaAceptado) {
                	
//                	String titulo = p;
//                	ArrayList<String> reparto = new ArrayList<>();
//                	int a = (Int) datosLista.get(1);
//                	float puntuacion = (float) 0.0;
//                	
//                	//AGREGAR PELICULA BASE DE DATOS
//
//                	Pelicula peli = new Pelicula(new Random().nextInt(10000 - 100 + 1), titulo, reparto, a, puntuacion, this, 1);
//                	tempPeliculas.add(peli);
                	
                    //Crear pelicula
                    //Agregar a la lista de peliculas
                }
            });
            
            botonPelicula.setPreferredSize(new Dimension(70, 30));
            panelBotonesPeliculas.add(botonPelicula); // Agregar el boton al panel
        }

        // Refrescar la vista para que los nuevos botones aparezcan
        panelBotonesPeliculas.revalidate();
        panelBotonesPeliculas.repaint();
    }


    private void agregarListaAlPanel(String nombreLista, String descripcion, String[] peliculas) {
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BorderLayout());
        listaPanel.setBackground(Color.LIGHT_GRAY);
        listaPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        listaPanel.setPreferredSize(new Dimension(760, 60));

        JLabel nombreLabel = new JLabel(nombreLista, SwingConstants.LEFT);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nombreLabel.setPreferredSize(new Dimension(200, 60));

        JTextArea detallesArea = new JTextArea();
        detallesArea.setEditable(false);
        detallesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        detallesArea.setText("Descripcion: " + descripcion + "\nPeliculas: " + String.join(", ", peliculas));
        detallesArea.setBackground(Color.LIGHT_GRAY);
        detallesArea.setBorder(null);

        listaPanel.add(nombreLabel, BorderLayout.WEST);
        listaPanel.add(detallesArea, BorderLayout.CENTER);

        panelListas.add(listaPanel);
        panelListas.revalidate(); // Actualizar el panel
        panelListas.repaint();   // Redibujar el panel
    }



}

