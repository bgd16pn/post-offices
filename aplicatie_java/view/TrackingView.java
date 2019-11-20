package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TrackingView extends JFrame {

	private static final long serialVersionUID = -2248567079869234070L;

	private final static int windowWidth = 1220;
	private final static int windowHeight = 161;
	
	private JTable table;

	public TrackingView(int widthOrigin, int heightOrigin) {
		this.setTitle("Tracking");
		this.setResizable(false);

		this.setBounds(widthOrigin / 2 - windowWidth / 2, heightOrigin / 2 - windowHeight / 2, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 1)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(12, 42, 1190, 71);
		getContentPane().add(tableScrollPane);

		JLabel lblTracking = new JLabel("Urmarire articol");
		lblTracking.setHorizontalAlignment(SwingConstants.CENTER);
		lblTracking.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTracking.setBounds(12, 9, 150, 33);
		getContentPane().add(lblTracking);
	}

	public void setTableModel(String[][] data, String[] columnNames) {
		table.setModel(new DefaultTableModel(data, columnNames){

			private static final long serialVersionUID = 7889538170907000110L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
	}

}
