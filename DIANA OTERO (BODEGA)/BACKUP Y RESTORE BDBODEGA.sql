USE master

ALTER DATABASE bdBodega
SET RECOVERY FULL ---COPIA DE SEGURIDAD COMPLETA
---SET RECOVERY BULK_LOGGED ---COMPIA DE SEGURIDAD MASIVA

-----------------------------------------------------------------
---HACER UNA COPIA DE SEGURIDAD DE LA BASE DE DATOS bdBodega
---ANTES CREAR LA CARPETA "BACKUP" EN EL DISCO RESPECTIVO Ejemplo: "C, D, E".
-----------------------------------------------------------
---INICO
BACKUP DATABASE bdBodega
TO DISK = 'E:\DOCUMENTACION PROYECTOS POO UNP\DIANA OTERO (BODEGA)\BACKUP\Backup-Bodega.bak'
WITH NAME ='BACKUP DE BODEGA',
DESCRIPTION ='BACKUP COMPLETO DE BODEGA DE NOVIEMBRE'
---FINAL
----------------------------------------------------------------
RESTORE HEADERONLY FROM DISK='E:\DOCUMENTACION PROYECTOS POO UNP\DIANA OTERO (BODEGA)\BACKUP\Backup-Bodega.bak'
----------------------------------------------------------------

DROP DATABASE bdBodega

----------------------------------------------------------------
---RESTAURAR LA BASE DE DATOS bb_hotel
-------------------------------------------------------------------
---INICIO
RESTORE DATABASE bd_hotel
FROM DISK ='E:\DOCUMENTACION PROYECTOS POO UNP\DIANA OTERO (BODEGA)\BACKUP\Backup-Bodega.bak'
WITH FILE=1, RECOVERY
---FINAL


