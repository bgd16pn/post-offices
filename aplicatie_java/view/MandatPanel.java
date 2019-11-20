package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MandatPanel extends JPanel {
	private static final long serialVersionUID = -5519017213991215089L;
	
	private JTextField valoareTextField;
	private JTextField detaliiTextField;
		
	public MandatPanel() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 304, 176);
		
		JLabel lblValoare = new JLabel("Valoare");
		lblValoare.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValoare.setBounds(12, 13, 125, 16);
		add(lblValoare);
		
		valoareTextField = new JTextField();
		valoareTextField.setBounds(149, 10, 104, 22);
		add(valoareTextField);
		valoareTextField.setColumns(10);
		
		JLabel lblDetalii = new JLabel("Detalii");
		lblDetalii.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalii.setBounds(12, 45, 125, 16);
		add(lblDetalii);
		
		detaliiTextField = new JTextField();
		detaliiTextField.setColumns(10);
		detaliiTextField.setBounds(149, 42, 104, 22);
		add(detaliiTextField);
		
	}

	public float getValoare() {
		final String valoare = valoareTextField.getText();
		if(!valoare.equals("")) {
			return Float.parseFloat(valoare);
		}
		return -1;
	}

	public String getDetalii() {
		return detaliiTextField.getText();
	}

	public void clearFields() {
		valoareTextField.setText("");
		detaliiTextField.setText("");
	}
	
}
