package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ColetPanel extends JPanel {

	private static final long serialVersionUID = -4023580893171158868L;
	
	private JTextField valoareRambursTextField;
	private JTextField valoareDeclarataTextField;
	private JTextField greutateTextField;

	private JCheckBox livrareSambataCheckBox;
	private JCheckBox voluminosCheckBox;
	private JCheckBox fragilCheckBox;
	private JCheckBox trackedCheckBox;
		
	public ColetPanel() {
		
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
		
		JLabel lblGreutate = new JLabel("Greutate (kg)");
		lblGreutate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGreutate.setBounds(12, 77, 125, 16);
		add(lblGreutate);
		
		greutateTextField = new JTextField();
		greutateTextField.setColumns(10);
		greutateTextField.setBounds(149, 74, 104, 22);
		add(greutateTextField);
		
		trackedCheckBox = new JCheckBox("");
		trackedCheckBox.setBounds(90, 110, 25, 25);
		add(trackedCheckBox);
		
		JLabel lblTracked = new JLabel("Tracked");
		lblTracked.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTracked.setBounds(12, 114, 125, 16);
		add(lblTracked);
		
		JLabel lblFragil = new JLabel("Fragil");
		lblFragil.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFragil.setBounds(12, 144, 59, 16);
		add(lblFragil);
		
		fragilCheckBox = new JCheckBox("");
		fragilCheckBox.setBounds(90, 140, 25, 25);
		add(fragilCheckBox);
		
		JLabel lblVoluminos = new JLabel("Voluminos");
		lblVoluminos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVoluminos.setBounds(149, 114, 81, 16);
		add(lblVoluminos);
		
		voluminosCheckBox = new JCheckBox("");
		voluminosCheckBox.setBounds(271, 110, 25, 25);
		add(voluminosCheckBox);
		
		JLabel lblLivrareSambata = new JLabel("Livrare sambata");
		lblLivrareSambata.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLivrareSambata.setBounds(149, 144, 111, 16);
		add(lblLivrareSambata);
		
		livrareSambataCheckBox = new JCheckBox("");
		livrareSambataCheckBox.setBounds(271, 140, 25, 25);
		add(livrareSambataCheckBox);
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
	
	public boolean isVoluminos() {
		return voluminosCheckBox.isSelected();
	}
	
	public boolean isFragil() {
		return fragilCheckBox.isSelected();
	}
	
	public boolean isLivrareSambata() {
		return livrareSambataCheckBox.isSelected();
	}

	public void clearFields() {
		valoareRambursTextField.setText("");
		valoareDeclarataTextField.setText("");
		greutateTextField.setText("");
		trackedCheckBox.setSelected(false);
		fragilCheckBox.setSelected(false);
		voluminosCheckBox.setSelected(false);
		livrareSambataCheckBox.setSelected(false);
	}
	
	
}
