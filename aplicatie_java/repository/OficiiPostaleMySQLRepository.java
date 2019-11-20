package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.OficiuPostal;

public class OficiiPostaleMySQLRepository extends MySQLOperations {

	public OficiuPostal get(final int id) {
		init();
		String sqlSt = "SELECT * FROM oficii_postale WHERE id = " + id + ";";
		List<OficiuPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<OficiuPostal> getAll() {
		init();
		String sqlSt = "SELECT * FROM oficii_postale ORDER BY id ASC;";
		List<OficiuPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final OficiuPostal oficiuPostal) {
		init();
		String sqlSt = "INSERT INTO oficii_postale(denumire, telefon)" + "VALUES('" + oficiuPostal.getDenumire()
				+ "', '" + oficiuPostal.getTelefon() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final OficiuPostal oficiuPostal) {
		init();
		String sqlSt = "UPDATE oficii_postale SET denumire = '" + oficiuPostal.getDenumire() + "', telefon = '"
				+ oficiuPostal.getTelefon() + "' WHERE id = " + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final int id) {
		init();
		String sqlSt = "DELETE FROM oficii_postale WHERE id =" + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM oficii_postale;";
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

	@Override
	public int lastInsertedIndex() {
		init();
		String sqlSt = "SELECT id FROM oficii_postale ORDER BY id DESC LIMIT 1;";
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

	@Override
	protected List<OficiuPostal> selectData(String querySt) {

		List<OficiuPostal> outputList = new ArrayList<OficiuPostal>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(querySt);

			while (rst.next()) {
				OficiuPostal oficiuPostalNou = new OficiuPostal();

				oficiuPostalNou.setId(rst.getInt(1));
				oficiuPostalNou.setDenumire(rst.getString(2));
//				oficiuPostalNou.setIdAdresa(rst.getInt(3));
				oficiuPostalNou.setTelefon(rst.getString(3));
//				oficiuPostalNou.setUsernameAdministrator(rst.getString(4));

				outputList.add(oficiuPostalNou);
			}

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		return outputList;
	}

	@Override
	protected void printAllData() {
		init();
		printQueryResult("SELECT * FROM oficii_postale");
		closeConnection();
	}

	public List<OficiuPostal> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM oficii_postale" + where + " " + orderBy + ";";
		List<OficiuPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
