package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Adresa;

public class AdreseMySQLRepository extends MySQLOperations {

	public Adresa get(final int id) {
		init();
		String sqlSt = "SELECT * FROM adrese WHERE id = " + id + ";";
		List<Adresa> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public Adresa get(final String strada, final String numar, final String bloc, final String scara,
			final String apartament, final String oras, final String judet, final String codPostal) {
		init();
		String sqlSt = "SELECT * FROM adrese WHERE strada = '" + strada + "' AND numar = '" + numar + "' AND bloc = '"
				+ bloc + "' AND scara = '" + scara + "' AND apartament = '" + apartament + "' AND oras = '" + oras
				+ "' AND judet = '" + judet + "' AND cod_postal = '" + codPostal + "';";
		List<Adresa> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public List<Adresa> getByOficiuPostal(final int idOficiuPostal) {
		init();
		String sqlSt = "SELECT * FROM adrese WHERE id_oficiu_postal = " + idOficiuPostal + " ORDER BY id ASC;";
		List<Adresa> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	@Override
	public List<Adresa> getAll() {
		init();
		String sqlSt = "SELECT * FROM adrese ORDER BY id, id_oficiu_postal ASC;";
		List<Adresa> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final Adresa adresa) {
		init();
		String sqlSt = "INSERT INTO adrese(strada, numar, bloc, scara, apartament, oras, judet, cod_postal, id_oficiu_postal)"
				+ "VALUES('" + adresa.getStrada() + "', '" + adresa.getNumar() + "', '" + adresa.getBloc() + "', '"
				+ adresa.getScara() + "', '" + adresa.getApartament() + "', '" + adresa.getOras() + "', '"
				+ adresa.getJudet() + "', '" + adresa.getCodPostal() + "', " + adresa.getIdOficiuPostal() + ");";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final Adresa adresa) {
		init();
		String sqlSt = "UPDATE adrese SET strada = '" + adresa.getStrada() + "', numar = '" + adresa.getNumar()
				+ "', bloc = '" + adresa.getBloc() + "', scara = '" + adresa.getScara() + "', apartament = '"
				+ adresa.getApartament() + "', oras = '" + adresa.getOras() + "', judet = '" + adresa.getJudet()
				+ "', cod_postal = '" + adresa.getCodPostal() + "', id_oficiu_postal = " + adresa.getIdOficiuPostal()
				+ " WHERE id = " + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final int id) {
		init();
		String sqlSt = "DELETE FROM adrese WHERE id =" + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM adrese;";
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
		String sqlSt = "SELECT id FROM adrese ORDER BY id DESC LIMIT 1;";
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
	protected List<Adresa> selectData(String queryString) {

		List<Adresa> outputList = new ArrayList<Adresa>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Adresa adresaNoua = new Adresa();

				adresaNoua.setId(rst.getInt(1));
				adresaNoua.setStrada(rst.getString(2));
				adresaNoua.setNumar(rst.getString(3));
				adresaNoua.setBloc(rst.getString(4));
				adresaNoua.setScara(rst.getString(5));
				adresaNoua.setApartament(rst.getString(6));
				adresaNoua.setOras(rst.getString(7));
				adresaNoua.setJudet(rst.getString(8));
				adresaNoua.setCodPostal(rst.getString(9));
				adresaNoua.setIdOficiuPostal(rst.getInt(10));

				outputList.add(adresaNoua);
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
		printQueryResult("SELECT * FROM adrese");
		closeConnection();
	}

	public List<Adresa> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM adrese" + where + " " + orderBy + ";";
		List<Adresa> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
