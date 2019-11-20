package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddressPanel extends JPanel {

	private static final long serialVersionUID = -8405870592831377918L;
	
	private JTextField stradaTextField;
	private JTextField numarTextField;
	private JTextField scaraTextField;
	private JTextField blocTextField;
	private JTextField apartamentTextField;
	private JTextField orasTextField;
	private JTextField judetTextField;
	private JTextField codPostalTextField;
	
	public AddressPanel() {
		
		this.setLayout(null);
		this.setBounds(0, 0, 339, 176);
		
		JLabel lblStarCodPostal = new JLabel("*");
		lblStarCodPostal.setForeground(Color.RED);
		lblStarCodPostal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarCodPostal.setBounds(310, 120, 18, 14);
		add(lblStarCodPostal);
		
		JLabel lblStarOras = new JLabel("*");
		lblStarOras.setForeground(Color.RED);
		lblStarOras.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarOras.setBounds(34, 122, 18, 14);
		add(lblStarOras);
		
		JLabel lblStarNumar = new JLabel("*");
		lblStarNumar.setForeground(Color.RED);
		lblStarNumar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarNumar.setBounds(327, 0, 18, 14);
		add(lblStarNumar);
		
		JLabel lblStarStrada = new JLabel("*");
		lblStarStrada.setForeground(Color.RED);
		lblStarStrada.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarStrada.setBounds(46, 1, 18, 14);
		add(lblStarStrada);
		
		stradaTextField = new JTextField();
		stradaTextField.setBounds(0, 26, 272, 26);
		add(stradaTextField);
		
		JLabel lblStada = new JLabel("Strada");
		lblStada.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStada.setBounds(0, 5, 56, 14);
		add(lblStada);
		
		numarTextField = new JTextField();
		numarTextField.setBounds(284, 26, 52, 26);
		add(numarTextField);
		
		JLabel lblNumar = new JLabel("Numar");
		lblNumar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumar.setBounds(284, 5, 52, 14);
		add(lblNumar);
		
		scaraTextField = new JTextField();
		scaraTextField.setBounds(122, 86, 76, 26);
		add(scaraTextField);
		
		JLabel lblBloc = new JLabel("Bloc");
		lblBloc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBloc.setBounds(0, 65, 76, 14);
		add(lblBloc);
		
		blocTextField = new JTextField();
		blocTextField.setBounds(0, 86, 76, 26);
		add(blocTextField);
		
		JLabel lblScara = new JLabel("Scara");
		lblScara.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScara.setBounds(122, 65, 76, 14);
		add(lblScara);
		
		apartamentTextField = new JTextField();
		apartamentTextField.setBounds(243, 86, 93, 26);
		add(apartamentTextField);
		
		JLabel lblApartament = new JLabel("Apartament");
		lblApartament.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApartament.setBounds(243, 65, 93, 14);
		add(lblApartament);
		
		orasTextField = new JTextField();
		orasTextField.setBounds(0, 146, 99, 26);
		add(orasTextField);
		
		JLabel lblOras = new JLabel("Oras");
		lblOras.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOras.setBounds(0, 125, 36, 14);
		add(lblOras);
		
		judetTextField = new JTextField();
		judetTextField.setBounds(122, 146, 99, 26);
		add(judetTextField);
		
		JLabel lblJudet = new JLabel("Judet");
		lblJudet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJudet.setBounds(122, 125, 46, 14);
		add(lblJudet);
		
		codPostalTextField = new JTextField();
		codPostalTextField.setBounds(237, 146, 99, 26);
		add(codPostalTextField);
		
		JLabel lblCodPostal = new JLabel("Cod postal");
		lblCodPostal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodPostal.setBounds(237, 125, 76, 14);
		add(lblCodPostal);
		
		JLabel lblStarJudet = new JLabel("*");
		lblStarJudet.setForeground(Color.RED);
		lblStarJudet.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarJudet.setBounds(161, 120, 18, 14);
		add(lblStarJudet);
		
	}
	
	public void setStrada(final String strada) {
		stradaTextField.setText(strada);
	}
	
	public void setNumar(final String numar) {
		numarTextField.setText(numar);
	}
	
	public void setBloc(final String bloc) {
		blocTextField.setText(bloc);
	}
	
	public void setScara(final String scara) {
		scaraTextField.setText(scara);
	}
	
	public void setApartament(final String apartament) {
		apartamentTextField.setText(apartament);
	}
	
	public void setOras(final String oras) {
		orasTextField.setText(oras);
	}
	
	public void setJudet(final String judet) {
		judetTextField.setText(judet);
	}
	
	public void setCodPostal(final String codPostal) {
		codPostalTextField.setText(codPostal);
	}
	
	public String getStrada() {
		return stradaTextField.getText();
	}
	
	public String getNumar() {
		return numarTextField.getText();
	}
	
	public String getBloc() {
		return blocTextField.getText();
	}
	
	public String getScara() {
		return scaraTextField.getText();
	}
	
	public String getApartament() {
		return apartamentTextField.getText();
	}
	
	public String getOras() {
		return orasTextField.getText();
	}
	
	public String getJudet() {
		return judetTextField.getText();
	}
	
	public String getCodPostal() {
		return codPostalTextField.getText();
	}
	
	public void clearTextFields() {
		setStrada("");
		setNumar("");
		setBloc("");
		setScara("");
		setApartament("");
		setOras("");
		setJudet("");
		setCodPostal("");
	}
	
}
