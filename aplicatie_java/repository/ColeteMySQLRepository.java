package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Colet;

public class ColeteMySQLRepository extends MySQLOperations {

	public Colet get(final int id) {
		init();
		String sqlSt = "SELECT * FROM colete WHERE id_articol_postal = " + id + ";";
		List<Colet> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	@Override
	public List<Colet> getAll() {
		init();
		String sqlSt = "SELECT * FROM sgbd_oficii_postale.colete ORDER BY id_articol_postal ASC;";
		List<Colet> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final Colet colet) {
		init();
		String sqlSt = "INSERT INTO sgbd_oficii_postale.colete(id_articol_postal, valoare_ramburs, valoare_declarata, greutate, "
				+ "tracked, fragil, voluminos, livrare_sambata)" + "VALUES(" + id + ", " + colet.getValoareRamburs()
				+ ", " + colet.getValoareDeclarata() + ", " + colet.getGreutate() + ", " + colet.isTracked() + ", "
				+ colet.isFragil() + ", " + colet.isVoluminos() + ", " + colet.isLivrareSambata() + ");";
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
		String sqlSt = "SELECT COUNT(id_articol_postal) FROM colete;";
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
		return -1;
	}

	@Override
	protected List<Colet> selectData(String queryString) {

		List<Colet> outputList = new ArrayList<Colet>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				Colet coletNou = new Colet();

				coletNou.setId(rst.getInt(1));
				coletNou.setValoareRamburs(rst.getFloat(2));
				coletNou.setValoareDeclarata(rst.getFloat(3));
				coletNou.setGreutate(rst.getFloat(4));
				coletNou.setTracked(rst.getBoolean(5));
				coletNou.setFragil(rst.getBoolean(6));
				coletNou.setVoluminos(rst.getBoolean(7));
				coletNou.setLivrareSambata(rst.getBoolean(8));

				outputList.add(coletNou);
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
		printQueryResult("SELECT * FROM colete");
		closeConnection();
	}

}
