package service;

import java.util.List;

import model.Mandat;
import repository.MandateMySQLRepository;

public class MandateService {

	private MandateMySQLRepository mandateRepository = new MandateMySQLRepository();

	public List<Mandat> getAll() {
		return mandateRepository.getAll();
	}

	public Mandat get(final int id) {
		return mandateRepository.get(id);
	}

	public void create(final Mandat mandat) {
		mandateRepository.put(mandat.getId(), mandat);
	}

	public void update(final Mandat mandat) {
		mandateRepository.put(mandat.getId(), mandat);
	}

	public int size() {
		return mandateRepository.size();
	}

	public int lastInsertedIndex() {
		return mandateRepository.lastInsertedIndex();
	}

}
