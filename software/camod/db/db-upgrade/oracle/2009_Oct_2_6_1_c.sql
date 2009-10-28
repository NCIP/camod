-- Delete duplicate (old) entries from micro_array_data table (re-entered from GUI)
DELETE FROM micro_array_data
WHERE experiment_id is not null;

 
