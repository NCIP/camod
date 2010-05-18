-- Clean up DB - delete orphan image records for caIMAGE upgrade
Delete from IMAGE i
where i.ABS_CANCER_MODEL_ID is null
And i.IMAGE_ID NOT IN (Select eg.IMAGE_ID
from engineered_gene eg
where eg.IMAGE_ID IS NOT NULL);

-- #18685  	Assign various records to curator - data cleanup		
Update abs_cancer_model abs 
set abs.submitter_id=150063906 
where abs.submitter_id=50057228 or abs.submitter_id=50056983
or abs.submitter_id=237 or abs.submitter_id=56;

-- #23750  	Links to sid, gif, jpeg, jpg, tif images needs to be modified for caIMAGE upgrade		
Update IMAGE i set i.URL=replace (i.URL,'http://caimage.nci.nih.gov/lizardtech','http://imageserver-dev.nci.nih.gov/adore-djatoka/images/caimage/Images/images')  
where i.url LIKE '%.JPG';		
		
Update IMAGE i set i.URL=replace (i.URL,'http://caimage.nci.nih.gov/lizardtech','http://imageserver-dev.nci.nih.gov/adore-djatoka/images/caimage/Images/images')  
where i.url LIKE '%.jpg';
		
Update IMAGE i set i.URL=replace (i.URL,'http://caimage.nci.nih.gov/lizardtech','http://imageserver-dev.nci.nih.gov/adore-djatoka/images/caimage/Images/images')  
where i.url LIKE '%.png'; 
		
Update IMAGE i set i.URL=replace (i.URL,'http://caimage.nci.nih.gov/lizardtech','http://imageserver-dev.nci.nih.gov/adore-djatoka/images/caimage/Images/images')  
where i.url LIKE '%.gif'; 
		
drop index IMAGEMAGE_URL;
drop index IMAGEMAGE_URL_LWR;

ALTER TABLE IMAGE MODIFY (URL VARCHAR2(1000));

CREATE INDEX IMAGEMAGE_URL ON IMAGE(URL);

CREATE INDEX IMAGEMAGE_URL_LWR ON IMAGE(LOWER("URL"));

-- #27167  	delete image record for image_id=1058		
Update engineered_gene eg
set eg.IMAGE_ID = null
where eg.ENGINEERED_GENE_ID = '63';
		
delete from IMAGE i 
where i.URL LIKE '%.cpt';

-- #18438  	Change label from MMHCC Repository to NCI Mouse Repository		
Update Animal_Distributor ad
set ad.NAME = 'NCI Mouse Repository'
where ad.ANIMAL_DISTRIBUTOR_ID = '3';

-- #27533  	Change zebrafish concept codes to accomodate new NCI Thesaurus release with improved zebrafish vocabularies		
Update Organ o 
set o.CONCEPT_CODE=replace (o.CONCEPT_CODE,'_',':')  
where o.CONCEPT_CODE LIKE 'ZFA_%';

-- #27533  	Change zebrafish concept codes to accomodate new NCI Thesaurus release with improved zebrafish vocabularies
Update Developmental_stage ds 
set ds.CONCEPT_CODE=replace (ds.CONCEPT_CODE,'_',':')  
where ds.CONCEPT_CODE LIKE 'ZFS_%';		

commit;

