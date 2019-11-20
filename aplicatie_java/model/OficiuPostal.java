package model;

public class OficiuPostal {

	private int id;
	private String denumire;
//	private int idAdresa;
	private String telefon;
//	private String usernameAdministrator;

	public int getId() {
		return id;
	}

	public String getDenumire() {
		return denumire;
	}

//	public int getIdAdresa() {
//		return idAdresa;
//	}

	public String getTelefon() {
		return telefon;
	}

//	public String getUsernameAdministrator() {
//		return usernameAdministrator;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

//	public void setIdAdresa(int idAdresa) {
//		this.idAdresa = idAdresa;
//	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

//	public void setUsernameAdministrator(String usernameAdministrator) {
//		this.usernameAdministrator = usernameAdministrator;
//	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Oficiul Postal: Id = ");
		return stringBuilder.append(id).append(", Denumire = ").append(denumire)//.append(", Id adresa = ").append(idAdresa)
				.append(", Telefon = ").append(telefon).toString();//append(", Username administrator = ")
			//	.append(usernameAdministrator).toString();
	}

}
