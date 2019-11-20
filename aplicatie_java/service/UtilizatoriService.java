package service;

import java.util.List;

import model.Utilizator;
import repository.UtilizatoriMySQLRepository;

public class UtilizatoriService {

	private UtilizatoriMySQLRepository utilizatoriRepository = new UtilizatoriMySQLRepository();

	public List<Utilizator> getAll() {
		return utilizatoriRepository.getAll();
	}

	public Utilizator get(final String username) {
		return utilizatoriRepository.get(username);
	}

	public void create(final Utilizator utilizator) {
		utilizatoriRepository.put(utilizator.getUsername(), utilizator);
	}

	public void update(final Utilizator utilizator) {
		utilizatoriRepository.update(utilizator.getUsername(), utilizator);
	}

	public void remove(final String username) {
		utilizatoriRepository.remove(username);
	}

	public int size() {
		return utilizatoriRepository.size();
	}

	public int lastInsertedIndex() {
		return utilizatoriRepository.lastInsertedIndex();
	}

	public List<Utilizator> getSorted(final String[] searchParams) {
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

		return utilizatoriRepository.getSorted(where, orderBy);
	}

}
