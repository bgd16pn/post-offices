package model;

public class Inregistrare {

	private int id;
	private int idOficiuPostal;
	private int idEntitate;
	private String dataInregistrare;
	private String tipInregistrare;

	public int getId() {
		return id;
	}

	public int getIdOficiuPostal() {
		return idOficiuPostal;
	}

	public int getIdEntitate() {
		return idEntitate;
	}

	public String getDataInregistrare() {
		return dataInregistrare;
	}

	public String getTipInregistrare() {
		return tipInregistrare;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdOficiuPostal(int idOficiuPostal) {
		this.idOficiuPostal = idOficiuPostal;
	}

	public void setIdEntitate(int idEntitate) {
		this.idEntitate = idEntitate;
	}

	public void setDataInregistrare(String dataInregistrare) {
		this.dataInregistrare = dataInregistrare;
	}

	public void setTipInregistrare(String tipInregistrare) {
		this.tipInregistrare = tipInregistrare;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Inregistrare registru inout: Id = ");
		return stringBuilder.append(id).append(", Id oficiu postal = ").append(idOficiuPostal)
				.append(", Id entitate = ").append(idEntitate).append(", Tip inregistrare = ")
				.append(tipInregistrare).append(", Data inregistrare = ").append(dataInregistrare).toString();
	}
}
