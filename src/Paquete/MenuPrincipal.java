package Paquete;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import comunicacion.ComunicacionMusicSP;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

//import comunicacion.ComunicacionMusicSP;
//import comunicacion.Tarea;
//import comunicacion.gestorTareas;
//import comunicacion.wc3270;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JTable tablaPrincipal;
	static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = gestorTareas.getInstancia(comunicacionWS);

	/**
	 * Launch the application.
	 */
    static public MenuPrincipal frame = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						frame = new MenuPrincipal();
						frame.setVisible(true);
						frame.addWindowListener(new WindowAdapter(){
						    @Override
						    public void windowClosing(WindowEvent et) {
						    	if(comunicacionWS.flagMainDisponible()) {
							        frame.dispose();
									comunicacionSP.finalizarPrograma();
									Inicio i = new Inicio();
									i.setVisible(true);	
						    	}
						    };
						});
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static public JButton btnEspecifica = new JButton("Especifica");
	static public JButton btnGeneral = new JButton("General");
	static public JButton btnSalir = new JButton("Cerrar Sesion");
	
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 781, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 531, 433);
		contentPane.add(scrollPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		JTable tablaPrincipal = new JTable(modelo);
		tablaPrincipal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Fecha", "Descripcion"
			}
		));
		scrollPane.setViewportView(tablaPrincipal);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(283, 11, 191, 35);
		contentPane.add(lblNewLabel);
		
		
		TareasGenerales tg = new TareasGenerales();
		
		//JButton btnGeneral = new JButton("General");
		btnGeneral.setBackground(Color.lightGray);
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comunicacionWS.flagMainDisponible()) {
					if(tg.getRootPane() != null) {
						//setVisible(false);
						//dispose();
						btnGeneral.setBackground(Color.RED);
						btnEspecifica.setBackground(Color.RED);
						btnSalir.setBackground(Color.RED);
						comunicacionWS.quitarMainFlag();
						tg.setVisible(true);
					}
				}
				else {
					System.out.println("Boton General bloqueado");
				}
			}
		});
		btnGeneral.setBounds(592, 88, 142, 58);
		contentPane.add(btnGeneral);
		

		TareasEspecificas te = new TareasEspecificas();
		
		//JButton btnEspecifica = new JButton("Especifica");
		btnEspecifica.setBackground(Color.lightGray);
		btnEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(tg.getRootPane() != null) {
						if(comunicacionWS.flagMainDisponible()) {
							//setVisible(false);
							//dispose();
							btnGeneral.setBackground(Color.RED);
							btnEspecifica.setBackground(Color.RED);
							btnSalir.setBackground(Color.RED);
							comunicacionWS.quitarMainFlag();
							te.setVisible(true);
						}
					}
			}
		});
		btnEspecifica.setBounds(592, 169, 142, 58);
		contentPane.add(btnEspecifica);
		
		//JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.lightGray);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comunicacionWS.flagMainDisponible()) {
					dispose();
					comunicacionSP.finalizarPrograma();
					/*Inicio i = new Inicio();
					i.setVisible(true);*/
					System.exit(0);
				}
			}
		});
		btnSalir.setBounds(592, 462, 142, 58);
		contentPane.add(btnSalir);
	}
}
