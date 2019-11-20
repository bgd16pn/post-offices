# CREARE PROCEDURI
# ##################################################################
DROP PROCEDURE IF EXISTS GENERATE_TRACKING_CODE;
Delimiter //
CREATE PROCEDURE GENERATE_TRACKING_CODE(id_articol_postal INT)
BEGIN
	SELECT UPPER(SUBSTRING(MD5(id_articol_postal) FROM 1 FOR 10));
End //
Delimiter //

