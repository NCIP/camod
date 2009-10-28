-- Delete disease entries without parent record in histopathology
Delete from disease d 
where d.NAME is null
and d.NAME_ALTERN_ENTRY is null
and d.DISEASE_ID NOT IN
(select disease_id from histopathology h);
 
