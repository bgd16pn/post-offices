
# CREARE TRIGGERE
# ###################################################################
DROP TRIGGER IF EXISTS on_update_articole_postale;
Delimiter //
CREATE TRIGGER on_update_articole_postale AFTER UPDATE ON articole_postale
FOR EACH ROW BEGIN

	IF(OLD.data_avizare IS NULL AND NEW.data_avizare IS NOT NULL) THEN         
        SET @id_oficiu_postal := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_expeditor);
        
        INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
        VALUE(NEW.id, @id_oficiu_postal, 'Avizare articol postal', NEW.data_avizare);
        
    END IF;

	IF(OLD.data_transmitere IS NULL AND NEW.data_transmitere IS NOT NULL) THEN 

        SET @id_oficiu_postal_expeditor := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_expeditor);
		SET @id_oficiu_postal_destinatar := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_destinatar);
        
        UPDATE magazie SET id_oficiu_postal = @id_oficiu_postal_destinatar WHERE id_articol_postal = NEW.id;
                
        INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
        VALUE(NEW.id, @id_oficiu_postal_expeditor, 'Transmitere articol postal', NEW.data_transmitere);
        
        INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
        VALUE(NEW.id, @id_oficiu_postal_destinatar, 'Colectare articol postal', NEW.data_transmitere);  
        
    END IF;
    
    IF(OLD.data_ridicare IS NULL AND NEW.data_ridicare IS NOT NULL) THEN
		DELETE FROM magazie WHERE id_articol_postal = NEW.id;
        
        SET @id_oficiu_postal := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_expeditor);
        
        INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
        VALUE(NEW.id, @id_oficiu_postal, 'Ridicare articol postal', NEW.data_ridicare);
    
    END IF;
    
     IF(OLD.cod_tracking IS NULL AND NEW.cod_tracking IS NOT NULL) THEN        
        SET @id_oficiu_postal := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_expeditor);
        
        INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
        VALUE(NEW.id, @id_oficiu_postal, 'Alocare cod tracking', NOW());
    
    END IF;

END //
Delimiter //


DROP TRIGGER IF EXISTS on_delete_articol_postal;
Delimiter //
CREATE TRIGGER on_delete_articol_postal AFTER DELETE ON articole_postale
FOR EACH ROW BEGIN

	SET @id_oficiu_postal := (SELECT id_oficiu_postal FROM adrese WHERE id = OLD.id_adresa_expeditor);

    IF(OLD.tip = 'Colet') THEN
		DELETE FROM colete WHERE id_articol_postal = OLD.id;
	ELSEIF(OLD.tip = 'Scrisoare') THEN
		DELETE FROM scrisori WHERE id_articol_postal = OLD.id;
    ELSE
		DELETE FROM mandate WHERE id_articol_postal = OLD.id;
    END IF;
    DELETE FROM magazie WHERE id_articol_postal = OLD.id;
    
    INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(OLD.id, @id_oficiu_postal, 'Stergere articol postal', NOW());
    
END //angajati
Delimiter //


-- ---------------------------------------------

DROP TRIGGER IF EXISTS on_insert_articole_postale;
Delimiter //
CREATE TRIGGER on_insert_articole_postale AFTER INSERT ON articole_postale
FOR EACH ROW BEGIN

	SET @id_oficiu_postal := (SELECT id_oficiu_postal FROM adrese WHERE id = NEW.id_adresa_expeditor);
	INSERT INTO magazie(id_articol_postal, id_oficiu_postal) VALUES(NEW.id, @id_oficiu_postal);

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, @id_oficiu_postal, 'Colectare articol postal', NEW.data_colectare);

END //
Delimiter //

DROP TRIGGER IF EXISTS on_insert_colete_tracking;
Delimiter //
CREATE TRIGGER on_insert_colete_tracking AFTER INSERT ON colete
FOR EACH ROW BEGIN

	IF(NEW.tracked IS TRUE) THEN
		SET @cod_tracking := (SELECT UPPER(SUBSTRING(MD5(NEW.id_articol_postal) FROM 1 FOR 10)));
		UPDATE articole_postale SET cod_tracking = @cod_tracking WHERE id = NEW.id_articol_postal;
    END IF;

END //
Delimiter //

DROP TRIGGER IF EXISTS on_insert_scrisori_tracking;
Delimiter //
CREATE TRIGGER on_insert_scrisori_tracking AFTER INSERT ON scrisori
FOR EACH ROW BEGIN

	IF(NEW.tracked IS TRUE) THEN
		SET @cod_tracking := (SELECT UPPER(SUBSTRING(MD5(NEW.id_articol_postal) FROM 1 FOR 10)));
		UPDATE articole_postale SET cod_tracking = @cod_tracking WHERE id = NEW.id_articol_postal;
    END IF;

END //
Delimiter //

-- -----------------------------------------------------------------

