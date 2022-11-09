package Paquete;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosGeneral extends JDialog {

	public final JPanel contentPanel = new JPanel();
	public JTextField txtFechaGen;
	public JTextField txtDescGen;

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
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 746, 233);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha ( dd/mm):");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 60, 111, 34);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDescripcionmax = new JLabel("Descripcion (max 25 caracteres):");
			lblDescripcionmax.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescripcionmax.setBounds(10, 105, 215, 34);
			contentPanel.add(lblDescripcionmax);
		}
		{
			txtFechaGen = new JTextField();
			txtFechaGen.setBounds(234, 65, 195, 26);
			contentPanel.add(txtFechaGen);
			txtFechaGen.setColumns(10);
		}
		{
			txtDescGen = new JTextField();
			txtDescGen.setColumns(10);
			txtDescGen.setBounds(235, 110, 466, 26);
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
							if(txtFechaGen.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(null, "La fecha es invalida");
								return;
							}
							if((txtDescGen.getText().trim().length() == 0)||(txtDescGen.getText().trim().length() > 25)) {
								JOptionPane.showMessageDialog(null, "La descripción es invalida");
								return;
							}
							
							//todo salio bien entonces ocultamos el Jdialog, sin destruirlo
							setVisible(false);
						}catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
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
