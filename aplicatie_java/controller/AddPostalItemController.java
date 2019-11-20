package controller;

import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.util.Random;

import model.Adresa;
import model.Colet;
import model.Mandat;
import model.Scrisoare;
import service.AdreseService;
import service.ArticolePostaleService;
import service.ColeteService;
import service.MandateService;
import service.OficiiPostaleService;
import service.ScrisoriService;
import service.TarifeService;
import view.AddArticolPostalView;
import view.AddressPanel;
import view.ColetPanel;
import view.MandatPanel;
import view.ScrisoarePanel;

public class AddPostalItemController {

	private AddArticolPostalView addPostalItemView;

	private ColetPanel coletPanel;
	private ScrisoarePanel scrisoarePanel;
	private MandatPanel mandatPanel;
	private AddressPanel adresaExpeditorPanel;
	private AddressPanel adresaDestinatarPanel;

	private ArticolePostaleService articolePostaleService;
	private ColeteService coleteService;
	private AdreseService adreseService;
	private ScrisoriService scrisoriService;
	private MandateService mandateService;
	private TarifeService tarifeService;
	private OficiiPostaleService oficiiPostaleService;

	public AddPostalItemController(AddArticolPostalView addArticolPostalView, Dimension screenSize,
			ArticolePostaleService articolePostaleService, ColeteService coleteService, ScrisoriService scrisoriService,
			MandateService mandateService, AdreseService adreseService, TarifeService tarifeService,
			OficiiPostaleService oficiiPostaleService) {

		this.addPostalItemView = addArticolPostalView;
		this.articolePostaleService = articolePostaleService;
		this.coleteService = coleteService;
		this.adreseService = adreseService;
		this.scrisoriService = scrisoriService;
		this.mandateService = mandateService;
		this.tarifeService = tarifeService;
		this.oficiiPostaleService = oficiiPostaleService;

		coletPanel = addPostalItemView.getColetPanel();
		scrisoarePanel = addPostalItemView.getScrisoarePanel();
		mandatPanel = addPostalItemView.getMandatPanel();

		adresaExpeditorPanel = addPostalItemView.getAdresaExpeditorPanel();
		adresaDestinatarPanel = addPostalItemView.getAdresaDestinatarPanel();

		initializeButtonListeners();

		addPostalItemView.setVisible(false);
	}

