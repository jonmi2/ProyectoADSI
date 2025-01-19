package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SolicitudIncorporacion extends JFrame {

	private static SolicitudIncorporacion miSolicitudIncorporacion = new SolicitudIncorporacion();
	private JPanel contentPane;
	private JButton btnNewButton;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private Controler controler = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitudIncorporacion frame = new SolicitudIncorporacion();
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
	private SolicitudIncorporacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getLblNewLabel());
		contentPane.add(getTextField());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
	}
	public static SolicitudIncorporacion getSolicitudIncorporacion() {
		return miSolicitudIncorporacion;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Solicitar incorporaci\u00F3n");
			btnNewButton.addActionListener(getControler());
		}
		return btnNewButton;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Escribe el t\u00EDtulo de la pel\u00EDcula que quieres que se incorpore:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Atras");
			btnNewButton_1.addActionListener(getControler());
		}
		return btnNewButton_1;
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
			if (e.getSource().equals(btnNewButton))
			{
				String tituloPeli = textField.getText();
				GestorPpal miGestorP = GestorPpal.getGestorPpal();
				miGestorP.anadirPeliSolicitada(tituloPeli);
			}
			
			else if (e.getSource().equals(btnNewButton_1))
			{
				System.out.println("volvemos patras");
				PanelUsuario panelU = PanelUsuario.getPanelUsuario();
				setVisible(false);
				panelU.setVisible(true);
			}
		}

	}
	//-----------------------------Controler------------------------------------------------
}
