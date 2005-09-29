/************************SEX_DISTRIBUTION**********************************/

TRUNCATE TABLE camoddev.SEX_DISTRIBUTION;

insert INTO camoddev.SEX_DISTRIBUTION (sex_distribution_id,
type)
SELECT sexdistributionuid,
sexdistributiontype
FROM datop.SEXDISTRIBUTION@camodop_datop;

commit;

/**************************AVAILABILITY*******************************/

Drop table AVAILABILITY_TMP;
CREATE TABLE AVAILABILITY_TMP
(
  AVAILABILITY_ID  NUMBER(19)                   NOT NULL,
	ModelUID		number,
	mmh_Availability_ID	number,
	CommentUID		number,
  ENTERED_DATE     DATE                             NULL,
  VISIBLE_TO       VARCHAR2(255 BYTE)               NULL,
  MODIFIED_DATE    DATE                             NULL,
  RELEASE_DATE     DATE                             NULL
);

--UPDATE TABLE AVAILABILITY_TMP 01 from datop.animalmodel

CREATE OR REPLACE procedure AVAILABILITY_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (SELECT rownum, ModelUID, TO_DATE(TIMESTAMP, 'MM/DD/YYYY HH:MI:SS PM') Timestamp, 
	DECODE(mmhcconly, 0, 'PUBLIC','PRIVATE') Visible_To, RecordRELEASEDATE
	FROM datop.AnimalModel@camodop_datop
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE AVAILABILITY_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO AVAILABILITY_TMP (Availability_id, ModelUID, ENTERED_DATE, VISIBLE_TO, RELEASE_DATE)
      VALUES
     (aRec.ROWNUM,
      aRec.ModelUID,
      aREC.Timestamp,
      aRec.Visible_To,
	aRec.RecordRELEASEDATE);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE AVAILABILITY_TMP 02 from cabiommh.availability

CREATE OR REPLACE procedure AVAILABILITY_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (SELECT rownum, availability_id, ENTEREDDATE, VISIBLETO, MODIFIEDDATE, RELEASEDATE
	FROM cabiommh.AVAILABILITY@camoddev_cabiommh
	where availability_id in (2000, 2001));

  aID number:=0;

BEGIN

   SELECT MAX(availability_id) INTO V_MAXROW FROM availability_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO availability_TMP (availability_id, mmh_Availability_ID, ENTERED_DATE, VISIBLE_TO, 
		MODIFIED_DATE, RELEASE_DATE)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.availability_id,
      aREC.ENTEREDDATE,
      aRec.VISIBLETO,
	aRec.MODIFIEDDATE,
	aRec.RELEASEDATE);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE AVAILABILITY_TMP 03 from datop.comments

CREATE OR REPLACE procedure AVAILABILITY_TMP_03_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (SELECT rownum, commentUID, TO_DATE(TIMESTAMP, 'MM/DD/YYYY HH:MI:SS PM') Timestamp
	FROM datop.comments@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(availability_id) INTO V_MAXROW FROM availability_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO availability_TMP (availability_id, CommentUID, ENTERED_DATE)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.CommentUID,
      aREC.Timestamp);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE AVAILABILITY_TMP_01_LD;
EXECUTE AVAILABILITY_TMP_02_LD;
EXECUTE AVAILABILITY_TMP_03_LD;

Truncate table AVAILABILITY;
Insert into AVAILABILITY
	(AVAILABILITY_ID,
  ENTERED_DATE,
  VISIBLE_TO,
  MODIFIED_DATE,
  RELEASE_DATE)
Select AVAILABILITY_ID,
  ENTERED_DATE,
  VISIBLE_TO,
  MODIFIED_DATE,
  RELEASE_DATE from AVAILABILITY_TMP;

Commit;


/**************************Nomenclature******************************/

DROP Table NOMENCLATURE_TMP; 
CREATE TABLE NOMENCLATURE_TMP
(
  NOMENCLATURE_ID  NUMBER(19)                   NOT NULL,
	mmh_NOMENCLATURE_ID	number,
	ModelUID		number,
  NAME             VARCHAR2(255 BYTE)               NULL
);

--UPDATE TABLE NOMENCLATURE_TMP 01 from cabiommh.NOMENCLATURE

CREATE OR REPLACE procedure NOMENCLATURE_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (SELECT rownum, Nomenclature_ID, Name
	FROM cabiommh.Nomenclature@camoddev_cabiommh where Nomenclature_ID between 41 and 56);

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE NOMENCLATURE_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO NOMENCLATURE_TMP (NOMENCLATURE_ID,
	mmh_NOMENCLATURE_ID, NAME)
      VALUES
     (aRec.ROWNUM,
      aRec.Nomenclature_ID,
      aREC.Name);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE NOMENCLATURE_TMP 02 from Datop.AnimalModel

CREATE OR REPLACE procedure NOMENCLATURE_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (SELECT rownum, ModelUID, NOMENCLATURE
	FROM datop.animalmodel@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(NOMENCLATURE_id) INTO V_MAXROW FROM NOMENCLATURE_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO NOMENCLATURE_TMP (NOMENCLATURE_id, ModelUID, Name)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.ModelUID,
      aREC.NOMENCLATURE);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE NOMENCLATURE_TMP_01_LD;
EXECUTE NOMENCLATURE_TMP_02_LD;

Truncate Table NOMENCLATURE; 
Insert into NOMENCLATURE (NOMENCLATURE_ID, NAME)
select NOMENCLATURE_ID, NAME from NOMENCLATURE_TMP;

Commit;


 /**********************PUBLICATION_STATUS******************************/

TRUNCATE TABLE camoddev.PUBLICATION_STATUS;

INSERT INTO camoddev.PUBLICATION_STATUS(PUBLICATION_STATUS_ID, NAME)
SELECT PUBLICATIONSTATUSUID, STATUS FROM datop.PUBLICATIONSTATUS@camodop_datop;



/************************Agent_target*****************************/

TRUNCATE TABLE camoddev.Agent_target;

INSERT INTO camoddev.Agent_target(Agent_target_id, target_Name)
SELECT TargetUID,
TargetType
FROM datop.Target@camodop_datop;

commit;


/***************************BIOLOGICAL_PROCESS**************************/

TRUNCATE TABLE camoddev.BIOLOGICAL_PROCESS;

INSERT INTO camoddev.BIOLOGICAL_PROCESS(BIOLOGICAL_PROCESS_id, PROCESS_name)
SELECT bioprocessuid,
bioprocesstype
FROM datop.Bioprocess@camodop_datop;

commit;

/**************************CHEMICAL_CLASS***************************/

TRUNCATE TABLE camoddev.CHEMICAL_CLASS;

INSERT INTO camoddev.CHEMICAL_CLASS(CHEMICAL_CLASS_id, CHEMICAL_CLASS_name)
SELECT chemicalclassuid,
chemicaltype
FROM datop.chemicalclass@camodop_datop;

commit;


/*************************REPOSITORY_INFO*****************************/

DROP TABLE REPOSITORY_INFO_TMP;
CREATE TABLE REPOSITORY_INFO_TMP
(
  REPOSITORY_INFO_ID  NUMBER(19)                NOT NULL,
	ModelUID	number,	
  IN_THE_REPOSITORY   NUMBER(19)                    NULL,
  SENT_EMAIL_CONTENT  VARCHAR2(255 BYTE)            NULL,
  SUGGEST_SUBMISSION  NUMBER(19)                    NULL
);

Truncate table REPOSITORY_INFO_TMP;
Insert into REPOSITORY_INFO_TMP (
	REPOSITORY_INFO_ID,
	ModelUID,	
  SENT_EMAIL_CONTENT,
  SUGGEST_SUBMISSION)
Select rownum, ModelUID, REPOSITORY_EMAIL_SENT, SUGGEST_SUBMISSION
FROM datop.ANIMALMODEL@camodop_datop;

commit;

Truncate table REPOSITORY_INFO;
Insert into REPOSITORY_INFO (
	REPOSITORY_INFO_ID,	
  SENT_EMAIL_CONTENT,
  SUGGEST_SUBMISSION)
Select REPOSITORY_INFO_ID,	
  SENT_EMAIL_CONTENT,
  SUGGEST_SUBMISSION
FROM REPOSITORY_INFO_TMP;
commit;

/************************Tumor_code******************************/

TRUNCATE TABLE camoddev.Tumor_code;

INSERT INTO camoddev.Tumor_code(Tumor_code_id, code, Description)
SELECT Tumor_code_id, Tumor_code, Tumor_code_Description
FROM cabiommh.Tumor_code@camoddev_cabiommh;

commit;

/*************************phenotype*******************************/

/* This table phenotype_tmp is for updating the phenotype_ID in the ABS_Cancer_Model */

Drop table phenotype_tmp;
CREATE TABLE PHENOTYPE_tmp
(
  PHENOTYPE_ID         NUMBER(19)               NOT NULL,
	MODELUID	number,
  BREEDING_NOTES       VARCHAR2(255 BYTE)           NULL,
  DESCRIPTION          VARCHAR2(4000 BYTE)          NULL,
  SEX_DISTRIBUTION_ID  NUMBER(19)                   NULL
);

create table pheno_tmp as
SELECT modeluid, sex, breeding, phenotype
FROM datop.ANIMALMODEL@camodop_datop;

update pheno_tmp set sex = null where sex not in ('1','2','3');
commit;

TRUNCATE TABLE camoddev.PHENOTYPE_tmp;

insert INTO camoddev.PHENOTYPE_tmp(PHENOTYPE_ID, ModelUID, SEX_DISTRIBUTION_ID, BREEDING_NOTES, DESCRIPTION)
SELECT ROWNUM, ModelUID, sex, breeding, phenotype FROM pheno_tmp;

commit;

TRUNCATE TABLE camoddev.PHENOTYPE;

insert INTO camoddev.PHENOTYPE(PHENOTYPE_ID, SEX_DISTRIBUTION_ID, BREEDING_NOTES, DESCRIPTION)
SELECT PHENOTYPE_ID, SEX_DISTRIBUTION_ID, BREEDING_NOTES, DESCRIPTION from PHENOTYPE_tmp;

commit;

Drop table pheno_tmp;


/*************************reg_element_type*****************************/

TRUNCATE TABLE camoddev.reg_element_type;

INSERT INTO camoddev.reg_element_type(reg_element_type_id, name)
SELECT elementTypeuid,
elementtype
FROM datop.regulatoryelementtype@camodop_datop;

commit;

/***************************Role**************************/

TRUNCATE TABLE Role;

INSERT INTO Role(Role_ID, Name)
SELECT User_Type, Role_Description
FROM datop.UserType@camodop_datop;

commit;

/*************************EXP_LEVEL_DESC****************************/

TRUNCATE TABLE camoddev.EXP_LEVEL_DESC;

INSERT INTO camoddev.EXP_LEVEL_DESC(EXP_LEVEL_DESC_id, Expression_Level)
SELECT expressionleveluid,
expressionlevel
FROM datop.expressionlevel@camodop_datop;

commit;

/*************************Image********************************/

