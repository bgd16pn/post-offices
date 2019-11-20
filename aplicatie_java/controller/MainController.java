package controller;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import component.FileChooser;
import component.PDFGenerator;
import model.*;
import service.*;
import view.*;

public class MainController {

	// securizare
	// generare rapoarte pdf/ xml/ excel -> angajat
	// neo4j - model translatat -> tracking cu hub-uri

	private AddPostalItemController addPostalItemController;

	private AdreseService adreseService;
	private AngajatiService angajatiService;
	private ArticolePostaleService articolePostaleService;
	private ColeteService coleteService;
	private InregistrariService inregistrariService;
	private MagazieService magazieService;
	private MandateService mandateService;
	private OficiiPostaleService oficiiPostaleService;
	private ScrisoriService scrisoriService;
	private TarifeService tarifeService;
	private ZileDeLucruService zileDeLucruService;
	private UtilizatoriService utilizatoriService;

	private LoginView loginView;
	private RegisterView registerView;
	private DashboardView dashboardView;
	private ControlPanelView controlPanelView;
	private TrackingView trackingView;
	private HelpView helpView;
	private AddArticolPostalView addArticolPostalView;

	private SearchPanel searchPanel;
	private TopSidePanel topPanel;
	private LeftSidePanel leftPanel;
	private RightSidePanel rightPanel;
	private BottomSidePanel bottomPanel;

	private Utilizator currentUser = null;
	private String currentUserRole = "User";
	private String currentSection = "";
	private String currentTask = "";
	private Object[] currentRowData = new Object[15];

	public void start() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		adreseService = new AdreseService();
		angajatiService = new AngajatiService();
		articolePostaleService = new ArticolePostaleService();
		coleteService = new ColeteService();
		inregistrariService = new InregistrariService();
		magazieService = new MagazieService();
		mandateService = new MandateService();
		oficiiPostaleService = new OficiiPostaleService();
		scrisoriService = new ScrisoriService();
		tarifeService = new TarifeService();
		zileDeLucruService = new ZileDeLucruService();
		utilizatoriService = new UtilizatoriService();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		loginView = new LoginView(screenSize.width, screenSize.height);
		registerView = new RegisterView(screenSize.width, screenSize.height);
		dashboardView = new DashboardView(screenSize.width, screenSize.height);
		controlPanelView = new ControlPanelView(screenSize.width, screenSize.height);
		trackingView = new TrackingView(screenSize.width, screenSize.height);
		helpView = new HelpView(screenSize.width, screenSize.height);
		addArticolPostalView = new AddArticolPostalView(screenSize.width, screenSize.height);

		addPostalItemController = new AddPostalItemController(addArticolPostalView, screenSize, articolePostaleService,
				coleteService, scrisoriService, mandateService, adreseService, tarifeService, oficiiPostaleService);

		searchPanel = controlPanelView.getSearchPanel();
		topPanel = controlPanelView.getTopPanel();
		bottomPanel = controlPanelView.getBottomPanel();
		leftPanel = controlPanelView.getLeftPanel();
		rightPanel = controlPanelView.getRightPanel();

		initializeButtonListeners();

