package service;

import java.util.List;

import model.Scrisoare;
import repository.ScrisoriMySQLRepository;

public class ScrisoriService {

	private ScrisoriMySQLRepository scrisoriRepository = new ScrisoriMySQLRepository();

	public List<Scrisoare> getAll() {
		return scrisoriRepository.getAll();
	}

	public Scrisoare get(final int id) {
		return scrisoriRepository.get(id);
	}

	public void create(final Scrisoare scrisoare) {
		scrisoriRepository.put(scrisoare.getId(), scrisoare);
	}

	public void update(final Scrisoare scrisoare) {
		scrisoriRepository.put(scrisoare.getId(), scrisoare);
	}

	public int size() {
		return scrisoriRepository.size();
	}

	public int lastInsertedIndex() {
		return scrisoriRepository.lastInsertedIndex();
	}
	
}
