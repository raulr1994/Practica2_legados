package Paquete;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import comunicacion.ComunicacionMusicSP;
import comunicacion.Sincronizador;
import comunicacion.Tarea;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

//import comunicacion.ComunicacionMusicSP;
//import comunicacion.Sincronizador;
//import comunicacion.Tarea;
//import comunicacion.gestorTareas;
//import comunicacion.wc3270;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TareasEspecificas extends JFrame {

	private JPanel contentPane;

	DefaultTableModel modeloTablaEsp = new DefaultTableModel();
	private JTable tablaEspecifica;
	
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
					TareasEspecificas frame = new TareasEspecificas();
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
	static public JButton btnAgregarEsp = new JButton("Agregar Tarea");
	static public JButton btnAtras = new JButton("Atras");
	
	public TareasEspecificas() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 878, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JButton btnAgregarEsp = new JButton("Agregar Tarea");
		btnAgregarEsp.setBackground(Color.lightGray);
		btnAgregarEsp.setBounds(705, 86, 147, 46);
		contentPane.add(btnAgregarEsp);
		
		JLabel lblNewLabel = new JLabel("Tareas Especificas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(304, 27, 225, 32);
		contentPane.add(lblNewLabel);
		
		//JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(Color.lightGray);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comunicacionWS.flagDisponible()) {
					setVisible(false);
					//MenuPrincipal.frame.setVisible(true);
					//MenuPrincipal mp = new MenuPrincipal();
					//mp.setVisible(true);
					MenuPrincipal.btnGeneral.setBackground(Color.lightGray);
					MenuPrincipal.btnEspecifica.setBackground(Color.lightGray);
					MenuPrincipal.btnSalir.setBackground(Color.lightGray);
					comunicacionWS.devolverMainFlag();
				}	
			}
		});
		btnAtras.setBounds(705, 489, 147, 46);
		contentPane.add(btnAtras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 86, 639, 449);
		contentPane.add(scrollPane);
		
		tablaEspecifica = new JTable();
		tablaEspecifica.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Fecha(mmdd)", "Descripcion"
			}
		));
		scrollPane.setViewportView(tablaEspecifica);
		
		btnAgregarEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//llamamos al dialogo para rellenar la tarea general
				if(comunicacionWS.flagDisponible()) {
					btnAgregarEsp.setBackground(Color.RED);
					btnAtras.setBackground(Color.RED);
					comunicacionWS.quitarFlag();
					DatosEspecifica de = new DatosEspecifica();
					de.setModal(true);
					de.setVisible(true);
				
					//comprobamos si el RootPane es null para ver si hemos clickado el boton 
					//aceptar o rechazar
					if(de.getRootPane() != null) {
						modeloTablaEsp.setColumnIdentifiers(new Object[] {"Nombre", "Fecha(mmdd)", "Descripcion"});
						tablaEspecifica.setModel(modeloTablaEsp);					
						
						try {
							appLegada.cargarListadoTareasEspecificas();
							Sincronizador.waitSyncro(1500); //3500,7500
							comunicacionWS.limpiarEntrada();
							Sincronizador.waitSyncro(1500); //3500,7500
							//mostrarTareasEspecificas();
							ArrayList<Tarea> nuevoListado = appLegada.obtenerListadoTareasEspecificas();
							if((nuevoListado!= null) && (nuevoListado.size()>0)) {
								for(int i = 0; i < nuevoListado.size(); i++) {
									Vector<String> v1 = new Vector<String>();
									v1.addElement(nuevoListado.get(i).getNombre());
									v1.addElement(nuevoListado.get(i).getFecha());
									v1.addElement(nuevoListado.get(i).getDescripcion());
									modeloTablaEsp.addRow(v1);
									/*System.out.println(nuevoListado.get(i).getNumeroTarea() + " " + nuevoListado.get(i).getNombre() + " " + 
						            		nuevoListado.get(i).getFecha() + " " + nuevoListado.get(i).getDescripcion());*/
						        }
								modeloTablaEsp = new DefaultTableModel();
							}
						} catch (Exception e1) {
				            e1.printStackTrace();
				        }
						btnAgregarEsp.setBackground(Color.lightGray);
						btnAtras.setBackground(Color.lightGray);
					}
					comunicacionWS.devolverFlag();
				}
			}
		});
	}

}
