package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TopSidePanel extends JPanel {

	private static final long serialVersionUID = -3503749439673395796L;

	private JButton btnLogs;
	private JButton btnHelp;
	private JButton btnHome;
	private JButton btnStorage;

	public TopSidePanel() {

		this.setBounds(0, 0, 930, 70);
		setLayout(null);

		btnHome = new JButton("Acasa");
		add(btnHome);
		btnHome.setBounds(12, 22, 100, 25);

		btnLogs = new JButton("Jurnal");
		btnLogs.setBounds(415, 22, 100, 25);
		add(btnLogs);

		btnHelp = new JButton("Ajutor");
		btnHelp.setBounds(818, 22, 100, 25);
		add(btnHelp);
		
		btnStorage = new JButton("Magazie");
		btnStorage.setBounds(213, 22, 100, 25);
		add(btnStorage);

	}

	public void setStorageButtonVisible(final boolean flag) {
		btnStorage.setVisible(flag);
	}
	
	public void setLogsButtonVisible(final boolean flag) {
		btnLogs.setVisible(flag);
	}
	
	public void addStorageButtonActionListener(final ActionListener actionListener) {
		btnStorage.addActionListener(actionListener);
	}
	
	public void addHomeButtonActionListener(final ActionListener actionListener) {
		btnHome.addActionListener(actionListener);
	}

	public void addLogsButtonActionListener(final ActionListener actionListener) {
		btnLogs.addActionListener(actionListener);
	}

	public void addHelpButtonActionListener(final ActionListener actionListener) {
		btnHelp.addActionListener(actionListener);
	}
}
