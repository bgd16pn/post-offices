package service;

import java.util.List;

import model.ArticolPostal;
import repository.MagazieMySQLRepository;

public class MagazieService {

	private MagazieMySQLRepository magazieRepository = new MagazieMySQLRepository();
	
	public ArticolPostal get(final int idOficiuPostal, final int idArticolPostal) {
		return magazieRepository.get(idOficiuPostal, idArticolPostal);
	}

	public List<ArticolPostal> getAll(final int idOficiuPostal) {
		return magazieRepository.getAll(idOficiuPostal);
	}

	public void put(final int idOficiuPostal, final ArticolPostal articolPostal) {
		magazieRepository.put(idOficiuPostal, articolPostal);
	}

	public void update(final int newIdOficiuPostal, final ArticolPostal articolPostal) {
		magazieRepository.update(newIdOficiuPostal, articolPostal);
	}

	public void remove(final int idArticolPostal) {
		magazieRepository.remove(idArticolPostal);
	}

	public int size(final int idOficiuPostal) {
		return magazieRepository.size(idOficiuPostal);
	}

	public int lastInsertedIndex(final int idOficiuPostal) {
		return magazieRepository.lastInsertedIndex(idOficiuPostal);
	}

	public List<ArticolPostal> getSorted(final int idOficiuPostal, final String[] searchParams) {
		final String sortBy = searchParams[0];
		String searchBy = searchParams[1];
		final String search = searchParams[2];

		String orderBy = "ORDER BY articole_postale.";
		String where = " ";

		searchBy = searchBy.toLowerCase().replace(" ", "_");
		
		if(searchBy.equals("id_articol_postal")) {
			searchBy = "id";
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
			where = " AND articole_postale.".concat(searchBy).concat(" = '").concat(search).concat("'");
		}

		return magazieRepository.getSorted(idOficiuPostal, where, orderBy);
	}
}
