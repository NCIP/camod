-- Update the Organ table to clean up organ.name field
update organ o
set o.NAME=replace(o.NAME, ' (MMHCC)', '')
where o.NAME LIKE '%(MMHCC)';


commit;