TRUNCATE TABLE camoddev.Image;

insert INTO camoddev.Image (Image_id, Title, Description, staining)
SELECT ImageUID, IMAGETitle, ImageDescription, stainType
FROM datop.Imagedata@camodop_datop;

commit;

/*******************************************************************/

TRUNCATE TABLE camoddev.SEGMENT_TYPE;

INSERT INTO camoddev.SEGMENT_TYPE(SEGMENT_TYPE_id, Name)
SELECT SegmenttypeUID,
segment_Type
FROM datop.segmenttype@camodop_datop;

commit;

/***************************Conditionality**************************/

TRUNCATE TABLE Conditionality;

INSERT INTO Conditionality(Conditionality_ID, Conditioned_By, Description)
SELECT GenotypesUID, ConditionalKey, Conditional_desc
FROM datop.Genetic_description@camodop_datop;

commit;

/***************************CONTACT_INFO*****************************/

TRUNCATE TABLE camoddev.CONTACT_INFO;

insert INTO camoddev.CONTACT_INFO(CONTACT_INFO_ID, EMAIL, PHONE, ZIP, FAX, STATE, CITY, ADDRESS, INSTITUTE, LAB)
SELECT SubmitterUID, EMAIL,
PHONE,
ZIP,
FAX,
STATE,
CITY,
ADDRESS1 || ' ' || ADDRESS2,
INSTITUTE,
LABORATORY
FROM datop.submitter@camodop_datop;

commit;

/***************************Genetic_Alteration**************************/

TRUNCATE TABLE Genetic_Alteration;

INSERT INTO Genetic_Alteration(Genetic_Alteration_ID, Observation)
SELECT Rownum theid, temp.*
FROM (select distinct GeneticAlterations
	from datop.Histopathology@camodop_datop 
	where GeneticAlterations is not null) temp;

commit;

/****************Endpoint_code: Take a long time to load---now short time***************/

TRUNCATE TABLE camoddev.Endpoint_code;

INSERT INTO camoddev.Endpoint_code(Endpoint_code_id, code, Description)
SELECT Endpoint_code_id, Endpoint_code, Endpoint_Description
FROM cabiommh.endpoint_code@camoddev_cabiommh;

commit;


DROP TABLE ENV_FACTOR_TMP;
CREATE TABLE ENV_FACTOR_TMP
(
  ENV_FACTOR_ID    NUMBER(19)                   NOT NULL,
	CarcinogenagentUID	number,
	Agent_ID		number,
	TherapyUID		number,
	GenotypesUID		number,
  ENV_FACTOR_TYPE  VARCHAR2(255 BYTE),
  TYPE             VARCHAR2(255 BYTE)               NULL,
  NAME             VARCHAR2(255 BYTE)               NULL,
  CAS_NUMBER       VARCHAR2(255 BYTE)               NULL,
  NSC_NUMBER       NUMBER(19)                       NULL,
  IS_CMAP_AGENT    NUMBER(1)                        NULL,
  EVS_ID           VARCHAR2(255 BYTE)               NULL,
  COMMENTS         VARCHAR2(255 BYTE)               NULL,
  SOURCE           VARCHAR2(255 BYTE)               NULL,
	name_unctrl_vocab	varchar2(255)
);


--UPDATE TABLE ENV_FACTOR_TMP 01 from datop.Carcinogenagent

CREATE OR REPLACE procedure ENV_FACTOR_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, CarcinogenagentUID, AgentCategory, AgentName, modelkey
		FROM datop.Carcinogenagent@camodop_datop
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE ENV_FACTOR_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

     If aRec.Modelkey is null Then 
	INSERT INTO ENV_FACTOR_TMP
		(ENV_FACTOR_ID,
		ENV_FACTOR_TYPE,
		CarcinogenagentUID,
		TYPE,
		Name)
      VALUES
     (aRec.ROWNUM,
	'AG',
      aRec.CarcinogenagentUID,
      aRec.AgentCategory,
      aREC.AgentName);
    ELSIF aRec.Modelkey is not null Then 
	INSERT INTO ENV_FACTOR_TMP
		(ENV_FACTOR_ID,
		ENV_FACTOR_TYPE,
		CarcinogenagentUID,
		TYPE,
		Name_unctrl_vocab)
      VALUES
     (aRec.ROWNUM,
	'AG',
      aRec.CarcinogenagentUID,
      aRec.AgentCategory,
      aREC.AgentName);
   END IF;

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ENV_FACTOR_TMP 02 from cabiommh.agent

CREATE OR REPLACE procedure ENV_FACTOR_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, agent_ID, agent_type, agent_name, NSC_Number, 
	CMAP_agent, EVS_ID, Agent_Comment, agent_Source 
	from cabiommh.agent@camoddev_cabiommh
	Where agent_ID >= 10000 or agent_ID in (3, 6, 48, 79, 104, 131));

  aID number:=0;

BEGIN

   SELECT MAX(ENV_FACTOR_ID) INTO V_MAXROW FROM ENV_FACTOR_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ENV_FACTOR_TMP(
	ENV_FACTOR_ID,
	ENV_FACTOR_TYPE,
	agent_ID,
  	TYPE,
  	NAME,
  	NSC_NUMBER,
  	IS_CMAP_AGENT,
  	EVS_ID,
  	COMMENTS,
  	SOURCE)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
	'AG',
      aRec.agent_ID,
      aRec.agent_type,
      aRec.agent_name,
      aRec.NSC_Number,
	aRec.CMAP_agent,
	aRec.EVS_ID,
	aRec.Agent_Comment,
	aRec.agent_Source);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ENV_FACTOR_TMP 03 from Datop.Therapy

CREATE OR REPLACE procedure ENV_FACTOR_TMP_03_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, TherapyUID, compound, NSC_Number 
	FROM datop.Therapy@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(ENV_FACTOR_ID) INTO V_MAXROW FROM ENV_FACTOR_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ENV_FACTOR_TMP(
	ENV_FACTOR_ID,
	ENV_FACTOR_TYPE,
	TherapyUID,
  	NAME,
  	NSC_NUMBER)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
	'AG',
      aRec.TherapyUID,
      aRec.Compound,
      aRec.NSC_Number);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ENV_FACTOR_TMP 04 from Datop.Genetic_Description

CREATE OR REPLACE procedure ENV_FACTOR_TMP_04_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, GenotypesUID, AgentName 
	FROM datop.Genetic_Description@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(ENV_FACTOR_ID) INTO V_MAXROW FROM ENV_FACTOR_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ENV_FACTOR_TMP(
	ENV_FACTOR_ID,
	ENV_FACTOR_TYPE,
	GenotypesUID,
	Type,
  	NAME)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
	'AG',
      aRec.GenotypesUID,
      'Chemical/Drug',
      aRec.AgentName);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE ENV_FACTOR_TMP_01_LD;
EXECUTE ENV_FACTOR_TMP_02_LD;
EXECUTE ENV_FACTOR_TMP_03_LD;
EXECUTE ENV_FACTOR_TMP_04_LD;

TRUNCATE TABLE ENV_FACTOR;
Insert into ENV_FACTOR (
  ENV_FACTOR_ID,
  ENV_FACTOR_TYPE,
  TYPE,
  NAME,
  CAS_NUMBER,
  NSC_NUMBER,
  IS_CMAP_AGENT,
  EVS_ID,
  COMMENTS,
  SOURCE,
 name_unctrl_vocab
) select ENV_FACTOR_ID,
  ENV_FACTOR_TYPE,
  TYPE,
  NAME,
  CAS_NUMBER,
  NSC_NUMBER,
  IS_CMAP_AGENT,
  EVS_ID,
  COMMENTS,
  SOURCE,
 name_unctrl_vocab from ENV_FACTOR_TMP;

Commit;


Drop table GENOTYPE_SUMMARY_TMP;
CREATE TABLE GENOTYPE_SUMMARY_TMP
(
  GENOTYPE_SUMMARY_ID  NUMBER(19)               NOT NULL,
	mmh_GenotypeSummary_ID		number,
	ModelUID			number,
  SUMMARY              VARCHAR2(255 BYTE)           NULL,
  GENOTYPE             VARCHAR2(255 BYTE)           NULL,
  NOMENCLATURE_ID      NUMBER(19)                   NULL
);

--UPDATE TABLE GENOTYPE_SUMMARY_TMP 01 from cabiommh.GENOTYPESUMMARY

CREATE OR REPLACE procedure GENOTYPE_SUMMARY_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, GenotypeSummary_ID, Summary, genotype, Nomenclature_ID
	FROM cabiommh.GenotypeSummary@camoddev_cabiommh 
		where genotypesummary_ID between 3000 and 3016);

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE GENOTYPE_SUMMARY_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO GENOTYPE_SUMMARY_TMP (
	GENOTYPE_SUMMARY_ID,
	mmh_GenotypeSummary_ID,
  	SUMMARY,
  	GENOTYPE,
  	NOMENCLATURE_ID)
      VALUES
     (aRec.ROWNUM,
      aRec.GenotypeSummary_ID,
      aREC.Summary,
      aRec.genotype,
      aRec.Nomenclature_ID);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE GENOTYPE_SUMMARY_TMP 02 from datop.AnimalModel

CREATE OR REPLACE procedure GENOTYPE_SUMMARY_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, ModelUID, Genotype 
	from datop.AnimalModel@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(GENOTYPE_SUMMARY_ID) INTO V_MAXROW FROM GENOTYPE_SUMMARY_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO GENOTYPE_SUMMARY_TMP (
	GENOTYPE_SUMMARY_ID,
	ModelUID,
  	Summary)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.ModelUID,
      aREC.Genotype);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE GENOTYPE_SUMMARY_TMP_01_LD;
EXECUTE GENOTYPE_SUMMARY_TMP_02_LD;

Update GENOTYPE_SUMMARY_TMP a set NOMENCLATURE_ID = (select NOMENCLATURE_ID
	from NOMENCLATURE_TMP b where a.NOMENCLATURE_ID = b.mmh_NOMENCLATURE_ID);

Commit;

Update GENOTYPE_SUMMARY_TMP a set NOMENCLATURE_ID = NVL((select NOMENCLATURE_ID
	from NOMENCLATURE_TMP b where a.ModelUID = b.ModelUID), a.NOMENCLATURE_ID);

Commit;

Truncate table GENOTYPE_SUMMARY;
Insert into GENOTYPE_SUMMARY
(GENOTYPE_SUMMARY_ID,
  SUMMARY,
  GENOTYPE,
  NOMENCLATURE_ID)
Select GENOTYPE_SUMMARY_ID,
  SUMMARY,
  GENOTYPE,
  NOMENCLATURE_ID from GENOTYPE_SUMMARY_TMP;

Commit;


DROP Table Organ_TMP;
CREATE TABLE ORGAN_TMP
(
  ORGAN_ID      NUMBER(19)                      NOT NULL,
	ExpressionsUID		number,
	HistopathologyUID	number,
	CellLinesUID		number,
	XenograftUID		number,
  NAME          VARCHAR2(255 BYTE)                  NULL,
  CONCEPT_CODE  VARCHAR2(255 BYTE)                  NULL
);

