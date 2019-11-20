package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Scrisoare;

public class ScrisoriMySQLRepository extends MySQLOperations {

	public Scrisoare get(final int id) {
		init();
		String sqlSt = "SELECT * FROM scrisori WHERE id_articol_postal = " + id + ";";
		List<Scrisoare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<Scrisoare> getAll() {
		init();
		String sqlSt = "SELECT * FROM scrisori ORDER BY id_articol_postal ASC;";
		List<Scrisoare> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final Scrisoare scrisoare) {
		init();
		String sqlSt = "INSERT INTO scrisori(id_articol_postal, valoare_ramburs, valoare_declarata, greutate, confirmare_primire, tracked)"
				+ "VALUES(" + id + ", " + scrisoare.getValoareRamburs() + ", " + scrisoare.getValoareDeclarata() + ", "
				+ scrisoare.getGreutate() + ", " + scrisoare.isConfirmarePrimire() + ", " + scrisoare.isTracked()
				+ ");";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final Scrisoare scrisoare) {
	}

	public void remove(final int id) {
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id_articol_postal) FROM scrisori;";
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
	protected List<Scrisoare> selectData(String queryString) {

		List<Scrisoare> outputList = new ArrayList<Scrisoare>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Scrisoare scrisoareNoua = new Scrisoare();

				scrisoareNoua.setId(rst.getInt(1));
				scrisoareNoua.setValoareRamburs(rst.getFloat(2));
				scrisoareNoua.setValoareDeclarata(rst.getFloat(3));
				scrisoareNoua.setGreutate(rst.getFloat(4));
				scrisoareNoua.setConfirmarePrimire(rst.getBoolean(5));
				scrisoareNoua.setTracked(rst.getBoolean(6));

				outputList.add(scrisoareNoua);
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
		printQueryResult("SELECT * FROM scrisori");
		closeConnection();
	}

}