		dashboardView.setVisible(true);
		controlPanelView.setVisible(false);
	}

	private void initializeButtonListeners() {

		dashboardView.addRegisterButtonActionListener(e -> {
			registerView.clearRegisterTextFields();
			registerView.setVisible(true);
		});

		dashboardView.addLoginButtonActionListener(e -> {
			loginView.clearLoginTextFields();
			loginView.setVisible(true);
		});

		dashboardView.addTrackButtonActionListener(e -> {
			final String trackingCode = dashboardView.getTrackingCode();

			if (validateTrackingCode(trackingCode)) {

				trackingView.setVisible(true);

				String[] columnNames = { "Tip", "Data colectare", "Data transmitere", "Data avizare", "Data ridicare",
						"NP expeditor", "Id adresa expeditor", "NP destinatar", "Id adresa destinatar",
						"Tarif ridicare", "Cod tracking" };
				String[][] data = new String[1][columnNames.length];

				ArticolPostal articol = articolePostaleService.get(trackingCode);

				if (articol != null) {
					data[0][0] = articol.getTip();
					data[0][1] = articol.getDataColectare();
					data[0][2] = articol.getDataTransmitere();
					data[0][3] = articol.getDataAvizare();
					data[0][4] = articol.getDataRidicare();
					data[0][5] = articol.getNumeExpeditor() + " " + articol.getPrenumeExpeditor();
					data[0][6] = String.valueOf(articol.getIdAdresaExpeditor());
					data[0][7] = articol.getNumeDestinatar() + " " + articol.getPrenumeDestinatar();
					data[0][8] = String.valueOf(articol.getIdAdresaDestinatar());
					data[0][9] = String.valueOf(articol.getTarifRidicare());
					data[0][10] = articol.getCodTracking();
				}

				trackingView.setTableModel(data, columnNames);
			} else {
				dashboardView.displayMessage("Cod de urmarire invalid");
				System.out.println("Tracking code error!");
			}

		});

		loginView.addLoginButtonActionListener(e -> {
			final String username = loginView.getUsername();
			final String password = loginView.getPassword();
			final Utilizator user = utilizatoriService.get(username);

			if (validateLogin(username, password, user)) {
				loginView.setVisible(false);
				dashboardView.setVisible(false);
				controlPanelView.setVisible(true);
				setDisplayMode(true, "");

				currentUser = user;
				currentUserRole = getRole(user.getUsername());
				controlPanelView.setMode(currentUserRole);

				controlPanelView.setUsernameLabel(user.getNume().concat(" ").concat(user.getPrenume()));
				controlPanelView.setRoleLabel(currentUserRole);

				System.out.println("Logged in as '" + user.getUsername() + "'");
			} else {
				System.out.println("Login error!");
			}
		});

		loginView.addPasswordToggleButtonMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				loginView.hidePassword();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				loginView.displayPassword();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

		});

		registerView.addRegisterButtonActionListener(e -> {
			final Adresa adresa = new Adresa();

			final String strada = registerView.addresPanel.getStrada();
			final String numar = registerView.addresPanel.getNumar();
			final String bloc = registerView.addresPanel.getBloc();
			final String scara = registerView.addresPanel.getScara();
			final String apartament = registerView.addresPanel.getApartament();
			final String oras = registerView.addresPanel.getOras();
			final String judet = registerView.addresPanel.getJudet();
			final String codPostal = registerView.addresPanel.getCodPostal();

			adresa.setStrada(strada);
			adresa.setNumar(numar);
			adresa.setBloc(bloc);
			adresa.setScara(scara);
			adresa.setApartament(apartament);
			adresa.setOras(oras);
			adresa.setJudet(judet);
			adresa.setCodPostal(codPostal);
			adresa.setIdOficiuPostal(1);

			if (!validateAddress(strada, numar, bloc, scara, apartament, oras, judet, codPostal)) {
				System.out.println("Address register error!");
				return;
			}

			final Adresa adresaResult = adreseService.get(strada, numar, bloc, scara, apartament, oras, judet,
					codPostal);

			if (adresaResult == null) {
				adreseService.create(adresa);
				adresa.setId(adreseService.lastInsertedIndex());
				System.out.println("'" + adresa.getStatus() + "' succesfully registered!");
			} else {
				adresa.setId(adresaResult.getId());
			}

			final Utilizator user = new Utilizator();

			final String nume = registerView.getNume();
			final String prenume = registerView.getPrenume();
			final String email = registerView.getEmail();
			final String telefon = registerView.getTelefon();
			final String username = registerView.getUsername();
			final String parola = registerView.getPassword();
			final String confirmParola = registerView.getConfirmPassword();

			user.setNume(nume);
			user.setPrenume(prenume);
			user.setUsername(username);
			user.setEmail(email);
			user.setTelefon(telefon);
			user.setParola(parola);
			user.setIdAdresa(adresa.getId());

			if (validateRegister(nume, prenume, email, telefon, username, parola, confirmParola)) {
				utilizatoriService.create(user);
				System.out.println("'" + username + "' succesfully registered!");

				registerView.setVisible(false);
				dashboardView.clearLoginTextFields();
				dashboardView.setVisible(true);
			} else {
				System.out.println("User register error!");
			}
		});

		bottomPanel.addCancelButtonActionListener(e -> {
			// cancels the ongoing task
			int rowIndex = -1;
			switch (currentTask) {
			case "create":
				rowIndex = controlPanelView.getEditableRow();
				controlPanelView.setEditableRow(-1);
				controlPanelView.removeTableRow(rowIndex);
				bottomPanel.setCancelButtonVisible(false);
				bottomPanel.setSaveButtonVisible(false);
				currentTask = "";
				System.out.println("Canceled 'create'");
				leftPanel.setVisible(true);
				topPanel.setVisible(true);
				break;
			case "modify":
				rowIndex = controlPanelView.getEditableRow();
				controlPanelView.setEditableRow(-1);
				controlPanelView.updateTableRow(rowIndex, currentRowData);
				bottomPanel.setCancelButtonVisible(false);
				bottomPanel.setSaveButtonVisible(false);
				currentTask = "";
				System.out.println("Canceled 'modify'");
				leftPanel.setVisible(true);
				topPanel.setVisible(true);
				break;
			case "delete":
				rowIndex = controlPanelView.getEditableRow();
				controlPanelView.setEditableRow(-1);
				controlPanelView.addTableRow(rowIndex, currentRowData);
				bottomPanel.setCancelButtonVisible(false);
				bottomPanel.setSaveButtonVisible(false);
				currentTask = "";
				System.out.println("Canceled 'delete'");
				leftPanel.setVisible(true);
				topPanel.setVisible(true);
				break;
			}
		});

		bottomPanel.addCreateButtonActionListener(e -> {
			// insert the newly created data after checking for privileges
			// enable rightPanel fields

			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");

			if (currentSection.equals("magazie")) {
				addPostalItemController.setAddPostalItemVisible(true);
			} else if (currentTask.equals("")) {
				bottomPanel.setCancelButtonVisible(true);
				currentTask = "create";
				leftPanel.setVisible(false);
				topPanel.setVisible(false);
				int rowIndex = controlPanelView.getTableRowCount();
				if (!currentUserRole.equals("User")) {
					bottomPanel.setSaveButtonVisible(true);
					controlPanelView.addTableRow(null);
					controlPanelView.setEditableRow(rowIndex);
					controlPanelView.setTableSelection(rowIndex);
					System.out.println("Adding a new row: " + rowIndex);

					switch (currentSection) {
					case "angajati":
						controlPanelView.setValueAt(rowIndex, 1, df.format(new Date()));
						controlPanelView.setValueAt(rowIndex, 4, false);
						break;
					case "zile_de_lucru":
						controlPanelView.setValueAt(rowIndex, 0,
								String.valueOf(1 + zileDeLucruService.lastInsertedIndex()));
						break;
					case "oficii_postale":
						controlPanelView.setValueAt(rowIndex, 0,
								String.valueOf(1 + oficiiPostaleService.lastInsertedIndex()));
						break;
					case "utilizatori":
						controlPanelView.setValueAt(rowIndex, 0,
								String.valueOf(1 + utilizatoriService.lastInsertedIndex()));
						break;
					case "adrese":
						controlPanelView.setValueAt(rowIndex, 0, String.valueOf(1 + adreseService.lastInsertedIndex()));
						break;
					}

				}
			} else {
				controlPanelView.displayMessage("Alta operatiune in derulare");
			}

		});

		bottomPanel.addModifyButtonActionListener(e -> {
			// check if an entry is selected in the centerTable and then launch editView
			// depending on the type of the selected item
			// check for privileges
			if (currentTask.equals("")) {
				currentTask = "modify";

				int rowIndex = controlPanelView.getTableRowIndex();
				if (rowIndex != -1) {
					leftPanel.setVisible(false);
					topPanel.setVisible(false);
					bottomPanel.setSaveButtonVisible(true);
					currentRowData = controlPanelView.getTableRowData(rowIndex);
					bottomPanel.setCancelButtonVisible(true);
					System.out.println("Modifying row: " + rowIndex);
					if (!currentUserRole.equals("User")) {
						controlPanelView.setEditableRow(rowIndex);
					} else if (currentUserRole.equals("User") && currentSection.equals("utilizatori")) {
						controlPanelView.setEditableRow(rowIndex);
					}
				} else {
					currentTask = "";
					controlPanelView.displayMessage("Niciun rand selectat");
				}
			} else {
				controlPanelView.displayMessage("Alta operatiune in derulare");
			}

		});

		bottomPanel.addDeleteButtonActionListener(e -> {
			// check if an entry is selected in the centerTable and then remove it
			// check for privileges
			if (currentTask.equals("")) {
				currentTask = "delete";
				int rowIndex = controlPanelView.getTableRowIndex();
				System.out.println("Selecting this table row: " + rowIndex);
				controlPanelView.setEditableRow(rowIndex);
				if (!currentUserRole.equals("User")) {
					if (rowIndex != -1) {
						leftPanel.setVisible(false);
						topPanel.setVisible(false);
						bottomPanel.setSaveButtonVisible(true);
						bottomPanel.setCancelButtonVisible(true);
						currentRowData = controlPanelView.getTableRowData(rowIndex);
						System.out.println("Deleting row: " + rowIndex);
						controlPanelView.removeTableRow(rowIndex);
					} else {
						currentTask = "";
						controlPanelView.displayMessage("Niciun rand selectat");
					}
				}
			} else {
				controlPanelView.displayMessage("Alta operatiune in derulare");
			}

		});

		bottomPanel.addSaveButtonActionListener(e -> {
			// save data
			// submit it to the DB
			int rowIndex = controlPanelView.getEditableRow();

			if (currentTask.equals("create") || currentTask.equals("modify")) {
				currentRowData = controlPanelView.getTableRowData(rowIndex);
			}

			if (validateInputData(currentRowData) && submitDataToDatabase()) {
				bottomPanel.setCancelButtonVisible(false);
				bottomPanel.setSaveButtonVisible(false);
				currentTask = "";
				System.out.println("Saving changes done to row: " + rowIndex);

				for (int i = 0; i < currentRowData.length; i++) {
					System.out.print("[" + i + "]: " + currentRowData[i] + "  ");
				}
				System.out.println();
				controlPanelView.setEditableRow(-1);
				controlPanelView.setTableSelection(-1);
				leftPanel.setVisible(true);
				topPanel.setVisible(true);

				controlPanelView.setDisplayMessageColor(Color.GREEN);
				controlPanelView.displayMessage("Operatie reusita!");
			}
		});

		bottomPanel.addSendButtonActionListener(e -> {
			int rowIndex = controlPanelView.getTableRowIndex();
			controlPanelView.setEditableRow(rowIndex);
			if (rowIndex != -1) {
				int idArticolPostal = Integer.parseInt((String) controlPanelView.getTableRowData(rowIndex)[0]);

				ArticolPostal articol = articolePostaleService.get(idArticolPostal);
				if (articol.getDataTransmitere() != null) {
					controlPanelView.setDisplayMessageColor(Color.MAGENTA);
					controlPanelView.displayMessage("Articolul a ajuns deja la destinatie");
					return;
				}

				articolePostaleService.send(idArticolPostal);
				System.out.println("Articolul '" + idArticolPostal + "' -> TRANSMIS");
				controlPanelView.setTableSelection(-1);
				controlPanelView.setEditableRow(-1);

				loadStorageData(false);

				controlPanelView.setDisplayMessageColor(Color.GREEN);
				controlPanelView.displayMessage("Transmitere reusita");
			} else {
				controlPanelView.displayMessage("Niciun rand selectat");
			}
		});

		bottomPanel.addAvizareButtonActionListener(e -> {
			int rowIndex = controlPanelView.getTableRowIndex();
			controlPanelView.setEditableRow(rowIndex);
			if (rowIndex != -1) {

				int idArticolPostal = Integer.parseInt((String) controlPanelView.getTableRowData(rowIndex)[0]);
				ArticolPostal articol = articolePostaleService.get(idArticolPostal);

				if (articol.getDataTransmitere() == null) {
					System.out.println("Articolul nu a fost inca transmis!");

					controlPanelView.setDisplayMessageColor(Color.MAGENTA);
					controlPanelView.displayMessage("Articolul nu este inca transmis!");
					return;
				}

				articolePostaleService.avizare(idArticolPostal);
				System.out.println("Articolul '" + idArticolPostal + "' -> AVIZAT");
				controlPanelView.setTableSelection(-1);
				controlPanelView.setEditableRow(-1);

				loadStorageData(false);

				controlPanelView.setDisplayMessageColor(Color.GREEN);
				controlPanelView.displayMessage("Avizare reusita");
			} else {
				controlPanelView.displayMessage("Niciun rand selectat");
			}
		});

		bottomPanel.addRidicaButtonActionListener(e -> {
			int rowIndex = controlPanelView.getTableRowIndex();
			controlPanelView.setEditableRow(rowIndex);
			if (rowIndex != -1) {
				int idArticolPostal = Integer.parseInt((String) controlPanelView.getTableRowData(rowIndex)[0]);
				ArticolPostal articol = articolePostaleService.get(idArticolPostal);

				if (articol.getDataAvizare() == null) {
					System.out.println("Articolul trebuie mai intai avizat!");

					controlPanelView.setDisplayMessageColor(Color.MAGENTA);
					controlPanelView.displayMessage("Articolul nu este inca avizat!");
					return;
				}

				articolePostaleService.ridicare(idArticolPostal);
				System.out.println("Articolul '" + idArticolPostal + "' -> RIDICAT");
				controlPanelView.setTableSelection(-1);
				controlPanelView.setEditableRow(-1);

				loadStorageData(false);

				controlPanelView.setDisplayMessageColor(Color.GREEN);
				controlPanelView.displayMessage("Ridicare reusita");
			} else {
				controlPanelView.displayMessage("Niciun rand selectat");
			}
		});

		bottomPanel.addGenerateReportButtonActionListener(e -> {
			// open a window to select the desired export path
			// generate a pdf report with the data from centerTable

			String[] columnNames = { "Id", "Id\nentitate", "Id oficiu postal", "Tip inregistrare",
					"Data inregistrare" };
			int rowCount = controlPanelView.getTableRowCount();

			String[][] data = new String[rowCount][columnNames.length];

			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnNames.length; j++) {
					data[i][j] = (String) controlPanelView.getTableValueAt(i, j);
				}
			}

			float[] rowsWidths = { 15f, 25f, 25f, 70f, 60f };

			FileChooser fc = new FileChooser(controlPanelView);

			if (fc.showSaveDialog(controlPanelView) == JFileChooser.APPROVE_OPTION) {
				System.out.println("Current file = " + fc.getSelectedFile().toString());
				new PDFGenerator(fc.getSelectedFile().toString(), columnNames, data, rowsWidths,
						currentUser.getUsername(), currentUserRole);
			} else {
				System.out.println("No selection");
			}

		});

		topPanel.addHomeButtonActionListener(e -> {
			// return to initial dimensions of the centerPanel
			// clean the centerTable
			setDisplayMode(true, "");
			bottomPanel.setSaveButtonVisible(false);
		});

		topPanel.addStorageButtonActionListener(e -> {
			// display possible actions on the data listed in the table
			// adauga, transmite, avizeaza, ridica
			loadStorageData(false);
		});

		topPanel.addHelpButtonActionListener(e -> {
			helpView.setVisible(true);
		});

		topPanel.addLogsButtonActionListener(e -> {
			// disable rightPanel, extend centerPanel to the right margin
			// load centerTablePanel with logs data
			loadLogsData(false);
		});

		leftPanel.addOfficeDataButtonActionListener(e -> {
			// get Office data and display it in centerTable
			loadPostalOfficeData(false);
		});

		leftPanel.addEmployeesButtonActionListener(e -> {
			// get employees and display them in centerTable
			loadEmployeesData(false);
		});

		leftPanel.addUsersButtonActionListener(e -> {
			// get users and display them in centerTable
			loadUsersData(false);
		});

		leftPanel.addTrackingButtonActionListener(e -> {
			// display in rightPanel trackingCode text field
			setDisplayMode(false, "");
			bottomPanel.setVisible(false);
		});

		leftPanel.addPersonalDataButtonActionListener(e -> {
			// display personal data in centerTable
			loadPersonalData();
		});

		leftPanel.addTariffsButtonActionListener(e -> {
			// display tariffs in centerTable
			loadTariffsData(false);
		});

		leftPanel.addPostalItemsButtonActionListener(e -> {
			// display history of sent and received postal items of the current user
			loadPostalItemsData(false);
		});

		leftPanel.addWorkingDaysButtonActionListener(e -> {
			// display working days in centerTable
			loadWorkingDaysData(false);
		});

		leftPanel.addAddressesButtonActionListener(e -> {
			// display addresses in centerTable
			loadAddressesData(false);
		});

		leftPanel.addLogoutButtonActionListener(e -> {
			// close controlPanelView, clear loginView fields, clear controlPanelView fields
			loginView.clearLoginTextFields();
			controlPanelView.setVisible(false);
			dashboardView.setVisible(true);
			currentUser = null;
			currentUserRole = "";
			currentSection = "";
			currentTask = "";
			MySqlCredentials.uid = "guest";
			MySqlCredentials.pwd = "";
		});

		rightPanel.addTrackButtonActionListener(e -> {
			// validate tracking code
			// display tracking data in centerTable
			loadTrackingData();
		});

		searchPanel.addSearchButtonActionListener(e -> {
			// search for data and display in centerTable using the applied filters

			final String sortBy = searchPanel.getSelectedItemSortByComboBox();
			final String searchBy = searchPanel.getSelectedItemSearchByComboBox();
			final String search = searchPanel.getSearchTextField();

			switch (currentSection) {
			case "adrese":
				loadAddressesData(true, sortBy, searchBy, search);
				break;
			case "zile_de_lucru":
				loadWorkingDaysData(true, sortBy, searchBy, search);
				break;
			case "tarife":
				loadTariffsData(true, sortBy, searchBy, search);
				break;
			case "articole_postale":
				loadPostalItemsData(true, sortBy, searchBy, search);
				break;
			case "utilizatori":
				loadUsersData(true, sortBy, searchBy, search);
				break;
			case "angajati":
				loadEmployeesData(true, sortBy, searchBy, search);
				break;
			case "oficii_postale":
				loadPostalOfficeData(true, sortBy, searchBy, search);
				break;
			case "magazie":
				loadStorageData(true, sortBy, searchBy, search);
				break;
			case "inregistrari":
				loadLogsData(true, sortBy, searchBy, search);
				break;
			}

		});

		addPostalItemController.addAddPostalItemViewActionListener(new WindowListener() {

			boolean voluntarilyClosed = false;

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				voluntarilyClosed = true;
			}

			@Override
			public void windowClosed(WindowEvent e) {
				voluntarilyClosed = true;
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				if (voluntarilyClosed == false) {
					controlPanelView.setDisplayMessageColor(Color.GREEN);
					controlPanelView.displayMessage("Inregistrare reusita!");

					loadStorageData(false);
				}
			}

		});

	}

	private boolean submitDataToDatabase() {

		Object[] rowData = null;

		if (!currentTask.equals("delete")) {
			rowData = controlPanelView.getTableRowData(controlPanelView.getEditableRow());
		} else {
			rowData = currentRowData;
		}

		switch (currentSection) {

		case "adrese":
			Adresa adresa = new Adresa();

			adresa.setId(Integer.parseInt((String) rowData[0]));
			adresa.setStrada((String) rowData[1]);
			adresa.setNumar((String) rowData[2]);
			adresa.setBloc((String) rowData[3]);
			adresa.setScara((String) rowData[4]);
			adresa.setApartament((String) rowData[5]);
			adresa.setOras((String) rowData[6]);
			adresa.setJudet((String) rowData[7]);
			adresa.setCodPostal((String) rowData[8]);
			adresa.setIdOficiuPostal(Integer.parseInt((String) rowData[9]));

			Adresa adresaResult = adreseService.get(adresa.getStrada(), adresa.getNumar(), adresa.getBloc(),
					adresa.getScara(), adresa.getApartament(), adresa.getOras(), adresa.getJudet(),
					adresa.getCodPostal());

			switch (currentTask) {
			case "create":
				if (adresaResult == null) {
					currentRowData = rowData;
					adreseService.create(adresa);
					return true;
				} else {
					controlPanelView.displayMessage("Adresa deja existenta");
					return false;
				}
			case "modify":
				adreseService.update(adresa);
				return true;
			case "delete":
				if (adresaResult != null) {
					adresa.setId(adresaResult.getId());
					adreseService.remove(adresa.getId());
					return true;
				}
				return false;
			}

			break;

		case "angajati":
			Angajat angajat = new Angajat();

			angajat.setUsername((String) rowData[0]);
			angajat.setDataAngajare((String) rowData[1]);
			angajat.setIdOficiuPostal(Integer.parseInt((String) rowData[2]));
			angajat.setSalariu(Float.parseFloat((String) rowData[3]));
			angajat.setAdministrator((boolean) rowData[4]);

			Angajat angajatResult = angajatiService.get(angajat.getUsername());

			switch (currentTask) {
			case "create":
				if (angajatResult == null) {
					currentRowData = rowData;
					angajatiService.create(angajat);
					return true;
				} else {
					controlPanelView.displayMessage("Angajat deja existent");
					return false;
				}
			case "modify":
				if (angajatResult != null) {
					angajatiService.update(angajat);
					return true;
				}
				return false;
			case "delete":
				if (angajatResult != null) {
					angajatiService.remove(angajat.getUsername());
					return true;
				}
				return false;
			}

			break;

		case "articole_postale":
			ArticolPostal articolPostal = new ArticolPostal();

			String[] npExpeditor = ((String) rowData[6]).split(" ");
			String[] npDestinatar = ((String) rowData[8]).split(" ");

			articolPostal.setId(Integer.parseInt((String) rowData[0]));
			articolPostal.setTip((String) rowData[1]);
			articolPostal.setDataColectare((String) rowData[2]);
			articolPostal.setDataTransmitere((String) rowData[3]);
			articolPostal.setDataAvizare((String) rowData[4]);
			articolPostal.setDataRidicare((String) rowData[5]);
			articolPostal.setNumeExpeditor(npExpeditor[0]);
			articolPostal.setPrenumeExpeditor(npExpeditor[1]);
			articolPostal.setIdAdresaExpeditor(Integer.parseInt((String) rowData[7]));
			articolPostal.setNumeDestinatar(npDestinatar[0]);
			articolPostal.setPrenumeDestinatar(npDestinatar[1]);
			articolPostal.setIdAdresaDestinatar(Integer.parseInt((String) rowData[9]));
			articolPostal.setTarifExpediere(Float.parseFloat((String) rowData[10]));
			articolPostal.setTarifRidicare(Float.parseFloat((String) rowData[11]));
			articolPostal.setCodTracking((String) rowData[12]);

			ArticolPostal articolPostalResult = articolePostaleService.get(articolPostal.getId());

			switch (currentTask) {
			case "create":
				if (articolPostalResult == null) {
					currentRowData = rowData;
					articolePostaleService.create(articolPostal);
					return true;
				} else {
					controlPanelView.displayMessage("Utilizator deja existent");
					return false;
				}
			case "modify":
				if (articolPostalResult != null) {
					articolPostal.setId(articolPostalResult.getId());
					articolePostaleService.update(articolPostal);
					return true;
				}
				return false;
			case "delete":
				if (articolPostalResult != null) {
					articolPostal.setId(articolPostalResult.getId());
					articolePostaleService.remove(articolPostal.getId());
					return true;
				}
				return false;
			}

			break;

		case "utilizatori":
			Utilizator utilizator = new Utilizator();

			utilizator.setId(Integer.parseInt((String) rowData[0]));
			utilizator.setNume((String) rowData[1]);
			utilizator.setPrenume((String) rowData[2]);
			utilizator.setUsername((String) rowData[3]);
			utilizator.setParola((String) rowData[4]);
			utilizator.setEmail((String) rowData[5]);
			utilizator.setTelefon((String) rowData[6]);
			utilizator.setIdAdresa(Integer.parseInt((String) rowData[7]));

			Utilizator utilizatorResult = utilizatoriService.get(utilizator.getUsername());

			switch (currentTask) {
			case "create":
				if (utilizatorResult == null) {
					currentRowData = rowData;
					utilizatoriService.create(utilizator);
					return true;
				} else {
					controlPanelView.displayMessage("Utilizator deja existent");
					return false;
				}
			case "modify":
				if (utilizatorResult != null) {
					utilizatoriService.update(utilizator);
					return true;
				}
				return false;
			case "delete":
				if (utilizatorResult != null) {
					utilizatoriService.remove(utilizator.getUsername());
					return true;
				}
				return false;
			}

			break;

		case "oficii_postale":
			OficiuPostal oficiuPostal = new OficiuPostal();

			oficiuPostal.setId(Integer.parseInt((String) rowData[0]));
			oficiuPostal.setDenumire((String) rowData[1]);
			oficiuPostal.setTelefon((String) rowData[2]);

			OficiuPostal oficiuPostalResult = oficiiPostaleService.get(oficiuPostal.getId());

			switch (currentTask) {
			case "create":
				if (oficiuPostalResult == null) {
					currentRowData = rowData;
					oficiiPostaleService.create(oficiuPostal);
					return true;
				} else {
					controlPanelView.displayMessage("Oficiu postal deja existent");
					return false;
				}
			case "modify":
				if (oficiuPostalResult != null) {
					oficiiPostaleService.update(oficiuPostal);
					return true;
				}
				return false;
			case "delete":
				if (oficiuPostalResult != null) {
					oficiiPostaleService.remove(oficiuPostal.getId());
					return true;
				}
				return false;
			}

			break;

		case "tarife":

			Tarif tarif = new Tarif();

			tarif.setTipArticolPostal((String) rowData[0]);
			tarif.setDescriere((String) rowData[1]);
			tarif.setValoare(Float.parseFloat((String) rowData[2]));

			Tarif tarifResult = tarifeService.get(tarif.getTipArticolPostal(), tarif.getDescriere());

			switch (currentTask) {
			case "create":
				if (tarifResult == null) {
					currentRowData = rowData;
					tarifeService.create(tarif);
					return true;
				} else {
					controlPanelView.displayMessage("Tarif deja existent");
					return false;
				}
			case "modify":
				tarifeService.update(tarif);
				return true;
			case "delete":
				if (tarifResult != null) {
					tarifeService.remove(tarif.getDescriere(), tarif.getTipArticolPostal());
					return true;
				}
				return false;
			}

			break;

		case "zile_de_lucru":
			ZiDeLucru ziDeLucru = new ZiDeLucru();

			ziDeLucru.setIdOficiuPostal(Integer.parseInt((String) rowData[0]));
			ziDeLucru.setZiuaSaptamanii((String) rowData[1]);
			ziDeLucru.setOraDeschidere(Integer.parseInt((String) rowData[2]));
			ziDeLucru.setOraInchidere(Integer.parseInt((String) rowData[3]));

			ZiDeLucru ziDeLucruResult = zileDeLucruService.get(ziDeLucru.getIdOficiuPostal(),
					ziDeLucru.getZiuaSaptamanii());

			switch (currentTask) {
			case "create":
				if (ziDeLucruResult == null) {
					currentRowData = rowData;
					zileDeLucruService.create(ziDeLucru);
					return true;
				} else {
					controlPanelView.displayMessage("Orar de lucru deja existent");
					return false;
				}
			case "modify":
				zileDeLucruService.update(ziDeLucru);
				return true;
			case "delete":
				if (ziDeLucruResult != null) {
					zileDeLucruService.remove(ziDeLucruResult);
					return true;
				}
				return false;
			}

			break;

		}
		return false;
	}

	private void setSearchByItems(String[] columnNames) {
		for (int i = 0; i < columnNames.length; i++) {
			searchPanel.addSearchByComboBoxItem(columnNames[i]);
		}
	}

	private void setDisplayMode(boolean extended, String currentSection) {
		if (extended == true) {
			controlPanelView.setExtendedCenterPanelMode();
		} else {
			controlPanelView.setShrinkedCenterPanelMode();
		}

		this.currentSection = currentSection;

		if (!currentUserRole.equals("User") && !currentSection.equals("inregistrari")) {
			bottomPanel.setGenerateReportButtonVisible(false);
		}

		if (!currentUserRole.equals("User") && !currentSection.equals("")) {
			bottomPanel.setSaveButtonVisible(false);
			bottomPanel.setCreateButtonVisible(true);
			bottomPanel.setModifyButtonVisible(true);
			bottomPanel.setDeleteButtonVisible(true);
			bottomPanel.setSendButtonVisible(false);
			bottomPanel.setAvizareButtonVisible(false);
			bottomPanel.setRidicaButtonVisible(false);
			bottomPanel.setGenerateReportButtonVisible(false);

			bottomPanel.setVisible(true);
		} else if (currentUserRole.equals("User") && currentSection.equals("utilizatori")) {
			bottomPanel.setSaveButtonVisible(false);
			bottomPanel.setCreateButtonVisible(false);
			bottomPanel.setModifyButtonVisible(true);
			bottomPanel.setDeleteButtonVisible(false);
			bottomPanel.setSendButtonVisible(false);
			bottomPanel.setAvizareButtonVisible(false);
			bottomPanel.setRidicaButtonVisible(false);
			bottomPanel.setGenerateReportButtonVisible(false);

			bottomPanel.setVisible(true);
		}

		if (currentSection.equals("")) {
			bottomPanel.setVisible(false);
		}

		controlPanelView.setTableModel(null, null);
		searchPanel.removeAllSearchByComboBoxItems();
	}

	private String getRole(String username) {
		// search user in employees --> Employee
		// check if employeeId matched oficiuPostal.idAdministrator --> Administrator
		// else --> User

		Angajat angajat = angajatiService.get(username);

		if (angajat != null) {
			if (angajat.isAdministrator()) {
				return "Administrator";
			} else {
				return "Employee";
			}
		}
		return "User";
	}

	private boolean validateTrackingCode(String codTracking) {

		if (codTracking.length() != 10) {
			dashboardView.displayMessage("Cod de urmarire invalid");
			controlPanelView.displayMessage("Cod de urmarire invalid");
			return false;
		}

		String pattern = "^[A-Z0-9]{10}$";
		if (!codTracking.matches(pattern)) {
			dashboardView.displayMessage("Formatul codului de urmarire este invalid");
			controlPanelView.displayMessage("Formatul codului de urmarire este invalid");
			return false;
		}

		return true;
	}

	private boolean validateRegister(String nume, String prenume, String email, String telefon, String username,
			String parola, String confirmParola) {

		final Utilizator user = utilizatoriService.get(username);

		if (nume.equals("") || prenume.equals("") || email.equals("") || telefon.equals("") || username.equals("")
				|| parola.equals("") || confirmParola.equals("")) {
			registerView.displayMessage("Date utilizator invalide");
			registerView.clearUserTextFields();
			return false;
		}

		if (!validateEmailAddress(email)) {
			registerView.displayMessage("Format email invalid");
			registerView.setEmail("");
			return false;
		}

		if (user != null) {
			registerView.displayMessage("Utilizatorul este deja existent");
			registerView.setUsername("");
			return false;
		}

		if (!parola.equals(confirmParola)) {
			registerView.displayMessage("Parolele nu se potrivesc");
			registerView.setPassword("");
			registerView.setConfirmPassword("");
			return false;
		}

		return true;
	}

	private boolean validateAddress(String strada, String numar, String bloc, String scara, String apartament,
			String oras, String judet, String codPostal) {

		if (strada.equals("") || numar.equals("") || oras.equals("") || judet.equals("") || codPostal.equals("")) {
			registerView.displayMessage("Adresa invalida");
			registerView.addresPanel.clearTextFields();
			return false;
		}

		return true;
	}

	private boolean validateLogin(String username, String password, Utilizator user) {

		if (user == null || password.equals("") || username.equals("") || !user.getUsername().equals(username)
				|| !user.getParola().equals(password)) {
			loginView.displayMessage("Username sau parola invalide");
			return false;
		}

		return true;
	}

	private boolean validateEmailAddress(String email) {
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (!email.matches(emailPattern)) {
			return false;
		}

		return true;
	}

	private boolean validateInputData(Object[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				controlPanelView.displayMessage("Date de intrare invalide");
				return false;
			}
		}
		return true;
	}

	private void loadTrackingData() {
		controlPanelView.setTableModel(null, null);
		currentSection = "";

		String[] columnNames = { "Data colectare", "Data transmitere", "Data avizare", "Data ridicare" };
		String[][] data = new String[1][columnNames.length];

		String trackingCode = rightPanel.getTrackingCode();

		if (validateTrackingCode(trackingCode)) {

			ArticolPostal articol = articolePostaleService.get(trackingCode);

			if (articol != null) {

				controlPanelView.setTrackingDataLabel(articol.getNumeExpeditor() + " " + articol.getPrenumeExpeditor()
						+ " <-> " + articol.getNumeDestinatar() + " " + articol.getPrenumeDestinatar() + " : "
						+ articol.getTip());

				data[0][0] = articol.getDataColectare() != null ? articol.getDataColectare() : "";
				data[0][1] = articol.getDataTransmitere() != null ? articol.getDataTransmitere() : "";
				data[0][2] = articol.getDataAvizare() != null ? articol.getDataAvizare() : "";
				data[0][3] = articol.getDataRidicare() != null ? articol.getDataRidicare() : "";
			}

			controlPanelView.setTableModel(data, columnNames);
			setSearchByItems(columnNames);
		}
	}

	private void loadAddressesData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "adrese");

		List<Adresa> adrese = null;

		if (!isSorted) {
			adrese = adreseService.getAll();
		} else {
			adrese = adreseService.getSorted(searchParams);
		}

		String[] columnNames = { "Id", "Strada", "Numar", "Bloc", "Scara", "Apartament", "Oras", "Judet", "Cod postal",
				"Id oficiu postal" };
		String[][] data = null;

		if (adrese != null) {
			data = new String[adrese.size()][columnNames.length];

			int i = 0;
			Iterator<Adresa> it = adrese.iterator();
			while (it.hasNext()) {
				Adresa adresa = it.next();
				data[i][0] = String.valueOf(adresa.getId());
				data[i][1] = adresa.getStrada();
				data[i][2] = String.valueOf(adresa.getNumar());
				data[i][3] = adresa.getBloc();
				data[i][4] = adresa.getScara();
				data[i][5] = adresa.getApartament();
				data[i][6] = adresa.getOras();
				data[i][7] = adresa.getJudet();
				data[i][8] = adresa.getCodPostal();
				data[i++][9] = String.valueOf(adresa.getIdOficiuPostal());
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadWorkingDaysData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "zile_de_lucru");

		List<ZiDeLucru> orar = null;

		if (!isSorted) {
			orar = zileDeLucruService.getAll();
		} else {
			orar = zileDeLucruService.getSorted(searchParams);
		}

		String[] columnNames = { "Id oficiu postal", "Ziua saptamanii", "Ora deschidere", "Ora inchidere" };
		String[][] data = null;

		if (orar != null) {
			data = new String[orar.size()][columnNames.length];

			int i = 0;
			Iterator<ZiDeLucru> it = orar.iterator();
			while (it.hasNext()) {
				ZiDeLucru ziDeLucru = it.next();
				data[i][0] = String.valueOf(ziDeLucru.getIdOficiuPostal());
				data[i][1] = ziDeLucru.getZiuaSaptamanii();
				data[i][2] = String.valueOf(ziDeLucru.getOraDeschidere());
				data[i++][3] = String.valueOf(ziDeLucru.getOraInchidere());
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadPostalItemsData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "articole_postale");
		bottomPanel.setVisible(false);

		List<ArticolPostal> articole = null;

		if (currentUserRole.equals("User")) {
			articole = articolePostaleService.getUserHistory(currentUser.getNume(), currentUser.getPrenume());
		} else {
			if (!isSorted) {
				articole = articolePostaleService.getAll();
			} else {
				articole = articolePostaleService.getSorted(searchParams);
			}
		}

		String[] columnNames = { "Id", "Tip", "Data colectare", "Data transmitere", "Data avizare", "Data ridicare",
				"NP expeditor", "Id adresa expeditor", "NP destinatar", "Id adresa destinatar", "Tarif expediere",
				"Tarif ridicare", "Cod tracking" };
		String[][] data = null;

		if (articole != null) {
			data = new String[articole.size()][columnNames.length];

			int i = 0;
			Iterator<ArticolPostal> it = articole.iterator();
			while (it.hasNext()) {
				ArticolPostal articol = it.next();

				data[i][0] = String.valueOf(articol.getId());
				data[i][1] = articol.getTip();
				data[i][2] = articol.getDataColectare();
				data[i][3] = articol.getDataTransmitere();
				data[i][4] = articol.getDataAvizare();
				data[i][5] = articol.getDataRidicare();
				data[i][6] = articol.getNumeExpeditor() + " " + articol.getPrenumeExpeditor();
				data[i][7] = String.valueOf(articol.getIdAdresaExpeditor());
				data[i][8] = articol.getNumeDestinatar() + " " + articol.getPrenumeDestinatar();
				data[i][9] = String.valueOf(articol.getIdAdresaDestinatar());
				data[i][10] = String.valueOf(articol.getTarifExpediere());
				data[i][11] = String.valueOf(articol.getTarifRidicare());
				data[i++][12] = articol.getCodTracking();

			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadTariffsData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "tarife");

		List<Tarif> tarife = null;

		if (!isSorted) {
			tarife = tarifeService.getAll();
		} else {
			tarife = tarifeService.getSorted(searchParams);
		}

		String[] columnNames = { "Tip articol postal", "Descriere", "Valoare" };
		String[][] data = null;

		if (tarife != null) {
			data = new String[tarife.size()][columnNames.length];

			int i = 0;
			Iterator<Tarif> it = tarife.iterator();
			while (it.hasNext()) {
				Tarif tarif = it.next();
				data[i][0] = tarif.getTipArticolPostal();
				data[i][1] = tarif.getDescriere();
				data[i++][2] = String.valueOf(tarif.getValoare());
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadPersonalData() {
		setDisplayMode(true, "utilizatori");
		bottomPanel.setCreateButtonVisible(false);
		bottomPanel.setDeleteButtonVisible(false);
		searchPanel.setVisible(false);

		String[] columnNames = new String[15];
		String[][] data = new String[1][15];

		if (!currentUserRole.equals("User")) {
			String[] columnNamesVar = { "Id", "Nume", "Prenume", "Username", "Parola", "Email", "Telefon", "Id adresa",
					"Id oficiu postal", "Salariu", "Data angajare", "Administrator" };
			columnNames = columnNamesVar;

			Angajat angajat = angajatiService.getPersonalData(currentUser.getUsername());

			if (angajat != null) {
				data[0][0] = String.valueOf(angajat.getId());
				data[0][1] = angajat.getNume();
				data[0][2] = angajat.getPrenume();
				data[0][3] = angajat.getUsername();
				data[0][4] = angajat.getParola();
				data[0][5] = angajat.getEmail();
				data[0][6] = angajat.getTelefon();
				data[0][7] = String.valueOf(angajat.getIdAdresa());
				data[0][8] = String.valueOf(angajat.getIdOficiuPostal());
				data[0][9] = String.valueOf(angajat.getSalariu());
				data[0][10] = angajat.getDataAngajare();
				data[0][11] = String.valueOf(angajat.isAdministrator());
			}

		} else {
			String[] columnNamesVar = { "Id", "Nume", "Prenume", "Username", "Parola", "Email", "Telefon",
					"Id adresa" };
			columnNames = columnNamesVar;

			Utilizator user = utilizatoriService.get(currentUser.getUsername());

			if (user != null) {
				data[0][0] = String.valueOf(user.getId());
				data[0][1] = user.getNume();
				data[0][2] = user.getPrenume();
				data[0][3] = user.getUsername();
				data[0][4] = user.getParola();
				data[0][5] = user.getEmail();
				data[0][6] = user.getTelefon();
				data[0][7] = String.valueOf(user.getIdAdresa());
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadEmployeesData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "angajati");

		List<Angajat> angajati = null;

		if (!isSorted) {
			angajati = angajatiService.getAll();
		} else {
			angajati = angajatiService.getSorted(searchParams);
		}

		String[] columnNames = { "Username", "Data angajare", "Id oficiu postal", "Salariu", "Administrator" };
		Object[][] data = null;

		if (angajati != null) {
			data = new Object[angajati.size()][columnNames.length];

			int i = 0;
			Iterator<Angajat> it = angajati.iterator();
			while (it.hasNext()) {
				Angajat angajat = it.next();
				data[i][0] = angajat.getUsername();
				data[i][1] = angajat.getDataAngajare().toString();
				data[i][2] = String.valueOf(angajat.getIdOficiuPostal());
				data[i][3] = String.valueOf(angajat.getSalariu());
				data[i++][4] = angajat.isAdministrator();
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadUsersData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "utilizatori");

		List<Utilizator> users = null;

		if (!isSorted) {
			users = utilizatoriService.getAll();
		} else {
			users = utilizatoriService.getSorted(searchParams);
		}

		String[] columnNames = { "Id", "Nume", "Prenume", "Username", "Parola", "Email", "Telefon", "Id adresa" };
		String[][] data = null;

		if (users != null) {
			data = new String[users.size()][columnNames.length];

			int i = 0;
			Iterator<Utilizator> it = users.iterator();
			while (it.hasNext()) {
				Utilizator user = it.next();
				data[i][0] = String.valueOf(user.getId());
				data[i][1] = user.getNume();
				data[i][2] = user.getPrenume();
				data[i][3] = user.getUsername();
				data[i][4] = user.getParola();
				data[i][5] = user.getEmail();
				data[i][6] = user.getTelefon();
				data[i++][7] = String.valueOf(user.getIdAdresa());
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadLogsData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "inregistrari");
		bottomPanel.setModifyButtonVisible(false);
		bottomPanel.setCreateButtonVisible(false);
		bottomPanel.setDeleteButtonVisible(false);
		bottomPanel.setSendButtonVisible(false);
		bottomPanel.setAvizareButtonVisible(false);
		bottomPanel.setRidicaButtonVisible(false);
		bottomPanel.setGenerateReportButtonVisible(true);

		List<Inregistrare> logs = null;

		if (!isSorted) {
			logs = inregistrariService.getAll();
		} else {
			logs = inregistrariService.getSorted(searchParams);
		}

		String[] columnNames = { "Id", "Id entitate", "Id oficiu postal", "Tip inregistrare", "Data inregistrare" };
		String[][] data = null;

		if (logs != null) {
			data = new String[logs.size()][columnNames.length];

			int i = 0;
			Iterator<Inregistrare> it = logs.iterator();
			while (it.hasNext()) {
				Inregistrare log = it.next();
				data[i][0] = String.valueOf(log.getId());
				data[i][1] = String.valueOf(log.getIdEntitate());
				data[i][2] = String.valueOf(log.getIdOficiuPostal());
				data[i][3] = log.getTipInregistrare();
				data[i++][4] = log.getDataInregistrare();
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadPostalOfficeData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "oficii_postale");

		List<OficiuPostal> oficiiPostale = null;

		if (!isSorted) {
			oficiiPostale = oficiiPostaleService.getAll();
		} else {
			oficiiPostale = oficiiPostaleService.getSorted(searchParams);
		}

		String[] columnNames = { "Id", "Denumire", "Telefon" };
		String[][] data = null;

		if (oficiiPostale != null) {
			data = new String[oficiiPostale.size()][columnNames.length];

			int i = 0;
			Iterator<OficiuPostal> it = oficiiPostale.iterator();
			while (it.hasNext()) {
				OficiuPostal op = it.next();
				data[i][0] = String.valueOf(op.getId());
				data[i][1] = op.getDenumire();
				data[i++][2] = op.getTelefon();
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	private void loadStorageData(final boolean isSorted, final String... searchParams) {
		setDisplayMode(true, "magazie");

		bottomPanel.setModifyButtonVisible(false);
		bottomPanel.setDeleteButtonVisible(false);
		bottomPanel.setSendButtonVisible(true);
		bottomPanel.setAvizareButtonVisible(true);
		bottomPanel.setRidicaButtonVisible(true);

		int idOficiuPostal = angajatiService.get(currentUser.getUsername()).getIdOficiuPostal();

		List<ArticolPostal> magazieArticole = null;

		if (!isSorted) {
			magazieArticole = magazieService.getAll(idOficiuPostal);
		} else {
			magazieArticole = magazieService.getSorted(idOficiuPostal, searchParams);
		}

		String[] columnNames = { "Id articol postal", "Tip", "Data colectare", "Data transmitere", "Data avizare" };
		String[][] data = null;

		if (magazieArticole != null) {
			data = new String[magazieArticole.size()][columnNames.length];

			int i = 0;
			Iterator<ArticolPostal> it = magazieArticole.iterator();
			while (it.hasNext()) {
				ArticolPostal articol = it.next();
				data[i][0] = String.valueOf(articol.getId());
				data[i][1] = articol.getTip();
				data[i][2] = articol.getDataColectare();
				data[i][3] = articol.getDataTransmitere();
				data[i++][4] = articol.getDataAvizare();
			}
		}

		controlPanelView.setTableModel(data, columnNames);
		setSearchByItems(columnNames);
	}

	public String getCurrentUserRole() {
		return this.currentUserRole;
	}
}
