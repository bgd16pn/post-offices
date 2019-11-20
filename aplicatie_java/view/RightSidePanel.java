package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RightSidePanel extends JPanel {

	private static final long serialVersionUID = 3251281475369572142L;

	private JTextField trackingCodeTextField;
	private JLabel lblTrackingCode;
	private JButton btnTrack;

	public RightSidePanel() {
		this.setBounds(0, 0, 193, 175);
		setLayout(null);

		JLabel lblTracking = new JLabel("Tracking");
		lblTracking.setBounds(12, 13, 127, 25);
		lblTracking.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblTracking);

		lblTrackingCode = new JLabel("Cod tracking");
		lblTrackingCode.setBounds(12, 64, 194, 23);
		lblTrackingCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblTrackingCode);

		trackingCodeTextField = new JTextField();
		trackingCodeTextField.setBounds(12, 95, 164, 23);
		trackingCodeTextField.setColumns(10);
		add(trackingCodeTextField);

		btnTrack = new JButton("Cauta");
		btnTrack.setBounds(12, 131, 97, 25);
		add(btnTrack);
	}

	public void setTrackingCode(final String code) {
		trackingCodeTextField.setText(code);
	}
	
	public String getTrackingCode() {
		return trackingCodeTextField.getText();
	}
	
	public void setTrackButtonVisible(final boolean flag) {
		btnTrack.setVisible(flag);
	}

	public void addTrackButtonActionListener(final ActionListener actionListener) {
		btnTrack.addActionListener(actionListener);
	}
}
