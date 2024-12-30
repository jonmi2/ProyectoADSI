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

import modelo.Usuario;

public class PanelUsuario extends JFrame {
	
	
	//JPANELS
	private JPanel panelUsuario;
	private JPanel panelIzquierdo;
	private JPanel panelCentral;
	private JPanel panelBotones;
	
	//JLABELS
	private JLabel nombreUsuario;
	
	//JBUTTONS
	private JButton boton1;
    private JButton boton2;
    private JButton boton3;
	private JButton listasPersonalizadasButton;
	
	//CONTROLADORES
	private ControlerBotones controler = null;
	
	//MODELO
	private Usuario usuario;

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
	
	public PanelUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		
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
	        panelIzquierdo.setPreferredSize(new Dimension(150, 0));
	        
	        panelIzquierdo.add(getUserNameLabel(),  BorderLayout.NORTH);
	        
		}
		return panelIzquierdo;
	}
	
	private JPanel getPanelCentral() {
	    if (panelCentral == null) {
	        panelCentral = new JPanel();
	        panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centrar y espaciar
	        panelCentral.add(getPanelBotones()); // Agregar el panel de botones centrado
	    }
	    return panelCentral;
	}
	
	private JLabel getUserNameLabel() {
		
		if (nombreUsuario == null) {
			nombreUsuario = new JLabel(this.usuario.getNombre(), SwingConstants.CENTER); //Aqui necesito el usuario para obtener el nombre
			nombreUsuario.setForeground(Color.WHITE);
			nombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
			
		}
		
		return nombreUsuario;
		
	}
	
	
	//Botones para funcionalidades
	
	private class ControlerBotones implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Identificar qué botón fue presionado y mostrar el mensaje correspondiente en consola
	        if (e.getSource().equals(getBoton1())) {
	            System.out.println("Funciona: Botón 1 presionado");
	        } else if (e.getSource().equals(getBoton2())) {
	            System.out.println("Funciona: Botón 2 presionado");
	        } else if (e.getSource().equals(getBoton3())) {
	            System.out.println("Funciona: Botón 3 presionado");
	        } else if (e.getSource().equals(getListarPersonalizadasButton())) {
	            ListaPersonalizadaVista lpv = new ListaPersonalizadaVista(usuario);
	            setVisible(false);
	            lpv.setVisible(true);
	        }
	    }
	}
	
	 private JPanel getPanelBotones() {
        if (panelBotones == null) {
        	panelBotones = new JPanel();
        	panelBotones.setLayout(new GridLayout(2, 2, 50, 50));
        	panelBotones.setPreferredSize(new Dimension(400, 100));// 2x2 con espaciado de 10px

            // Agregar botones
        	panelBotones.add(getBoton1());
        	panelBotones.add(getBoton2());
            panelBotones.add(getBoton3());
            panelBotones.add(getListarPersonalizadasButton());
            
        }
        return panelBotones;
    }
	 
	 
	
	//Otras fiuncionalidades
	 
	 private JButton getBoton1() {
        if (boton1 == null) {
            boton1 = new JButton("Botón 1");
            boton1.setPreferredSize(new Dimension(80, 30));
        }
        return boton1;
    }

    private JButton getBoton2() {
        if (boton2 == null) {
            boton2 = new JButton("Botón 2");
            boton2.setPreferredSize(new Dimension(80, 30));
        }
        return boton2;
    }

    private JButton getBoton3() {
        if (boton3 == null) {
            boton3 = new JButton("Botón 3");
            boton3.setPreferredSize(new Dimension(80, 30));
        }
        return boton3;
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

}
