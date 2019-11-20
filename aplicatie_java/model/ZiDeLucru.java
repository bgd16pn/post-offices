package model;

public class ZiDeLucru {

	private int id;
	private int idOficiuPostal;
	private String ziuaSaptamanii;
	private int oraDeschidere;
	private int oraInchidere;

	public String getZiuaSaptamanii() {
		return ziuaSaptamanii;
	}

	public void setZiuaSaptamanii(String ziuaSaptamanii) {
		this.ziuaSaptamanii = ziuaSaptamanii;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOficiuPostal() {
		return idOficiuPostal;
	}

	public int getOraDeschidere() {
		return oraDeschidere;
	}

	public int getOraInchidere() {
		return oraInchidere;
	}

	public void setIdOficiuPostal(int idOficiuPostal) {
		this.idOficiuPostal = idOficiuPostal;
	}

	public void setOraDeschidere(int oraDeschidere) {
		this.oraDeschidere = oraDeschidere;
	}

	public void setOraInchidere(int oraInchidere) {
		this.oraInchidere = oraInchidere;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Program de lucru: Id = ");
		return stringBuilder.append(id).append(", Id oficiu postal = ").append(idOficiuPostal)
				.append(", Ziua saptamanii = ").append(ziuaSaptamanii).append(", Ora deschidere = ")
				.append(oraDeschidere).append(", Ora inchidere = ").append(oraInchidere).toString();
	}
}
