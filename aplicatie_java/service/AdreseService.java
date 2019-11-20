package service;

import java.util.List;

import model.Adresa;
import repository.AdreseMySQLRepository;

public class AdreseService {

	private AdreseMySQLRepository adreseRepository = new AdreseMySQLRepository();

	public List<Adresa> getAll() {
		return adreseRepository.getAll();
	}

	public List<Adresa> getByOficiuPostal(final int idOficiuPostal) {
		return adreseRepository.getByOficiuPostal(idOficiuPostal);
	}

	public Adresa get(final int id) {
		return adreseRepository.get(id);
	}

	public Adresa get(final String strada, final String numar, final String bloc, final String scara,
			final String apartament, final String oras, final String judet, final String codPostal) {
		return adreseRepository.get(strada, numar, bloc, scara, apartament, oras, judet, codPostal);
	}

	public void create(final Adresa adresa) {
		adreseRepository.put(adresa.getId(), adresa);
	}

	public void update(final Adresa adresa) {
		adreseRepository.update(adresa.getId(), adresa);
	}

	public void remove(final int id) {
		adreseRepository.remove(id);
	}

	public int size() {
		return adreseRepository.size();
	}

	public int lastInsertedIndex() {
		return adreseRepository.lastInsertedIndex();
	}

	public List<Adresa> getSorted(final String[] searchParams) {
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

		return adreseRepository.getSorted(where, orderBy);
	}
}