--UPDATE TABLE Organ_TMP 01 from datop.Expressions

CREATE OR REPLACE procedure Organ_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, ExpressionsUID, EVS_Organ_ID
		FROM datop.Expressions@camodop_datop
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE Organ_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Organ_TMP
		(Organ_ID,
		ExpressionsUID,
		Concept_Code)
      VALUES
     (aRec.ROWNUM,
      aRec.ExpressionsUID,
      aRec.EVS_Organ_ID);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Organ_TMP 02 from datop.Histopathology

CREATE OR REPLACE procedure Organ_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, HistopathologyUID, EVS_Organ_ID
		FROM datop.Histopathology@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Organ_ID) INTO V_MAXROW FROM Organ_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Organ_TMP
		(Organ_ID,
		HistopathologyUID,
		Concept_Code)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.HistopathologyUID,
      aRec.EVS_Organ_ID);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Organ_TMP 03 from datop.CellLines

CREATE OR REPLACE procedure Organ_TMP_03_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, CellLinesUID, EVS_Organ_ID
		FROM datop.CellLines@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Organ_ID) INTO V_MAXROW FROM Organ_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Organ_TMP
		(Organ_ID,
		CellLinesUID,
		Concept_Code)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.CellLinesUID,
      aRec.EVS_Organ_ID);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Organ_TMP 04 from datop.Xenograft

CREATE OR REPLACE procedure Organ_TMP_04_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, XenograftUID, EVS_Organ_ID
		FROM datop.Xenograft@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Organ_ID) INTO V_MAXROW FROM Organ_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Organ_TMP
		(Organ_ID,
		XenograftUID,
		Concept_Code)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.XenograftUID,
      aRec.EVS_Organ_ID);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE Organ_TMP_01_LD;
EXECUTE Organ_TMP_02_LD;
EXECUTE Organ_TMP_03_LD;
EXECUTE Organ_TMP_04_LD;

Truncate Table Organ;
Insert into ORGAN
(ORGAN_ID, NAME, CONCEPT_CODE)
Select ORGAN_ID, NAME, CONCEPT_CODE from organ_tmp;

Commit;


TRUNCATE TABLE Party;

INSERT INTO Party(Party_ID, Party_Type, First_name, Last_name, UserName)
SELECT SubmitterUID, 'PE', Firstname, Lastname, UserName
FROM datop.Submitter@camodop_datop;

commit;

Update party set IS_PRINCIPAL_INVESTIGATOR = 1 where party_id in(
select submitterUID from submitter_is_also_pi_stg);

Commit; 


DROP table Taxon_tmp;
CREATE TABLE TAXON_TMP
(
  TAXON_ID          NUMBER(19)                  NOT NULL,
	mmh_taxon_ID	number,
	speciesUID	number,
	strainUID	number,
	originSpeciesUID	number,
  SCIENTIFIC_NAME   VARCHAR2(255 BYTE)              NULL,
  ETHNICITY_STRAIN  VARCHAR2(255 BYTE)              NULL,
  ABBREVIATION      VARCHAR2(255 BYTE)              NULL,
  COMMON_NAME       VARCHAR2(255 BYTE)              NULL
);

--UPDATE TABLE Taxon_tmp 01 from cabiommh.taxon

CREATE OR REPLACE procedure Taxon_tmp_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, Taxon_ID, Scientific_name, strain_or_ethnicity, abbreviation, common_name
		FROM cabiommh.taxon@camoddev_cabiommh
		where taxon_ID > 2000 
			or Taxon_ID between 81 and 98 or taxon_ID in (13, 35, 54, 67)
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE Taxon_tmp REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Taxon_TMP (
	TAXON_ID,
	mmh_taxon_ID,
  	SCIENTIFIC_NAME,
  	ETHNICITY_STRAIN,
  	ABBREVIATION,
  	COMMON_NAME)
      VALUES
     (aRec.ROWNUM,
      aRec.Taxon_ID,
      aREC.Scientific_name,
      aRec.strain_or_ethnicity,
      aRec.abbreviation,
      aRec.common_name);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Taxon_TMP 02 from datop.species

CREATE OR REPLACE procedure Taxon_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, SpeciesUID, LatinName, EnglishName 
	from datop.species@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Taxon_ID) INTO V_MAXROW FROM Taxon_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Taxon_TMP (
	TAXON_ID,
	SpeciesUID,
  	SCIENTIFIC_NAME,
  	COMMON_NAME)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.SpeciesUID,
      aREC.LatinName,
      aRec.EnglishName);


      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Taxon_TMP 03 from datop.strain

CREATE OR REPLACE procedure Taxon_TMP_03_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, StrainUID, StrainName 
	from datop.strain@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Taxon_ID) INTO V_MAXROW FROM Taxon_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Taxon_TMP (
	TAXON_ID,
	strainUID,
  	Ethnicity_strain)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.StrainUID,
      aREC.StrainName);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE Taxon_TMP 04 from datop.OriginSpecies

CREATE OR REPLACE procedure Taxon_TMP_04_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, SpeciesUID, LatinName, EnglishName 
	from datop.Originspecies@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Taxon_ID) INTO V_MAXROW FROM Taxon_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO Taxon_TMP (
	TAXON_ID,
	OriginSpeciesUID,
  	SCIENTIFIC_NAME,
  	COMMON_NAME)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.SpeciesUID,
      aREC.LatinName,
      aRec.EnglishName);


      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE Taxon_TMP_01_LD;
EXECUTE Taxon_TMP_02_LD;
EXECUTE Taxon_TMP_03_LD;
EXECUTE Taxon_TMP_04_LD;

--update scientific_name and common_name for the strains

Create view strain_view_tmp as select a.strainUID, b.latinname, b.EnglishName
from datop.strain@camodop_datop a, datop.species@camodop_datop b
where a.specieskey = b.speciesUID;

update taxon_tmp a set a.scientific_name = NVL ((select b.latinName from strain_view_tmp b
	where a.strainUID = b.strainUID), a.scientific_name);

update taxon_tmp a set a.Common_Name = NVL ((select b.EnglishName from strain_view_tmp b
	where a.strainUID = b.strainUID), a.Common_Name);

drop view strain_view_tmp;

Truncate table taxon;
Insert into TAXON (TAXON_ID,
  SCIENTIFIC_NAME,
  ETHNICITY_STRAIN,
  ABBREVIATION,
  COMMON_NAME)
select TAXON_ID,
  SCIENTIFIC_NAME,
  ETHNICITY_STRAIN,
  ABBREVIATION,
  COMMON_NAME from Taxon_tmp;

commit;


/***********************Take a long time to run*************************/

DROP table Treatment_tmp;
CREATE TABLE TREATMENT_TMP
(
  TREATMENT_ID          NUMBER(19)              NOT NULL,
	TreatmentSchedule_ID	number,
	CarcinogenUID		number,
  REGIMEN               VARCHAR2(255 BYTE)          NULL,
  DOSAGE                VARCHAR2(255 BYTE)          NULL,
  ADMINISTRATIVE_ROUTE  VARCHAR2(255 BYTE)          NULL,
  AGE_AT_TREATMENT      VARCHAR2(255 BYTE)          NULL,
  SEX_DISTRIBUTION_ID   NUMBER(19)                  NULL
);

DROP table carcinogen_tmp;
create table carcinogen_tmp as
SELECT CarcinogenUID, treatmentlength, dose, age, sex
FROM datop.carcinogen@camodop_datop;

update carcinogen_tmp set sex = null where sex not in ('1','2','3');
commit;

--UPDATE TABLE TREATMENT_TMP 01 from cabiommh.TreatmentSchedule

CREATE OR REPLACE procedure TREATMENT_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (SELECT ROWNUM, TreatmentSchedule_id, Regimen, Dosage 
	FROM cabiommh.treatmentSchedule@camoddev_cabiommh
	Where TreatmentSchedule_id > 2000 or TreatmentSchedule_id between 26 and 32
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE TREATMENT_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO TREATMENT_TMP (Treatment_id, TreatmentSchedule_id, Regimen, Dosage)
      VALUES
     (aRec.ROWNUM,
      aRec.TreatmentSchedule_id,
      aREC.Regimen,
      aRec.Dosage);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE TREATMENT_TMP 02 from datop.carcinogen

CREATE OR REPLACE procedure TREATMENT_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, CarcinogenUID, treatmentlength, dose, age, SEX
	 from carcinogen_tmp);

  aID number:=0;

BEGIN

   SELECT MAX(Treatment_ID) INTO V_MAXROW FROM Treatment_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO TREATMENT_TMP (
	TREATMENT_ID,
	CarcinogenUID,
  	REGIMEN,
  	DOSAGE,
  	AGE_AT_TREATMENT,
  	SEX_DISTRIBUTION_ID)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.CarcinogenUID,
      aREC.treatmentlength,
      aRec.dose,
	aRec.age,
	aRec.sex);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE Treatment_TMP_01_LD;

EXECUTE Treatment_TMP_02_LD;

-- DROP table carcinogen_tmp;

create view carcinogen_route_view as select a.carcinogenUID, b.agentname 
from datop.carcinogen@camodop_datop a, datop.carcinogenagent@camodop_datop b
where a.carcinogenagentadminKey = b.carcinogenagentUID;

update treatment_tmp a set administrative_Route = (select agentname 
from carcinogen_route_view b 
where a.carcinogenUID = b.carcinogenUID) where a.carcinogenUID is not null;

Commit;

DROP view carcinogen_route_view;

Truncate table Treatment;
Insert into TREATMENT (
  TREATMENT_ID,
  REGIMEN,
  DOSAGE,
  ADMINISTRATIVE_ROUTE,
  AGE_AT_TREATMENT,
  SEX_DISTRIBUTION_ID)
Select TREATMENT_ID,
  REGIMEN,
  DOSAGE,
  ADMINISTRATIVE_ROUTE,
  AGE_AT_TREATMENT,
  SEX_DISTRIBUTION_ID from Treatment_tmp;

Commit;

/*********************Animal_Distributor*******************************/

TRUNCATE TABLE camoddev.Animal_Distributor;

INSERT INTO camoddev.Animal_Distributor(Animal_Distributor_id, Name)
Values(1, 'Investigator');
INSERT INTO camoddev.Animal_Distributor(Animal_Distributor_id, Name)
Values(2, 'Jackson Laboratory');
INSERT INTO camoddev.Animal_Distributor(Animal_Distributor_id, Name)
Values(3, 'MMHCC Repository');
INSERT INTO camoddev.Animal_Distributor(Animal_Distributor_id, Name)
Values(4, 'IMSR');

commit;

/**************************PUBLICATION***************************/

TRUNCATE TABLE camoddev.PUBLICATION;

