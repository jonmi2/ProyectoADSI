package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.GestorListaPersonalizada;
import modelo.ListaPersonalizada;
import modelo.Pelicula;
import modelo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    Usuario usuario;

    public ListaPersonalizadaVista(Usuario usuario) {
    	
    	this.usuario = usuario;
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Listas Personalizadas");

        // Configuración del panel principal
        panelListaPersonalizada = new JPanel();
        panelListaPersonalizada.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelListaPersonalizada.setLayout(new BorderLayout());
        setContentPane(panelListaPersonalizada);

        panelListaPersonalizada.add(getPanelTitulo(), BorderLayout.NORTH);
        panelListaPersonalizada.add(getPanelListas(), BorderLayout.CENTER);
    }

    private JPanel getPanelTitulo() {
        if (panelTitulo == null) {
            panelTitulo = new JPanel();
            panelTitulo.setLayout(new BorderLayout());
            panelTitulo.setPreferredSize(new Dimension(800, 60)); // Tamaño preferido
            panelTitulo.setBackground(Color.LIGHT_GRAY);

            // Configurar el título
            tituloLabel = new JLabel("Listas Personalizadas", SwingConstants.CENTER);
            tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
            panelTitulo.add(tituloLabel, BorderLayout.CENTER);

            // Configurar el botón para crear una lista
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

    private JPanel getPanelListas() {
        if (panelListas == null) {
            panelListas = new JPanel();
            panelListas.setLayout(new GridLayout(0, 1, 10, 10)); // Listas en columnas
            panelListas.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Ejemplo de elementos en el panel
            for (int i = 1; i <= 5; i++) {
                JLabel listaLabel = new JLabel("Lista " + i, SwingConstants.LEFT);
                listaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                panelListas.add(listaLabel);
            }
        }
        return panelListas;
    }
    
    
    private void mostrarEntradaNombreLista() {
        // Panel principal para el diálogo
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // Layout vertical
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Entrada para el nombre de la lista
        JPanel nombreListaPanel = new JPanel(new BorderLayout(10, 10));
        JLabel promptLabel = new JLabel("Nombre de la nueva lista:");
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        nombreListaArea = new JTextArea(1, 20); // Una línea para ingresar el nombre
        JScrollPane scrollPaneNombreLista = new JScrollPane(nombreListaArea);

        JButton confirmarButton = new JButton("Crear");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreLista = nombreListaArea.getText().trim();
                if (!nombreLista.isEmpty()) {
                    ArrayList<Pelicula> tempPeliculas = new ArrayList<>();
                    ListaPersonalizada lp = new ListaPersonalizada(nombreLista, "privada", usuario.getIdUsuario(), tempPeliculas);

                    System.out.println("Lista creada: " + lp.toString()); // Imprimir en consola
//                    agregarListaAlPanel(nombreLista); // Agregar la lista al panel central
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre de la lista no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        nombreListaPanel.add(promptLabel, BorderLayout.WEST);
        nombreListaPanel.add(scrollPaneNombreLista, BorderLayout.CENTER);
        nombreListaPanel.add(confirmarButton, BorderLayout.EAST);

     // Entrada para buscar película
        JPanel buscarPeliculaPanel = new JPanel(new BorderLayout(10, 10));
        JLabel buscarLabel = new JLabel("Buscar película:");
        buscarLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        nombreBuscarPelicula = new JTextField(20); // Campo de texto para buscar películas
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String nombrePelicula = nombreBuscarPelicula.getText().trim();
                List<String> infoPeliculas = GestorListaPersonalizada.getGestorListaPersonalizada().buscarPelicula(nombrePelicula);

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

        // Panel para los botones de las películas
        panelBotonesPeliculas = new JPanel();
        panelBotonesPeliculas.setLayout(new GridLayout (0, 3, 10, 10)); // Centrado y espaciado entre botones

	     // Crear el JScrollPane para envolver el panelBotonesPeliculas
	     JScrollPane scrollPane = new JScrollPane(panelBotonesPeliculas); 
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// Barra de desplazamiento vertical siempre
	     scrollPane.setPreferredSize(new Dimension(500, 400)); // Tamaño preferido para el scroll
	
	     // Reemplazar la adición de panelBotonesPeliculas directamente al detallesPeliculaPanel
	     detallesPeliculaPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar los paneles al panel principal
        inputPanel.add(nombreListaPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Espaciado entre componentes
        inputPanel.add(buscarPeliculaPanel);
        inputPanel.add(Box.createVerticalStrut(10)); // Espaciado entre componentes
        inputPanel.add(detallesPeliculaPanel); // Agregar el panel de detalles

        // Mostrar el cuadro de diálogo
        JOptionPane.showMessageDialog(this, inputPanel, "Crear Lista y Buscar Película", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void mostrarBotonesConNombresPeliculas(List<String> infoPeliculas) {
    	panelBotonesPeliculas.removeAll();

        // Generar botones para las películas encontradas
        for (String p : infoPeliculas) {
            
        	JLabel labelPelicula = new JLabel(p);
            labelPelicula.setFont(new Font("Arial", Font.PLAIN, 12));
            labelPelicula.setHorizontalAlignment(SwingConstants.CENTER);
            labelPelicula.setVerticalAlignment(SwingConstants.CENTER);
            labelPelicula.setPreferredSize(new Dimension(150, 50)); // Tamaño de los botones

            // Crear un JButton con el JLabel como contenido
            JButton botonPelicula = new JButton();
            botonPelicula.setLayout(new BorderLayout());
            botonPelicula.add(labelPelicula, BorderLayout.CENTER);
            botonPelicula.setPreferredSize(new Dimension(150, 50)); // Tamaño de los botones

            // Configurar el botón para que no se corte el texto y se ajuste a múltiples líneas
            labelPelicula.setText("<html>" + p.replaceAll("\n", "<br>") + "</html>");
        	
            botonPelicula.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Acción cuando el usuario hace clic en un botón
                    System.out.println("Película seleccionada: " + p);
                }
            });
            
            botonPelicula.setPreferredSize(new Dimension(70, 30));
            panelBotonesPeliculas.add(botonPelicula); // Agregar el botón al panel
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
        detallesArea.setText("Descripción: " + descripcion + "\nPelículas: " + String.join(", ", peliculas));
        detallesArea.setBackground(Color.LIGHT_GRAY);
        detallesArea.setBorder(null);

        listaPanel.add(nombreLabel, BorderLayout.WEST);
        listaPanel.add(detallesArea, BorderLayout.CENTER);

        panelListas.add(listaPanel);
        panelListas.revalidate(); // Actualizar el panel
        panelListas.repaint();   // Redibujar el panel
    }

}

