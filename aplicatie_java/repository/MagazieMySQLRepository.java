package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ArticolPostal;
import model.MySqlCredentials;

public class MagazieMySQLRepository {

	protected String url = MySqlCredentials.url;
	protected Connection con;

	public ArticolPostal get(final int idOficiuPostal, final int idArticolPostal) {
		init();
		String sqlSt = "SELECT * FROM articole_postale JOIN magazie ON articole_postale.id = " + idArticolPostal
				+ " AND magazie.id_oficiu_postal = " + idOficiuPostal + " AND magazie.id_articol_postal = "
				+ idArticolPostal + ";";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public List<ArticolPostal> getAll(final int idOficiuPostal) {
		init();
		String sqlSt = "SELECT * FROM articole_postale JOIN magazie ON magazie.id_oficiu_postal = " + idOficiuPostal
				+ " AND magazie.id_articol_postal = articole_postale.id " + "ORDER BY articole_postale.id ASC;";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int idOficiuPostal, final ArticolPostal articolPostal) {
		init();
		String sqlSt = "INSERT INTO magazie(id_oficiu_postal, id_articol_postal)" + "VALUES(" + idOficiuPostal + ", "
				+ articolPostal.getId() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int newIdOficiuPostal, final ArticolPostal articolPostal) {
		init();
		String sqlSt = "UPDATE magazie SET id_oficiu_postal = '" + newIdOficiuPostal + " WHERE id_articol_postal = "
				+ articolPostal.getId() + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final int idArticolPostal) {
		init();
		String sqlSt = "DELETE FROM magazie WHERE id_articol_postal =" + idArticolPostal + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public int size(final int idOficiuPostal) {
		init();
		String sqlSt = "SELECT COUNT(id_articol_postal) FROM magazie WHERE id_oficiu_postal = " + idOficiuPostal + ";";
		int size = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sqlSt);

			rst.next();
			size = rst.getInt(1);

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		closeConnection();

		return size;
	}

	public int lastInsertedIndex(final int idOficiuPostal) {
		init();
		String sqlSt = "SELECT id_articol_postal FROM magazie WHERE id_oficiu_postal = " + idOficiuPostal
				+ "ORDER BY id_articol_postal DESC LIMIT 1;";
		int index = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sqlSt);

			rst.next();
			index = rst.getInt(1);

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		closeConnection();

		return index;
	}

	protected List<ArticolPostal> selectData(String queryString) {

		List<ArticolPostal> outputList = new ArrayList<ArticolPostal>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				ArticolPostal articolPostalNou = new ArticolPostal();

				articolPostalNou.setId(rst.getInt(1));
				articolPostalNou.setTip(rst.getString(2));
				articolPostalNou.setDataColectare(rst.getString(3));
				articolPostalNou.setDataTransmitere(rst.getString(4));
				articolPostalNou.setDataRidicare(rst.getString(5));
				articolPostalNou.setDataAvizare(rst.getString(6));
				articolPostalNou.setNumeExpeditor(rst.getString(7));
				articolPostalNou.setPrenumeExpeditor(rst.getString(8));
				articolPostalNou.setIdAdresaExpeditor(rst.getInt(9));
				articolPostalNou.setNumeDestinatar(rst.getString(10));
				articolPostalNou.setPrenumeDestinatar(rst.getString(11));
				articolPostalNou.setIdAdresaDestinatar(rst.getInt(12));
				articolPostalNou.setTarifExpediere(rst.getFloat(13));
				articolPostalNou.setTarifRidicare(rst.getFloat(14));
				articolPostalNou.setCodTracking(rst.getString(15));

				outputList.add(articolPostalNou);
			}

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		return outputList;
	}

	public void printAllData() {
		init();
		printQueryResult("SELECT * FROM magazie ORDER BY id_oficiu_postal, id_articol_postal ASC");
		closeConnection();
	}

	private void init() {
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

	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.err.println("Exception during connection close: " + ex);
		}
	}

	private void alterData(final String queryString) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(queryString);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}
	}

	private void printQueryResult(final String queryStr) {
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

	private String spaces(final int space) {
		String sp = "";
		for (int i = 0; i < space; i++)
			sp = sp + " ";
		return sp;
	}

	public List<ArticolPostal> getSorted(final int idOficiuPostal, String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM articole_postale JOIN magazie ON magazie.id_oficiu_postal = " + idOficiuPostal
				+ " AND magazie.id_articol_postal = articole_postale.id " + where + " " + orderBy + ";";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
