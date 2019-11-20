package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftSidePanel extends JPanel {

	private static final long serialVersionUID = 1298301381021412066L;

	private JButton btnOfficeData;
	private JButton btnEmployees;
	private JButton btnUsers;
	private JButton btnPostalItems;
	private JButton btnLogout;
	private JButton btnTracking;
	private JButton btnPersonalData;
	private JButton btnTariffs;
	private JButton btnWorkingDays;
	private JButton btnAddresses;

	public LeftSidePanel() {

		this.setLayout(null);
		this.setBounds(0, 0, 160, 480);

		btnOfficeData = new JButton("Oficii postale");
		btnOfficeData.setBounds(20, 16, 120, 25);
		add(btnOfficeData);

		btnEmployees = new JButton("Angajati");
		btnEmployees.setBounds(20, 58, 120, 25);
		add(btnEmployees);

		btnUsers = new JButton("Utilizatori");
		btnUsers.setBounds(20, 101, 120, 25);
		add(btnUsers);

		btnPostalItems = new JButton("Articole postale");
		btnPostalItems.setBounds(20, 143, 120, 25);
		add(btnPostalItems);

		btnTracking = new JButton("Tracking");
		btnTracking.setBounds(20, 185, 120, 25);
		add(btnTracking);

		btnPersonalData = new JButton("Date personale");
		btnPersonalData.setBounds(20, 227, 120, 25);
		add(btnPersonalData);

		btnTariffs = new JButton("Tarife");
		btnTariffs.setBounds(20, 269, 120, 25);
		add(btnTariffs);

		btnWorkingDays = new JButton("Programe lucru");
		btnWorkingDays.setBounds(20, 311, 120, 25);
		add(btnWorkingDays);
		
		btnAddresses = new JButton("Adrese");
		btnAddresses.setBounds(20, 353, 120, 25);
		add(btnAddresses);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(31, 442, 97, 25);
		add(btnLogout);
	}

	public void setAdministratorMode() {
		btnOfficeData.setBounds(20, 16, 120, 25);
		btnOfficeData.setVisible(true);
		
		btnEmployees.setBounds(20, 58, 120, 25);
		btnEmployees.setVisible(true);
		
		btnUsers.setBounds(20, 101, 120, 25);
		btnUsers.setVisible(true);
		
		btnPostalItems.setBounds(20, 143, 120, 25);
		btnPostalItems.setVisible(true);
		
		btnTracking.setBounds(20, 185, 120, 25);
		btnTracking.setVisible(true);
		
		btnPersonalData.setBounds(20, 227, 120, 25);
		btnPersonalData.setVisible(true);
		
		btnTariffs.setBounds(20, 269, 120, 25);
		btnTariffs.setVisible(true);
		
		btnWorkingDays.setBounds(20, 311, 120, 25);
		btnWorkingDays.setVisible(true);	
		
		btnAddresses.setBounds(20, 353, 120, 25);
		btnAddresses.setVisible(true);
	}


	public void setEmployeeMode() {
		btnOfficeData.setBounds(20, 43, 120, 25);
		btnOfficeData.setVisible(false);
		
		btnEmployees.setBounds(20, 85, 120, 25);
		btnEmployees.setVisible(false);
		
		btnUsers.setBounds(20, 16, 120, 25);
		btnUsers.setVisible(true);
		
		btnPostalItems.setBounds(20, 58, 120, 25);
		btnPostalItems.setVisible(true);
		
		btnTracking.setBounds(20, 101, 120, 25);
		btnTracking.setVisible(true);
		
		btnPersonalData.setBounds(20, 143, 120, 25);
		btnPersonalData.setVisible(true);
		
		btnTariffs.setBounds(20, 227, 120, 25);
		btnTariffs.setVisible(true);
		
		btnWorkingDays.setBounds(20, 269, 120, 25);
		btnWorkingDays.setVisible(true);
		
		btnAddresses.setBounds(20, 185, 120, 25);
		btnAddresses.setVisible(true);
	}
	
	public void setUserMode() {
		btnOfficeData.setBounds(20, 43, 120, 25);
		btnOfficeData.setVisible(false);
		
		btnEmployees.setBounds(20, 85, 120, 25);
		btnEmployees.setVisible(false);
		
		btnUsers.setBounds(20, 128, 120, 25);
		btnUsers.setVisible(false);
		
		btnPostalItems.setBounds(20, 16, 120, 25);
		btnPostalItems.setVisible(true);
		
		btnTracking.setBounds(20, 58, 120, 25);
		btnTracking.setVisible(true);
		
		btnPersonalData.setBounds(20, 101, 120, 25);
		btnPersonalData.setVisible(true);
		
		btnTariffs.setBounds(20, 296, 120, 25);
		btnTariffs.setVisible(false);
		
		btnWorkingDays.setBounds(20, 338, 120, 25);
		btnWorkingDays.setVisible(false);
		
		btnAddresses = new JButton("Adrese");
		btnAddresses.setVisible(false);
	}

	public void addOfficeDataButtonActionListener(final ActionListener actionListener) {
		btnOfficeData.addActionListener(actionListener);
	}

	public void addEmployeesButtonActionListener(final ActionListener actionListener) {
		btnEmployees.addActionListener(actionListener);
	}

	public void addUsersButtonActionListener(final ActionListener actionListener) {
		btnUsers.addActionListener(actionListener);
	}

	public void addPostalItemsButtonActionListener(final ActionListener actionListener) {
		btnPostalItems.addActionListener(actionListener);
	}

	public void addLogoutButtonActionListener(final ActionListener actionListener) {
		btnLogout.addActionListener(actionListener);
	}

	public void addTrackingButtonActionListener(final ActionListener actionListener) {
		btnTracking.addActionListener(actionListener);
	}

	public void addPersonalDataButtonActionListener(final ActionListener actionListener) {
		btnPersonalData.addActionListener(actionListener);
	}

	public void addTariffsButtonActionListener(final ActionListener actionListener) {
		btnTariffs.addActionListener(actionListener);
	}

	public void addWorkingDaysButtonActionListener(final ActionListener actionListener) {
		btnWorkingDays.addActionListener(actionListener);
	}
	
	public void addAddressesButtonActionListener(final ActionListener actionListener) {
		btnAddresses.addActionListener(actionListener);
	}

	public void setAddressesButtonVisible(boolean flag) {
		btnAddresses.setVisible(flag);
	}
}
