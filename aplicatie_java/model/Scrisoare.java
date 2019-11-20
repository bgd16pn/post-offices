package model;

public class Scrisoare extends ArticolPostal {

	private float valoareRamburs;
	private float valoareDeclarata;
	private float greutate;
	private boolean confirmarePrimire;
	private boolean tracked;

	public Scrisoare() {
		this.setTip("Scrisoare");
	}
	
	public float getValoareRamburs() {
		return valoareRamburs;
	}

	public float getValoareDeclarata() {
		return valoareDeclarata;
	}

	public float getGreutate() {
		return greutate;
	}

	public boolean isConfirmarePrimire() {
		return confirmarePrimire;
	}

	public boolean isTracked() {
		return tracked;
	}

	public void setConfirmarePrimire(boolean confirmarePrimire) {
		this.confirmarePrimire = confirmarePrimire;
	}

	public void setTracked(boolean tracked) {
		this.tracked = tracked;
	}

	public void setValoareRamburs(float valoareRamburs) {
		this.valoareRamburs = valoareRamburs;
	}

	public void setValoareDeclarata(float valoareDeclarata) {
		this.valoareDeclarata = valoareDeclarata;
	}

	public void setGreutate(float greutate) {
		this.greutate = greutate;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder(super.getStatus());
		return stringBuilder.append("\t\tSCRISOARE RECOMANDATA: ").append("Valoare ramburs = ").append(valoareRamburs)
				.append(" lei, ").append("Valoare declarata = ").append(valoareDeclarata).append(" lei, ")
				.append("Greutate = ").append(greutate).append(", Confirmare de primire = ").append(confirmarePrimire)
				.append(", Tracked = ").append(tracked).toString();
	}
}