insert INTO camoddev.PUBLICATION(PUBLICATION_ID,
JOURNAL,
PMID,
AUTHORS,
YEAR,
VOLUME,
START_PAGE,
END_PAGE,
PUBLICATION_STATUS_ID,
TITLE)
SELECT PUBLICATIONUID,
JOURNAL,
PMID,
AUTHOR,
YEAR,
VOLUME,
PAGESTART,
PAGEEND,
PUBLICATIONSTATUSKEY,
TITLE
FROM datop.PUBLICATION@camodop_datop;

commit;



/************************Agent_Agent_Target**********************************/

TRUNCATE TABLE camoddev.Agent_Agent_Target;

insert INTO camoddev.Agent_Agent_Target (Agent_Target_ID, EnV_FACTOR_ID)
SELECT TargetKey, TherapyKey
FROM datop.TargetTherapy@camodop_datop;

commit;

Update Agent_Agent_Target a set ENV_FACTOR_ID = (select ENV_FACTOR_ID
	from ENV_FACTOR_TMP b where a.EnV_FACTOR_ID = b.TherapyUID);

Commit;

/************************Agent_Biological_Process**********************************/

TRUNCATE TABLE camoddev.Agent_Biological_Process;

insert INTO camoddev.Agent_Biological_Process (Biological_Process_ID, EnV_FACTOR_ID)
SELECT BioprocessKey, TherapyKey
FROM datop.BiologicalTherapy@camodop_datop;

commit;

Update Agent_Biological_Process a set ENV_FACTOR_ID = (select ENV_FACTOR_ID
	from ENV_FACTOR_TMP b where a.EnV_FACTOR_ID = b.TherapyUID);

Commit;

/************************Agent_Chemical_Class**********************************/

TRUNCATE TABLE camoddev.Agent_Chemical_Class;

insert INTO camoddev.Agent_Chemical_Class (Chemical_Class_ID, EnV_FACTOR_ID)
SELECT ChemicalClassKey, TherapyKey
FROM datop.ChemicalTherapy@camodop_datop;

commit;

Update Agent_Chemical_Class a set ENV_FACTOR_ID = (select ENV_FACTOR_ID
	from ENV_FACTOR_TMP b where a.EnV_FACTOR_ID = b.TherapyUID);

Commit;

/************************CELL_LINE**********************************/

TRUNCATE TABLE CELL_LINE;
Insert into CELL_LINE (
  CELL_LINE_ID,
  COMMENTS,
  EXPERIMENT,
  NAME,
  RESULTS)
Select   CellLinesUID,
  COMMENTS,
  EXPERIMENT,
  NAME,
  RESULTS from datop.Celllines@camodop_datop ;

Commit;

Update CELL_LINE a set Organ_ID = (select Organ_ID
	from Organ_TMP b where a.CELL_LINE_ID = b.CellLinesUID);

Commit;

/********************MICRO_ARRAY_DATA****************************/

DROP TABLE MICRO_ARRAY_DATA_TMP;
CREATE TABLE MICRO_ARRAY_DATA_TMP
(
  MICRO_ARRAY_DATA_ID  NUMBER(19)               NOT NULL,
	ModelID		number,
  EXPERIMENT_NAME      VARCHAR2(255 BYTE)           NULL,
  EXPERIMENT_ID        NUMBER(19)                   NULL,
  OTHER_LOCATION_URL   VARCHAR2(255 BYTE)           NULL,
  AVAILABILITY_ID      NUMBER(19)                   NULL
);

TRUNCATE TABLE camoddev.MICRO_ARRAY_DATA_TMP;

 insert INTO camoddev.MICRO_ARRAY_DATA_TMP (
	MICRO_ARRAY_DATA_ID,
	ModelID,
  	EXPERIMENT_NAME,
  	EXPERIMENT_ID)  
 SELECT 
	rownum,
	ModelID,
	EXPERIMENTNAME,
	EXPERIMENTID	
 FROM datop.Microarray@camodop_datop;

commit;

TRUNCATE TABLE camoddev.MICRO_ARRAY_DATA;

 insert INTO camoddev.MICRO_ARRAY_DATA (
	MICRO_ARRAY_DATA_ID,
  	EXPERIMENT_NAME,
  	EXPERIMENT_ID)  
 SELECT 
	MICRO_ARRAY_DATA_ID,
  	EXPERIMENT_NAME,
  	EXPERIMENT_ID  
 FROM MICRO_ARRAY_DATA_TMP;

commit;

/**********************Model_Section********************************/

TRUNCATE TABLE camoddev.Model_Section;

INSERT INTO camoddev.Model_Section(Model_Section_id, name)
SELECT pagestatusuid,
status
FROM datop.commentedpage_status@camodop_datop;

commit;

/************************Regulatory_Element**********************************/

TRUNCATE TABLE camoddev.Regulatory_Element;

insert INTO camoddev.Regulatory_Element (Regulatory_Element_ID, Name, 
	Reg_Element_Type_ID, Taxon_ID)
SELECT ElementUID, Name, ElementTypeKey, SpeciesKey
FROM datop.RegulatoryElement@camodop_datop;

commit;

Update Regulatory_Element a set Taxon_ID = (select Taxon_ID
	from Taxon_TMP b where a.Taxon_ID = b.SpeciesUID);

Commit;

/************************Organ_Exp_Level_Desc**********************************/

DROP TABLE ORGAN_EXP_LEVEL_DESC_TMP;
CREATE TABLE ORGAN_EXP_LEVEL_DESC_TMP
(
  EXP_LEVEL_DESC_ID  NUMBER(19)                 NOT NULL,
	ExpressionsUID number,	
  ORGAN_ID           NUMBER(19)
);

TRUNCATE TABLE camoddev.Organ_Exp_Level_Desc_TMP;

insert INTO camoddev.Organ_Exp_Level_Desc_TMP (Exp_Level_Desc_ID, ExpressionsUID)
SELECT ExpressionLevelKey, ExpressionsUID
FROM datop.Expressions@camodop_datop where ExpressionLevelKey is not null;

commit;

Update Organ_Exp_Level_Desc_TMP a set Organ_ID = (select Organ_ID
	from Organ_TMP b where a.ExpressionsUID = b.ExpressionsUID);

Commit;

/* Update Organ_Exp_Level_Desc_TMP a set Exp_Level_Desc_id = (select Exp_Level_Desc_id
	from Exp_Level_Desc b where a.Exp_Level_Desc_id = b.Exp_Level_Desc_id);

Commit; */

TRUNCATE TABLE camoddev.Organ_Exp_Level_Desc;

insert INTO camoddev.Organ_Exp_Level_Desc (Exp_Level_Desc_ID, Organ_ID)
SELECT EXP_LEVEL_DESC_ID, Organ_ID
FROM Organ_Exp_Level_Desc_TMP;

commit;

************************Expression_Feature**********************************/

Truncate TABLE Expression_Feature;

insert INTO Expression_Feature (Expression_Feature_ID, Exp_Level_Desc_ID, organ_ID)
SELECT a.ExpressionsUID, a.ExpressionLevelKey, b.organ_ID
FROM datop.Expressions@camodop_datop a, organ_Tmp b 
where a.ExpressionsUID = b.ExpressionsUID and a.ExpressionLevelKey is not null;

commit;


/************************Party_Role**********************************/

TRUNCATE TABLE camoddev.Party_Role;

insert INTO camoddev.Party_Role (Party_ID, Role_ID)
SELECT SubmitterKey, UserTypeKey
FROM datop.User_Role@camodop_datop;

commit;

/************************Party_Contact_Info**********************************/

TRUNCATE TABLE camoddev.Party_Contact_Info;

insert INTO camoddev.Party_Contact_Info (Contact_Info_ID, Party_ID)
SELECT SubmitterUID, SubmitterUID
FROM datop.Submitter@camodop_datop;

commit;

/************************Modification_Type**********************************/

TRUNCATE TABLE camoddev.Modification_Type;

insert INTO Modification_Type (Modification_Type_ID, Name)
SELECT ModificationTypeUID, Modification_Type
FROM datop.ModificationType@camodop_datop;

commit;

/************************Disease**********************************/

TRUNCATE TABLE Disease;

insert INTO Disease (Disease_ID, Name, Concept_Code)
SELECT HistopathologyUID, TumorClassification, EVS_ID
FROM datop.Histopathology@camodop_datop;

commit;

/***********************Take a long time******************************/

TRUNCATE TABLE camoddev.screening_result;

INSERT INTO camoddev.screening_result(screening_result_id, stage, 
diffinh, aveinh, inhibition_rate, treatment_ID, agent_ID)
SELECT screeningresult_id, stage, 
diffinh, aveinh, inhibition_rate, treatmentschedule_ID, agent_ID
FROM cabiommh.screeningresult@camoddev_cabiommh;

commit;

--update Treatment_ID from Treatment_TMP

CREATE UNIQUE INDEX CAMODDEV.treatmentschedule_ID_INDEX ON CAMODDEV.TREATMENT_TMP
(treatmentschedule_ID);

Update screening_result a set Treatment_ID = (select Treatment_ID
	from Treatment_TMP b where a.treatment_ID = b.treatmentschedule_ID);

Commit;

DROP INDEX CAMODDEV.treatmentschedule_ID_INDEX; 

CREATE UNIQUE INDEX CAMODDEV.agent_ID_INDEX ON CAMODDEV.ENV_FACTOR_TMP
(agent_ID);

Update screening_result a set Agent_ID = (select ENV_FACTOR_ID from ENV_FACTOR_TMP b 
where a.agent_ID = b.agent_ID);

Commit;

DROP INDEX CAMODDEV.agent_ID_INDEX;


/* This scripts must be run after availability table was loaded 02 */

DROP TABLE ABS_CANCER_MODEL_TMP CASCADE CONSTRAINTS;

CREATE TABLE ABS_CANCER_MODEL_TMP
(
  ABS_CANCER_MODEL_ID        NUMBER(19)         NOT NULL,
	ModelUID        	NUMBER(19),
	Xenograft_ID        NUMBER(19),
	YeastModel_ID        NUMBER(19),
	Xenograft_UID		NUMBER,
  ABS_CANCER_MODEL_TYPE      VARCHAR2(255 BYTE) NOT NULL,
  EXPERIMENT_DESIGN          CLOB                   NULL,
  MODEL_DESCRIPTOR           VARCHAR2(255 BYTE)     NULL,
  STATE                      VARCHAR2(255 BYTE)     NULL,
  AVAILABILITY_ID            NUMBER(19)             NULL,
  TAXON_ID                   NUMBER(19)             NULL,
	SpeciesKey	number,
	StrainKey	number,
  SUBMITTER_ID               NUMBER(19)             NULL,
  PRINCIPAL_INVESTIGATOR_ID  NUMBER(19)             NULL,
  URL                        VARCHAR2(255 BYTE)     NULL,
  IS_TOOL_MOUSE              NUMBER(1)              NULL,
  REPOSITORY_INFO_ID         NUMBER(19)             NULL,
  PHENOTYPE_ID               NUMBER(19)             NULL,
  ADMINISTRATIVE_SITE        VARCHAR2(255 BYTE)     NULL,
  GENETIC_MANIPULATION       VARCHAR2(255 BYTE)     NULL,
  MODIFICATION_DESCRIPTION   VARCHAR2(255 BYTE)     NULL,
  PARENTAL_CELL_LINE_NAME    VARCHAR2(255 BYTE)     NULL,
  NAME                       VARCHAR2(255 BYTE)     NULL,
  ATCC_NUMBER                VARCHAR2(255 BYTE)     NULL,
  CELL_AMOUNT                VARCHAR2(255 BYTE)     NULL,
  HARVEST_DATE               DATE                   NULL,
  GRAFT_TYPE                 VARCHAR2(255 BYTE)     NULL,
  TUMOR_CODE_ID              NUMBER(19)             NULL,
  ORIGIN_SPECIES_ID          NUMBER(19)             NULL,
  HOST_SPECIES_ID            NUMBER(19)             NULL,
  organ_ID			number,
  PAR_ABS_CAN_MODEL_ID       NUMBER(19)             NULL,
  graft_type_unctrl_vocab	varchar2(255)
);

