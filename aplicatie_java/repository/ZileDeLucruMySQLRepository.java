package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ZiDeLucru;

public class ZileDeLucruMySQLRepository extends MySQLOperations {

	public ZiDeLucru get(final int idOficiuPostal, final String ziuaSaptamanii) {
		init();
		String sqlSt = "SELECT * FROM zile_de_lucru WHERE id_oficiu_postal = " + idOficiuPostal + " AND "
				+ "ziua_saptamanii = '" + ziuaSaptamanii + "';";
		List<ZiDeLucru> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public ZiDeLucru get(final int id) {
		init();
		String sqlSt = "SELECT * FROM zile_de_lucru WHERE id = " + id + ";";
		List<ZiDeLucru> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<ZiDeLucru> getAll() {
		init();
		String sqlSt = "SELECT * FROM zile_de_lucru ORDER BY id_oficiu_postal ASC;";
		List<ZiDeLucru> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final ZiDeLucru ziDeLucru) {
		init();
		String sqlSt = "INSERT INTO zile_de_lucru(id_oficiu_postal, ziua_saptamanii, ora_deschidere, ora_inchidere)"
				+ "VALUES(" + ziDeLucru.getIdOficiuPostal() + ", '" + ziDeLucru.getZiuaSaptamanii() + "', "
				+ ziDeLucru.getOraDeschidere() + ", " + ziDeLucru.getOraInchidere() + ");";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int idOficiuPostal, final ZiDeLucru ziDeLucru) {
		init();
		String sqlSt = "UPDATE zile_de_lucru SET ora_deschidere = " + ziDeLucru.getOraDeschidere()
				+ ", ora_inchidere = " + ziDeLucru.getOraInchidere() + " WHERE id_oficiu_postal = " + idOficiuPostal
				+ " AND ziua_saptamanii = '" + ziDeLucru.getZiuaSaptamanii() + "';";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final int id) {
		init();
		String sqlSt = "DELETE FROM zile_de_lucru WHERE id = " + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM zile_de_lucru;";
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
		String sqlSt = "SELECT id FROM zile_de_lucru ORDER BY id DESC LIMIT 1;";
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
	protected List<ZiDeLucru> selectData(String queryString) {

		List<ZiDeLucru> outputList = new ArrayList<ZiDeLucru>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				ZiDeLucru ziDeLucruNoua = new ZiDeLucru();

				ziDeLucruNoua.setId(rst.getInt(1));
				ziDeLucruNoua.setIdOficiuPostal(rst.getInt(2));
				ziDeLucruNoua.setZiuaSaptamanii(rst.getString(3));
				ziDeLucruNoua.setOraDeschidere(rst.getInt(4));
				ziDeLucruNoua.setOraInchidere(rst.getInt(5));

				outputList.add(ziDeLucruNoua);
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
		printQueryResult("SELECT * FROM zile_de_lucru");
		closeConnection();
	}
	
	public List<ZiDeLucru> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM zile_de_lucru" + where + " " + orderBy + ";";
		List<ZiDeLucru> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
