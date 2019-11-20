package model;

public class Colet extends ArticolPostal {

	private float valoareRamburs;
	private float valoareDeclarata;
	private float greutate;
	private boolean tracked;
	private boolean fragil;
	private boolean voluminos;
	private boolean livrareSambata;

	public Colet() {
		this.setTip("Colet");
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

	public boolean isFragil() {
		return fragil;
	}

	public boolean isVoluminos() {
		return voluminos;
	}

	public boolean isTracked() {
		return tracked;
	}

	public boolean isLivrareSambata() {
		return livrareSambata;
	}

	public void setTracked(boolean tracked) {
		this.tracked = tracked;
	}

	public void setLivrareSambata(boolean livrareSambata) {
		this.livrareSambata = livrareSambata;
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

	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}

	public void setVoluminos(boolean voluminos) {
		this.voluminos = voluminos;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder(super.getStatus());
		return stringBuilder.append("\t\tCOLET: Valoare ramburs = ").append(valoareRamburs)
				.append(" lei, Valoare declarata = ").append(valoareDeclarata).append(" lei, Greutate = ")
				.append(greutate).append(" kg, Livrare sambata = ").append(livrareSambata).append(", Tracked = ")
				.append(tracked).append(", Fragil = ").append(fragil).append(", Voluminos = ").append(voluminos)
				.toString();
	}

}
