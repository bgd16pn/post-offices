package model;

public class Angajat extends Utilizator {

	private int idOficiuPostal;
	private float salariu;
	private String dataAngajare;
	private boolean administrator;

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public int getIdOficiuPostal() {
		return idOficiuPostal;
	}

	public float getSalariu() {
		return salariu;
	}

	public String getDataAngajare() {
		return dataAngajare;
	}

	public void setIdOficiuPostal(int idOficiuPostal) {
		this.idOficiuPostal = idOficiuPostal;
	}

	public void setSalariu(float salariu) {
		this.salariu = salariu;
	}

	public void setDataAngajare(String dataAngajare) {
		this.dataAngajare = dataAngajare;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder(super.getStatus());
		return stringBuilder.append("\n\tANGAJAT: Id oficiu postal = ").append(idOficiuPostal)
				.append(", Administrator = ").append(administrator).append(", Salariu = ").append(salariu)
				.append(", Data angajare = ").append(dataAngajare).toString();
	}
}
