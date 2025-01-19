package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;
import modelo.Usuario;

public class PanelUsuario extends JFrame {
	
	//singleton
	private static PanelUsuario miPanelUsuarios = new PanelUsuario();
	
	//JPANELS
	private JPanel panelUsuario;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;
	private JPanel panelBotonesUsuario;
	
	//JLABELS
	private JLabel nombreUsuario;
	
	//JBUTTONS
	private JButton botonActu;
    private JButton botonCerrarSes;
    private JButton botonAlqui;
	private JButton listasPersonalizadasButton;
	
	//CONTROLADORES
	private ControlerBotones controler = null;
	
	//MODELO
	private int idUsuario;
	private String rol;

	private String nombreU;
	private JPanel panelAdmin;
	private JPanel panelCerrarSesion;
	private JButton btnValidar;
	private JButton btnEditar;
	private JButton btnEliminarCuenta;
	private JButton btnAceptar;
	private JButton btnVerAlquileres;
	private JButton btnSolicitar;
	
	public static PanelUsuario getPanelUsuario()
	{
		return miPanelUsuarios;
	}

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PanelUsuario frame = new PanelUsuario(usuario);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}	
//		});
//	}
	
	private PanelUsuario() {
		
		//this.usuario = usuario;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("Panel del usuario");
		
		panelUsuario = new JPanel();
		panelUsuario.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelUsuario.setLayout(new BorderLayout());
		setContentPane(panelUsuario);
		
		panelUsuario.add(getJPanelIzquierda(), BorderLayout.WEST);
		panelUsuario.add(getPanelCentral(), BorderLayout.CENTER);
		
	}
	
	private ControlerBotones getControler() {
		if (controler == null) {
			controler = new ControlerBotones();
		}
		return controler;
	}
	
	//Informacion del usuario 
	private JPanel getJPanelIzquierda() {
		if (panelIzquierdo == null) {
			
			panelIzquierdo = new JPanel();
	        panelIzquierdo.setBackground(Color.BLUE); // Fondo azul
	        panelIzquierdo.setLayout(new BorderLayout()); // Para posicionar el JLabel arriba
	        panelIzquierdo.setPreferredSize(new Dimension(230, 0));
	        
	        panelIzquierdo.add(getUserNameLabel(),  BorderLayout.NORTH);
	        
		}
		return panelIzquierdo;
	}
	
	private JPanel getPanelCentral() {
	    if (panelCentral == null) {
	        panelCentral = new JPanel();
	        panelCentral.setLayout(new GridLayout(3, 1, 0, 0));
	        panelCentral.add(getPanelAdmin());
	        panelCentral.add(getPanelBotonesUsuario());
	        panelCentral.add(getPanelCerrarSesion());
	    }
	    return panelCentral;
	}
	
	private JLabel getUserNameLabel() {
		
		if (nombreUsuario == null) {
			nombreUsuario = new JLabel(""); 
			nombreUsuario.setForeground(Color.WHITE);
			nombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
			
		}
		
		return nombreUsuario;
		
	}
	
	
	//Botones para funcionalidades
	
	private class ControlerBotones implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Identificar qeu boton fue presionado y mostrar el mensaje correspondiente en consola
	        if (e.getSource().equals(getActualizarDatos())) 
	        {
	            System.out.println("Funciona: Boton actualizar mis datos presionado");
	        } 
	        else if (e.getSource().equals(getBotonCerrar())) 
	        {
	            System.out.println("Funciona: Boton cerrar sesion presionado");
	        } 
	        else if (e.getSource().equals(btnVerAlquileres)) 
	        {
	        	PanelPeliculasAlquiladas miPanelPeliculasAlquiladas = PanelPeliculasAlquiladas.getPanelPeliculasAlquiladas();
	        	miPanelPeliculasAlquiladas.setPanel(idUsuario);
	            setVisible(false);	
	            miPanelPeliculasAlquiladas.setVisible(true);
	        } 
	        else if (e.getSource().equals(getBotonAlquileres())) 
	        {
	            System.out.println("Funciona: Boton alquileres presionado");
	            AlquilerPeliculas miAlquilerPeliculas = AlquilerPeliculas.getAlquilerPeliculas();
	            miAlquilerPeliculas.actualizar(idUsuario);
	            setVisible(false);	
	            miAlquilerPeliculas.setVisible(true);
	        } 
	        else if (e.getSource().equals(getListarPersonalizadasButton())) 
	        {
	            ListaPersonalizadaVista lpv = new ListaPersonalizadaVista(idUsuario);
	            setVisible(false);
//	            lpv.actualizar(idUsuario);
	            lpv.setVisible(true);
	        }
	        else if (e.getSource().equals(getBtnAceptar())) 
	        {
	        	System.out.println("aceptar solicitudes de registro");
	        } 
	        else if (e.getSource().equals(btnEditar)) 
	        {
	        	System.out.println("editar cuentas de otros usuarios");   
	        } 
	        else if (e.getSource().equals(btnEliminarCuenta)) 
	        {
	        	System.out.println("eliminar cuentas de otros usuarios");
	        }  
	        else if (e.getSource().equals(btnSolicitar)) 
	        {
	        	System.out.println("solicitar la incorporacion de nuevas pelis");
	        	SolicitudIncorporacion miSolicitud = SolicitudIncorporacion.getSolicitudIncorporacion();
	        	setVisible(false);
	        	miSolicitud.setVisible(true);
	        } 
	        else if (e.getSource().equals(btnValidar)) 
	        {
	        	System.out.println("validar solicitud de pelicula");
	        } 
	        else if (e.getSource().equals(botonCerrarSes)) 
	        {
	        	System.out.println("cerrar sesion");
	        } 
	    }
	}
	
	 private JPanel getPanelBotonesUsuario() {
        if (panelBotonesUsuario == null) {
        	panelBotonesUsuario = new JPanel();
        	panelBotonesUsuario.setLayout(new GridLayout(2, 2, 20, 30));
        	panelBotonesUsuario.setPreferredSize(new Dimension(400, 200));// 2x2 con espaciado de 10px

            // Agregar botones
        	panelBotonesUsuario.add(getActualizarDatos());
            panelBotonesUsuario.add(getBtnVerAlquileres());
            panelBotonesUsuario.add(getBotonAlquileres());
            panelBotonesUsuario.add(getListarPersonalizadasButton());
            panelBotonesUsuario.add(getBtnSolicitar());
            
        }
        return panelBotonesUsuario;
    }
	 
	 
	
	//Otras fiuncionalidades
	 
	 private JButton getActualizarDatos() {
        if (botonActu == null) {
        	botonActu = new JButton("Actualizar mis datos");
        	botonActu.setPreferredSize(new Dimension(80, 30));
        }
        return botonActu;
    }

    private JButton getBotonCerrar() {
        if (botonCerrarSes == null) {
        	botonCerrarSes = new JButton("Cerrar sesion");
        	botonCerrarSes.setPreferredSize(new Dimension(200, 60));
        	botonCerrarSes.addActionListener(getControler());
        }
        return botonCerrarSes;
    }

    private JButton getBotonAlquileres() {
        if (botonAlqui == null) {
            botonAlqui = new JButton("Alquilar pelicula");
            botonAlqui.setPreferredSize(new Dimension(80, 30));
            botonAlqui.addActionListener(getControler());
        }
        return botonAlqui;
    }
	 
   //Crear listas personalizadas
	private JButton getListarPersonalizadasButton() {
		
		if (listasPersonalizadasButton == null) {
			listasPersonalizadasButton = new JButton("Listar Personalizadas");
			listasPersonalizadasButton.setPreferredSize(new Dimension(80, 30));
			listasPersonalizadasButton.addActionListener(getControler());
		}
		
		return listasPersonalizadasButton;
		
	}

	public void actualizar(int pid, String rol, String nombreU) 
	{
		this.nombreU=nombreU;
		this.idUsuario=pid;
		this.rol=rol;
		this.nombreUsuario.setText("Pantalla de "+nombreU);
	}

	private JPanel getPanelAdmin() {
		if (panelAdmin == null) {
			panelAdmin = new JPanel();
			panelAdmin.setLayout(new GridLayout(2, 2, 50, 50));
			panelAdmin.add(getBtnAceptar());
			panelAdmin.add(getBtnEliminarCuenta());
			panelAdmin.add(getBtnEditar());
			panelAdmin.add(getBtnValidar());
		}
		return panelAdmin;
	}
	private JPanel getPanelCerrarSesion() {
		if (panelCerrarSesion == null) {
			panelCerrarSesion = new JPanel();
			panelCerrarSesion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelCerrarSesion.add(getBotonCerrar());
			panelCerrarSesion.setPreferredSize(new Dimension(400, 50));
		}
		return panelCerrarSesion;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar Peli");
			btnValidar.addActionListener(getControler());
		}
		return btnValidar;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar datos de un usuario");
			btnEditar.addActionListener(getControler());
		}
		return btnEditar;
	}
	private JButton getBtnEliminarCuenta() {
		if (btnEliminarCuenta == null) {
			btnEliminarCuenta = new JButton("Eliminar cuenta de otro");
			btnEliminarCuenta.addActionListener(getControler());
		}
		return btnEliminarCuenta;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar solicitudes de registoro");
			btnAceptar.addActionListener(getControler());
		}
		return btnAceptar;
	}
	private JButton getBtnVerAlquileres() {
		if (btnVerAlquileres == null) {
			btnVerAlquileres = new JButton("Ver mis alquileres");
			btnVerAlquileres.addActionListener(getControler());
		}
		return btnVerAlquileres;
	}
	private JButton getBtnSolicitar() {
		if (btnSolicitar == null) {
			btnSolicitar = new JButton("Solicitar incorporacion peli");
			btnSolicitar.addActionListener(getControler());
		}
		return btnSolicitar;
	}
}