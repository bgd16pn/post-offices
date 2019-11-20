package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

// check this for creating a list of elements - http://www.java2s.com/Code/Java/Swing-Components/CheckListExample2.htm

public class DashboardView extends JFrame {

	private static final long serialVersionUID = -754961072607056889L;

	private JLabel lblMessage;
	private JLabel lblWelcome;
	private JButton btnTrack;
	private JButton btnLogin;
	private JButton btnRegister;
	private JTextField trackingCodeTextField;

	private final static int windowWidth = 359;
	private final static int windowHeight = 425;

	public DashboardView(int widthOrigin, int heightOrigin) {

		this.setTitle("Meniu start");
		this.setResizable(false);

		this.setBounds(widthOrigin / 2 - windowWidth / 2, heightOrigin / 2 - windowHeight / 2, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblWelcome = new JLabel("Bine ati venit!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblWelcome.setBounds(41, 72, 270, 39);
		getContentPane().add(lblWelcome);

		btnTrack = new JButton("Track");
		btnTrack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTrack.setForeground(Color.BLACK);
		btnTrack.setBounds(98, 205, 156, 25);
		getContentPane().add(btnTrack);

		btnRegister = new JButton("Inregistrare");
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.setBounds(98, 299, 156, 25);
		getContentPane().add(btnRegister);

		btnLogin = new JButton("Autentificare");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(98, 332, 156, 25);
		getContentPane().add(btnLogin);

		trackingCodeTextField = new JTextField();
		trackingCodeTextField.setBounds(98, 173, 156, 25);
		getContentPane().add(trackingCodeTextField);
		trackingCodeTextField.setColumns(10);

		lblMessage = new JLabel("Error message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(45, 243, 262, 25);
		getContentPane().add(lblMessage);
		lblMessage.setVisible(false);
	}
	
	public void addTrackButtonActionListener(final ActionListener actionListener) {
		btnTrack.addActionListener(actionListener);
	}
	
	public void addLoginButtonActionListener(final ActionListener actionListener) {
		btnLogin.addActionListener(actionListener);
	}
	
	public void addRegisterButtonActionListener(final ActionListener actionListener) {
		btnRegister.addActionListener(actionListener);
	}
	
	public String getTrackingCode() {
		return trackingCodeTextField.getText();
	}
	
	public void setTrackingCode(final String trackingCode) {
		trackingCodeTextField.setText(trackingCode);
	}
	
	public void displayMessage(String messageText) {
		final Runnable runnable = new Runnable() {

			@Override
			public void run() {
				lblMessage.setText(messageText);
				lblMessage.setVisible(true);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lblMessage.setVisible(false);
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void clearLoginTextFields() {
		trackingCodeTextField.setText("");
	}
}