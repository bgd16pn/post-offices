DROP SCHEMA IF EXISTS sgbd_oficii_postale;
CREATE DATABASE sgbd_oficii_postale;
USE sgbd_oficii_postale;


# CREARE TABELE
# ##################################################################
CREATE TABLE articole_postale (
    id INT PRIMARY KEY AUTO_INCREMENT,	
    tip ENUM('Colet', 'Scrisoare', 'Mandat') NOT NULL,
    data_colectare DATETIME NOT NULL DEFAULT NOW(),
    data_transmitere DATETIME,
    data_ridicare DATETIME,
	data_avizare DATETIME,
    nume_expeditor VARCHAR(50) NOT NULL,
    prenume_expeditor VARCHAR(50) NOT NULL,
    id_adresa_expeditor INT NOT NULL,
    nume_destinatar VARCHAR(50) NOT NULL,
    prenume_destinatar VARCHAR(50) NOT NULL,
    id_adresa_destinatar INT NOT NULL,
    tarif_expediere DOUBLE DEFAULT 0 CHECK(tarif_expediere > 0),
    tarif_ridicare DOUBLE DEFAULT 0 CHECK(tarif_ridicare > 0),
	cod_tracking VARCHAR(10) DEFAULT NULL CHECK(length(cod_tracking) = 10)
);
	
CREATE TABLE colete(
    id_articol_postal INT PRIMARY KEY,
	valoare_ramburs DOUBLE DEFAULT 0 CHECK(valoare_ramburs > 0),
    valoare_declarata DOUBLE NOT NULL DEFAULT 20 CHECK(valoare_declarata > 0),
    greutate DOUBLE NOT NULL DEFAULT 1 CHECK(greutate > 0),
    tracked BOOL NOT NULL DEFAULT FALSE,
    fragil BOOL NOT NULL DEFAULT FALSE,
    voluminos BOOL NOT NULL DEFAULT FALSE,
    livrare_sambata BOOL NOT NULL DEFAULT FALSE,
    FOREIGN KEY FK_colet_id_articol(id_articol_postal) REFERENCES articole_postale(id)
);

CREATE TABLE scrisori(
    id_articol_postal INT PRIMARY KEY,
    valoare_ramburs DOUBLE DEFAULT 0 CHECK(valoare_ramburs > 0),
    valoare_declarata DOUBLE NOT NULL DEFAULT 0 CHECK(valoare_declarata > 0),
	greutate DOUBLE NOT NULL DEFAULT 10 CHECK(greutate > 0),
    confirmare_primire BOOL NOT NULL DEFAULT FALSE,
    tracked BOOL NOT NULL DEFAULT FALSE,
    FOREIGN KEY FK_scrisoare_id_articol(id_articol_postal) REFERENCES articole_postale(id)
);

CREATE TABLE mandate(
    id_articol_postal INT PRIMARY KEY,
    valoare DOUBLE NOT NULL DEFAULT 0 CHECK(valoare > 0),
    detalii varchar(50) DEFAULT NULL CHECK(length(detalii) <= 50),
    FOREIGN KEY FK_mandate_id_articol(id_articol_postal) REFERENCES articole_postale(id)
);

CREATE TABLE utilizatori(
	id INT UNIQUE NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) PRIMARY KEY,
    parola VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
    telefon VARCHAR(10) NOT NULL UNIQUE CHECK(length(telefon) = 10),
    nume VARCHAR(50) NOT NULL,
    prenume VARCHAR(50) NOT NULL,
    id_adresa INT NOT NULL
);

CREATE TABLE angajati(
	username_utilizator VARCHAR(50) PRIMARY KEY,
	id_oficiu_postal INT NOT NULL,
    administrator BOOL NOT NULL,
    salariu DOUBLE NOT NULL DEFAULT 1 CHECK(salariu > 0),
    data_angajare DATE NOT NULL,
    FOREIGN KEY FK_angajat_username_utilizator(username_utilizator) REFERENCES utilizatori(username)
);

CREATE TABLE tarife(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	tip_articol_postal ENUM('Colet', 'Scrisoare', 'Mandat') NOT NULL,
    descriere VARCHAR(50) NOT NULL,
    valoare DOUBLE NOT NULL CHECK(valoare > 0),
    PRIMARY KEY(tip_articol_postal, descriere)
);

CREATE TABLE inregistrari(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_entitate INT NOT NULL,
    id_oficiu_postal INT,
	tip_inregistrare VARCHAR(50) NOT NULL,
    data_inregistrare DATETIME NOT NULL DEFAULT NOW()
);

CREATE TABLE magazie(
	id_articol_postal INT PRIMARY KEY,
    id_oficiu_postal INT NOT NULL,
    FOREIGN KEY FK_magazie_id_articol(id_articol_postal) REFERENCES articole_postale(id)
);

CREATE TABLE adrese(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    strada VARCHAR(50) NOT NULL,
    numar VARCHAR(3) NOT NULL,
    bloc VARCHAR(3)	,
    scara VARCHAR(3),
    apartament VARCHAR(3),
    oras VARCHAR(30) NOT NULL,
    judet VARCHAR(30) NOT NULL,
    cod_postal VARCHAR(6) NOT NULL CHECK(length(cod_postal) = 6),
    id_oficiu_postal INT NOT NULL,
    PRIMARY KEY(strada, numar, bloc, scara, apartament, oras, judet, cod_postal)
);

CREATE TABLE zile_de_lucru(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	id_oficiu_postal INT NOT NULL,
    ziua_saptamanii ENUM('Luni', 'Marti', 'Miercuri', 'Joi', 'Vineri', 'Sambata') NOT NULL,
    ora_deschidere INT NOT NULL CHECK(ora_deschidere >= 7 AND ora_deschidere <= 10),
    ora_inchidere INT NOT NULL CHECK(ora_inchidere >= 14 AND ora_inchidere <= 20),
    PRIMARY KEY(id_oficiu_postal, ziua_saptamanii)
);

CREATE TABLE oficii_postale(
	id INT PRIMARY KEY AUTO_INCREMENT,
    denumire VARCHAR(10) NOT NULL,
    telefon VARCHAR(10) NOT NULL UNIQUE CHECK(length(telefon) = 10)
);

# CREARE CHEI STRAINE
# ##################################################################
ALTER TABLE utilizatori ADD FOREIGN KEY FK_utilizator_id_adresa(id_adresa) REFERENCES adrese(id);
ALTER TABLE angajati ADD FOREIGN KEY FK_angajat_id_oficiu_postal(id_oficiu_postal) REFERENCES oficii_postale(id);
ALTER TABLE zile_de_lucru ADD FOREIGN KEY FK_zile_de_lucru_id_oficiu_postal(id_oficiu_postal) REFERENCES oficii_postale(id);
ALTER TABLE inregistrari ADD FOREIGN KEY FK_inregistrari_id_oficiu_postal(id_oficiu_postal) REFERENCES oficii_postale(id);
ALTER TABLE adrese ADD FOREIGN KEY FK_adrese_id_oficiu_postal(id_oficiu_postal) REFERENCES oficii_postale(id);
