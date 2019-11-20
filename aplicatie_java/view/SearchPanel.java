package view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 4766860421139619544L;

	private JButton btnSearch;
	private JTextField searchTextField;

	private JLabel lblSearchBy;
	private JLabel lblSortBy;

	private JComboBox<String> sortByComboBox;
	private JComboBox<String> searchByComboBox;

	public SearchPanel() {

		this.setBounds(0, 0, 931, 65);
		this.setLayout(null);

		lblSortBy = new JLabel("Sorteaza");
		lblSortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSortBy.setBounds(45, 24, 65, 16);
		add(lblSortBy);

		sortByComboBox = new JComboBox<String>();
		sortByComboBox.setBounds(122, 20, 121, 25);
		add(sortByComboBox);
		
		sortByComboBox.addItem("Crescator");
		sortByComboBox.addItem("Descrescator");

		searchByComboBox = new JComboBox<String>();
		searchByComboBox.setBounds(403, 20, 121, 25);
		add(searchByComboBox);

		searchTextField = new JTextField();
		searchTextField.setBounds(620, 20, 179, 25);
		add(searchTextField);
		searchTextField.setColumns(10);

		btnSearch = new JButton("Cauta");
		btnSearch.setBounds(811, 20, 73, 25);
		add(btnSearch);

		lblSearchBy = new JLabel("Cautare dupa");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchBy.setBounds(303, 24, 88, 16);
		add(lblSearchBy);
	}

	public void addSearchButtonActionListener(final ActionListener actionListener) {
		btnSearch.addActionListener(actionListener);
	}

	public void addSearchByComboBoxItem(String item) {
		searchByComboBox.addItem(item);
	}

	public void addSortByComboBoxItem(String item) {
		sortByComboBox.addItem(item);
	}

	public String getSelectedItemSearchByComboBox() {
		return (String) searchByComboBox.getSelectedItem();
	}

	public String getSelectedItemSortByComboBox() {
		return (String) sortByComboBox.getSelectedItem();
	}

	public String getSearchTextField() {
		return searchTextField.getText();
	}

	public void setSearchTextField(final String searchString) {
		searchTextField.setText(searchString);
	}
	
	public void removeAllSearchByComboBoxItems() {
		searchByComboBox.removeAllItems();
	}
}
