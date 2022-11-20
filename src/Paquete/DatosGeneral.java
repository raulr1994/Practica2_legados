package Paquete;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class DatosGeneral extends JDialog {

	public final JPanel contentPanel = new JPanel();
	public JTextField txtDia;
	public JTextField txtDescGen;
	private JTextField txtMes;

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
	public DatosGeneral() {
		//super(parent, modal);
		addWindowListener(new WindowAdapter(){
		    @Override
		    public void windowClosing(WindowEvent et) {
				//marcar el Jdialog como no accesible
				comunicacionWS.devolverFlag();
				setRootPane(null);
				//sabemos que se hizo click en el boton cancelar
				dispose();
				TareasGenerales.btnAgregarGen.setBackground(Color.lightGray);
				TareasGenerales.btnAtras.setBackground(Color.lightGray);
		    };
		});
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 746, 288);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha (dia):");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 60, 111, 34);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDescripcionmax = new JLabel("Descripcion (max 15 caracteres):");
			lblDescripcionmax.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescripcionmax.setBounds(10, 171, 215, 34);
			contentPanel.add(lblDescripcionmax);
		}
		{
			txtDia = new JTextField();
			txtDia.setBounds(234, 65, 195, 26);
			contentPanel.add(txtDia);
			txtDia.setColumns(10);
		}
		{
			txtDescGen = new JTextField();
			txtDescGen.setColumns(10);
			txtDescGen.setBounds(235, 176, 466, 26);
			contentPanel.add(txtDescGen);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("A\u00F1adir Tarea General");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_1.setBounds(213, 11, 291, 34);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblFechames = new JLabel("Fecha (mes):");
			lblFechames.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFechames.setBounds(10, 115, 111, 34);
			contentPanel.add(lblFechames);
		}
		{
			txtMes = new JTextField();
			txtMes.setColumns(10);
			txtMes.setBounds(234, 120, 195, 26);
			contentPanel.add(txtMes);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.lightGray);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//comprobamos los campos
						try 
						{
							
							
							String diaTxt, mesTxt;
							diaTxt = txtDia.getText();
							mesTxt = txtMes.getText();
							
							int dia, mes;
							dia = Integer.parseInt(diaTxt);
							mes = Integer.parseInt(mesTxt);
							
							if(diaTxt.length() == 0) {
								JOptionPane.showMessageDialog(null, "El dia introduccido es invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
							if(mesTxt.length() == 0) {
								JOptionPane.showMessageDialog(null, "El mes introduccido es invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
							
							boolean diaOk, mesOk;
							
							if(mes>12 || mes<1) {
								mesOk = false;
							}else {
								mesOk = true;
							}
							
							if(dia>31 || dia<1) {
								diaOk = false;
							}else {
								diaOk = true;
							}
							
							if(mesOk) {
								if(mes ==1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
									if (dia<=31 && dia >=1) {
										diaOk = true;
									}else {
										diaOk = false;
									}
								}
								
								if(mes ==4 || mes == 6 || mes == 9 || mes == 11) {
									if (dia<=30 && dia >=1) {
										diaOk = true;
									}else {
										diaOk = false;
									}
								}
								
								if(mes == 2) {
									if (dia<=28 && dia >=1) {
										diaOk = true;
									}else {
										diaOk = false;
									}
								}
							
							}else {
								JOptionPane.showMessageDialog(null, "El mes introduccido es invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							
							if(!diaOk) {
								JOptionPane.showMessageDialog(null, "El dia introduccido es invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
							if((txtDescGen.getText().trim().length() == 0)||(txtDescGen.getText().trim().length() > 15)) {
								JOptionPane.showMessageDialog(null, "La descripci�n introduccida es inv�lida", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
							JOptionPane.showMessageDialog(null, "Los datos introduccidos son v�lidos");
							//todo salio bien entonces ocultamos el Jdialog, sin destruirlo
							setVisible(false);
							try {
								crearGeneral(mesTxt,diaTxt,txtDescGen.getText());
							} catch (Exception e1) {
					            e1.printStackTrace();
					        }
							
						}catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.lightGray);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//marcar el Jdialog como no accesible
						comunicacionWS.devolverFlag();
						setRootPane(null);
						//sabemos que se hizo click en el boton cancelar
						dispose();
						TareasGenerales.btnAgregarGen.setBackground(Color.lightGray);
						TareasGenerales.btnAtras.setBackground(Color.lightGray);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	void crearGeneral(String mes, String dia, String descripcion){
		appLegada.crearTareaGeneral(mes,dia,descripcion);
		Sincronizador.waitSyncro(2500); //3500,9500
		comunicacionWS.limpiarEntrada();
		Sincronizador.waitSyncro(2500); //3500,9500
		System.out.println("Tarea general creada");
	}
}
