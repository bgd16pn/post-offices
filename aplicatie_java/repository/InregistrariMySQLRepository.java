package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Inregistrare;

public class InregistrariMySQLRepository extends MySQLOperations {

	public Inregistrare get(final int id) {
		init();
		String sqlSt = "SELECT * FROM inregistrari WHERE id = " + id + ";";
		List<Inregistrare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public List<Inregistrare> getByOficiuPostal(final int idOficiuPostal) {
		init();
		String sqlSt = "SELECT * FROM inregistrari WHERE id_oficiu_postal = '" + idOficiuPostal + "' ORDER BY id ASC;";
		List<Inregistrare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	@Override
	public List<Inregistrare> getAll() {
		init();
		String sqlSt = "SELECT * FROM inregistrari ORDER BY id ASC;";
		List<Inregistrare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final Inregistrare inregistrare) {
		init();
		String sqlSt = "INSERT INTO inregistrari(id, id_oficiu_postal, id_entitate, tip_inregistrare, data_inregistrare)"
				+ "VALUES(" + id + ", '" + inregistrare.getIdOficiuPostal() + "', '" + inregistrare.getIdEntitate()
				+ "', '" + inregistrare.getTipInregistrare() + "', '" + inregistrare.getDataInregistrare() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final Inregistrare inregistrare) {
	}

	public void remove(final int id) {
		init();
		String sqlSt = "DELETE FROM inregistrari WHERE id =" + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM inregistrari;";
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
		String sqlSt = "SELECT id FROM inregistrari ORDER BY id DESC LIMIT 1;";
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
	protected List<Inregistrare> selectData(String queryString) {

		List<Inregistrare> outputList = new ArrayList<Inregistrare>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Inregistrare inregistrareNoua = new Inregistrare();

				inregistrareNoua.setId(rst.getInt(1));
				inregistrareNoua.setIdEntitate(rst.getInt(2));
				inregistrareNoua.setIdOficiuPostal(rst.getInt(3));
				inregistrareNoua.setTipInregistrare(rst.getString(4));
				inregistrareNoua.setDataInregistrare(rst.getString(5));

				outputList.add(inregistrareNoua);
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
		printQueryResult("SELECT * FROM inregistrari");
		closeConnection();
	}

	public List<Inregistrare> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM inregistrari" + where + " " + orderBy + ";";
		List<Inregistrare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
