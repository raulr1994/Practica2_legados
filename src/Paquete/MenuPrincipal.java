package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import comunicacion.ComunicacionMusicSP;
import comunicacion.Tarea;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	
	public ArrayList<Tarea> nuevoListado = null;
	//private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
		
		JButton btnGeneral = new JButton("General");
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tg.getRootPane() != null) {
					tg.setVisible(true);
				}
			}
		});
		btnGeneral.setBounds(592, 88, 142, 58);
		contentPane.add(btnGeneral);
		

		TareasEspecificas te = new TareasEspecificas();
		
		JButton btnEspecifica = new JButton("Especifica");
		btnEspecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tg.getRootPane() != null) {
					te.setVisible(true);
				}
			}
		});
		btnEspecifica.setBounds(592, 169, 142, 58);
		contentPane.add(btnEspecifica);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				comunicacionSP.finalizarPrograma();
				Inicio i = new Inicio();
				i.setVisible(true);
			}
		});
		btnSalir.setBounds(592, 462, 142, 58);
		contentPane.add(btnSalir);
	}
}
