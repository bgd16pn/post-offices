package model;

public class ArticolPostal {

	private int id;
	private String tip;
	private String dataColectare;
	private String dataTransmitere;
	private String dataAvizare;
	private String dataRidicare;
	private String numeExpeditor;
	private String prenumeExpeditor;
	private int idAdresaExpeditor;
	private String numeDestinatar;
	private String prenumeDestinatar;
	private int idAdresaDestinatar;
	private float tarifExpediere;
	private float tarifRidicare;
	private String codTracking;

	public String getPrenumeExpeditor() {
		return prenumeExpeditor;
	}

	public String getPrenumeDestinatar() {
		return prenumeDestinatar;
	}

	public void dataColectare(String prenumeExpeditor) {
		this.prenumeExpeditor = prenumeExpeditor;
	}

	public void setPrenumeDestinatar(String prenumeDestinatar) {
		this.prenumeDestinatar = prenumeDestinatar;
	}

	public int getId() {
		return id;
	}

	public String getTip() {
		return tip;
	}

	public String getDataColectare() {
		return dataColectare;
	}

	public String getDataTransmitere() {
		return dataTransmitere;
	}

	public String getDataAvizare() {
		return dataAvizare;
	}

	public String getDataRidicare() {
		return dataRidicare;
	}

	public String getNumeExpeditor() {
		return numeExpeditor;
	}
	
	public void setPrenumeExpeditor(String prenumeExpeditor) {
		this.prenumeExpeditor = prenumeExpeditor;
	}

	public int getIdAdresaExpeditor() {
		return idAdresaExpeditor;
	}

	public String getNumeDestinatar() {
		return numeDestinatar;
	}

	public int getIdAdresaDestinatar() {
		return idAdresaDestinatar;
	}

	public float getTarifExpediere() {
		return tarifExpediere;
	}

	public float getTarifRidicare() {
		return tarifRidicare;
	}

	public String getCodTracking() {
		return codTracking;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void setDataColectare(String dataColectare) {
		this.dataColectare = dataColectare;
	}

	public void setDataTransmitere(String dataTransmitere) {
		this.dataTransmitere = dataTransmitere;
	}

	public void setDataAvizare(String dataAvizare) {
		this.dataAvizare = dataAvizare;
	}

	public void setDataRidicare(String dataRidicare) {
		this.dataRidicare = dataRidicare;
	}

	public void setNumeExpeditor(String numeExpeditor) {
		this.numeExpeditor = numeExpeditor;
	}

	public void setIdAdresaExpeditor(int idAdresaExpeditor) {
		this.idAdresaExpeditor = idAdresaExpeditor;
	}

	public void setNumeDestinatar(String numeDestinatar) {
		this.numeDestinatar = numeDestinatar;
	}

	public void setIdAdresaDestinatar(int idAdresaDestinatar) {
		this.idAdresaDestinatar = idAdresaDestinatar;
	}

	public void setTarifExpediere(float tarifExpediere) {
		this.tarifExpediere = tarifExpediere;
	}

	public void setTarifRidicare(float tarifRidicare) {
		this.tarifRidicare = tarifRidicare;
	}

	public void setCodTracking(String codTracking) {
		this.codTracking = codTracking;
	}

	public String getStatus() {
		StringBuilder stringBuilder = new StringBuilder("Detalii: ID = ");
		return stringBuilder.append(id).append(", Tip = ").append(tip).append(", Data Colectare = ")
				.append(dataColectare).append(", Data Transmitere = ").append(dataTransmitere)
				.append(", Data Avizare = ").append(dataAvizare).append(", Data Ridicare = ").append(dataRidicare)
				.append("\n\tExpeditor = ").append(numeExpeditor).append(" ").append(prenumeExpeditor)
				.append(", Id adresa EXP = ").append(idAdresaExpeditor).append("\tDestinatar = ").append(numeDestinatar)
				.append(" ").append(prenumeDestinatar).append(", Id adresa DEST = ").append(idAdresaDestinatar)
				.append("\n\tTarif Expediere = ").append(tarifExpediere).append(", Tarif ridicare = ")
				.append(tarifRidicare).append("\n\tCod Tracking = ").append(codTracking).toString();
	}

}
