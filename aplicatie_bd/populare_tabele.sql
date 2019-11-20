# POPULARE TABELE
# ##################################################################
INSERT INTO `oficii_postale` (`denumire`, `telefon`) VALUES
('Nr. 1', '0123654789'),
('Nr. 2', '7142589630'),
('Nr. 3', '0987623456'),
('Nr. 4', '4569871236');

INSERT INTO `adrese` (`strada`, `numar`, `bloc`, `scara`, `apartament`, `oras`, `judet`, `cod_postal`, `id_oficiu_postal`) VALUES
('Gral. dr. Cernatescu Ion', '7', 'A12', '1', '8', 'Craiova', 'Dolj', '200093', 1),
('Observatorului', '34', 'C7', '1', '528', 'Cluj-Napoca', 'Cluj', '400016', 2);

INSERT INTO `utilizatori` (`username`, `parola`, `email`, `telefon`, `nume`, `prenume`, `id_adresa`) VALUES
('admin', 'admin', 'admin@admin.com', '7418529334', 'Paun', 'Bogdan', 1),
('a', 'a', 'a@a.com', '0852741963', 'St', 'A', 1),
('b', 'b', 'b@b.com', '2791325048', 'b', 'b', 2),
('asda', 'asdads', 'sada@asd.ro', '7465238704', 'asd', 'asda', 2),
('user', 'user', 'user@user.ro', '1234567890', 'user', 'user', 1);

INSERT INTO `angajati` (`username_utilizator`, `id_oficiu_postal`, `administrator`, `salariu`, `data_angajare`) VALUES
('admin', 1, true, 8765, '2019-01-11'),
('a', 1, false, 1200, '2019-01-11'),
('b', 2, true, 6520, '2019-01-11'),
('asda', 3, true, 6152, '2019-01-11'),
('user', 4, true, 3125, '2019-01-11');

INSERT INTO `zile_de_lucru` (`id_oficiu_postal`, `ziua_saptamanii`, `ora_deschidere`, `ora_inchidere` ) VALUES
(1,'Luni',8,20),
(1,'Marti',8,20),
(1,'Miercuri',8,20),
(1,'Joi',8,20),
(1,'Vineri',8,16),
(1,'Sambata',10,14);

INSERT INTO `articole_postale` (`tip`, `data_colectare`, `nume_expeditor`, `prenume_expeditor`, `id_adresa_expeditor`, 
`nume_destinatar`, `prenume_destinatar`, `id_adresa_destinatar`, `tarif_expediere`, `tarif_ridicare`, `cod_tracking`) VALUES
('Colet', '2019-01-15 13:52:38', 'Paun', 'Bogdan', 1, 'Paun', 'Mihai', 1, 0, 0, 'C4CA4238A0'),
('Mandat', '2019-01-15 13:58:30', 'Paun', 'Mihai', 1, 'Paun', 'Bogdan', 1, 0, 0, '');

INSERT INTO `colete` (`id_articol_postal`, `valoare_ramburs`, `valoare_declarata`, `greutate`, `tracked`, `fragil`, `voluminos`, `livrare_sambata`) VALUES
(1, 0, 20, 2, true, false, false, true);

INSERT INTO `mandate` (`id_articol_postal`, `valoare`, `detalii`) VALUES
(2, 200, 'Pachet');

INSERT INTO `tarife` (`tip_articol_postal`, `descriere`, `valoare`) VALUES
('Colet', 'Tarif fix', 10),
('Colet', 'Tarif fragil', 4),
('Colet', 'Tarif voluminos', 5),
('Colet', 'Tarif livrare sambata', 6),
('Colet', 'Tarif tracking', 7),
('Colet', 'Tarif per kg', 2),
('Colet', 'Tarif trimitere ramburs', 3),
('Scrisoare', 'Tarif fix', 5),
('Scrisoare', 'Tarif trimitere ramburs', 3),
('Scrisoare', 'Tarif confirmare primire', 2.5),
('Scrisoare', 'Tarif tracking', 11.27),
('Mandat', 'Tarif fix', 5);