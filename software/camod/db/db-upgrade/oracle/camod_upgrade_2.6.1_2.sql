-- Add new column named thumb_url and populate column with data from the URL column 
-- The column data will be different for tif images only
ALTER TABLE image
ADD THUMB_URL VARCHAR2(1000);
		
Update IMAGE i set i.THUMB_URL=i.URL
where i.url LIKE '%.JPG';		
		
Update IMAGE i set i.THUMB_URL=i.URL
where i.url LIKE '%.jpg';
		
Update IMAGE i set i.THUMB_URL=i.URL 
where i.url LIKE '%.png'; 
		
Update IMAGE i set i.THUMB_URL=i.URL 
where i.url LIKE '%.gif'; 

Update IMAGE i set i.THUMB_URL=i.URL
where i.url LIKE '%.jpeg';

	

commit;

