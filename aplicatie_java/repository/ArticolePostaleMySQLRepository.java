package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ArticolPostal;

public class ArticolePostaleMySQLRepository extends MySQLOperations {

	public void send(int idArticolPostal) {
		init();
		String sqlSt = "UPDATE articole_postale SET data_transmitere = NOW() WHERE id = " + idArticolPostal + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void avizare(int idArticolPostal) {
		init();
		String sqlSt = "UPDATE articole_postale SET data_avizare = NOW() WHERE id = " + idArticolPostal + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void ridicare(int idArticolPostal) {
		init();
		String sqlSt = "UPDATE articole_postale SET data_ridicare = NOW() WHERE id = " + idArticolPostal + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public ArticolPostal get(final String trackingCode) {
		init();
		String sqlSt = "SELECT * FROM articole_postale WHERE cod_tracking = '" + trackingCode + "';";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public ArticolPostal get(final int id) {
		init();
		String sqlSt = "SELECT * FROM articole_postale WHERE id = " + id + ";";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList.iterator().next();
		}

		return null;
	}

	public String generateTrackingCode(final int id) {
		init();
		String sqlSt = "CALL GENERATE_TRACKING_CODE(" + id + ");";
		String trackingCode = "";

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sqlSt);

			rst.next();
			trackingCode = rst.getString(1);
			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		closeConnection();

		return trackingCode;
	}

	@Override
	public List<ArticolPostal> getAll() {
		init();
		String sqlSt = "SELECT * FROM articole_postale ORDER BY id ASC;";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

	public void put(final int id, final ArticolPostal articolPostal) {
		init();
		String sqlSt = "INSERT INTO articole_postale(id, tip, nume_expeditor, prenume_expeditor, "
				+ "id_adresa_expeditor, nume_destinatar, prenume_destinatar, id_adresa_destinatar, tarif_expediere, tarif_ridicare)"
				+ "VALUES(" + id + ", '" + articolPostal.getTip() + "', '" + articolPostal.getNumeExpeditor() + "', '"
				+ articolPostal.getPrenumeExpeditor() + "', " + articolPostal.getIdAdresaExpeditor() + ", '"
				+ articolPostal.getNumeDestinatar() + "', '" + articolPostal.getPrenumeDestinatar() + "', "
				+ articolPostal.getIdAdresaDestinatar() + ", " + articolPostal.getTarifExpediere() + ", "
				+ articolPostal.getTarifRidicare() + ");";
		alterData(sqlSt);
		closeConnection();
	}

	public void update(final int id, final ArticolPostal articolPostal) {
		init();
		String sqlSt = "UPDATE articole_postale SET data_transmitere = '" + articolPostal.getDataTransmitere()
				+ "', data_ridicare = '" + articolPostal.getDataRidicare() + "', data_avizare = '"
				+ articolPostal.getDataAvizare() + "' WHERE id =" + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	public void remove(final int id) {
		init();
		String sqlSt = "DELETE FROM articole_postale WHERE id =" + id + ";";
		alterData(sqlSt);
		closeConnection();
	}

	@Override
	public int size() {
		init();
		String sqlSt = "SELECT COUNT(id) FROM articole_postale;";
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
		String sqlSt = "SELECT id FROM articole_postale ORDER BY id DESC LIMIT 1;";
		int index = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(sqlSt);

			if (rst.next()) {
				index = rst.getInt(1);
			}

			rst.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex);
		}

		closeConnection();

		return index;
	}

	@Override
	protected List<ArticolPostal> selectData(String queryString) {

		List<ArticolPostal> outputList = new ArrayList<ArticolPostal>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery(queryString);

			while (rst.next()) {
				ArticolPostal articolPostalNou = new ArticolPostal();

				articolPostalNou.setId(rst.getInt(1));
				articolPostalNou.setTip(rst.getString(2));
				articolPostalNou.setDataColectare(rst.getString(3));
				articolPostalNou.setDataTransmitere(rst.getString(4));
				articolPostalNou.setDataRidicare(rst.getString(5));
				articolPostalNou.setDataAvizare(rst.getString(6));
				articolPostalNou.setNumeExpeditor(rst.getString(7));
				articolPostalNou.setPrenumeExpeditor(rst.getString(8));
				articolPostalNou.setIdAdresaExpeditor(rst.getInt(9));
				articolPostalNou.setNumeDestinatar(rst.getString(10));
				articolPostalNou.setPrenumeDestinatar(rst.getString(11));
				articolPostalNou.setIdAdresaDestinatar(rst.getInt(12));
				articolPostalNou.setTarifExpediere(rst.getFloat(13));
				articolPostalNou.setTarifRidicare(rst.getFloat(14));
				articolPostalNou.setCodTracking(rst.getString(15));

				outputList.add(articolPostalNou);
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
		printQueryResult("SELECT * FROM articole_postale");
		closeConnection();
	}

	public List<ArticolPostal> getSorted(String where, String orderBy) {
		init();
		String sqlSt = "SELECT * FROM articole_postale" + where + " " + orderBy + ";";
		List<ArticolPostal> outputList = selectData(sqlSt);
		closeConnection();

		if (!outputList.isEmpty()) {
			return outputList;
		}

		return null;
	}

}
