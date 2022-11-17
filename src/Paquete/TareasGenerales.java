package Paquete;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;

public class TareasGenerales extends JFrame {
	
	private JPanel contentPane;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private JTable tablaGeneral;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TareasGenerales frame = new TareasGenerales();
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
	public TareasGenerales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tareas Generales");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(346, 11, 202, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnAgregarGen = new JButton("Agregar Tarea");
		
		btnAgregarGen.setBounds(658, 80, 153, 47);
		contentPane.add(btnAgregarGen);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//MenuPrincipal mp = new MenuPrincipal();
				//mp.setVisible(true);
			}
		});
		btnAtras.setBounds(658, 456, 153, 47);
		contentPane.add(btnAtras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 615, 426);
		contentPane.add(scrollPane);
		
		tablaGeneral = new JTable();
		scrollPane.setViewportView(tablaGeneral);
		
		btnAgregarGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamamos al dialogo para rellenar la tarea general
				DatosGeneral dg = new DatosGeneral();
				dg.setModal(true);
				dg.setVisible(true);
				
				if(dg.getRootPane() != null) {
					modeloTabla.setColumnIdentifiers(new Object[] {"Fecha", "Descripcion"});
					tablaGeneral.setModel(modeloTabla);
					
					Vector<String> v = new Vector<String>();
					//v.addElement( dg.txtFechaGen.getText() );
					v.addElement( dg.txtDescGen.getText() );
					
					modeloTabla.addRow(v);
					//o[0]= dg.txtFechaGen.getText();
					
					 
				}
			}
		});
	}

}
