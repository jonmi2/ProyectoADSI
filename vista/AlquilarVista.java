package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPpal;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlquilarVista extends JFrame {

	
	private JPanel contentPane;
	private static AlquilarVista mialquiVista = new AlquilarVista();
	private JPanel panelAb;
	private JPanel panelAr;
	private JButton btnVolver;
	private JLabel lblInfo;
	private JLabel lblALQUILADA;
	private Controler controler = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlquilarVista frame = new AlquilarVista();
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
			if (e.getSource().equals(btnVolver))
			{
				System.out.println("VOLVEMOS TRAS ALQUILAR");
				PanelUsuario pnlUsuario = PanelUsuario.getPanelUsuario();
				pnlUsuario.setVisible(true);
				setVisible(false);
			}
		}

	}
	//-----------------------------Controler------------------------------------------------
	
	
	/**
	 * Create the frame.
	 */
	private AlquilarVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(getPanelAr());
		contentPane.add(getPanelAb());
	}

	public static AlquilarVista getAlquilarVista() {
		
		return mialquiVista;
	}

	public void actualizarYalquilar(int idUsuario, int idPeli, String nomPeli) 
	{
		//generar objeto java (y añadir al gestor) y añadir a la base de datos
		//cmabiar info pantalla
		
		// Obtener la fecha y hora actual
	    LocalDateTime ahora = LocalDateTime.now();

	    // Sumar 48 horas a la fecha y hora actual
	    LocalDateTime dentroDe48Horas = ahora.plusHours(48);

	    // Formatear las fechas a un formato legible (puedes modificar el formato según lo necesites)
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String fechaActual = ahora.format(formatter);
	    String fecha48Horas = dentroDe48Horas.format(formatter);	
		
	    // Cambiar la información en la pantalla
	    this.lblInfo.setText("Hoy día " + fechaActual + " has alquilado la peli " + nomPeli + 
	                          ". Tienes hasta el día " + fecha48Horas + " para ver la película.");
		}
	private JPanel getPanelAb() {
		if (panelAb == null) {
			panelAb = new JPanel();
			panelAb.add(getBtnVolver());
		}
		return panelAb;
	}
	private JPanel getPanelAr() {
		if (panelAr == null) {
			panelAr = new JPanel();
			panelAr.setLayout(new GridLayout(2, 1, 0, 0));
			panelAr.add(getLblALQUILADA());
			panelAr.add(getLblInfo());
		}
		return panelAr;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("VOLVER");
			btnVolver.addActionListener(getControler());
		}
		return btnVolver;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblInfo;
	}
	private JLabel getLblALQUILADA() {
		if (lblALQUILADA == null) {
			lblALQUILADA = new JLabel("PELICULA ALQUILADA!");
			lblALQUILADA.setForeground(new Color(128, 0, 128));
			lblALQUILADA.setFont(new Font("Stencil", Font.PLAIN, 17));
			lblALQUILADA.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblALQUILADA;
	}
}
