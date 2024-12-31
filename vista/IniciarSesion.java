package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IniciarSesion extends JFrame {

	private JPanel panelPpal;
	private static IniciarSesion miInicioSesion = new IniciarSesion();
//	private static PanelUsuario miPanelUsuario = new PanelUsuario();
	private Controler controler = null;
	private JPanel panelArriba;
	private JPanel panelAbajo;
	private JLabel lblNewLabel;
	private JPanel panelAbajoArriba;
	private JPanel panelAbajoAbajo;
	private JTextField fieldIntroducirID;
	private JButton botonINICIARSESION;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private IniciarSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Iniciar sesion");
		
		panelPpal = new JPanel();
		panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPpal);
		panelPpal.setLayout(new GridLayout(2, 1, 0, 0));
		panelPpal.add(getPanelArriba());
		panelPpal.add(getPanelAbajo());
	}
	
	public static IniciarSesion getIniciarSesion() {
		return miInicioSesion;
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	//-----------------------------Controler------------------------------------------------
	private class Controler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(getBotonINICIARSESION()))
			{
				System.out.println("INICIAR SESION");
				//comprobar si el ID existe y en caso de que exita y que su rol == "usuario registrado" cambiamos a otra pantalla
				GestorPpal miPpal = GestorPpal.getGestorPpal();
				int pid = Integer.valueOf(fieldIntroducirID.getText());
				
				if (miPpal.puedeIniciarSesion(pid))
				{
					System.out.println("usuario existe y se puede iniciar sesion");
					//ahora cambiar de pantalla
					
					
					PanelUsuario miPanelUsuario = PanelUsuario.getPanelUsuario();
					miPanelUsuario.actualizar(pid);
					miPanelUsuario.setVisible(true);
					setVisible(false);
					//PanelUsuario panelUsuario = panelUsuario.getPanelUsuario();
					//panelUsuario.setVisible(true);
				}
				else
				{
					System.out.println("no se puede iniciar sesion");
					//mostrar error
				}
			}
		}

	}
	//-----------------------------Controler------------------------------------------------
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			//lo que tenga q hacer
		}
	}

	private JPanel getPanelArriba() {
		if (panelArriba == null) {
			panelArriba = new JPanel();
			panelArriba.add(getLblNewLabel());
		}
		return panelArriba;
	}
	private JPanel getPanelAbajo() {
		if (panelAbajo == null) {
			panelAbajo = new JPanel();
			panelAbajo.setLayout(new GridLayout(2, 1, 0, 0));
			panelAbajo.add(getPanelAbajoArriba());
			panelAbajo.add(getPanelAbajoAbajo());
		}
		return panelAbajo;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("PANTALLA DE INICIO SESION");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 19));
		}
		return lblNewLabel;
	}
	private JPanel getPanelAbajoArriba() {
		if (panelAbajoArriba == null) {
			panelAbajoArriba = new JPanel();
			panelAbajoArriba.add(getFieldIntroducirID());
		}
		return panelAbajoArriba;
	}
	private JPanel getPanelAbajoAbajo() {
		if (panelAbajoAbajo == null) {
			panelAbajoAbajo = new JPanel();
			panelAbajoAbajo.add(getBotonINICIARSESION());
		}
		return panelAbajoAbajo;
	}
	private JTextField getFieldIntroducirID() {
		if (fieldIntroducirID == null) {
			fieldIntroducirID = new JTextField();
			fieldIntroducirID.setText("INTRODUCE TU ID");
			fieldIntroducirID.setColumns(10);
		}
		return fieldIntroducirID;
	}
	private JButton getBotonINICIARSESION() {
		if (botonINICIARSESION == null) {
			botonINICIARSESION = new JButton("Login");
			botonINICIARSESION.addActionListener(getControler());
		}
		return botonINICIARSESION;
	}
}