	public void initializeButtonListeners() {
		addPostalItemView.addTipArticolComboBoxActionListener(e -> {
			switch (addPostalItemView.getTipArticolComboBoxSelectedIndex()) {
			case 0:
				addPostalItemView.setPanelsVisible(false, false, false);
				break;
			case 1:
				addPostalItemView.setPanelsVisible(true, false, false);
				break;
			case 2:
				addPostalItemView.setPanelsVisible(false, true, false);
				break;
			case 3:
				addPostalItemView.setPanelsVisible(false, false, true);
				break;
			}
			addPostalItemView.clearInputFields();
		});

		addPostalItemView.addAddButtonActionListener(e -> {

			final String postalItemType = addPostalItemView.getTipArticolComboBoxItem();

			if (postalItemType.equals("")) {
				addPostalItemView.displayMessage("Niciun tip ales");
				return;
			}

			final String numeExpeditor = addPostalItemView.getNumeExpeditor();
			final String prenumeExpeditor = addPostalItemView.getPrenumeExpeditor();
			final String numeDestinatar = addPostalItemView.getNumeDestinatar();
			final String prenumeDestinatar = addPostalItemView.getPrenumeDestinatar();

			if (numeExpeditor.equals("") || prenumeExpeditor.equals("")) {
				addPostalItemView.displayMessage("Date expeditor invalide");
				System.out.println("Expeditor register error!");
				return;
			}

			if (numeDestinatar.equals("") || prenumeDestinatar.equals("")) {
				addPostalItemView.displayMessage("Date destinatar invalide");
				System.out.println("Destinatar register error!");
				return;
			}

			final Adresa adresaExpeditor = createAdresaExpeditor();
			final Adresa adresaDestinatar = createAdresaDestinatar();

			if (adresaExpeditor == null || adresaDestinatar == null) {
				return;
			}

			final int nextIndex = 1 + articolePostaleService.lastInsertedIndex();

			switch (postalItemType) {
			case "Colet":
				final Colet colet = new Colet();

				final float valoareRambursColet = coletPanel.getValoareRamburs();
				final float valoareDeclarataColet = coletPanel.getValoareDeclarata();
				final float greutateColet = coletPanel.getGreutate();
				final boolean trackedColet = coletPanel.isTracked();
				final boolean fragil = coletPanel.isFragil();
				final boolean voluminos = coletPanel.isVoluminos();
				final boolean livrareSambata = coletPanel.isLivrareSambata();

				colet.setId(nextIndex);
				colet.setNumeExpeditor(numeExpeditor);
				colet.setPrenumeExpeditor(prenumeExpeditor);
				colet.setIdAdresaExpeditor(adresaExpeditor.getId());
				colet.setNumeDestinatar(numeDestinatar);
				colet.setPrenumeDestinatar(prenumeDestinatar);
				colet.setIdAdresaDestinatar(adresaDestinatar.getId());

				colet.setValoareRamburs(valoareRambursColet);
				colet.setValoareDeclarata(valoareDeclarataColet);
				colet.setGreutate(greutateColet);
				colet.setTracked(trackedColet);
				colet.setFragil(fragil);
				colet.setVoluminos(voluminos);
				colet.setLivrareSambata(livrareSambata);

				colet.setTarifExpediere(tarifeService.getTarifExpediereColet(colet));
				colet.setTarifRidicare(valoareRambursColet);

				if (validateColet(colet)) {
					articolePostaleService.create(colet);
					coleteService.create(colet);

					System.out.println("Colet inregistrat cu succes");
					addPostalItemView.clearInputFields();
					addPostalItemView.clearRegisterTextFields();

					addPostalItemView.setVisible(false);
				}

				break;
			case "Scrisoare":
				final Scrisoare scrisoare = new Scrisoare();

				final float valoareRambursScrisoare = scrisoarePanel.getValoareRamburs();
				final float valoareDeclarataScrisoare = scrisoarePanel.getValoareDeclarata();
				final float greutateScrisoare = scrisoarePanel.getGreutate();
				final boolean trackedScrisoare = scrisoarePanel.isTracked();
				final boolean confirmarePrimire = scrisoarePanel.isConfirmarePrimire();

				scrisoare.setId(nextIndex);
				scrisoare.setNumeExpeditor(numeExpeditor);
				scrisoare.setPrenumeExpeditor(prenumeExpeditor);
				scrisoare.setIdAdresaExpeditor(adresaExpeditor.getId());
				scrisoare.setNumeDestinatar(numeDestinatar);
				scrisoare.setPrenumeDestinatar(prenumeDestinatar);
				scrisoare.setIdAdresaDestinatar(adresaDestinatar.getId());

				scrisoare.setValoareRamburs(valoareRambursScrisoare);
				scrisoare.setValoareDeclarata(valoareDeclarataScrisoare);
				scrisoare.setGreutate(greutateScrisoare);
				scrisoare.setTracked(trackedScrisoare);
				scrisoare.setConfirmarePrimire(confirmarePrimire);

				scrisoare.setTarifExpediere(tarifeService.getTarifExpediereScrisoare(scrisoare));
				scrisoare.setTarifRidicare(valoareRambursScrisoare);

				if (validateScrisoare(scrisoare)) {
					articolePostaleService.create(scrisoare);
					scrisoriService.create(scrisoare);

					System.out.println("Scrisoare inregistrata cu succes");
					addPostalItemView.clearInputFields();
					addPostalItemView.clearRegisterTextFields();

					addPostalItemView.setVisible(false);
				}

				break;
			case "Mandat":
				final Mandat mandat = new Mandat();

				final float valoare = mandatPanel.getValoare();
				final String detalii = mandatPanel.getDetalii();

				mandat.setId(nextIndex);
				mandat.setNumeExpeditor(numeExpeditor);
				mandat.setPrenumeExpeditor(prenumeExpeditor);
				mandat.setIdAdresaExpeditor(adresaExpeditor.getId());
				mandat.setNumeDestinatar(numeDestinatar);
				mandat.setPrenumeDestinatar(prenumeDestinatar);
				mandat.setIdAdresaDestinatar(adresaDestinatar.getId());

				mandat.setValoare(valoare);
				mandat.setDetalii(detalii);

				mandat.setTarifExpediere(tarifeService.getTarifExpediereMandat(mandat));
				mandat.setTarifRidicare(0);

				if (validateMandat(mandat)) {
					articolePostaleService.create(mandat);
					mandateService.create(mandat);

					System.out.println("Mandat inregistrata cu succes");
					addPostalItemView.clearInputFields();
					addPostalItemView.clearRegisterTextFields();

					addPostalItemView.setVisible(false);
				}

				break;
			}

		});
	}

