package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Colet;
import model.Mandat;

public class MandateMySQLRepository extends MySQLOperations {

	public Mandat get(final int id) {
		init();
		String sqlSt = "SELECT * FROM mandate WHERE id_articol_postal = " + id + ";";
		List<Mandat> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<Mandat> getAll() {
		init();
		String sqlSt = "SELECT * FROM mandate ORDER BY id_articol_postal ASC;";
		List<Mandat> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final Mandat mandat) {
		init();
		String sqlSt = "INSERT INTO mandate(id_articol_postal, valoare, detalii)" + "VALUES(" + id + ", "
				+ mandat.getValoare() + ", '" + mandat.getDetalii() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final Colet colet) {
	}

	public void remove(final int id) {
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id_articol_postal) FROM mandate;";
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
		String sqlSt = "SELECT id FROM articole_postale ORDER BY articole_postale.id DESC LIMIT 1;";
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
	protected List<Mandat> selectData(String queryString) {

		List<Mandat> outputList = new ArrayList<Mandat>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Mandat mandatPostalNou = new Mandat();

				mandatPostalNou.setId(rst.getInt(1));
				mandatPostalNou.setValoare(rst.getFloat(2));
				mandatPostalNou.setDetalii(rst.getString(3));

				outputList.add(mandatPostalNou);
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
		printQueryResult("SELECT * FROM mandate");
		closeConnection();
	}

}
