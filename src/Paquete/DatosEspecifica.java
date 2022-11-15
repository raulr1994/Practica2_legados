package Paquete;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comunicacion.ComunicacionMusicSP;
import comunicacion.Sincronizador;
import comunicacion.gestorTareas;
import comunicacion.wc3270;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosEspecifica extends JDialog {

	public final JPanel contentPanel = new JPanel();
	public JTextField txtFechaEsp;
	public JTextField txtDescEsp;
	public JTextField txtNombreEsp;
	
	static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = gestorTareas.getInstancia(comunicacionWS);

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			DatosGeneral dialog = new DatosGeneral();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * @param b 
	 * @param actionListener 
	 */
	public DatosEspecifica() {
		//super(parent, modal);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 746, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha ( dd/mm):");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 105, 111, 34);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDescripcionmax = new JLabel("Descripcion (max 15 caracteres):");
			lblDescripcionmax.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescripcionmax.setBounds(10, 150, 215, 34);
			contentPanel.add(lblDescripcionmax);
		}
		{
			txtFechaEsp = new JTextField();
			txtFechaEsp.setBounds(234, 110, 195, 26);
			contentPanel.add(txtFechaEsp);
			txtFechaEsp.setColumns(10);
		}
		{
			txtDescEsp = new JTextField();
			txtDescEsp.setColumns(10);
			txtDescEsp.setBounds(235, 155, 466, 26);
			contentPanel.add(txtDescEsp);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("A\u00F1adir Tarea Especifica");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_1.setBounds(213, 11, 291, 34);
			contentPanel.add(lblNewLabel_1);
		}
		
		JLabel lblNombremaxCaracteres = new JLabel("Nombre (max15 caracteres):");
		lblNombremaxCaracteres.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombremaxCaracteres.setBounds(10, 56, 215, 34);
		contentPanel.add(lblNombremaxCaracteres);
		
		txtNombreEsp = new JTextField();
		txtNombreEsp.setColumns(10);
		txtNombreEsp.setBounds(234, 61, 467, 26);
		contentPanel.add(txtNombreEsp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//comprobamos los campos
						try 
						{
							if((txtNombreEsp.getText().trim().length() == 0)||(txtNombreEsp.getText().trim().length() > 15)) {
								JOptionPane.showMessageDialog(null, "La fecha es invalida");
								return;
							}
							if(txtFechaEsp.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(null, "La fecha es invalida");
								return;
							}
							if((txtDescEsp.getText().trim().length() == 0)||(txtDescEsp.getText().trim().length() > 15)) {
								JOptionPane.showMessageDialog(null, "La descripción es invalida");
								return;
							}
							
							//todo salio bien entonces ocultamos el Jdialog, sin destruirlo
							setVisible(false);
						}catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
						}
						
						
						String fechaAux;
						fechaAux = txtFechaEsp.toString();
						
						int posicion;
						String dia, mes;
						
						posicion = fechaAux.indexOf("/");
						dia = fechaAux.substring(0,posicion);
						fechaAux = fechaAux.substring(posicion + 1);
						posicion = fechaAux.indexOf("/");
						mes = fechaAux.substring(0, posicion);
						
						try {
							appLegada.crearTareaEspecifica(mes, dia, txtNombreEsp.toString(), txtDescEsp.toString());
							Sincronizador.waitSyncro(9500);
							//Sincronizador.waitSyncro(1000);
							comunicacionWS.limpiarEntrada();
							Sincronizador.waitSyncro(9500);
							//Sincronizador.waitSyncro(1000);
							//appLegada.crearTareaEspecifica("12","13","Tarea2","Prueba de tarea 2");
							//comunicacionWS.limpiarEntrada();
							System.out.println("Tarea creada");
						} catch (Exception e1) {
				            e1.printStackTrace();
				        }
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//marcar el Jdialog como no accesible
						setRootPane(null);
						//sabemos que se hizo click en el boton cancelar
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
