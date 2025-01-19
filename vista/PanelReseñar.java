package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;



public class PanelReseñar extends JFrame {
	private static PanelReseñar miPanelReseñar = new PanelReseñar();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controler controler = null;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
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
					PanelReseñar frame = new PanelReseñar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static PanelReseñar getPanelReseñar()
	{
		return miPanelReseñar;
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
	private  PanelReseñar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Comentario:");
		lblNewLabel.setBounds(67, 50, 144, 54);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(127, 67, 419, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Puntuacion Sobre 10:");
		lblNewLabel_1.setBounds(20, 127, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Agregar Reseña");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(258, 216, 180, 23);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 124, 419, 20);
		contentPane.add(textField_1);
	}
	public void setPanelReseñar(int idUsNuevo, int idPeliNuevo, String nomPeliNuevo) {
		this.idPeli = idPeliNuevo;
		this.idUsuario = idUsNuevo;
		this.nombrePeli = nomPeliNuevo;
		
	}
	private JButton getBotonReseña() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Login");
			btnNewButton.addActionListener(getControler());
		}
		return btnNewButton;
	}
}
