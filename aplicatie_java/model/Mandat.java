package model;

public class Mandat extends ArticolPostal {

	private float valoare;
	private String detalii;

	public Mandat() {
		this.setTip("Mandat");
	}
	
	public float getValoare() {
		return valoare;
	}

	public String getDetalii() {
		return detalii;
	}

	public void setValoare(float valoare) {
		this.valoare = valoare;
	}

	public void setDetalii(String detalii) {
		this.detalii = detalii;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder(super.getStatus());
		return stringBuilder.append("\t\tMANDAT POSTAL: ").append("Valoare = ").append(valoare)
				.append(" lei, Detalii = ").append(detalii).toString();
	}

}
