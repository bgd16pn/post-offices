package service;

import java.util.List;

import model.ZiDeLucru;
import repository.ZileDeLucruMySQLRepository;

public class ZileDeLucruService {

	private ZileDeLucruMySQLRepository zileDeLucruRepository = new ZileDeLucruMySQLRepository();

	public List<ZiDeLucru> getAll() {
		return zileDeLucruRepository.getAll();
	}

	public ZiDeLucru get(final int id) {
		return zileDeLucruRepository.get(id);
	}

	public ZiDeLucru get(final int idOficiuPostal, final String ziuaSaptamanii) {
		return zileDeLucruRepository.get(idOficiuPostal, ziuaSaptamanii);
	}

	public void create(final ZiDeLucru ziDeLucru) {
		zileDeLucruRepository.put(ziDeLucru.getId(), ziDeLucru);
	}

	public void update(final ZiDeLucru ziDeLucru) {
		zileDeLucruRepository.update(ziDeLucru.getIdOficiuPostal(), ziDeLucru);
	}

	public void remove(final ZiDeLucru ziDeLucru) {
		zileDeLucruRepository.remove(ziDeLucru.getId());
	}

	public int size() {
		return zileDeLucruRepository.size();
	}

	public int lastInsertedIndex() {
		return zileDeLucruRepository.lastInsertedIndex();
	}

	public List<ZiDeLucru> getSorted(final String[] searchParams) {
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

		return zileDeLucruRepository.getSorted(where, orderBy);
	}

}
