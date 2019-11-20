package service;

import java.util.List;

import model.ArticolPostal;
import repository.ArticolePostaleMySQLRepository;

public class ArticolePostaleService {

	private ArticolePostaleMySQLRepository articolePostaleRepository = new ArticolePostaleMySQLRepository();

	public List<ArticolPostal> getAll() {
		List<ArticolPostal> outputList = articolePostaleRepository.getAll();
		if (outputList != null && !outputList.isEmpty()) {
			return outputList;
		}
		return null;
	}

	public ArticolPostal get(final String trackingCode) {
		return articolePostaleRepository.get(trackingCode);
	}

	public ArticolPostal get(final int id) {
		return articolePostaleRepository.get(id);
	}

	public List<ArticolPostal> getUserHistory(final String nume, final String prenume) {
		List<ArticolPostal> outputList = getAll();
		if (outputList != null && !outputList.isEmpty()) {
			outputList.removeIf(e -> (!(e.getNumeDestinatar().equals(nume) && e.getPrenumeDestinatar().equals(prenume))
					|| !(e.getNumeExpeditor().equals(nume) && e.getPrenumeExpeditor().equals(prenume))));
			return outputList;
		}

		return null;
	}

	public void create(final ArticolPostal articolPostal) {
		articolePostaleRepository.put(articolPostal.getId(), articolPostal);
	}

	public void update(final ArticolPostal articolPostal) {
		articolePostaleRepository.update(articolPostal.getId(), articolPostal);
	}

	public void remove(final int id) {
		articolePostaleRepository.remove(id);
	}

	public int size() {
		return articolePostaleRepository.size();
	}

	public int lastInsertedIndex() {
		return articolePostaleRepository.lastInsertedIndex();
	}

	public String generateTrackingCode(final int id) {
		return articolePostaleRepository.generateTrackingCode(id);
	}

	public void send(final int idArticolPostal) {
		articolePostaleRepository.send(idArticolPostal);
	}

	public void avizare(final int idArticolPostal) {
		articolePostaleRepository.avizare(idArticolPostal);
	}

	public void ridicare(final int idArticolPostal) {
		articolePostaleRepository.ridicare(idArticolPostal);
	}

	public List<ArticolPostal> getSorted(final String[] searchParams) {
		final String sortBy = searchParams[0];
		String searchBy = searchParams[1];
		final String search = searchParams[2];

		String orderBy = " ORDER BY ";
		String where = " ";

		searchBy = searchBy.toLowerCase().replace(" ", "_");

		if (searchBy.equals("np_expeditor")) {
			searchBy = "nume_expeditor";
		}

		if (searchBy.equals("nume_destinatar")) {
			searchBy = "nume_destinatar";
		}

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

		return articolePostaleRepository.getSorted(where, orderBy);
	}
}
