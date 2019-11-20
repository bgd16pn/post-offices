package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LoginView extends JFrame{

	private static final long serialVersionUID = 3168345495485349297L;
	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JButton passwordToggleButton;
	private JLabel messageLabel;
	
	private final static int windowWidth = 350;
	private final static int windowHeight = 300;
	
	public LoginView(int widthOrigin, int heightOrigin) {
		
		this.setTitle("Login");
		this.setResizable(false);
		
		this.setBounds(widthOrigin/2 - windowWidth/2, heightOrigin/2 - windowHeight/2, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(130, 49, 84, 14);
		getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("parola");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(136, 108, 71, 14);
		getContentPane().add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(91, 69, 162, 26);
		getContentPane().add(usernameTextField);

		passwordTextField = new JPasswordField();
		passwordTextField.setEchoChar('*');
		passwordTextField.setBounds(91, 129, 162, 26);
		getContentPane().add(passwordTextField);

		loginButton = new JButton("Login");
		loginButton.setForeground(Color.BLACK);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		loginButton.setBounds(134, 183, 75, 23);
		getContentPane().add(loginButton);
		
		messageLabel = new JLabel("Error message");
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		messageLabel.setForeground(Color.RED);
		messageLabel.setBounds(12, 236, 309, 16);
		getContentPane().add(messageLabel);
		messageLabel.setVisible(false);
		
		passwordToggleButton = new JButton("?");
		passwordToggleButton.setForeground(Color.RED);
		passwordToggleButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordToggleButton.setBounds(293, 129, 39, 26);
		getContentPane().add(passwordToggleButton);
	}
	
	public void addLoginButtonActionListener(final ActionListener actionListener) {
		loginButton.addActionListener(actionListener);
	}
	
	public void addPasswordToggleButtonMouseListener(final MouseListener mouseListener) {
		passwordToggleButton.addMouseListener(mouseListener);
	}

	public void setUsername(final String username) {
		this.usernameTextField.setText(username);
	}
	
	public void setPassword(final String password) {
		this.passwordTextField.setText(password);
	}
	
	public String getUsername() {
		return usernameTextField.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordTextField.getPassword());
	}
	
	public void hidePassword() {
		passwordTextField.setEchoChar('*');
	}

	public void displayPassword() {
		passwordTextField.setEchoChar((char) 0);
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
	
	public void clearLoginTextFields() {
		this.setUsername("");
		this.setPassword("");
	}	
}
