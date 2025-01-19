package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.GestorPeliculas;
import modelo.GestorPpal;
import modelo.GestorUsuarios;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PanelPeliculasAlquiladas extends JFrame {
	private static PanelPeliculasAlquiladas miPanelPeliculasAlquiladas = new PanelPeliculasAlquiladas();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controler controler = null;
	private JLabel Pelicula_1;
	private JLabel Pelicula_2;
	private JLabel Pelicula_3;
	private JLabel Pelicula_4;
	private JLabel Pelicula_5;
	private JLabel Pelicula_6;
	private JLabel Pelicula_7;
	private JLabel Pelicula_8;
	private JButton Boton_1;
	private JButton Boton_2;
	private JButton Boton_3;
	private JButton Boton_4;
	private JButton Boton_5;
	private JButton Boton_6;
	private JButton Boton_7;
	private JButton Boton_8;
	

	/*modelo*/
	private int idUsuario;
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
	public static PanelPeliculasAlquiladas getPanelPeliculasAlquiladas()
	{
		return miPanelPeliculasAlquiladas;
	}
	//-----------------------------Controler------------------------------------------------
			private class Controler implements ActionListener 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if (e.getSource().equals(Boton_1)) {
							GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
							GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
							ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
							PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
				            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(1), miGPs.buscarPeliculaPorId(listaIdPeli.get(1)).getTitulo());
				            setVisible(false);	
				            miPanelResenar.setVisible(true);
						}
					if (e.getSource().equals(Boton_2)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(2), miGPs.buscarPeliculaPorId(listaIdPeli.get(2)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_3)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(3), miGPs.buscarPeliculaPorId(listaIdPeli.get(3)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_4)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(4), miGPs.buscarPeliculaPorId(listaIdPeli.get(4)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_5)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(5), miGPs.buscarPeliculaPorId(listaIdPeli.get(5)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_6)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(6), miGPs.buscarPeliculaPorId(listaIdPeli.get(6)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_7)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(7), miGPs.buscarPeliculaPorId(listaIdPeli.get(7)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
					}
					if (e.getSource().equals(Boton_8)) {
						GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
						GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
						ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(idUsuario);
						PanelResenar miPanelResenar = PanelResenar.getPanelResenar();
			            miPanelResenar.setPanelResenar(idUsuario, listaIdPeli.get(8), miGPs.buscarPeliculaPorId(listaIdPeli.get(8)).getTitulo());
			            setVisible(false);	
			            miPanelResenar.setVisible(true);
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
			    
			    Pelicula_1 = new JLabel("New label");
			    contentPane.add(Pelicula_1);
			    
			    Boton_1 = new JButton("Resenar peli");
			    contentPane.add(Boton_1);
			    
			    Pelicula_2 = new JLabel("New label");
			    contentPane.add(Pelicula_2);
			    
			    Boton_2 = new JButton("Resenar peli");
			    contentPane.add(Boton_2);
			    
			    Pelicula_3 = new JLabel("New label");
			    contentPane.add(Pelicula_3);
			    
			    Boton_3 = new JButton("Resenar peli");
			    contentPane.add(Boton_3);
			    
			    Pelicula_4 = new JLabel("New label");
			    contentPane.add(Pelicula_4);
			    
			    Boton_4 = new JButton("Resenar peli");
			    contentPane.add(Boton_4);
			    
			    Pelicula_5 = new JLabel("New label");
			    contentPane.add(Pelicula_5);
			    
			    Boton_5 = new JButton("Resenar peli");
			    contentPane.add(Boton_5);
			    
			    Pelicula_6 = new JLabel("New label");
			    contentPane.add(Pelicula_6);
			    
			    Boton_6 = new JButton("Resenar peli");
			    contentPane.add(Boton_6);
			    
			    Pelicula_7 = new JLabel("New label");
			    contentPane.add(Pelicula_7);
			    
			    Boton_7 = new JButton("Resenar peli");
			    contentPane.add(Boton_7);
			    
			    Pelicula_8 = new JLabel("New label");
			    contentPane.add(Pelicula_8);
			    
			    Boton_8 = new JButton("Resenar peli");
			    contentPane.add(Boton_8);
			}
			
			
	public void setPanel(int nuevoNombreUs) {
		this.idUsuario = nuevoNombreUs;
		GestorUsuarios miGUs = GestorUsuarios.getGestorUsuarios();
		GestorPeliculas miGPs = GestorPeliculas.getGestorPelis();
		ArrayList<Integer> listaIdPeli = miGUs.getListaAlquilerUs(nuevoNombreUs);
		if (listaIdPeli.size()==0)
		{
			Pelicula_1.setText("sin alquileres");
			Pelicula_2.setText("sin alquileres");
			Pelicula_3.setText("sin alquileres");
			Pelicula_4.setText("sin alquileres");
			Pelicula_5.setText("sin alquileres");
			Pelicula_6.setText("sin alquileres");
			Pelicula_7.setText("sin alquileres");
			Pelicula_8.setText("sin alquileres");
		}
		
		/*Pelicula_1.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(1)).getTitulo());
		Pelicula_2.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(2)).getTitulo());
		Pelicula_3.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(3)).getTitulo());
		Pelicula_4.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(4)).getTitulo());
		Pelicula_5.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(5)).getTitulo());
		Pelicula_6.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(6)).getTitulo());
		Pelicula_7.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(7)).getTitulo());
		Pelicula_8.setText(miGPs.buscarPeliculaPorId(listaIdPeli.get(8)).getTitulo());*/
	}
	private JLabel getPelicula_1() {
	    if (Pelicula_1 == null) {
	        Pelicula_1 = new JLabel("New label");
	    }
	    return Pelicula_1;
	}

	private JLabel getPelicula_2() {
	    if (Pelicula_2 == null) {
	        Pelicula_2 = new JLabel("New label");
	    }
	    return Pelicula_2;
	}

	private JLabel getPelicula_3() {
	    if (Pelicula_3 == null) {
	        Pelicula_3 = new JLabel("New label");
	    }
	    return Pelicula_3;
	}

	private JLabel getPelicula_4() {
	    if (Pelicula_4 == null) {
	        Pelicula_4 = new JLabel("New label");
	    }
	    return Pelicula_4;
	}

	private JLabel getPelicula_5() {
	    if (Pelicula_5 == null) {
	        Pelicula_5 = new JLabel("New label");
	    }
	    return Pelicula_5;
	}

	private JLabel getPelicula_6() {
	    if (Pelicula_6 == null) {
	        Pelicula_6 = new JLabel("New label");
	    }
	    return Pelicula_6;
	}

	private JLabel getPelicula_7() {
	    if (Pelicula_7 == null) {
	        Pelicula_7 = new JLabel("New label");
	    }
	    return Pelicula_7;
	}

	private JLabel getPelicula_8() {
	    if (Pelicula_8 == null) {
	        Pelicula_8 = new JLabel("New label");
	    }
	    return Pelicula_8;
	}

	private JButton getBoton_1() {
	    if (Boton_1 == null) {
	        Boton_1 = new JButton("Resenar peli");
	        Boton_1.addActionListener(getControler());
	    }
	    return Boton_1;
	}

	private JButton getBoton_2() {
	    if (Boton_2 == null) {
	        Boton_2 = new JButton("Resenar peli");
	        Boton_2.addActionListener(getControler());
	    }
	    return Boton_2;
	}

	private JButton getBoton_3() {
	    if (Boton_3 == null) {
	        Boton_3 = new JButton("Resenar peli");
	        Boton_3.addActionListener(getControler());
	    }
	    return Boton_3;
	}

	private JButton getBoton_4() {
	    if (Boton_4 == null) {
	        Boton_4 = new JButton("Resenar peli");
	        Boton_4.addActionListener(getControler());
	    }
	    return Boton_4;
	}

	private JButton getBoton_5() {
	    if (Boton_5 == null) {
	        Boton_5 = new JButton("Resenar peli");
	        Boton_5.addActionListener(getControler());
	    }
	    return Boton_5;
	}

	private JButton getBoton_6() {
	    if (Boton_6 == null) {
	        Boton_6 = new JButton("Resenar peli");
	        Boton_6.addActionListener(getControler());
	    }
	    return Boton_6;
	}

	private JButton getBoton_7() {
	    if (Boton_7 == null) {
	        Boton_7 = new JButton("Resenar peli");
	        Boton_7.addActionListener(getControler());
	    }
	    return Boton_7;
	}

	private JButton getBoton_8() {
	    if (Boton_8 == null) {
	        Boton_8 = new JButton("Resenar peli");
	        Boton_8.addActionListener(getControler());
	    }
	    return Boton_8;
	}

}
