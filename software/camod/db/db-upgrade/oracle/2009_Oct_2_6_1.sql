update ENVIRONMENTAL_FACTOR ef
set ef.IS_INDUCED_MUTATION_TRIGGER = '0'
where ef.IS_INDUCED_MUTATION_TRIGGER is null
/

-- ran on dev manually - will automate in BDA (Fixed 1730 rows)
update ENVIRONMENTAL_FACTOR ef
set ef.TYPE = ef.TYPE_ALTERN_ENTRY
where ef.TYPE is null
and ef.ENVIRONMENTAL_FACTOR_ID IN (
select ef.ENVIRONMENTAL_FACTOR_ID 
From environmental_factor ef, abs_cancer_model ac, carcinogen_exposure ce 
where ef.ENVIRONMENTAL_FACTOR_ID = ce.ENVIRONMENTAL_FACTOR_ID
and ce.ABS_CANCER_MODEL_ID = ac.ABS_CANCER_MODEL_ID
and ac.EXTERNAL_SOURCE = 'Jax MTB')
/

-- then null out the typeAlternEntry field (Fixed 1730 rows)
update ENVIRONMENTAL_FACTOR ef
set ef.TYPE_ALTERN_ENTRY = null
where ef.ENVIRONMENTAL_FACTOR_ID IN (
select ef.ENVIRONMENTAL_FACTOR_ID 
From environmental_factor ef, abs_cancer_model ac, carcinogen_exposure ce 
where ef.ENVIRONMENTAL_FACTOR_ID = ce.ENVIRONMENTAL_FACTOR_ID
and ce.ABS_CANCER_MODEL_ID = ac.ABS_CANCER_MODEL_ID
and ac.EXTERNAL_SOURCE = 'Jax MTB')
/

-- repeat for name and nameAlternEntry fields (Fixed 1723 rows)
update ENVIRONMENTAL_FACTOR ef
set ef.NAME = ef.NAME_ALTERN_ENTRY
where ef.NAME is null
and ef.ENVIRONMENTAL_FACTOR_ID IN (
select ef.ENVIRONMENTAL_FACTOR_ID 
From environmental_factor ef, abs_cancer_model ac, carcinogen_exposure ce 
where ef.ENVIRONMENTAL_FACTOR_ID = ce.ENVIRONMENTAL_FACTOR_ID
and ce.ABS_CANCER_MODEL_ID = ac.ABS_CANCER_MODEL_ID
and ac.EXTERNAL_SOURCE = 'Jax MTB')
/

-- then null out the typeAlternEntry field (Fixed 1730 rows)
update ENVIRONMENTAL_FACTOR ef
set ef.NAME_ALTERN_ENTRY = null
where ef.ENVIRONMENTAL_FACTOR_ID IN (
select ef.ENVIRONMENTAL_FACTOR_ID 
From environmental_factor ef, abs_cancer_model ac, carcinogen_exposure ce 
where ef.ENVIRONMENTAL_FACTOR_ID = ce.ENVIRONMENTAL_FACTOR_ID
and ce.ABS_CANCER_MODEL_ID = ac.ABS_CANCER_MODEL_ID
and ac.EXTERNAL_SOURCE = 'Jax MTB')
/ 