--UPDATE TABLE ABS_CANCER_MODEL_TMP 01 from datop.AnimalModel

CREATE OR REPLACE procedure ABS_CANCER_MODEL_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, ModelUID, ModelName, SubmitterKey, URL, ModelTypeKey, speciesKey, StrainKey, 
	decode(Visibility, 
0, 'Incomplete',                                                                                          
1, 'Complete-not screened',                                                                               
2, 'Screener-assigned',                                                                                   
3,  'Screened-approved',                                                                                   
4, 'Screened-rejected',                                                                                   
5,                   'Editor-assigned',                                                                                     
6,                   'Edited-approved',                                                                                     
7,                   'Edited-need more info',                                                                               
8,                   'Reviewer-assigned',                                                                                   
9,                   'Reviewed-approved',                                                                                   
10,                  'Reviewed-need more info' ,                                                                            
11,                  'Comments: completed-not screened',                                                                   
12,                  'Comments: Screener-assigned',                                                                         
13,                  'Comments: Screened-approved' ,                                                                        
14,                  'Comments: Screened-rejected', NULL) state    
		FROM datop.AnimalModel@camodop_datop
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE ABS_CANCER_MODEL_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

	IF aREC.ModelTypeKey = 8 then
      INSERT INTO ABS_CANCER_MODEL_TMP
		(ABS_CANCER_MODEL_ID,
		ModelUID,
		ABS_CANCER_MODEL_TYPE,
		MODEL_DESCRIPTOR,
		SUBMITTER_ID,
		URL,
		IS_TOOL_MOUSE,
		speciesKey,
		strainKey, state)
      VALUES
     (aRec.ROWNUM,
      aRec.ModelUID,
      'AM',
      aREC.ModelName,
      aRec.SubmitterKey,
      aRec.URL,
      0,
	aRec.speciesKey,
	aRec.strainKey,
	aRec.state);

	ELSE 
      INSERT INTO ABS_CANCER_MODEL_TMP
		(ABS_CANCER_MODEL_ID,
		ModelUID,
		ABS_CANCER_MODEL_TYPE,
		MODEL_DESCRIPTOR,
		SUBMITTER_ID,
		URL,
		IS_TOOL_MOUSE,
		speciesKey,
		strainKey, state)
      VALUES
     (aRec.ROWNUM,
      aRec.ModelUID,
      'AM',
      aREC.ModelName,
      aRec.SubmitterKey,
      aRec.URL,
      1,
	aRec.speciesKey,
	aRec.strainKey,
	aRec.state);

	END IF;

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ABS_CANCER_MODEL_TMP 02 from cabiommh.Xenograft

CREATE OR REPLACE procedure ABS_CANCER_MODEL_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, Xenograft_ID, ModelDescriptor, AVAILABILITY_ID, Taxon_ID, 
	AdministrativeSite, Tumor_Code_ID, Hostspecies_ID 
	from cabiommh.Xenograft@camoddev_cabiommh where Xenograft_ID > 2000);

  aID number:=0;

BEGIN

   SELECT MAX(ABS_CANCER_MODEL_ID) INTO V_MAXROW FROM ABS_CANCER_MODEL_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ABS_CANCER_MODEL_TMP(
		ABS_CANCER_MODEL_ID,
		Xenograft_ID,
		ABS_CANCER_MODEL_TYPE,
		MODEL_DESCRIPTOR,
		AVAILABILITY_ID,
		Taxon_ID,
		ADMINISTRATIVE_SITE,
		TUMOR_CODE_ID,
		HOST_SPECIES_ID, state)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.Xenograft_ID,
      'X',
      aRec.ModelDescriptor,
      aRec.AVAILABILITY_ID,
      aRec.Taxon_ID,
	aRec.AdministrativeSite,
	aRec.Tumor_Code_ID,
	aRec.Hostspecies_ID,
	'Reviewed-approved');

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ABS_CANCER_MODEL_TMP 03 from cabiommh.YeastModel

CREATE OR REPLACE procedure ABS_CANCER_MODEL_TMP_03_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, YeastModel_ID, ModelDescriptor, AVAILABILITY_ID, Taxon_ID  
	from cabiommh.YeastModel@camoddev_cabiommh);

  aID number:=0;

BEGIN

   SELECT MAX(ABS_CANCER_MODEL_ID) INTO V_MAXROW FROM ABS_CANCER_MODEL_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ABS_CANCER_MODEL_TMP(
		ABS_CANCER_MODEL_ID,
		YeastModel_ID,
		ABS_CANCER_MODEL_TYPE,
		MODEL_DESCRIPTOR,
		AVAILABILITY_ID,
		Taxon_ID, state)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.YeastModel_ID,
      'YM',
      aRec.ModelDescriptor,
      aRec.AVAILABILITY_ID,
      aRec.Taxon_ID,
	'Reviewed-approved');

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ABS_CANCER_MODEL_TMP 04 from datop.Xenograft

CREATE OR REPLACE procedure ABS_CANCER_MODEL_TMP_04_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, XenograftUID, GeneticAlteration, ModificationMethod, 
	ParentalCellLine, CellLine, originspecieskey
	from datop.Xenograft@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(ABS_CANCER_MODEL_ID) INTO V_MAXROW FROM ABS_CANCER_MODEL_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ABS_CANCER_MODEL_TMP(
		ABS_CANCER_MODEL_ID,
		Xenograft_UID,
		ABS_CANCER_MODEL_TYPE,
		GENETIC_MANIPULATION,
		MODIFICATION_DESCRIPTION,
		PARENTAL_CELL_LINE_NAME,
		NAME, state, origin_species_ID)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.XenograftUID,
      'X',
      aRec.GeneticAlteration,
      aRec.ModificationMethod,
      aRec.ParentalCellLine,
	aRec.CellLine,
	'Reviewed-approved', aRec.originspecieskey);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE ABS_CANCER_MODEL_TMP_01_LD;
EXECUTE ABS_CANCER_MODEL_TMP_02_LD;
EXECUTE ABS_CANCER_MODEL_TMP_03_LD;
EXECUTE ABS_CANCER_MODEL_TMP_04_LD;


--update Par_ABS_CAN_MODEL_ID

Create view update_PAR_ABS as
select ACMT.ABS_CANCER_Model_ID, X.ModelKey, X.XenograftUID
from ABS_Cancer_Model_TMP ACMT, datop.Xenograft@camodop_datop X
WHERE ACMT.ModelUID = X.ModelKey;

update ABS_Cancer_Model_TMP ACMT set Par_ABS_CAN_Model_ID = (select upa.ABS_CANCER_MODEL_ID 
from update_PAR_ABS upa where ACMT.Xenograft_UID = upa.XenograftUID);

DROP view update_PAR_ABS;

-- update Experiment_design
update ABS_CANCER_MODEL_TMP at set Experiment_Design = (select al.comments_lob 
	from datop.ANIMALMODEL_LOB@camodop_datop al
	where at.Modeluid = al.AnimalmodelUID);
Commit;

-- update repository_info_id 
update ABS_CANCER_MODEL_TMP a set repository_info_id = NVL((select b.repository_info_id 
	from repository_info_TMP b
	where a.Modeluid = b.modelUID), a.repository_info_id);
Commit;

-- update availability_id for animalmodel and from cabiommh 
update ABS_CANCER_MODEL_TMP a set availability_id = NVL((select b.availability_id 
	from AVAILABILITY_TMP b
	where a.modelUID = b.modelUID), a.availability_id);
Commit;

update ABS_CANCER_MODEL_TMP a set availability_id = NVL((select b.availability_id 
	from AVAILABILITY_TMP b
	where a.availability_id = b.mmh_availability_id), a.availability_id);
Commit;

-- update phenotype_ID
update ABS_CANCER_MODEL_TMP a set phenotype_id = NVL((select b.phenotype_ID 
	from Phenotype_TMP b
	where a.Modeluid = b.modelUID), a.phenotype_id);
Commit;


-- update Taxon_ID---01 from cabiommh or other
update ABS_CANCER_MODEL_TMP a set Taxon_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.taxon_ID = b.mmh_taxon_ID), a.Taxon_ID);
Commit;

update ABS_CANCER_MODEL_TMP a set Taxon_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.speciesKey = b.speciesUID), a.Taxon_ID);
Commit;

update ABS_CANCER_MODEL_TMP a set Taxon_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.StrainKey = b.StrainUID), a.Taxon_ID);
Commit;

-- update Origin_Species_ID from Xenograft
update ABS_CANCER_MODEL_TMP a set Origin_Species_ID = (select b.Taxon_ID from taxon_TMP b
	where a.Origin_Species_ID = b.OriginSpeciesUID);
Commit;

-- update Organ_ID from Xenograft
update ABS_CANCER_MODEL_TMP a set organ_ID = (select b.organ_ID from organ_TMP b
	where a.xenograft_UID = b.xenograftUID);
Commit;

-- update Host_Species_ID from cabiommh.Xenograft or other
update ABS_CANCER_MODEL_TMP a set Host_Species_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.Host_Species_ID = b.SpeciesUID), a.Taxon_ID);
Commit;

-- update Host_Species_ID from cabiommh.Xenograft or other
update ABS_CANCER_MODEL_TMP a set Host_Species_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.speciesKey = b.speciesUID), a.Taxon_ID);
Commit;

-- update Host_Species_ID from cabiommh.Xenograft or other
update ABS_CANCER_MODEL_TMP a set Host_Species_ID = NVL((select b.Taxon_ID from taxon_TMP b
	where a.StrainKey = b.StrainUID), a.Taxon_ID);
Commit;

-- update PRINCIPAL_INVESTIGATOR_ID
update ABS_CANCER_MODEL_TMP set PRINCIPAL_INVESTIGATOR_ID = submitter_ID
	where submitter_ID in (select submitterUID from SUBMITTER_IS_ALSO_PI_STG);
Commit;

Create table ABS_CANCER_MODEL_TMP_01 as select * from ABS_CANCER_MODEL_TMP;
Alter table ABS_CANCER_MODEL_TMP_01 drop (ModelUID, Xenograft_ID, YeastModel_ID, 
	SpeciesKey, StrainKey, Xenograft_UID);

