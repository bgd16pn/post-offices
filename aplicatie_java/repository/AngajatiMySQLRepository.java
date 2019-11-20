package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Angajat;

public class AngajatiMySQLRepository extends MySQLOperations {

	public Angajat get(final String username) {
		init();
		String sqlSt = "SELECT * FROM view_angajati WHERE username = '" + username + "';";
		List<Angajat> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<Angajat> getAll() {
		init();
		String sqlSt = "SELECT * FROM view_angajati;";
		List<Angajat> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final String username, final Angajat angajat) {
		init();
		String sqlSt = "INSERT INTO angajati(username_utilizator, id_oficiu_postal, administrator, salariu, data_angajare)"
				+ "VALUES('" + username + "', " + angajat.getIdOficiuPostal() + ", " + angajat.isAdministrator() + ", "
				+ angajat.getSalariu() + ", NOW());";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final String username, final Angajat angajat) {
		init();
		String sqlSt = "UPDATE angajati SET administrator = " + angajat.isAdministrator() + ", salariu = '"
				+ angajat.getSalariu() + "' WHERE username_utilizator = '" + username + "';";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final String username) {
		init();
		String sqlSt = "DELETE FROM angajati WHERE username_utilizator = '" + username + "';";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM angajati;";
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
		String sqlSt = "SELECT id FROM utilizatori ORDER BY id DESC LIMIT 1;";
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
	protected List<Angajat> selectData(String queryString) {

		List<Angajat> outputList = new ArrayList<Angajat>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Angajat angajat = new Angajat();

				angajat.setId(rst.getInt(1));
				angajat.setNume(rst.getString(2));
				angajat.setPrenume(rst.getString(3));
				angajat.setUsername(rst.getString(4));
				angajat.setIdAdresa(rst.getInt(5));
				angajat.setTelefon(rst.getString(6));
				angajat.setEmail(rst.getString(7));
				angajat.setParola(rst.getString(8));
				angajat.setIdOficiuPostal(rst.getInt(9));
				angajat.setAdministrator(rst.getBoolean(10));
				angajat.setSalariu(rst.getFloat(11));
				angajat.setDataAngajare(rst.getString(12));

				outputList.add(angajat);
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
		printQueryResult("SELECT * FROM angajati");
		closeConnection();
	}

	public List<Angajat> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM view_angajati" + where + " " + orderBy + ";";
		List<Angajat> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public Angajat getPersonalData(final String username) {
		init();
		String sqlSt = "SELECT * FROM view_date_personale_angajat WHERE username = '" + username + "';";
		
		Angajat angajat = new Angajat();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sqlSt);

			while (rst.next()) {
				angajat.setId(rst.getInt(1));
				angajat.setNume(rst.getString(2));
				angajat.setPrenume(rst.getString(3));
				angajat.setUsername(rst.getString(4));
				angajat.setParola(rst.getString(5));
				angajat.setEmail(rst.getString(6));
				angajat.setTelefon(rst.getString(7));
				angajat.setIdAdresa(rst.getInt(8));
				angajat.setIdOficiuPostal(rst.getInt(9));
				angajat.setSalariu(rst.getFloat(10));
				angajat.setDataAngajare(rst.getString(11));
				angajat.setAdministrator(rst.getBoolean(12));
			}
			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		closeConnection();
		
		return angajat;
	}

}
