package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.MySqlCredentials;

public abstract class MySQLOperations {

	protected String url = MySqlCredentials.url;
	protected Connection con;

	public abstract List<?> getAll();

	public abstract int size();

	public abstract int lastInsertedIndex();

	protected void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e);
		}
		con = null;
		try {
			con = DriverManager.getConnection(url, MySqlCredentials.uid, MySqlCredentials.pwd);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
			System.exit(1);
		}
	}

	protected void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.err.println("Exception during connection close: " + ex);
		}
	}

	protected void alterData(final String queryString) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(queryString);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}
	}

	protected abstract List<?> selectData(final String queryStr);

	protected void printQueryResult(final String queryStr) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryStr);
			ResultSetMetaData rsmd = rst.getMetaData();

			// Calculate column sizes (cut off large columns to 35)
			int colCount = rsmd.getColumnCount();
			int rowCount = 0;
			int colWidth[] = new int[colCount];
			for (int i = 1; i <= colCount; i++) {
				colWidth[i - 1] = rsmd.getColumnDisplaySize(i);
				if (colWidth[i - 1] > 35) {
					colWidth[i - 1] = 35;
				}
			}

			System.out.println();
			// Print header
			for (int i = 1; i <= colCount; i++) {
				String colName = rsmd.getColumnName(i);
				System.out.print(colName + spaces(colWidth[i - 1] - colName.length()) + ' ');
			}
			System.out.println("\n-----------------------------------------------------------------------");

			while (rst.next()) {
				for (int i = 1; i <= colCount; i++) {
					Object obj = rst.getObject(i);
					if (obj == null)
						System.out.print(spaces(colWidth[i - 1]));
					else {
						String data = obj.toString();
						System.out.print(data + spaces(colWidth[i - 1] - data.length()) + ' ');
					}
				}
				System.out.println();
				rowCount++;
			}
			if (rowCount == 0) {
				System.out.println("No results.");
			}
			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}
	}

	protected abstract void printAllData();

	private String spaces(final int space) {
		String sp = "";
		for (int i = 0; i < space; i++)
			sp = sp + " ";
		return sp;
	}

}