Truncate table ABS_CANCER_MODEL;
Insert into ABS_CANCER_MODEL select * from ABS_CANCER_MODEL_TMP_01;
commit;

DROP table ABS_CANCER_MODEL_TMP_01;	



DROP TABLE ENGINEERED_GENE_TMP CASCADE CONSTRAINTS;

CREATE TABLE ENGINEERED_GENE_TMP
(
  ENGINEERED_GENE_ID       NUMBER(19)           NOT NULL,
	ENGINEEREDGENE_ID	number,
	GenotypesUID		number,
	modelkey		number,
  ENGINEERED_GENE_TYPE     VARCHAR2(255 BYTE)   	NULL,
  CABIO_ID                 NUMBER(19)               NULL,
  NAME                     VARCHAR2(255 BYTE)       NULL,
  COMMENTS                 VARCHAR2(2000)       NULL,
  CONDITIONALITY_ID        NUMBER(19)               NULL,
  GENOTYPE_SUMMARY_ID      NUMBER(19)               NULL,
  IMAGE_ID                 NUMBER(19)               NULL,
  MUTATION_IDENTIFIER_ID   NUMBER(19)               NULL,
  LOCATION_OF_INTEGRATION  VARCHAR2(255 BYTE)       NULL,
  SEGMENT_SIZE             VARCHAR2(255 BYTE)       NULL,
  CLONE_DESIGNATOR         VARCHAR2(255 BYTE)       NULL,
  GENE_ID                  VARCHAR2(255 BYTE)       NULL,
  DESCRIPTION              VARCHAR2(255 BYTE)       NULL,
  ES_CELL_LINE_NAME        VARCHAR2(255 BYTE)       NULL,
  BLASTOCYST_NAME          VARCHAR2(255 BYTE)       NULL
);

--UPDATE TABLE ENGINEERED_GENE_TMP 01 from cabiommh.ENGINEEREDGENE

CREATE OR REPLACE procedure ENGINEERED_GENE_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, ENGINEEREDGENE_ID, Name, genotypeSummary_ID
	FROM cabiommh.ENGINEEREDGENE@camoddev_cabiommh 
		where ENGINEEREDGENE_ID between 3000 and 3058);

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE ENGINEERED_GENE_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ENGINEERED_GENE_TMP (
	ENGINEERED_GENE_ID,
	ENGINEEREDGENE_ID,
  	Name,
  	GENOTYPE_Summary_ID,
	ENGINEERED_GENE_Type)
      VALUES
     (aRec.ROWNUM,
      aRec.ENGINEEREDGENE_ID,
      aREC.Name,
      aRec.genotypeSummary_ID,
	'IM');

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE ENGINEERED_GENE_TMP 02 from datop.Genetic_Description

CREATE OR REPLACE procedure ENGINEERED_GENE_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, GenotypesUID, Decode(GemtypeKey, 1, 'T', 2, 'GS', 3, 'TM', 4, 'IM', NULL) TYPE,
		GeneName, Additional_Feature, GeneLocation,
		LocusLinkID, RecipientStrain, modelkey 
	from datop.Genetic_Description@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(ENGINEERED_GENE_ID) INTO V_MAXROW FROM ENGINEERED_GENE_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO ENGINEERED_GENE_TMP (
	ENGINEERED_GENE_ID,
	GenotypesUID,
  	ENGINEERED_GENE_Type,
	Name,
	Comments,
	Location_of_Integration,
	Gene_ID,
	ES_Cell_Line_Name,
	modelkey)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.GenotypesUID,
      aREC.Type,
	aRec.GeneName,
	aRec.Additional_Feature,
	aRec.GeneLocation,
	aRec.LocusLinkID,
	aRec.RecipientStrain,
	aRec.modelkey);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE ENGINEERED_GENE_TMP_01_LD;
EXECUTE ENGINEERED_GENE_TMP_02_LD;

Update ENGINEERED_GENE_TMP a set Image_ID = (select ImageUID 
	from datop.constructs@camodop_datop b
	where a.GenotypesUID = b.GenotypesUID);

Commit;

--No Species_ID column in the new model

/* Update ENGINEERED_GENE_TMP a set Species_ID = (select Taxon_ID 
	from Taxon_TMP b
	where a.Species_ID = b.OriginspeciesUID);

Commit; */

Update ENGINEERED_GENE_TMP a set Genotype_Summary_ID = NVL((select Genotype_Summary_ID 
	from Genotype_Summary_TMP b
	where a.Genotype_Summary_ID = b.mmh_GenotypeSummary_ID), a.Genotype_Summary_ID);

Commit;

Update ENGINEERED_GENE_TMP a set Genotype_Summary_ID = NVL((select Genotype_Summary_ID 
	from Genotype_Summary_TMP b
	where a.modelkey = b.modelUID), a.Genotype_Summary_ID);

Commit;

Update ENGINEERED_GENE_TMP a set Segment_Size = (select Segment_Size 
	from datop.SegmentDesignator@camodop_datop b
	where a.GenotypesUID = b.GenotypeKey);

Commit;

Update ENGINEERED_GENE_TMP a set Clone_Designator = (select Designator 
	from datop.SegmentDesignator@camodop_datop b
	where a.GenotypesUID = b.GenotypeKey);

Commit;

Create view segment_view as select a.GenotypesUID, b.SegmentTypeKey 
	from  ENGINEERED_GENE_TMP a, datop.SegmentDesignator@camodop_datop b
	where a.GenotypesUID = b.GenotypeKey;

--Segment_Type_ID not exist
/* Update ENGINEERED_GENE_TMP a set Segment_Type_ID = (select b.Segment_Type_ID
	from Segment_Type b, segment_view c
	where a.GenotypesUID = c.GenotypesUID AND b.Segment_Type_ID = c.SegmentTypeKey);

Commit; */

DROP VIEW segment_view;

Truncate TABLE ENGINEERED_GENE;

Insert into ENGINEERED_GENE
(
  ENGINEERED_GENE_ID,
  ENGINEERED_GENE_TYPE,
  CABIO_ID,
  NAME,
  COMMENTS,
  CONDITIONALITY_ID,
  GENOTYPE_SUMMARY_ID,
  IMAGE_ID,
  MUTATION_IDENTIFIER_ID,
  LOCATION_OF_INTEGRATION,
  SEGMENT_SIZE,
  CLONE_DESIGNATOR,
  GENE_ID,
  DESCRIPTION,
  ES_CELL_LINE_NAME,
  BLASTOCYST_NAME)
Select ENGINEERED_GENE_ID,
  ENGINEERED_GENE_TYPE,
  CABIO_ID,
  NAME,
  COMMENTS,
  CONDITIONALITY_ID,
  GENOTYPE_SUMMARY_ID,
  IMAGE_ID,
  MUTATION_IDENTIFIER_ID,
  LOCATION_OF_INTEGRATION,
  SEGMENT_SIZE,
  CLONE_DESIGNATOR,
  GENE_ID,
  DESCRIPTION,
  ES_CELL_LINE_NAME,
  BLASTOCYST_NAME from ENGINEERED_GENE_TMP;

Commit;


DROP TABLE HISTOPATHOLOGY_TMP CASCADE CONSTRAINTS;

CREATE TABLE HISTOPATHOLOGY_TMP
(
  HISTOPATHOLOGY_ID         NUMBER(19)          NOT NULL,
  COMMENTS                  CLOB                    NULL,
  GROSS_DESCRIPTION         VARCHAR2(2000 BYTE)     NULL,
  RELATIONAL_OPERATION      VARCHAR2(255 BYTE)      NULL,
  TUMOR_INCIDENCE_RATE      FLOAT(126)              NULL,
  SURVIVAL_INFO             VARCHAR2(255 BYTE)      NULL,
  AGE_OF_ONSET              VARCHAR2(255 BYTE)      NULL,
  MICROSCOPIC_DESCRIPTION   VARCHAR2(2000 BYTE)     NULL,
  WEIGHT_OF_TUMOR           FLOAT(126)              NULL,
  VOLUME_OF_TUMOR           FLOAT(126)              NULL,
  COMPARATIVE_DATA          VARCHAR2(255 BYTE)      NULL,
  GENETIC_ALTERATION_ID     NUMBER(19)              NULL,
	GENETIC_ALTERATION_varch	varchar2(255),
  PARENT_HISTOPATHOLOGY_ID  NUMBER(19)              NULL
);


TRUNCATE table HISTOPATHOLOGY_TMP;

INSERT into HISTOPATHOLOGY_TMP (
  HISTOPATHOLOGY_ID,
  COMMENTS,
  GROSS_DESCRIPTION,
  TUMOR_INCIDENCE_RATE,
  SURVIVAL_INFO,
  AGE_OF_ONSET,
  MICROSCOPIC_DESCRIPTION,
  WEIGHT_OF_TUMOR,
  VOLUME_OF_TUMOR,
	GENETIC_ALTERATION_varch,
  PARENT_HISTOPATHOLOGY_ID)
Select
  HISTOPATHOLOGYUID,
  COMMENTS,
  MACROLESION,
  TUMORINCIDENCE,
  SURVIVAL,
  TUMORONSET,
  DESCRIPTION,
  WEIGHT,
  VOLUME,
	GeneticAlterations,
  METASTASISOF 
	FROM datop.HISTOPATHOLOGY@camodop_datop;

Commit;

Update HISTOPATHOLOGY_TMP a set GENETIC_ALTERATION_ID = (Select GENETIC_ALTERATION_ID
	from Genetic_alteration b where a.GENETIC_ALTERATION_varch= b.Observation
	and b.Observation is not null);

Commit;

TRUNCATE table HISTOPATHOLOGY;

INSERT into HISTOPATHOLOGY (
  HISTOPATHOLOGY_ID,
  COMMENTS,
  GROSS_DESCRIPTION,
  RELATIONAL_OPERATION,
  TUMOR_INCIDENCE_RATE,
  SURVIVAL_INFO,
  AGE_OF_ONSET,
  MICROSCOPIC_DESCRIPTION,
  WEIGHT_OF_TUMOR,
  VOLUME_OF_TUMOR,
  COMPARATIVE_DATA,
  GENETIC_ALTERATION_ID,
  PARENT_HISTOPATHOLOGY_ID)
Select
  HISTOPATHOLOGY_ID,
  COMMENTS,
  GROSS_DESCRIPTION,
  RELATIONAL_OPERATION,
  TUMOR_INCIDENCE_RATE,
  SURVIVAL_INFO,
  AGE_OF_ONSET,
  MICROSCOPIC_DESCRIPTION,
  WEIGHT_OF_TUMOR,
  VOLUME_OF_TUMOR,
  COMPARATIVE_DATA,
  GENETIC_ALTERATION_ID,
  PARENT_HISTOPATHOLOGY_ID 
	FROM HISTOPATHOLOGY_TMP;

Commit;

/**********************Take a long time*******************************/

TRUNCATE TABLE camoddev.invivo_result;

