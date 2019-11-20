package model;

public class Utilizator {

	private int id;
	private String username;
	private String parola;
	private String nume;
	private String prenume;
	private String email;
	private String telefon;
	private int idAdresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getParola() {
		return parola;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefon() {
		return telefon;
	}

	public int getIdAdresa() {
		return idAdresa;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Utilizator: Username = ");
		return stringBuilder.append(username).append(", Parola = ").append(parola).append(", Nume = ").append(nume)
				.append(", Prenume = ").append(prenume).append(", Email = ").append(email).append(", Telefon = ")
				.append(telefon).append(", Id adresa = ").append(idAdresa).toString();
	}
}
