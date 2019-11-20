package view;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScrisoarePanel extends JPanel {

	private static final long serialVersionUID = 5448688214526630014L;
	
	private JTextField valoareRambursTextField;
	private JTextField valoareDeclarataTextField;
	private JTextField greutateTextField;

	private JCheckBox trackedCheckBox;

	private JCheckBox confirmarePrimireCheckBox;
		
	public ScrisoarePanel() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 304, 176);
		
		JLabel lblValoareRamburs = new JLabel("Valoare ramburs");
		lblValoareRamburs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValoareRamburs.setBounds(12, 13, 125, 16);
		add(lblValoareRamburs);
		
		valoareRambursTextField = new JTextField();
		valoareRambursTextField.setBounds(149, 10, 104, 22);
		add(valoareRambursTextField);
		valoareRambursTextField.setColumns(10);
		
		JLabel lblValoareDeclarata = new JLabel("Valoare declarata");
		lblValoareDeclarata.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValoareDeclarata.setBounds(12, 45, 125, 16);
		add(lblValoareDeclarata);
		
		valoareDeclarataTextField = new JTextField();
		valoareDeclarataTextField.setColumns(10);
		valoareDeclarataTextField.setBounds(149, 42, 104, 22);
		add(valoareDeclarataTextField);
		
		JLabel lblGreutate = new JLabel("Greutate (g)");
		lblGreutate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGreutate.setBounds(12, 77, 125, 16);
		add(lblGreutate);
		
		greutateTextField = new JTextField();
		greutateTextField.setColumns(10);
		greutateTextField.setBounds(149, 74, 104, 22);
		add(greutateTextField);
		
		trackedCheckBox = new JCheckBox("");
		trackedCheckBox.setBounds(145, 109, 25, 25);
		add(trackedCheckBox);
		
		JLabel lblTracked = new JLabel("Tracked");
		lblTracked.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTracked.setBounds(12, 114, 125, 16);
		add(lblTracked);
		
		confirmarePrimireCheckBox = new JCheckBox("");
		confirmarePrimireCheckBox.setBounds(144, 139, 25, 25);
		add(confirmarePrimireCheckBox);
		
		JLabel lblConfirmarePrimire = new JLabel("Confirmare primire");
		lblConfirmarePrimire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmarePrimire.setBounds(12, 144, 125, 16);
		add(lblConfirmarePrimire);
	}

	public float getValoareRamburs() {
		final String ramburs = valoareRambursTextField.getText();
		if(!ramburs.equals("")) {
			return Float.parseFloat(ramburs);
		}
		return -1;
	}

	public float getValoareDeclarata() {
		final String valoareDeclarata = valoareDeclarataTextField.getText();
		if(!valoareDeclarata.equals("")) {
			return Float.parseFloat(valoareDeclarata);
		}
		return -1;
	}

	public float getGreutate() {
		final String greutate = greutateTextField.getText();
		if(!greutate.equals("")) {
			return Float.parseFloat(greutate);
		}
		return -1;
	}

	public boolean isTracked() {
		return trackedCheckBox.isSelected();
	}

	public boolean isConfirmarePrimire() {
		return confirmarePrimireCheckBox.isSelected();
	}

	public void clearFields() {
		valoareRambursTextField.setText("");
		valoareDeclarataTextField.setText("");
		greutateTextField.setText("");
		trackedCheckBox.setSelected(false);
		confirmarePrimireCheckBox.setSelected(false);
	}
}
