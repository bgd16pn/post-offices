package model;

public class Adresa {

	private int id;
	private String strada;
	private String numar;
	private String bloc;
	private String scara;
	private String apartament;
	private String oras;
	private String judet;
	private String codPostal;
	private int idOficiuPostal;

	public int getIdOficiuPostal() {
		return idOficiuPostal;
	}

	public void setIdOficiuPostal(int idOficiuPostal) {
		this.idOficiuPostal = idOficiuPostal;
	}

	public int getId() {
		return id;
	}

	public String getStrada() {
		return strada;
	}

	public String getNumar() {
		return numar;
	}

	public String getBloc() {
		return bloc;
	}

	public String getScara() {
		return scara;
	}

	public String getApartament() {
		return apartament;
	}

	public String getOras() {
		return oras;
	}

	public String getJudet() {
		return judet;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStrada(String strada) {
		this.strada = strada;
	}

	public void setNumar(String numar) {
		this.numar = numar;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public void setScara(String scara) {
		this.scara = scara;
	}

	public void setApartament(String apartament) {
		this.apartament = apartament;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}

	public void setJudet(String judet) {
		this.judet = judet;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Adresa: Id = ");
		return stringBuilder.append(id).append(", Strada =  ").append(strada).append(", Numar =  ").append(numar)
				.append("\nBloc =  ").append(bloc).append(", Scara =  ").append(scara).append(", Apartament =  ")
				.append(apartament).append("\nOras = ").append(oras).append(", Judet = ").append(judet)
				.append(", Cod postal = ").append(codPostal).toString();
	}
}
