package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Tarif;

public class TarifeMySQLRepository extends MySQLOperations {

	public Tarif get(final String descriere, final String tipArticolPostal) {
		init();
		String sqlSt = "SELECT * FROM tarife WHERE descriere = '" + descriere + "' AND tip_articol_postal = '"
				+ tipArticolPostal + "';";
		List<Tarif> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public List<Tarif> getByTipArticolPostal(final String tipArticolPostal) {
		init();
		String sqlSt = "SELECT * FROM tarife WHERE tip_articol_postal = '" + tipArticolPostal
				+ "' ORDER BY valoare ASC;";
		List<Tarif> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	@Override
	public List<Tarif> getAll() {
		init();
		String sqlSt = "SELECT * FROM tarife ORDER BY tip_articol_postal, length(descriere) ASC;";
		List<Tarif> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final String descriere, final Tarif tarif) {
		init();
		String sqlSt = "INSERT INTO tarife(tip_articol_postal, descriere, valoare)" + "VALUES('"
				+ tarif.getTipArticolPostal() + "', '" + descriere + "', '" + tarif.getValoare() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final String descriere, final Tarif tarif) {
		init();
		String sqlSt = "UPDATE tarife SET valoare = " + tarif.getValoare() + " WHERE descriere = '" + descriere
				+ "' AND tip_articol_postal = '" + tarif.getTipArticolPostal() + "';";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final String descriere, final String tipArticolPostal) {
		init();
		String sqlSt = "DELETE FROM tarife WHERE descriere = '" + descriere + "' AND tip_articol_postal = '"
				+ tipArticolPostal + "';";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM tarife;";
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
		String sqlSt = "SELECT id FROM tarife ORDER BY id DESC LIMIT 1;";
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
	protected List<Tarif> selectData(String queryString) {

		List<Tarif> outputList = new ArrayList<Tarif>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Tarif tarifNou = new Tarif();

				tarifNou.setId(rst.getInt(1));
				tarifNou.setTipArticolPostal(rst.getString(2));
				tarifNou.setDescriere(rst.getString(3));
				tarifNou.setValoare(rst.getFloat(4));

				outputList.add(tarifNou);
			}

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		return outputList;
	}

	@Override
	public void printAllData() {
		init();
		printQueryResult("SELECT * FROM tarife");
		closeConnection();
	}

	public List<Tarif> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM tarife" + where + " " + orderBy + ";";
		List<Tarif> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
