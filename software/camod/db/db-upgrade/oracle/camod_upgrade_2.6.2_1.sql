/*L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L*/

-- Update the roles for a test user (ran on all tiers except prod)
Delete from PARTY_ROLE pr
where pr.PARTY_ID  = '50057228';	

commit;

