package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AddArticolPostalView extends JFrame {

	private static final long serialVersionUID = -4902971771142213136L;

	private JTextField prenumeDestinatarTextField;
	private JTextField numeDestinatarTextField;
	private JTextField prenumeExpeditorTextField;
	private JTextField numeExpeditorTextField;
	private JLabel messageLabel;
	private JButton btnAdd;
	private JComboBox<String> tipArticolComboBox;

	private ColetPanel coletPanel;
	private ScrisoarePanel scrisoarePanel;
	private MandatPanel mandatPanel;
	private AddressPanel adresaExpeditorPanel;
	private AddressPanel adresaDestinatarPanel;

	private final static int windowWidth = 770;
	private final static int windowHeight = 646;
	
	private final static String[] itemCategories = {"", "Colet", "Scrisoare", "Mandat"};
	
	public AddArticolPostalView(int widthOrigin, int heightOrigin) {
		this.setTitle("Adauga articol postal");
		this.setResizable(false);

		this.setBounds(widthOrigin / 2 - windowWidth / 2, heightOrigin / 2 - windowHeight / 2, windowWidth,
				windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		adresaExpeditorPanel = new AddressPanel();
		adresaExpeditorPanel.setBounds(32, 93, 339, 174);
		getContentPane().add(adresaExpeditorPanel);

		JLabel lblPrenumeExpeditor = new JLabel("Prenume expeditor");
		lblPrenumeExpeditor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrenumeExpeditor.setBounds(206, 23, 123, 14);
		getContentPane().add(lblPrenumeExpeditor);

		prenumeExpeditorTextField = new JTextField();
		prenumeExpeditorTextField.setBounds(206, 44, 162, 26);
		getContentPane().add(prenumeExpeditorTextField);

		JLabel lblNumeExpeditor = new JLabel("Nume expeditor");
		lblNumeExpeditor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumeExpeditor.setBounds(32, 23, 111, 14);
		getContentPane().add(lblNumeExpeditor);

		numeExpeditorTextField = new JTextField();
		numeExpeditorTextField.setBounds(32, 44, 162, 26);
		getContentPane().add(numeExpeditorTextField);

		btnAdd = new JButton("Adauga");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(341, 559, 89, 23);
		getContentPane().add(btnAdd);

		messageLabel = new JLabel("Error message");
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		messageLabel.setForeground(Color.RED);
		messageLabel.setBounds(12, 582, 174, 16);
		getContentPane().add(messageLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(32, 276, 699, 8);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(32, 82, 697, 8);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(380, 23, 8, 254);
		getContentPane().add(separator_2);

		JLabel lblNumeDestinatar = new JLabel("Nume destinatar");
		lblNumeDestinatar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumeDestinatar.setBounds(391, 23, 111, 14);
		getContentPane().add(lblNumeDestinatar);

		JLabel lblPrenumeDestinatar = new JLabel("Prenume destinatar");
		lblPrenumeDestinatar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrenumeDestinatar.setBounds(565, 23, 144, 14);
		getContentPane().add(lblPrenumeDestinatar);

		prenumeDestinatarTextField = new JTextField();
		prenumeDestinatarTextField.setBounds(565, 44, 162, 26);
		getContentPane().add(prenumeDestinatarTextField);

		numeDestinatarTextField = new JTextField();
		numeDestinatarTextField.setBounds(391, 44, 162, 26);
		getContentPane().add(numeDestinatarTextField);

		adresaDestinatarPanel = new AddressPanel();
		adresaDestinatarPanel.setBounds(391, 93, 339, 174);
		getContentPane().add(adresaDestinatarPanel);

		tipArticolComboBox = new JComboBox<String>();
		tipArticolComboBox.setBounds(200, 293, 129, 22);
		getContentPane().add(tipArticolComboBox);
		
		for(String category : itemCategories) {
			tipArticolComboBox.addItem(category);
		}

		coletPanel = new ColetPanel();
		coletPanel.setBounds(390, 292, 304, 176);
		getContentPane().add(coletPanel);
		coletPanel.setVisible(false);

		scrisoarePanel = new ScrisoarePanel();
		scrisoarePanel.setBounds(390, 292, 304, 176);
		getContentPane().add(scrisoarePanel);
		scrisoarePanel.setVisible(false);

		mandatPanel = new MandatPanel();
		mandatPanel.setBounds(390, 292, 304, 176);
		getContentPane().add(mandatPanel);
		mandatPanel.setVisible(false);

		JLabel lblTipArticolPostal = new JLabel("Tip articol postal");
		lblTipArticolPostal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipArticolPostal.setBounds(77, 297, 111, 14);
		getContentPane().add(lblTipArticolPostal);
		messageLabel.setVisible(false);
	}

	public void addTipArticolComboBoxActionListener(final ActionListener actionListener) {
		tipArticolComboBox.addActionListener(actionListener);
	}

	public void addAddButtonActionListener(final ActionListener e) {
		btnAdd.addActionListener(e);
	}

	public ColetPanel getColetPanel() {
		return coletPanel;
	}

	public ScrisoarePanel getScrisoarePanel() {
		return scrisoarePanel;
	}

	public MandatPanel getMandatPanel() {
		return mandatPanel;
	}

	public void setNumeExpeditor(final String nume) {
		this.numeExpeditorTextField.setText(nume);
	}

	public void setPrenumeExpeditor(final String prenume) {
		this.prenumeExpeditorTextField.setText(prenume);
	}

	public String getNumeExpeditor() {
		return numeExpeditorTextField.getText();
	}

	public String getPrenumeExpeditor() {
		return prenumeExpeditorTextField.getText();
	}
	
	public void setNumeDestinatar(final String nume) {
		this.numeDestinatarTextField.setText(nume);
	}

	public void setPrenumeDestinatar(final String prenume) {
		this.prenumeDestinatarTextField.setText(prenume);
	}

	public String getNumeDestinatar() {
		return numeDestinatarTextField.getText();
	}

	public String getPrenumeDestinatar() {
		return prenumeDestinatarTextField.getText();
	}

	public void displayMessage(String messageText) {
		final Runnable runnable = new Runnable() {

			@Override
			public void run() {
				messageLabel.setText(messageText);
				messageLabel.setVisible(true);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					messageLabel.setVisible(false);
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void clearRegisterTextFields() {
		adresaExpeditorPanel.clearTextFields();
		adresaDestinatarPanel.clearTextFields();
		clearUserTextFields();
		tipArticolComboBox.setSelectedIndex(-1);
	}

	public void clearUserTextFields() {
		setNumeExpeditor("");
		setPrenumeExpeditor("");
		setNumeDestinatar("");
		setPrenumeDestinatar("");
	}

	public int getTipArticolComboBoxSelectedIndex() {
		return tipArticolComboBox.getSelectedIndex();
	}

	public String getTipArticolComboBoxItem() {
		return (String) tipArticolComboBox.getSelectedItem();
	}

	public void setPanelsVisible(boolean coletPanelVisibleFlag, boolean scrisoarePanelVisibleFlag,
			boolean mandatPanelVisibleFlag) {
		coletPanel.setVisible(coletPanelVisibleFlag);
		scrisoarePanel.setVisible(scrisoarePanelVisibleFlag);
		mandatPanel.setVisible(mandatPanelVisibleFlag);
	}

	public AddressPanel getAdresaExpeditorPanel() {
		return adresaExpeditorPanel;
	}
	
	public AddressPanel getAdresaDestinatarPanel() {
		return adresaDestinatarPanel;
	}

	public void clearInputFields() {
		coletPanel.clearFields();
		scrisoarePanel.clearFields();
		mandatPanel.clearFields();
	}

	public void setDisplayMessageColor(Color color) {
		messageLabel.setForeground(color);
	}
}
