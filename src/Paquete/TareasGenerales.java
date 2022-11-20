package Paquete;

import java.awt.Color;
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

import comunicacion.ComunicacionMusicSP;
import comunicacion.Sincronizador;
import comunicacion.Tarea;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

public class TareasGenerales extends JFrame {
	
	private JPanel contentPane;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private JTable tablaGeneral;

	static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = gestorTareas.getInstancia(comunicacionWS);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TareasGenerales frame = new TareasGenerales();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter(){
				           @Override
				           public void windowClosing(WindowEvent et) {
				        	   if(comunicacionWS.flagDisponible()) {
									frame.setVisible(false);
									//MenuPrincipal.frame.setVisible(true);
									/*MenuPrincipal mp = new MenuPrincipal();
									mp.setVisible(true);*/
									MenuPrincipal.btnGeneral.setBackground(Color.lightGray);
									MenuPrincipal.btnEspecifica.setBackground(Color.lightGray);
									MenuPrincipal.btnSalir.setBackground(Color.lightGray);
									comunicacionWS.devolverMainFlag();
								}	
				           };
				     });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static public JButton btnAgregarGen = new JButton("Agregar Tarea");
	static public JButton btnAtras = new JButton("Atras");
	
	public TareasGenerales() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 837, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tareas Generales");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(346, 11, 202, 40);
		contentPane.add(lblNewLabel);
		
		//JButton btnAgregarGen = new JButton("Agregar Tarea");
		btnAgregarGen.setBackground(Color.lightGray);
		btnAgregarGen.setBounds(658, 80, 153, 47);
		contentPane.add(btnAgregarGen);
		
		//JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(Color.lightGray);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comunicacionWS.flagDisponible()) {
					setVisible(false);
					//MenuPrincipal.frame.setVisible(true);
					/*MenuPrincipal mp = new MenuPrincipal();
					mp.setVisible(true);*/
					MenuPrincipal.btnGeneral.setBackground(Color.lightGray);
					MenuPrincipal.btnEspecifica.setBackground(Color.lightGray);
					MenuPrincipal.btnSalir.setBackground(Color.lightGray);
					comunicacionWS.devolverMainFlag();
				}
			}
		});
		btnAtras.setBounds(658, 456, 153, 47);
		contentPane.add(btnAtras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 615, 426);
		contentPane.add(scrollPane);
		
		tablaGeneral = new JTable();
		scrollPane.setViewportView(tablaGeneral);
		tablaGeneral.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha(mmdd)", "Descripcion"
				}
			));
		
		btnAgregarGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comunicacionWS.flagDisponible()) {
					btnAgregarGen.setBackground(Color.RED);
					btnAtras.setBackground(Color.RED);
					comunicacionWS.quitarFlag();
					//llamamos al dialogo para rellenar la tarea general
					DatosGeneral dg = new DatosGeneral();
					dg.setModal(true);
					dg.setVisible(true);
					
					if(dg.getRootPane() != null) {
						modeloTabla.setColumnIdentifiers(new Object[] {"Fecha(mmdd)", "Descripcion"});
						tablaGeneral.setModel(modeloTabla);
						
						try {
							appLegada.cargarListadoTareasGenerales();
							Sincronizador.waitSyncro(1500); //3500,7500
							comunicacionWS.limpiarEntrada();
							Sincronizador.waitSyncro(1500); //3500,7500
							//mostrarTareasEspecificas();
							ArrayList<Tarea> nuevoListado = appLegada.obtenerListadoTareasGenerales();
							if((nuevoListado!= null) && (nuevoListado.size()>0)) {
								for(int i = 0; i < nuevoListado.size(); i++) {
									Vector<String> v1 = new Vector<String>();
									v1.addElement(nuevoListado.get(i).getFecha());
									v1.addElement(nuevoListado.get(i).getDescripcion());
									modeloTabla.addRow(v1);
									/*System.out.println(nuevoListado.get(i).getNumeroTarea() + " " + nuevoListado.get(i).getNombre() + " " + 
						            		nuevoListado.get(i).getFecha() + " " + nuevoListado.get(i).getDescripcion());*/
						        }
								modeloTabla = new DefaultTableModel();
							}
						} catch (Exception e1) {
				            e1.printStackTrace();
				        }
					}
					btnAgregarGen.setBackground(Color.lightGray);
					btnAtras.setBackground(Color.lightGray);
				}
				comunicacionWS.devolverFlag();
			}
		});
	}

}