DROP TRIGGER IF EXISTS on_insert_utilizator;
Delimiter //
CREATE TRIGGER on_insert_utilizator AFTER INSERT ON utilizatori
FOR EACH ROW BEGIN
     
    INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, 'Creare utilizator', NOW());
    
END //
Delimiter //

DROP TRIGGER IF EXISTS on_update_utilizator;
Delimiter //
CREATE TRIGGER on_update_utilizator AFTER UPDATE ON utilizatori
FOR EACH ROW BEGIN
     
    INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, 'Actualizare utilizator', NOW());
    
END //
Delimiter //

DROP TRIGGER IF EXISTS on_delete_utilizator;
Delimiter //
CREATE TRIGGER on_delete_utilizator AFTER DELETE ON utilizatori
FOR EACH ROW BEGIN
	SET @v := (SELECT username_utilizator FROM angajati WHERE username_utilizator = OLD.username);	
    IF(@v IS NOT NULL) THEN
		DELETE FROM angajati WHERE username_utilizator = OLD.username;
    END IF;
    
    INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(OLD.id, 'Stergere utilizator', NOW());
    
END //
Delimiter //

-- -----------------------

DROP TRIGGER IF EXISTS on_insert_angajati;
Delimiter //
CREATE TRIGGER on_insert_angajati AFTER INSERT ON angajati
FOR EACH ROW BEGIN

	SET @id_utilizator := (SELECT id FROM utilizatori WHERE username = NEW.username_utilizator);
     
    INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(@id_utilizator, NEW.id_oficiu_postal, 'Creare angajat', NOW());
    
END //
Delimiter //

DROP TRIGGER IF EXISTS on_update_angajati;
Delimiter //
CREATE TRIGGER on_update_angajati AFTER UPDATE ON angajati
FOR EACH ROW BEGIN

	SET @id_utilizator := (SELECT id FROM utilizatori WHERE username = NEW.username_utilizator);
     
    INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(@id_utilizator, NEW.id_oficiu_postal, 'Actualizare angajat', NOW());
    
END //
Delimiter //

DROP TRIGGER IF EXISTS on_delete_angajati;
Delimiter //
CREATE TRIGGER on_delete_angajati AFTER DELETE ON angajati
FOR EACH ROW BEGIN

	SET @id_utilizator := (SELECT id FROM utilizatori WHERE username = OLD.username_utilizator);
     
    INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(@id_utilizator, OLD.id_oficiu_postal, 'Stergere angajat', NOW());
    
END //
Delimiter //

-- ----------------------------------------

DROP TRIGGER IF EXISTS on_insert_tarife;
Delimiter //
CREATE TRIGGER on_insert_tarife AFTER INSERT ON tarife
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, 'Adaugare tarif', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_update_tarife;
Delimiter //
CREATE TRIGGER on_update_tarife AFTER UPDATE ON tarife
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, 'Actualizare tarif', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_delete_tarife;
Delimiter //
CREATE TRIGGER on_delete_tarife AFTER DELETE ON tarife
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, tip_inregistrare, data_inregistrare) 
    VALUE(OLD.id, 'Stergere tarif', NOW());

END //
Delimiter //

-- ----------------------------------------------------------
DROP TRIGGER IF EXISTS on_insert_zile_de_lucru;
Delimiter //
CREATE TRIGGER on_insert_zi_de_lucru AFTER INSERT ON zile_de_lucru
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, NEW.id_oficiu_postal, 'Adaugare program de lucru', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_update_zile_de_lucru;
Delimiter //
CREATE TRIGGER on_update_zile_de_lucru AFTER UPDATE ON zile_de_lucru
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, NEW.id_oficiu_postal, 'Actualizare program de lucru', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_delete_zile_de_lucru;
Delimiter //
CREATE TRIGGER on_delete_zile_de_lucru AFTER DELETE ON zile_de_lucru
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(OLD.id, OLD.id_oficiu_postal, 'Stergere program de lucru', NOW());

END //
Delimiter //
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS on_insert_adrese;
Delimiter //
CREATE TRIGGER on_insert_adrese AFTER INSERT ON adrese
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, NEW.id_oficiu_postal, 'Adaugare adresa', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_update_adrese;
Delimiter //
CREATE TRIGGER on_update_adrese AFTER UPDATE ON adrese
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(NEW.id, NEW.id_oficiu_postal, 'Actualizare adresa', NOW());

END //
Delimiter //

DROP TRIGGER IF EXISTS on_delete_adrese;
Delimiter //
CREATE TRIGGER on_delete_adrese AFTER DELETE ON adrese
FOR EACH ROW BEGIN

	INSERT INTO inregistrari(id_entitate, id_oficiu_postal, tip_inregistrare, data_inregistrare) 
    VALUE(OLD.id, OLD.id_oficiu_postal, 'Stergere adresa', NOW());

END //
Delimiter //

-- ------------------------------------------------------------