INSERT INTO camoddev.invivo_result(invivo_result_id, evaluationday, 
toxicitysurvivors, toxicityevalday, endpoint_code_ID, treatment_ID, Percent_treated_control, agent_ID)
SELECT invivoresult_id, evaluation_day, 
toxicity_survivors, toxicity_evaluation_day, endpoint_code_ID, 
treatmentschedule_ID, PERCENT_TREATE_CONTROL, agent_ID 
FROM cabiommh.invivoresult@camoddev_cabiommh;

commit;

--update Treatment_ID from Treatment_TMP

CREATE UNIQUE INDEX CAMODDEV.treatmentschedule_ID_INDEX ON CAMODDEV.TREATMENT_TMP
(treatmentschedule_ID);

Update invivo_result a set Treatment_ID = (select Treatment_ID
	from Treatment_TMP b where a.treatment_ID = b.treatmentschedule_ID);

Commit;

DROP INDEX CAMODDEV.treatmentschedule_ID_INDEX; 

CREATE UNIQUE INDEX CAMODDEV.agent_ID_INDEX ON CAMODDEV.ENV_FACTOR_TMP
(agent_ID);

Update invivo_result a set Agent_ID = (select ENV_FACTOR_ID from ENV_FACTOR_TMP b 
where a.agent_ID = b.agent_ID);

Commit;

DROP INDEX CAMODDEV.agent_ID_INDEX;


Drop Table THERAPY_TMP;
CREATE TABLE THERAPY_TMP
(
  THERAPY_ID      NUMBER(19)                    NOT NULL,
	TherapyUID	number,
	CarcinogenUID	number,
	carcinogenagentkey	number,
  EXPERIMENT      VARCHAR2(255 BYTE)                NULL,
  COMMENTS        VARCHAR2(255 BYTE)                NULL,
  RESULTS         VARCHAR2(255 BYTE)                NULL,
  TOXICITY_GRADE  VARCHAR2(255 BYTE)                NULL,
  BIOMARKER       VARCHAR2(255 BYTE)                NULL,
  TUMOR_RESPONSE  VARCHAR2(255 BYTE)                NULL,
  TREATMENT_ID    NUMBER(19)                        NULL,
  ENV_FACTOR_ID   NUMBER(19)                        NULL,
	therapeutic_experiment		number(1)
);

--UPDATE TABLE THERAPY_TMP 01 from datop.Therapy

CREATE OR REPLACE procedure THERAPY_TMP_01_LD as

  CURSOR DATABASECROSSCUR IS
  (SELECT rownum, therapyUID, Experiment, Comments, Results
	FROM datop.therapy@camodop_datop
  );

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE THERAPY_TMP REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO THERAPY_TMP
		(Therapy_id, therapyUID, Experiment, Comments, Results, therapeutic_experiment)
      VALUES
     (aRec.ROWNUM,
      aRec.therapyUID,
      aRec.Experiment,
	aRec.Comments,
	aRec.Results,
	1);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE THERAPY_TMP 02 from datop.Carcinogen

CREATE OR REPLACE procedure THERAPY_TMP_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, CarcinogenUID, carcinogenagentkey
		FROM datop.Carcinogen@camodop_datop);

  aID number:=0;

BEGIN

   SELECT MAX(Organ_ID) INTO V_MAXROW FROM Organ_TMP;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

      INSERT INTO THERAPY_TMP
		(Therapy_ID,
		CarcinogenUID,
		therapeutic_experiment,
		carcinogenagentkey)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.CarcinogenUID, 0,
	aRec.carcinogenagentkey);

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

EXECUTE THERAPY_TMP_01_LD;
EXECUTE THERAPY_TMP_02_LD;

--This update runs very slow because of treatment table NEED INDEX

CREATE UNIQUE INDEX CAMODDEV.CARCINOGENUID_INDEX ON CAMODDEV.TREATMENT_TMP
(CARCINOGENUID);
Update THERAPY_TMP a set Treatment_ID = (select Treatment_ID
	from Treatment_TMP b where a.CarcinogenUID = b.CarcinogenUID);

Commit;

DROP INDEX CAMODDEV.CARCINOGENUID_INDEX; 

CREATE UNIQUE INDEX CAMODDEV.ENV_FACTOR_ID_INDEX ON CAMODDEV.ENV_FACTOR_TMP
(TherapyUID);

Update THERAPY_TMP a set ENV_FACTOR_ID = NVL((select ENV_FACTOR_ID
	from ENV_FACTOR_TMP b where a.TherapyUID = b.TherapyUID), a.ENV_FACTOR_ID);

Commit;

DROP INDEX CAMODDEV.ENV_FACTOR_ID_INDEX;

CREATE UNIQUE INDEX CAMODDEV.ENV_FACTOR_ID_INDEX ON CAMODDEV.ENV_FACTOR_TMP
(CarcinogenagentUID);

Update THERAPY_TMP a set ENV_FACTOR_ID = NVL((select ENV_FACTOR_ID
	from ENV_FACTOR_TMP b where a.carcinogenagentkey = b.carcinogenagentUID), a.ENV_FACTOR_ID);

Commit;

DROP INDEX CAMODDEV.ENV_FACTOR_ID_INDEX;

TRUNCATE Table THERAPY;
Insert into THERAPY (
  THERAPY_ID,
  EXPERIMENT,
  COMMENTS,
  RESULTS,
  TOXICITY_GRADE,
  BIOMARKER,
  TUMOR_RESPONSE,
  TREATMENT_ID,
  ENV_FACTOR_ID,
	therapeutic_experiment)
Select THERAPY_ID,
  EXPERIMENT,
  COMMENTS,
  RESULTS,
  TOXICITY_GRADE,
  BIOMARKER,
  TUMOR_RESPONSE,
  TREATMENT_ID,
  ENV_FACTOR_ID, therapeutic_experiment
 from THERAPY_TMP;

Commit;


/************************Therapy_Publication***************************/

TRUNCATE TABLE camoddev.therapy_Publication;

INSERT INTO therapy_Publication(therapy_id, Publication_ID)
SELECT a.therapy_ID, b.PublicationUID
FROM therapy_Tmp a, datop.publication@camodop_datop b 
	WHERE a.therapyUID = b.therapyKey and b.therapyKey is not null;

commit;

/*************************cell_line_Publication**************************/

TRUNCATE TABLE camoddev.cell_line_Publication;

INSERT INTO camoddev.cell_line_Publication(cell_line_id, Publication_ID)
SELECT celllinesKey,
PublicationUID
FROM datop.publication@camodop_datop where celllinesKey is not null;

commit;

/*******************************************************************/

TRUNCATE TABLE camoddev.ABS_CAN_MOD_PUBLICATION;

INSERT INTO camoddev.ABS_CAN_MOD_PUBLICATION(ABS_CANCER_MODEL_id, Publication_ID)
SELECT a.ABS_CANCER_MODEL_id, b.PublicationUID
FROM ABS_CANCER_MODEL_tmp a, datop.publication@camodop_datop b
where a.ModelUID = b.ModelKey;

commit;

/**********************Animal_Model_Treatment******************************/

--No this table in the new model
/* TRUNCATE TABLE camoddev.Animal_Model_Treatment;

INSERT INTO camoddev.Animal_Model_Treatment(ABS_CANCER_MODEL_id, Treatment_ID)
SELECT a.ABS_CANCER_MODEL_id, b.Treatment_ID
FROM ABS_CANCER_MODEL_TMP a, Treatment_TMP b, datop.Carcinogen@camodop_datop c
where a.ModelUID = c.ModelKey AND b.CarcinogenUID = c.CarcinogenUID;

commit; */

/**********************Animal_Model_Therapy******************************/

TRUNCATE TABLE camoddev.Animal_Model_Therapy;

INSERT INTO camoddev.Animal_Model_Therapy(ABS_CANCER_MODEL_id, Therapy_ID)
SELECT a.ABS_CANCER_MODEL_id, b.Therapy_ID
FROM ABS_CANCER_MODEL_TMP a, Therapy_TMP b, datop.Therapy@camodop_datop c
where a.ModelUID = c.ModelKey AND b.TherapyUID = c.TherapyUID;

commit;

INSERT INTO camoddev.Animal_Model_Therapy(ABS_CANCER_MODEL_id, Therapy_ID)
SELECT a.ABS_CANCER_MODEL_id, b.Therapy_ID
FROM ABS_CANCER_MODEL_TMP a, Therapy_TMP b, datop.carcinogen@camodop_datop c
where a.ModelUID = c.ModelKey AND b.carcinogenUID = c.carcinogenUID;

commit;

/************************ANI_MOD_CELL_LINE************************/

TRUNCATE TABLE camoddev.ANI_MOD_CELL_LINE;

INSERT INTO camoddev.ANI_MOD_CELL_LINE(ABS_CANCER_MODEL_id, CELL_LINE_ID)
SELECT a.ABS_CANCER_MODEL_id, b.CellLinesUID
FROM ABS_CANCER_MODEL_tmp a, datop.CellLines@camodop_datop b
Where a.ModelUID = b.ModelKey;

commit;

/*************************Animal_Model_Image******************************/

TRUNCATE TABLE camoddev.Animal_Model_Image;

INSERT INTO camoddev.Animal_Model_Image(ABS_Cancer_Model_id, Image_ID)
SELECT a.ABS_CANCER_MODEL_id, b.ImageUID
FROM ABS_CANCER_MODEL_tmp a, datop.images@camodop_datop b
Where a.ModelUID = b.ModelUID;

commit;

/**********************Animal_Model_Therapy******************************/

TRUNCATE TABLE camoddev.ANI_MOD_Engineered_Gene;

INSERT INTO camoddev.ANI_MOD_Engineered_Gene(ABS_CANCER_MODEL_id, Engineered_Gene_ID)
SELECT a.ABS_CANCER_MODEL_id, b.Engineered_Gene_ID
FROM ABS_CANCER_MODEL_TMP a, Engineered_Gene_TMP b, datop.Genetic_Description@camodop_datop c
where a.ModelUID = c.ModelKey AND b.GenotypesUID = c.GenotypesUID;

commit;

/*************************Transgene_Reg_Element******************************/

TRUNCATE TABLE camoddev.Transgene_Reg_Element;

INSERT INTO camoddev.Transgene_Reg_Element(Engineered_Gene_ID, Regulatory_element_ID)
SELECT a.Engineered_Gene_ID, b.ElementUID
FROM Engineered_Gene_tmp a, datop.Regulatoryelement@camodop_datop b
Where a.GenotypesUID = b.GenotypeKey;

commit;

/*************************Eng_GEN_EXP_LEVEL_DESC******************************/

--No this table in the new model
/* TRUNCATE TABLE camoddev.Eng_GEN_EXP_LEVEL_DESC;

INSERT INTO camoddev.Eng_GEN_EXP_LEVEL_DESC(Engineered_Gene_ID, EXP_LEVEL_DESC_ID)
SELECT a.Engineered_Gene_ID, b.ExpressionLevelKey
FROM Engineered_Gene_tmp a, datop.Expressions@camodop_datop b
Where a.GenotypesUID = b.GenotypeKey and b.ExpressionLevelKey is not null;

commit; */

