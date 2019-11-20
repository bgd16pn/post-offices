package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.MySqlCredentials;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ControlPanelView extends JFrame {

	private static final long serialVersionUID = 468084122641534664L;

	private JLabel lblMessage;
	private JLabel lblWelcome;
	private JLabel lblUsername;
	private JLabel lblRole;
	private JLabel lblTrackingData;
	private JLabel lblTrackingInfo;

	private JTable table;
	private JScrollPane tableScrollPane;
	private DefaultTableModel tableModel;

	private JSeparator centerSectionRightSectionSeparator;

	private SearchPanel searchPanel;
	private TopSidePanel topPanel;
	private LeftSidePanel leftPanel;
	private RightSidePanel rightPanel;
	private BottomSidePanel bottomPanel;

	private final static int windowWidth = 1150;
	private final static int windowHeight = 695;

	private int editableRow = -1;

	public ControlPanelView(int widthOrigin, int heightOrigin) {

		searchPanel = new SearchPanel();
		topPanel = new TopSidePanel();
		leftPanel = new LeftSidePanel();
		rightPanel = new RightSidePanel();
		bottomPanel = new BottomSidePanel();

		this.setTitle("Control panel");
		this.setResizable(false);

		this.setBounds(widthOrigin / 2 - windowWidth / 2, heightOrigin / 2 - windowHeight / 2, windowWidth,
				windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblWelcome = new JLabel("Main menu");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblWelcome.setBounds(21, 44, 141, 39);
		getContentPane().add(lblWelcome);

		lblMessage = new JLabel("Error message");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(21, 622, 274, 25);
		getContentPane().add(lblMessage);
		lblMessage.setVisible(false);

		JSeparator leftSideCenterSeparator = new JSeparator();
		leftSideCenterSeparator.setOrientation(SwingConstants.VERTICAL);
		leftSideCenterSeparator.setBounds(174, 13, 11, 612);
		getContentPane().add(leftSideCenterSeparator);

		JSeparator TopSectionCenterSeparator = new JSeparator();
		TopSectionCenterSeparator.setBounds(12, 84, 1094, 11);
		getContentPane().add(TopSectionCenterSeparator);

		JSeparator usernameMainMenuSeparator = new JSeparator();
		usernameMainMenuSeparator.setBounds(12, 43, 163, 11);
		getContentPane().add(usernameMainMenuSeparator);

		centerSectionRightSectionSeparator = new JSeparator();
		centerSectionRightSectionSeparator.setOrientation(SwingConstants.VERTICAL);
		centerSectionRightSectionSeparator.setBounds(900, 84, 11, 541);
		getContentPane().add(centerSectionRightSectionSeparator);

		lblUsername = new JLabel("");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(21, 8, 141, 16);
		getContentPane().add(lblUsername);

		lblRole = new JLabel("");
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRole.setBounds(38, 26, 106, 16);
		getContentPane().add(lblRole);

		lblTrackingInfo = new JLabel("Tracking Info");
		lblTrackingInfo.setBounds(186, 100, 130, 39);
		getContentPane().add(lblTrackingInfo);
		lblTrackingInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrackingInfo.setVisible(false);

		lblTrackingData = new JLabel("Data");
		lblTrackingData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrackingData.setBounds(321, 100, 552, 39);
		getContentPane().add(lblTrackingData);
		lblTrackingData.setVisible(false);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 1)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 7175762304322871756L;

			@Override
			public Class<?> getColumnClass(int column) {
				if(getValueAt(0, column) != null) {
					return getValueAt(0, column).getClass();
				}
				return String.class;
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (row == editableRow) {
					return true;
				}
				return false;
			}
		};
		table.setModel(tableModel);
		table.setRowHeight(20);	
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.getTableHeader().setDefaultRenderer(centerRenderer);

		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(184, 152, 930, 402);
		getContentPane().add(tableScrollPane);

		topPanel.setBounds(176, 13, 930, 70);
		searchPanel.setBounds(174, 85, 931, 65);
		leftPanel.setBounds(12, 84, 162, 480);
		bottomPanel.setBounds(188, 566, 860, 80);
		rightPanel.setBounds(910, 153, 193, 175);

		getContentPane().add(topPanel);
		getContentPane().add(leftPanel);
		getContentPane().add(bottomPanel);
		getContentPane().add(searchPanel);
		getContentPane().add(rightPanel);

		rightPanel.setVisible(false);
		centerSectionRightSectionSeparator.setVisible(false);
	}

	public void setExtendedCenterPanelMode() {
		tableScrollPane.setBounds(184, 152, 930, 402);
		centerSectionRightSectionSeparator.setVisible(false);
		searchPanel.setVisible(true);
		bottomPanel.setSaveButtonVisible(true);
		rightPanel.setVisible(false);
		setTrackingDataVisible(false);
		setTrackingInfoVisible(false);
	}

	public void setShrinkedCenterPanelMode() {
		tableScrollPane.setBounds(184, 152, 709, 402);
		centerSectionRightSectionSeparator.setVisible(true);
		searchPanel.setVisible(false);
		bottomPanel.setSaveButtonVisible(false);
		rightPanel.setVisible(true);
		setTrackingDataVisible(true);
		setTrackingInfoVisible(true);
	}

	public void setMode(final String mode) {
		switch (mode) {
		case "Administrator":
			leftPanel.setAdministratorMode();
			topPanel.setStorageButtonVisible(true);
			topPanel.setLogsButtonVisible(true);
			MySqlCredentials.uid = "administrator";
			MySqlCredentials.pwd = "admin";
			break;
		case "Employee":
			leftPanel.setEmployeeMode();
			topPanel.setStorageButtonVisible(true);
			topPanel.setLogsButtonVisible(true);
			MySqlCredentials.uid = "angajat";
			MySqlCredentials.pwd = "angajat";
			break;
		case "User":
			leftPanel.setUserMode();
			topPanel.setStorageButtonVisible(false);
			topPanel.setLogsButtonVisible(false);
			bottomPanel.setVisible(false);
			MySqlCredentials.uid = "user";
			MySqlCredentials.pwd = "user";
			break;
		}
	}

	public void setEditableRow(final int rowIndex) {
		this.editableRow = rowIndex;
	}

	public int getEditableRow() {
		return editableRow;
	}

	public void setTableSelection(final int rowIndex) {
		table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
	}

	public void setTableEnabled(final boolean flag) {
		table.setEnabled(flag);
	}

	public void setTableModel(Object[][] data, String[] columnNames) {
		tableModel.setDataVector(data, columnNames);
	}

	public void removeTableRow(int rowIndex) {
		tableModel.removeRow(rowIndex);
	}

	public void addTableRow(final int rowIndex, final Object[] rowData) {
		tableModel.insertRow(rowIndex, rowData);
	}

	public void addTableRow(final Object[] rowData) {
		tableModel.addRow(rowData);
	}
	
	public void setValueAt(final int row, final int column, Object value) {
		tableModel.setValueAt(value, row, column);
	}

	public void updateTableRow(final int rowIndex, final Object[] data) {
		for (int i = 0; i < data.length; i++) {
			tableModel.setValueAt(data[i], rowIndex, i);
		}
	}

	public Object getTableValueAt(int rowIndex, int colIndex) {
		return tableModel.getValueAt(rowIndex, colIndex);
	}
	
	public Object[] getTableRowData(int rowIndex) {
		Object[] output = new Object[tableModel.getColumnCount()];
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			output[i] = tableModel.getValueAt(rowIndex, i);
		}
		return output;
	}

	public TableColumnModel getTableColumnModel() {
		return table.getColumnModel();
	}

	public int getTableRowIndex() {
		return table.getSelectedRow();
	}

	public int getTableRowCount() {
		return table.getRowCount();
	}

	public int getTableColumnCount() {
		return tableModel.getColumnCount();
	}

	public void setUsernameLabel(final String username) {
		lblUsername.setText(username);
	}

	public void setRoleLabel(final String role) {
		lblRole.setText(role);
	}

	public String getWelcomeLabel() {
		return lblMessage.getText();
	}

	public void setTrackingDataVisible(final boolean flag) {
		lblTrackingData.setVisible(flag);
	}

	public void setTrackingInfoVisible(final boolean flag) {
		lblTrackingInfo.setVisible(flag);
	}

	public void setTrackingDataLabel(final String info) {
		lblTrackingData.setText(info);
	}

	public SearchPanel getSearchPanel() {
		return searchPanel;
	}

	public TopSidePanel getTopPanel() {
		return topPanel;
	}

	public LeftSidePanel getLeftPanel() {
		return leftPanel;
	}

	public RightSidePanel getRightPanel() {
		return rightPanel;
	}

	public BottomSidePanel getBottomPanel() {
		return bottomPanel;
	}

	public void setSearchPanel(SearchPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public void setTopPanel(TopSidePanel topPanel) {
		this.topPanel = topPanel;
	}

	public void setLeftPanel(LeftSidePanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public void setRightPanel(RightSidePanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setBottomPanel(BottomSidePanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public void displayMessage(final String messageText) {
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
					lblMessage.setForeground(Color.RED);
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void setDisplayMessageColor(Color color) {
		lblMessage.setForeground(color);
	}
}
