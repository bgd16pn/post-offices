package model;

public class Tarif {

	private int id;
	private String tipArticolPostal;
	private String descriere;
	private float valoare;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipArticolPostal() {
		return tipArticolPostal;
	}

	public String getDescriere() {
		return descriere;
	}

	public float getValoare() {
		return valoare;
	}

	public void setTipArticolPostal(String tipArticolPostal) {
		this.tipArticolPostal = tipArticolPostal;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public void setValoare(float valoare) {
		this.valoare = valoare;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Tarif: Id = ");
		return stringBuilder.append(id).append(", Tip articol postal = ").append(tipArticolPostal)
				.append(", Descriere = ").append(descriere).append(", Valoare = ").append(valoare).toString();
	}

}