/*******************************************************************/

TRUNCATE TABLE camoddev.ANI_MOD_HISTOPATHOLOGY;

INSERT INTO camoddev.ANI_MOD_HISTOPATHOLOGY(ABS_Cancer_Model_id, HISTOPATHOLOGY_ID)
SELECT a.ABS_CANCER_MODEL_id, b.HISTOPATHOLOGYUID
FROM ABS_CANCER_MODEL_tmp a, datop.HISTOPATHOLOGY@camodop_datop b
Where a.ModelUID = b.ModelKEY;

commit;

/*************************Gene_Function******************************/

TRUNCATE TABLE camoddev.Gene_Function;

INSERT INTO camoddev.Gene_Function(Gene_Function_ID, Function, Engineered_Gene_ID)
SELECT b.GeneFunctionUID, b.GeneFunctionName, a.Engineered_Gene_ID
FROM Engineered_Gene_tmp a, datop.GeneFunctions@camodop_datop b
Where a.GenotypesUID = b.GenotypeKey;

commit;

/*************************Tar_MOD_Modification_Type******************************/

TRUNCATE TABLE camoddev.Tar_MOD_Modification_Type;

INSERT INTO camoddev.Tar_MOD_Modification_Type(Engineered_Gene_ID, Modification_Type_ID)
SELECT a.Engineered_Gene_ID, b.MODIFICATIONTYPEKEY
FROM Engineered_Gene_tmp a, datop.MODIFICATIONTARGET@camodop_datop b
Where a.GenotypesUID = b.GenotypeKey;

commit;

/***************************Organ_HISTOPATHOLOGY***********************/

TRUNCATE TABLE camoddev.Organ_HISTOPATHOLOGY;

INSERT INTO camoddev.Organ_HISTOPATHOLOGY(HISTOPATHOLOGY_ID, Organ_ID)
SELECT b.HISTOPATHOLOGYUID, a.Organ_id
FROM Organ_TMP a, datop.HISTOPATHOLOGY@camodop_datop b
Where a.HISTOPATHOLOGYUID = b.HISTOPATHOLOGYUID;

commit;

/*************************Histopathology_Disease******************************/

TRUNCATE TABLE camoddev.Histopathology_Disease;

insert INTO camoddev.Histopathology_Disease (Disease_id, Histopathology_ID)
SELECT Histopathologyuid, Histopathologyuid
FROM datop.Histopathology@camodop_datop;

commit;

/*************************Comments******************************/

TRUNCATE TABLE camoddev.Comments;

INSERT INTO camoddev.Comments(Comments_ID, Remark, Model_Section_ID, Party_ID, ABS_Cancer_Model_ID, state)
SELECT b.CommentUID, b.Comments, b.PageID, b.submitterKey, a.ABS_Cancer_Model_ID, b.commentvisibility
FROM ABS_Cancer_Model_TMP a, datop.Comments@camodop_datop b
Where a.ModelUID = b.ModelKey;

commit;

/*************************ANI_MOD_MIC_ARRAY_DATA******************************/

Truncate table ANI_MOD_MIC_ARRAY_DATA; 
Insert into ANI_MOD_MIC_ARRAY_DATA
select a.Abs_cancer_Model_ID, b.micro_Array_data_ID from ABS_Cancer_Model_TMP a, Micro_array_data_TMP b 
	where a.modelUID = b.modelID;

Commit;

/*************************Eng_gene_exp_feature******************************/

Truncate table Eng_gene_exp_feature;
Insert into Eng_gene_exp_feature (engineered_gene_ID, expression_feature_id)
select b.engineered_gene_id, a.expressionsUID 
FROM datop.expressions@camodop_datop a, engineered_gene_tmp b
where a.genotypekey = b.genotypesUID;

Commit;

/*************************Gen_seg_segment_type******************************/

Truncate table Gen_seg_segment_type;
Insert into Gen_seg_segment_type (engineered_gene_ID, segment_type_id)
select b.engineered_gene_id, a.segmenttypekey 
FROM datop.genetic_description@camodop_datop a, engineered_gene_tmp b
where a.genotypesUID = b.genotypesUID and a.segmenttypekey is not null;

Commit;

/*************************Transgene_taxon******************************/

Truncate table Transgene_taxon;
Insert into Transgene_taxon (engineered_gene_ID, taxon_id)
select b.engineered_gene_id, c.taxon_ID 
FROM datop.genetic_description@camodop_datop a, engineered_gene_tmp b, taxon_tmp c
where a.genotypesUID = b.genotypesUID and a.originspecieskey = c.originspeciesUID;

Commit;


/*************************Ani_mod_ani_availability******************************/

Truncate table Ani_mod_ani_availability;
Insert into Ani_mod_ani_availability (ABS_cancer_model_ID, animal_availability_id)
select a.ABS_cancer_model_ID, b.animal_availability_id 
FROM ABS_cancer_model_tmp a, animal_availability_tmp b
where a.modeluid = b.modelUID;

Commit;

/*************************Env_fac_ind_mutation******************************/

Truncate table Env_fac_ind_mutation;
Insert into Env_fac_ind_mutation (engineered_gene_ID, Env_factor_id)
select a.engineered_gene_ID, b.Env_factor_id 
FROM engineered_gene_tmp a, env_factor_tmp b
where a.genotypesuid = b.genotypesuid and a.engineered_gene_type = 'IM';

Commit;

/************************Take a long time to run**************************/

TRUNCATE TABLE YST_MDL_SCRNING_RESULT;

INSERT INTO camoddev.YST_MDL_SCRNING_RESULT(ABS_Cancer_Model_ID, Screening_Result_ID)
SELECT a.ABS_Cancer_Model_ID, b.Screeningresult_id
FROM ABS_CANCER_MODEL_tmp a, cabiommh.Screeningresult@camoddev_cabiommh b
Where a.yeastmodel_ID = b.yeastmodel_ID;

commit;

/************************Take a long time to run****************************/

TRUNCATE TABLE Xenograft_invivo_result;

INSERT INTO camoddev.Xenograft_invivo_result(ABS_Cancer_Model_ID, Invivo_Result_ID)
SELECT a.ABS_Cancer_Model_ID, b.Invivoresult_id
FROM ABS_CANCER_MODEL_tmp a, cabiommh.Invivoresult@camoddev_cabiommh b
Where a.Xenograft_id = b.Xenograft_id;

commit;

/*************************Log******************************/

/* DROP TABLE LOG_TMP CASCADE CONSTRAINTS;

CREATE TABLE LOG_TMP
(
  LOG_ID               NUMBER(19)               NOT NULL,
	Editlog			number,
	ModelKey_ASS		number,
	SubmitterKey_ASS	number,
  NOTES                VARCHAR2(2000 BYTE)          NULL,
  TYPE                 VARCHAR2(255 BYTE)           NULL,
  SUB_SYSTEM           VARCHAR2(255 BYTE)           NULL,
  TIMESTAMP            VARCHAR2(255 BYTE)           NULL,
  PARTY_ID             NUMBER(19)                   NULL,
  ABS_CANCER_MODEL_ID  NUMBER(19)                   NULL,
  COMMENTS_ID          NUMBER(19)                   NULL
);
*/

--UPDATE TABLE log 01 from datop.editlog

CREATE OR REPLACE procedure log_01_LD as

  CURSOR DATABASECROSSCUR IS
  (select rownum, b.Comments comments, b.timestamp timestamp, 
	b.submitterKey submitterKey, a.ABS_Cancer_Model_ID ABS_Cancer_Model_ID
	FROM ABS_Cancer_Model_TMP a, datop.Editlog@camodop_datop b
	Where a.ModelUID = b.ModelKey);

  aID number:=0;

BEGIN

   EXECUTE IMMEDIATE('TRUNCATE TABLE log REUSE STORAGE ');

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

    IF aRec.comments = 'edit approved' or aRec.comments = 'rejected' 
	or aRec.comments = 'screener approved' or aRec.comments = 'sceener approved' Then 
	
	INSERT INTO log (
	log_ID,
	type,
  	timestamp,
  	Party_ID,
  	ABS_Cancer_Model_ID)
      VALUES
     (aRec.ROWNUM,
      aRec.comments,
      aREC.timestamp,
      aRec.submitterKey,
      aRec.ABS_Cancer_Model_ID);

   ELSE 

	INSERT INTO log (
	log_ID,
	Notes,
  	timestamp,
  	Party_ID,
  	ABS_Cancer_Model_ID)
      VALUES
     (aRec.ROWNUM,
      aRec.comments,
      aREC.timestamp,
      aRec.submitterKey,
      aRec.ABS_Cancer_Model_ID);
	END IF;

      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

--UPDATE TABLE log 02 from datop.ModelAssignment

CREATE OR REPLACE procedure log_02_LD as

  V_MAXROW NUMBER :=0;
  
 CURSOR DATABASECROSSCUR IS
  (select rownum, Decode (b.reviewstatusKey, 2, 'Screener-assigned', 
	4, 'Reviewer-assigned', 3, 'Editor-assigned', NULL) type, b.assigndate timestamp, 
	b.submitterKey submitterKey, a.ABS_Cancer_Model_ID ABS_Cancer_Model_ID
	FROM ABS_Cancer_Model_TMP a, datop.ModelAssignment@camodop_datop b
	Where a.ModelUID = b.ModelKey);

  aID number:=0;

BEGIN

   SELECT MAX(log_ID) INTO V_MAXROW FROM log;  

   FOR aRec in DATABASECROSSCUR LOOP
      aID := aID + 1;

     INSERT INTO log (
	log_ID,
	Type,
  	timestamp,
  	Party_ID,
  	ABS_Cancer_Model_ID)
      VALUES
     (aRec.ROWNUM + V_MAXROW,
      aRec.Type,
      aREC.timestamp,
      aRec.submitterKey,
      aRec.ABS_Cancer_Model_ID);


      IF MOD(aID, 500) = 0 THEN
         COMMIT;
      END IF;

   END LOOP;

COMMIT;

END;
/

TRUNCATE TABLE camoddev.Log;
EXECUTE log_01_LD;
EXECUTE log_02_LD;

Update log set type = 'Screener-approved' where type = 'sceener approved';
Commit;

Update log set type = 'Edit-approved' where type = 'edit approved';
Commit;

/* TRUNCATE TABLE camoddev.Log; */

/* Update log a set comments_ID = (select Comments_ID from comments b
	Where a.ABS_Cancer_Model_ID = b.ABS_Cancer_Model_ID);

Commit; */

update disease a set concept_code = NVL((select nci_code from concept_code_stg b 
where a.concept_code = substr(b.ctrm_code, 2)), a.concept_code);

Commit;

update organ a set concept_code = NVL((select nci_code from concept_code_stg b 
where a.concept_code = substr(b.ctrm_code, 2)), a.concept_code);

Commit;

update organ a set name = NVL((select nci_name from concept_code_stg b 
where a.concept_code = b.nci_code), a.name);

Commit;
  



