package service;

import java.util.List;

import model.Colet;
import repository.ColeteMySQLRepository;

public class ColeteService {

	private ColeteMySQLRepository coleteRepository = new ColeteMySQLRepository();

	public List<Colet> getAll() {
		return coleteRepository.getAll();
	}
	
	public Colet get(final int id) {
		return coleteRepository.get(id);
	}

	public void create(final Colet colet) {
		coleteRepository.put(colet.getId(), colet);
	}

	public void update(final Colet colet) {
		coleteRepository.put(colet.getId(), colet);
	}

	public int size() {
		return coleteRepository.size();
	}

	public int lastInsertedIndex() {
		return coleteRepository.lastInsertedIndex();
	}
		
}
