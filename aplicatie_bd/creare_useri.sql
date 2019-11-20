# CREARE USERI DE ACCES LA BAZA DE DATE
# ##################################################################
DROP USER IF EXISTS 'creator'@'localhost';
CREATE USER 'creator'@'localhost' IDENTIFIED BY 'creator';
GRANT ALL PRIVILEGES ON sgbd_oficii_postale.* TO 'creator'@'localhost'
WITH GRANT OPTION;

DROP USER IF EXISTS 'administrator'@'localhost';
CREATE USER 'administrator'@'localhost' IDENTIFIED BY 'admin';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.oficii_postale TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.adrese TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.utilizatori TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sgbd_oficii_postale.articole_postale TO 'administrator'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.colete TO 'administrator'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.scrisori TO 'administrator'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.mandate TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sgbd_oficii_postale.magazie TO 'administrator'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.inregistrari TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.tarife TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.zile_de_lucru TO 'administrator'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.view_angajati TO 'administrator'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.view_date_personale_angajat TO 'administrator'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.angajati TO 'administrator'@'localhost';

DROP USER IF EXISTS 'angajat'@'localhost';
CREATE USER 'angajat'@'localhost' IDENTIFIED BY 'angajat';
GRANT SELECT ON sgbd_oficii_postale.oficii_postale TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.adrese TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.utilizatori TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sgbd_oficii_postale.articole_postale TO 'angajat'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.colete TO 'angajat'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.scrisori TO 'angajat'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.mandate TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sgbd_oficii_postale.magazie TO 'angajat'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.inregistrari TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.tarife TO 'angajat'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON sgbd_oficii_postale.zile_de_lucru TO 'angajat'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.view_angajati TO 'angajat'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.view_date_personale_angajat TO 'angajat'@'localhost';


DROP USER IF EXISTS 'user'@'localhost';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT SELECT ON sgbd_oficii_postale.adrese TO 'user'@'localhost';
GRANT SELECT, INSERT, UPDATE ON sgbd_oficii_postale.utilizatori TO 'user'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.articole_postale TO 'user'@'localhost';


DROP USER IF EXISTS 'guest'@'localhost';
CREATE USER 'guest'@'localhost' IDENTIFIED BY '';
GRANT SELECT ON sgbd_oficii_postale.oficii_postale TO 'guest'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.adrese TO 'guest'@'localhost';
GRANT SELECT, INSERT ON sgbd_oficii_postale.utilizatori TO 'guest'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.view_angajati TO 'guest'@'localhost';
GRANT SELECT ON sgbd_oficii_postale.articole_postale TO 'guest'@'localhost';
