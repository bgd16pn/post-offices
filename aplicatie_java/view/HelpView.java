package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HelpView extends JFrame {

	private static final long serialVersionUID = 3134930154432916964L;

	private final static int windowWidth = 350;
	private final static int windowHeight = 300;

	public HelpView(int widthOrigin, int heightOrigin) {

		this.setTitle("Ajutor");
		this.setResizable(false);

		this.setBounds(widthOrigin / 2 - windowWidth / 2, heightOrigin / 2 - windowHeight / 2, windowWidth,
				windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCreator = new JLabel("Developer");
		lblCreator.setForeground(new Color(128, 0, 128));
		lblCreator.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCreator.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreator.setBounds(88, 42, 168, 32);
		getContentPane().add(lblCreator);

		JLabel lblPaunBogdan = new JLabel("Paun Bogdan");
		lblPaunBogdan.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaunBogdan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaunBogdan.setBounds(88, 101, 168, 32);
		getContentPane().add(lblPaunBogdan);

		JLabel lblA = new JLabel("30211_2 A");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblA.setBounds(88, 134, 168, 32);
		getContentPane().add(lblA);

		JLabel lblBogdanpaungmailcom = new JLabel("bogdan16paun@gmail.com");
		lblBogdanpaungmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblBogdanpaungmailcom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBogdanpaungmailcom.setBounds(88, 167, 168, 32);
		getContentPane().add(lblBogdanpaungmailcom);

	}
}
