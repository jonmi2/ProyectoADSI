package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PanelPeliculasAlquiladas extends JFrame {
	private static PanelPeliculasAlquiladas miPanelPeliculasAlquiladas = new PanelPeliculasAlquiladas();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controler controler = null;

	/*modelo*/
	private int idUsuario;
	private int idPeli;
	private String nombrePeli;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPeliculasAlquiladas frame = new PanelPeliculasAlquiladas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
					if (e.getSource().equals(btnNewButton)) {
						 float valor = Float.parseFloat(textField_1.getText());
						GestorPpal miPpal = GestorPpal.getGestorPpal();
						miPpal.anadirResena(nombrePeli, idUsuario, idPeli, valor, textField.getText());
						}
					
						
					
					}
				
			}
			
		/**
		 * Create the frame.
		 */

	/**
	 * Create the frame.
	 */
	public PanelPeliculasAlquiladas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel Pelicula_1 = new JLabel("New label");
		contentPane.add(Pelicula_1);
		
		JButton Boton_1 = new JButton("New button");
		contentPane.add(Boton_1);
		
		JLabel Pelicula_2 = new JLabel("New label");
		contentPane.add(Pelicula_2);
		
		JButton Boton_2 = new JButton("New button");
		contentPane.add(Boton_2);
		
		JLabel Pelicula_3 = new JLabel("New label");
		contentPane.add(Pelicula_3);
		
		JButton Boton_3 = new JButton("New button");
		contentPane.add(Boton_3);
		
		JLabel Pelicula_4 = new JLabel("New label");
		contentPane.add(Pelicula_4);
		
		JButton Boton_4 = new JButton("New button");
		contentPane.add(Boton_4);
		
		JLabel Pelicula_5 = new JLabel("New label");
		contentPane.add(Pelicula_5);
		
		JButton Boton_5 = new JButton("New button");
		contentPane.add(Boton_5);
		
		JLabel Pelicula_6 = new JLabel("New label");
		contentPane.add(Pelicula_6);
		
		JButton Boton_6 = new JButton("New button");
		contentPane.add(Boton_6);
		
		JLabel Pelicula_7 = new JLabel("New label");
		contentPane.add(Pelicula_7);
		
		JButton Boton_7 = new JButton("New button");
		contentPane.add(Boton_7);
		
		JLabel Pelicula_8 = new JLabel("New label");
		contentPane.add(Pelicula_8);
		
		JButton Boton_8 = new JButton("New button");
		contentPane.add(Boton_8);
	}
	public void setPanel(int nuevoNombreUs) {
		this.idUsuario = nuevoNombreUs;
	}

}
