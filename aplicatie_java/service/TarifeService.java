package service;

import java.util.Iterator;
import java.util.List;

import model.Colet;
import model.Mandat;
import model.Scrisoare;
import model.Tarif;
import repository.TarifeMySQLRepository;

public class TarifeService {

	private TarifeMySQLRepository tarifeRepository = new TarifeMySQLRepository();

	public List<Tarif> getAll() {
		return tarifeRepository.getAll();
	}

	public Tarif get(final String descriere, final String tipArticolPostal) {
		return tarifeRepository.get(descriere, tipArticolPostal);
	}

	public void create(final Tarif tarif) {
		tarifeRepository.put(tarif.getDescriere(), tarif);
	}

	public void update(final Tarif tarif) {
		tarifeRepository.update(tarif.getDescriere(), tarif);
	}

	public void remove(final String descriere, final String tipArticolPostal) {
		tarifeRepository.remove(descriere, tipArticolPostal);
	}

	public int size() {
		return tarifeRepository.size();
	}

	public int lastInsertedIndex() {
		return tarifeRepository.lastInsertedIndex();
	}

	public float getTarifExpediereColet(final Colet colet) {

		float tarifExpediere = colet.getValoareDeclarata() * 0.08f;

		int isRamburs = colet.getValoareRamburs() > 0 ? 1 : 0;
		int isFragil = colet.isFragil() ? 1 : 0;
		int isVoluminos = colet.isVoluminos() ? 1 : 0;
		int isTracked = colet.isTracked() ? 1 : 0;
		int isLivrareSambata = colet.isLivrareSambata() ? 1 : 0;

		List<Tarif> tarifeColete = tarifeRepository.getByTipArticolPostal("Colet");
		Iterator<Tarif> it = tarifeColete.iterator();

		while (it.hasNext()) {
			Tarif tarif = it.next();
			switch (tarif.getDescriere()) {
			case "Tarif fix":
				tarifExpediere += tarif.getValoare();
				break;
			case "Tarif per kg":
				tarifExpediere += colet.getGreutate() * tarif.getValoare();
				break;
			case "Tarif trimitere ramburs":
				tarifExpediere += isRamburs * tarif.getValoare();
				break;
			case "Tarif fragil":
				tarifExpediere += isFragil * tarif.getValoare();
				break;
			case "Tarif voluminos":
				tarifExpediere += isVoluminos * tarif.getValoare();
				break;
			case "Tarif tracking":
				tarifExpediere += isTracked * tarif.getValoare();
				break;
			case "Tarif livrare sambata":
				tarifExpediere += isLivrareSambata * tarif.getValoare();
				break;
			}
		}

		return tarifExpediere;
	}

	public float getTarifExpediereScrisoare(final Scrisoare scrisoare) {

		float tarifExpediere = scrisoare.getValoareDeclarata() * 0.08f;

		int isRamburs = scrisoare.getValoareRamburs() > 0 ? 1 : 0;
		int isTracked = scrisoare.isTracked() ? 1 : 0;
		int isConfirmarePrimire = scrisoare.isConfirmarePrimire() ? 1 : 0;

		List<Tarif> tarifeScrisori = tarifeRepository.getByTipArticolPostal("Scrisoare");
		Iterator<Tarif> it = tarifeScrisori.iterator();

		while (it.hasNext()) {
			Tarif tarif = it.next();
			switch (tarif.getDescriere()) {
			case "Tarif fix":
				tarifExpediere += tarif.getValoare();
				break;
			case "Tarif ramburs":
				tarifExpediere += isRamburs * tarif.getValoare();
				break;
			case "Tarif tracking":
				tarifExpediere += isTracked * tarif.getValoare();
				break;
			case "Tarif confirmare primire":
				tarifExpediere += isConfirmarePrimire * tarif.getValoare();
				break;
			}
		}

		return tarifExpediere;
	}

	public float getTarifExpediereMandat(final Mandat mandat) {
		return tarifeRepository.get("Tarif fix", "Mandat").getValoare();
	}

	public List<Tarif> getSorted(final String[] searchParams) {
		final String sortBy = searchParams[0];
		String searchBy = searchParams[1];
		final String search = searchParams[2];

		String orderBy = " ORDER BY ";
		String where = " ";

		searchBy = searchBy.toLowerCase().replace(" ", "_");

		switch (sortBy) {
		case "Crescator":
			orderBy = orderBy.concat(searchBy).concat(" ASC");
			break;
		case "Descrescator":
			orderBy = orderBy.concat(searchBy).concat(" DESC");
			break;
		}

		if (!search.equals("")) {
			where = " WHERE ".concat(searchBy).concat(" = '").concat(search).concat("'");
		}

		return tarifeRepository.getSorted(where, orderBy);
	}

}
