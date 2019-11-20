package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Utilizator;

public class UtilizatoriMySQLRepository extends MySQLOperations {

	public Utilizator get(final String username) {
		init();
		String sqlSt = "SELECT * FROM utilizatori WHERE username = '" + username + "';";
		List<Utilizator> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public List<Utilizator> getAll() {
		init();
		String sqlSt = "SELECT * FROM utilizatori ORDER BY id ASC;";
		List<Utilizator> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final String username, final Utilizator utilizator) {
		init();
		String sqlSt = "INSERT INTO utilizatori(nume, prenume, username, parola, email, telefon, id_adresa)"
				+ "VALUES('" + utilizator.getNume() + "', '" + utilizator.getPrenume() + "', '"
				+ utilizator.getUsername() + "', '" + utilizator.getParola() + "', '" + utilizator.getEmail() + "', '"
				+ utilizator.getTelefon() + "', '" + utilizator.getIdAdresa() + "');";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final String username, final Utilizator utilizator) {
		init();
		String sqlSt = "UPDATE utilizatori SET email = '" + utilizator.getEmail() + "', telefon = '"
				+ utilizator.getTelefon() + "', parola = '" + utilizator.getParola() + "', id_adresa = "
				+ utilizator.getIdAdresa() + " WHERE username = '" + username + "';";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final String username) {
		init();
		String sqlSt = "DELETE FROM utilizatori WHERE username = '" + username + "';";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM utilizatori;";
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
	protected List<Utilizator> selectData(String queryString) {

		List<Utilizator> outputList = new ArrayList<Utilizator>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Utilizator utilizatorNou = new Utilizator();

				utilizatorNou.setId(rst.getInt(1));
				utilizatorNou.setNume(rst.getString(6));
				utilizatorNou.setPrenume(rst.getString(7));
				utilizatorNou.setUsername(rst.getString(2));
				utilizatorNou.setParola(rst.getString(3));
				utilizatorNou.setEmail(rst.getString(4));
				utilizatorNou.setTelefon(rst.getString(5));
				utilizatorNou.setIdAdresa(rst.getInt(8));

				outputList.add(utilizatorNou);
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
		printQueryResult("SELECT * FROM utilizatori");
		closeConnection();
	}

	public List<Utilizator> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM utilizatori" + where + " " + orderBy + ";";
		List<Utilizator> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}
}
