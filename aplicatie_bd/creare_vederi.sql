# CREARE VEDERI
# ##################################################################
DROP VIEW IF EXISTS view_angajati;
CREATE VIEW view_angajati AS
SELECT utilizatori.id, utilizatori.nume, utilizatori.prenume, utilizatori.username, utilizatori.id_adresa, 
	   utilizatori.email, utilizatori.telefon, utilizatori.parola, angajati.id_oficiu_postal, angajati.administrator, 
       angajati.salariu, angajati.data_angajare 
FROM angajati JOIN utilizatori ON utilizatori.username = angajati.username_utilizator ORDER BY utilizatori.id ASC;

DROP VIEW IF EXISTS view_date_personale_angajat;
CREATE VIEW view_date_personale_angajat AS
SELECT utilizatori.id, utilizatori.nume, utilizatori.prenume, utilizatori.username, utilizatori.parola, utilizatori.email, 
	   utilizatori.telefon, utilizatori.id_adresa, angajati.id_oficiu_postal, angajati.salariu, angajati.data_angajare, angajati.administrator
FROM angajati JOIN utilizatori ON utilizatori.username = angajati.username_utilizator;
