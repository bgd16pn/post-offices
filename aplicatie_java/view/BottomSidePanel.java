package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomSidePanel extends JPanel {

	private static final long serialVersionUID = -2684899800532913094L;

	private JButton btnCreate;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnSend;
	private JButton btnAvizare;
	private JButton btnRidica;
	private JButton btnGenerateReport;

	public BottomSidePanel() {

		this.setLayout(null);
		this.setBounds(0, 0, 860, 80);
		
		btnSend = new JButton("Transmite");
		btnSend.setBounds(284, 12, 100, 25);
		add(btnSend);
		
		btnAvizare = new JButton("Avizeaza");
		btnAvizare.setBounds(476, 12, 100, 25);
		add(btnAvizare);

		btnCreate = new JButton("Adauga");
		btnCreate.setBounds(94, 12, 100, 25);
		add(btnCreate);

		btnModify = new JButton("Modifica");
		btnModify.setBounds(284, 12, 100, 25);
		add(btnModify);

		btnDelete = new JButton("Sterge");
		btnDelete.setBounds(476, 12, 100, 25);
		add(btnDelete);

		btnSave = new JButton("Salveaza");
		btnSave.setBounds(668, 12, 100, 25);
		add(btnSave);
		
		btnCancel = new JButton("Anuleaza");
		btnCancel.setBounds(385, 50, 100, 25);
		add(btnCancel);
		
		btnRidica = new JButton("Ridica");
		btnRidica.setBounds(668, 12, 100, 25);
		add(btnRidica);
		
		btnGenerateReport = new JButton("Genereaza raport");
		btnGenerateReport.setBounds(330, 12, 201, 25);
		add(btnGenerateReport);
		
		btnCancel.setVisible(false);
		btnSave.setVisible(false);
		btnRidica.setVisible(false);
		btnSend.setVisible(false);
		btnAvizare.setVisible(false);
		btnGenerateReport.setVisible(false);
	}
	
	public void setGenerateReportButtonVisible(final boolean flag) {
		btnGenerateReport.setVisible(flag);
	}
	
	public void setCancelButtonVisible(final boolean flag) {
		btnCancel.setVisible(flag);
	}

	public void setCreateButtonVisible(final boolean flag) {
		btnCreate.setVisible(flag);
	}

	public void setModifyButtonVisible(final boolean flag) {
		btnModify.setVisible(flag);
	}

	public void setDeleteButtonVisible(final boolean flag) {
		btnDelete.setVisible(flag);
	}

	public void setSaveButtonVisible(final boolean flag) {
		btnSave.setVisible(flag);
	}
	
	public void setSendButtonVisible(final boolean flag) {
		btnSend.setVisible(flag);
	}

	public void setAvizareButtonVisible(final boolean flag) {
		btnAvizare.setVisible(flag);
	}

	public void setRidicaButtonVisible(final boolean flag) {
		btnRidica.setVisible(flag);
	}

	public void addGenerateReportButtonActionListener(final ActionListener actionListener) {
		btnGenerateReport.addActionListener(actionListener);
	}
	
	public void addCancelButtonActionListener(final ActionListener actionListener) {
		btnCancel.addActionListener(actionListener);
	}
	
	public void addCreateButtonActionListener(final ActionListener actionListener) {
		btnCreate.addActionListener(actionListener);
	}

	public void addModifyButtonActionListener(final ActionListener actionListener) {
		btnModify.addActionListener(actionListener);
	}

	public void addDeleteButtonActionListener(final ActionListener actionListener) {
		btnDelete.addActionListener(actionListener);
	}

	public void addSaveButtonActionListener(final ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}
	
	public void addSendButtonActionListener(final ActionListener actionListener) {
		btnSend.addActionListener(actionListener);
	}

	public void addAvizareButtonActionListener(final ActionListener actionListener) {
		btnAvizare.addActionListener(actionListener);
	}

	public void addRidicaButtonActionListener(final ActionListener actionListener) {
		btnRidica.addActionListener(actionListener);
	}
}