	private boolean validateAddress(Adresa adresa) {
		if (adresa.getStrada().equals("") || adresa.getNumar().equals("") || adresa.getOras().equals("")
				|| adresa.getJudet().equals("") || adresa.getCodPostal().equals("")) {
			return false;
		}

		return true;
	}

	private boolean validateMandat(Mandat mandat) {
		if (mandat.getValoare() == -1 || mandat.getDetalii().equals("")) {
			addPostalItemView.displayMessage("Date mandat invalide");
			return false;
		}

		return true;
	}

	private boolean validateColet(Colet colet) {
		if (colet.getValoareRamburs() == -1 || colet.getValoareDeclarata() == -1 || colet.getGreutate() == -1) {
			addPostalItemView.displayMessage("Date colet invalide");
			return false;
		}

		return true;
	}

	private boolean validateScrisoare(Scrisoare scrisoare) {
		if (scrisoare.getValoareRamburs() == -1 || scrisoare.getValoareDeclarata() == -1
				|| scrisoare.getGreutate() == -1) {
			addPostalItemView.displayMessage("Date scrisoare invalide");
			return false;
		}

		return true;
	}

	private Adresa createAdresaDestinatar() {
		Random random = new Random(System.currentTimeMillis());

		final Adresa adresa = new Adresa();

		final String strada = adresaDestinatarPanel.getStrada();
		final String numar = adresaDestinatarPanel.getNumar();
		final String bloc = adresaDestinatarPanel.getBloc();
		final String scara = adresaDestinatarPanel.getScara();
		final String apartament = adresaDestinatarPanel.getApartament();
		final String oras = adresaDestinatarPanel.getOras();
		final String judet = adresaDestinatarPanel.getJudet();
		final String codPostal = adresaDestinatarPanel.getCodPostal();

		adresa.setId(1 + adreseService.lastInsertedIndex());
		adresa.setStrada(strada);
		adresa.setNumar(numar);
		adresa.setBloc(bloc);
		adresa.setScara(scara);
		adresa.setApartament(apartament);
		adresa.setOras(oras);
		adresa.setJudet(judet);
		adresa.setCodPostal(codPostal);
		adresa.setIdOficiuPostal(1 + random.nextInt(oficiiPostaleService.lastInsertedIndex()));

		if (!validateAddress(adresa)) {
			System.out.println("Address register error - destinatar!");
			addPostalItemView.displayMessage("Adresa destinatar invalida");
			adresaExpeditorPanel.clearTextFields();
			return null;
		}

		final Adresa adresaResult = adreseService.get(strada, numar, bloc, scara, apartament, oras, judet, codPostal);

		if (adresaResult == null) {
			adreseService.create(adresa);
		} else {
			adresa.setId(adresaResult.getId());
		}

		return adresa;
	}

	private Adresa createAdresaExpeditor() {
		final Adresa adresa = new Adresa();

		final String strada = adresaExpeditorPanel.getStrada();
		final String numar = adresaExpeditorPanel.getNumar();
		final String bloc = adresaExpeditorPanel.getBloc();
		final String scara = adresaExpeditorPanel.getScara();
		final String apartament = adresaExpeditorPanel.getApartament();
		final String oras = adresaExpeditorPanel.getOras();
		final String judet = adresaExpeditorPanel.getJudet();
		final String codPostal = adresaExpeditorPanel.getCodPostal();

		adresa.setId(1 + adreseService.lastInsertedIndex());
		adresa.setStrada(strada);
		adresa.setNumar(numar);
		adresa.setBloc(bloc);
		adresa.setScara(scara);
		adresa.setApartament(apartament);
		adresa.setOras(oras);
		adresa.setJudet(judet);
		adresa.setCodPostal(codPostal);
		adresa.setIdOficiuPostal(1);

		if (!validateAddress(adresa)) {
			System.out.println("Address register error! - expeditor");
			addPostalItemView.displayMessage("Adresa expeditor invalida");
			adresaDestinatarPanel.clearTextFields();
			return null;
		}

		final Adresa adresaResult = adreseService.get(strada, numar, bloc, scara, apartament, oras, judet, codPostal);

		if (adresaResult == null) {
			adreseService.create(adresa);
		} else {
			adresa.setId(adresaResult.getId());
		}

		return adresa;
	}

	public void setAddPostalItemVisible(final boolean flag) {
		addPostalItemView.setVisible(flag);
	}

	public void addAddPostalItemViewActionListener(final WindowListener windowListener) {
		addPostalItemView.addWindowListener(windowListener);
	}

}
