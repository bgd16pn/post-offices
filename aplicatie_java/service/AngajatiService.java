package service;

import java.util.List;

import model.Angajat;
import repository.AngajatiMySQLRepository;

public class AngajatiService {

	private AngajatiMySQLRepository angajatiRepository = new AngajatiMySQLRepository();

	public List<Angajat> getAll() {
		return angajatiRepository.getAll();
	}

	public Angajat get(final String username) {
		return angajatiRepository.get(username);
	}

	public void create(final Angajat angajat) {
		angajatiRepository.put(angajat.getUsername(), angajat);
	}

	public void update(final Angajat angajat) {
		angajatiRepository.update(angajat.getUsername(), angajat);
	}

	public void remove(final String username) {
		angajatiRepository.remove(username);
	}

	public int size() {
		return angajatiRepository.size();
	}

	public int lastInsertedIndex() {
		return angajatiRepository.lastInsertedIndex();
	}

	public List<Angajat> getSorted(final String[] searchParams) {
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

		return angajatiRepository.getSorted(where, orderBy);
	}

	public Angajat getPersonalData(final String username) {
		return angajatiRepository.getPersonalData(username);
	}
}
