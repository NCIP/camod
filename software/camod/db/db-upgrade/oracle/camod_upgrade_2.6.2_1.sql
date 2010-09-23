-- Update the roles for a test user 
Delete from PARTY_ROLE pr
where pr.PARTY_ID  = '50057228';	

commit;

