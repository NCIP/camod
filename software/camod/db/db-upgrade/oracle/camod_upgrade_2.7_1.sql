-- Update the Organ table to clean up organ.name field
update organ o
set o.NAME=replace(o.NAME, ' (MMHCC)', '')
where o.NAME LIKE '%(MMHCC)';

update organ o
set o.NAME=replace(o.NAME, ' (Segmentation:20-25 somites to Adult)', '')
where o.NAME LIKE '%(Segmentation:20-25 somites to Adult)';

update organ o
set o.NAME=replace(o.NAME, ' (Segmentation:1-4 somites to Adult)', '')
where o.NAME LIKE '%(Segmentation:1-4 somites to Adult)';

update organ o
set o.NAME=replace(o.NAME, ' (Unknown to Adult)', '')
where o.NAME LIKE '%(Unknown to Adult)';

update organ o
set o.NAME=replace(o.NAME, ' (Zygote:1-cell to Adult)', '')
where o.NAME LIKE '%(Zygote:1-cell to Adult)';

update organ o
set o.NAME=replace(o.NAME, ' (Blastula:High to Adult)', '')
where o.NAME LIKE '%(Blastula:High to Adult)';


commit;

