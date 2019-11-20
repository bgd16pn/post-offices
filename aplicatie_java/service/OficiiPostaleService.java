package service;

import java.util.List;

import model.OficiuPostal;
import repository.OficiiPostaleMySQLRepository;

public class OficiiPostaleService {

	private OficiiPostaleMySQLRepository oficiiPostaleRepository = new OficiiPostaleMySQLRepository();

	public List<OficiuPostal> getAll() {
		return oficiiPostaleRepository.getAll();
	}

	public OficiuPostal get(final int id) {
		return oficiiPostaleRepository.get(id);
	}
	
	public void create(final OficiuPostal oficiuPostal) {
		oficiiPostaleRepository.put(oficiuPostal.getId(), oficiuPostal);
	}

	public void update(final OficiuPostal oficiuPostal) {
		oficiiPostaleRepository.update(oficiuPostal.getId(), oficiuPostal);
	}

	public void remove(final int id) {
		oficiiPostaleRepository.remove(id);
	}

	public int size() {
		return oficiiPostaleRepository.size();
	}

	public int lastInsertedIndex() {
		return oficiiPostaleRepository.lastInsertedIndex();
	}

	public List<OficiuPostal> getSorted(final String[] searchParams) {
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

		return oficiiPostaleRepository.getSorted(where, orderBy);
	}
	
}
