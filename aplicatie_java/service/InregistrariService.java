package service;

import java.util.List;

import model.Inregistrare;
import repository.InregistrariMySQLRepository;

public class InregistrariService {

	private InregistrariMySQLRepository inregistrariRepository = new InregistrariMySQLRepository();

	public List<Inregistrare> getAll() {
		return inregistrariRepository.getAll();
	}

	public List<Inregistrare> getByOficiuPostal(final int idOficiuPostal){
		return inregistrariRepository.getByOficiuPostal(idOficiuPostal);
	}
	
	public Inregistrare get(final int id) {
		return inregistrariRepository.get(id);
	}

	public void create(final Inregistrare inregistrare) {
		inregistrariRepository.put(inregistrare.getId(), inregistrare);
	}

	public void update(final Inregistrare inregistrare) {
		inregistrariRepository.put(inregistrare.getId(), inregistrare);
	}

	public void remove(final int id) {
		inregistrariRepository.remove(id);
	}

	public int size() {
		return inregistrariRepository.size();
	}

	public int lastInsertedIndex() {
		return inregistrariRepository.lastInsertedIndex();
	}

	public List<Inregistrare> getSorted(final String[] searchParams) {
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

		return inregistrariRepository.getSorted(where, orderBy);
	}
	
}
