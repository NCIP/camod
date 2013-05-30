/*L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L*/

-- I had an old DATOP file to match the following metastasis records
-- JIRA #CAMOD-974  	 Metastases are gone from earlier models
-- Matches were made based on the close promiximity of the histopathology Ids and the context of the model entry 

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008866 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008869;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008884 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008888;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008929 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008932;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008929 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008934;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008929 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008936;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008929 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10008938;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009096 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009098;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009096 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009099;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009197 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009198;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009658 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009665;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009658 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009666;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10008492 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009799;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009993 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009995;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009993 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009997;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009993 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009998;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10009993 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10009999;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 10010710 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=10010711;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061844;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061845;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061846;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061847;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061848;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061848;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061848;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50061843 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50061848;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID =50052646  where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50052649;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50052657 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id= 50052660;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID =50052661  where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50052663;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50052475 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id= 50052479;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50052475 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id= 50052482;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50052393  where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id= 50052396;

update histopathology h set h.PARENT_HISTOPATHOLOGY_ID = 50050679 where h.PARENT_HISTOPATHOLOGY_ID is null and h.histopathology_id=50050680;

commit;