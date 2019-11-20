package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

public class RegisterView extends JFrame {
	
	private static final long serialVersionUID = 1900096388246794613L;
	
	private JTextField usernameTextField;
	private JTextField prenumeTextField;
	private JTextField NumeTextField;
	private JTextField emailTextField;
	private JTextField telefonTextField;
	
	private JPasswordField passwordTextField;
	private JPasswordField confirmPasswordTextField;
	
	private JLabel messageLabel;
	private JButton registerButton;
	
	public AddressPanel addresPanel = new AddressPanel();
	
	private final static int windowWidth = 406;
	private final static int windowHeight = 646;
	
	public RegisterView(int widthOrigin, int heightOrigin) {
		this.setTitle("Register");
		this.setResizable(false);

		this.setBounds(widthOrigin/2 - windowWidth/2, heightOrigin/2 - windowHeight/2, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		addresPanel.setBounds(32, 93, 339, 174);
		getContentPane().add(addresPanel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(32, 289, 76, 14);
		getContentPane().add(lblUsername);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(32, 310, 162, 26);
		getContentPane().add(usernameTextField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(32, 469, 68, 14);
		getContentPane().add(lblPassword);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(32, 491, 162, 26);
		getContentPane().add(passwordTextField);

		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrenume.setBounds(206, 23, 76, 14);
		getContentPane().add(lblPrenume);

		prenumeTextField = new JTextField();
		prenumeTextField.setBounds(206, 44, 162, 26);
		getContentPane().add(prenumeTextField);

		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNume.setBounds(32, 23, 76, 14);
		getContentPane().add(lblNume);

		NumeTextField = new JTextField();
		NumeTextField.setBounds(32, 44, 162, 26);
		getContentPane().add(NumeTextField);

		registerButton = new JButton("Register");
		registerButton.setForeground(Color.BLACK);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		registerButton.setBounds(155, 549, 89, 23);
		getContentPane().add(registerButton);
		
		messageLabel = new JLabel("Error message");
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		messageLabel.setForeground(Color.RED);
		messageLabel.setBounds(32, 585, 174, 16);
		getContentPane().add(messageLabel);
		
		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField.setBounds(206, 491, 162, 26);
		getContentPane().add(confirmPasswordTextField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmPassword.setBounds(206, 469, 134, 14);
		getContentPane().add(lblConfirmPassword);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(32, 370, 162, 26);
		getContentPane().add(emailTextField);
		
		JLabel lblEmail = new JLabel("Email address");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(32, 349, 99, 14);
		getContentPane().add(lblEmail);
		
		telefonTextField = new JTextField();
		telefonTextField.setBounds(32, 430, 162, 26);
		getContentPane().add(telefonTextField);
		
		JLabel lblTelefon = new JLabel("Telephone");
		lblTelefon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefon.setBounds(32, 409, 76, 14);
		getContentPane().add(lblTelefon);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 276, 336, 8);
		getContentPane().add(separator);
		
		JLabel lblStarNume = new JLabel("*");
		lblStarNume.setForeground(Color.RED);
		lblStarNume.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarNume.setBounds(70, 22, 18, 14);
		getContentPane().add(lblStarNume);
		
		JLabel lblStarPrenume = new JLabel("*");
		lblStarPrenume.setForeground(Color.RED);
		lblStarPrenume.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarPrenume.setBounds(269, 22, 18, 14);
		getContentPane().add(lblStarPrenume);
				
		JLabel lblStarUsername = new JLabel("*");
		lblStarUsername.setForeground(Color.RED);
		lblStarUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarUsername.setBounds(104, 288, 18, 14);
		getContentPane().add(lblStarUsername);
		
		JLabel lblStarEmail = new JLabel("*");
		lblStarEmail.setForeground(Color.RED);
		lblStarEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarEmail.setBounds(124, 349, 18, 14);
		getContentPane().add(lblStarEmail);
		
		JLabel lblStarTelefon = new JLabel("*");
		lblStarTelefon.setForeground(Color.RED);
		lblStarTelefon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarTelefon.setBounds(104, 409, 18, 14);
		getContentPane().add(lblStarTelefon);
		
		JLabel lblStarParola = new JLabel("*");
		lblStarParola.setForeground(Color.RED);
		lblStarParola.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarParola.setBounds(102, 468, 18, 14);
		getContentPane().add(lblStarParola);
		
		JLabel lblStarConfirmParola = new JLabel("*");
		lblStarConfirmParola.setForeground(Color.RED);
		lblStarConfirmParola.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStarConfirmParola.setBounds(326, 464, 18, 14);
		getContentPane().add(lblStarConfirmParola);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(32, 82, 336, 8);
		getContentPane().add(separator_1);
		messageLabel.setVisible(false);
	}

	public void addRegisterButtonActionListener(final ActionListener e) {
		registerButton.addActionListener(e);
	}
	
	public void setEmail(final String email) {
		emailTextField.setText(email);
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}
	
	public void setTelefon(final String telefon) {
		telefonTextField.setText(telefon);
	}
	
	public String getTelefon() {
		return telefonTextField.getText();
	}
	
	public void setNume(final String nume) {
		this.NumeTextField.setText(nume);
	}

	public void setPrenume(final String prenume) {
		this.prenumeTextField.setText(prenume);
	}

	public void setUsername(final String username) {
		this.usernameTextField.setText(username);
	}

	public void setPassword(final String password) {
		passwordTextField.setText(password);
	}
	
	public void setConfirmPassword(final String confirmPassword) {
		confirmPasswordTextField.setText(confirmPassword);
	}

	public String getNume() {
		return NumeTextField.getText();
	}

	public String getPrenume() {
		return prenumeTextField.getText();
	}

	public String getUsername() {
		return usernameTextField.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordTextField.getPassword());
	}
	
	public String getConfirmPassword() {
		return String.valueOf(confirmPasswordTextField.getPassword());
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
		addresPanel.clearTextFields();
		clearUserTextFields();
	}

	public void clearUserTextFields() {
		setNume("");
		setPrenume("");
		setUsername("");
		setEmail("");
		setTelefon("");
		setPassword("");
		setConfirmPassword("");
	}

}
