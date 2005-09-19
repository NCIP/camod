drop table abs_can_mod_publication cascade constraints;
drop table abs_cancer_model cascade constraints;
drop table agent_agent_target cascade constraints;
drop table agent_biological_process cascade constraints;
drop table agent_chemical_class cascade constraints;
drop table agent_target cascade constraints;
drop table ani_ava_ani_distributor cascade constraints;
drop table ani_mod_ani_availability cascade constraints;
drop table ani_mod_cell_line cascade constraints;
drop table ani_mod_engineered_gene cascade constraints;
drop table ani_mod_env_factor cascade constraints;
drop table ani_mod_gen_delivery cascade constraints;
drop table ani_mod_histopathology cascade constraints;
drop table ani_mod_mic_array_data cascade constraints;
drop table ani_mod_spon_mutation cascade constraints;
drop table animal_availability cascade constraints;
drop table animal_distributor cascade constraints;
drop table animal_model_image cascade constraints;
drop table animal_model_therapy cascade constraints;
drop table availability cascade constraints;
drop table biological_process cascade constraints;
drop table cell_line cascade constraints;
drop table cell_line_publication cascade constraints;
drop table chemical_class cascade constraints;
drop table clinical_marker cascade constraints;
drop table comments cascade constraints;
drop table conditionality cascade constraints;
drop table contact_info cascade constraints;
drop table disease cascade constraints;
drop table endpoint_code cascade constraints;
drop table eng_gene_exp_feature cascade constraints;
drop table engineered_gene cascade constraints;
drop table env_fac_ind_mutation cascade constraints;
drop table env_factor cascade constraints;
drop table exp_level_desc cascade constraints;
drop table expression_feature cascade constraints;
drop table gen_seg_segment_type cascade constraints;
drop table gene_delivery cascade constraints;
drop table gene_function cascade constraints;
drop table genetic_alteration cascade constraints;
drop table genotype_summary cascade constraints;
drop table his_clinical_marker cascade constraints;
drop table histopathology cascade constraints;
drop table histopathology_disease cascade constraints;
drop table image cascade constraints;
drop table ind_mut_gen_alteration cascade constraints;
drop table invivo_result cascade constraints;
drop table log cascade constraints;
drop table micro_array_data cascade constraints;
drop table model_section cascade constraints;
drop table modification_type cascade constraints;
drop table mutation_identifier cascade constraints;
drop table nomenclature cascade constraints;
drop table organ cascade constraints;
drop table organ_histopathology cascade constraints;
drop table party cascade constraints;
drop table party_contact_info cascade constraints;
drop table party_role cascade constraints;
drop table phenotype cascade constraints;
drop table publication cascade constraints;
drop table publication_status cascade constraints;
drop table reg_element_type cascade constraints;
drop table regulatory_element cascade constraints;
drop table repository_info cascade constraints;
drop table role cascade constraints;
drop table screening_result cascade constraints;
drop table segment_type cascade constraints;
drop table sex_distribution cascade constraints;
drop table spon_mut_gen_alteration cascade constraints;
drop table spontaneous_mutation cascade constraints;
drop table tar_mod_modification_type cascade constraints;
drop table taxon cascade constraints;
drop table therapy cascade constraints;
drop table therapy_publication cascade constraints;
drop table transgene_reg_element cascade constraints;
drop table transgene_taxon cascade constraints;
drop table treatment cascade constraints;
drop table tumor_code cascade constraints;
drop table xenograft_invivo_result cascade constraints;
drop table yst_mdl_scrning_result cascade constraints;
drop table yst_mdl_trgtd_mod cascade constraints;
drop sequence hibernate_sequence;
create table abs_can_mod_publication (
   abs_cancer_model_id number(19,0) not null,
   publication_id number(19,0) not null unique
);
create table abs_cancer_model (
   abs_cancer_model_id number(19,0) not null,
   abs_cancer_model_type varchar2(255) not null,
   experiment_design clob,
   model_descriptor varchar2(255),
   state varchar2(255),
   availability_id number(19,0) unique,
   taxon_id number(19,0),
   submitter_id number(19,0),
   principal_investigator_id number(19,0),
   url varchar2(255),
   is_tool_mouse number(1,0),
   repository_info_id number(19,0),
   phenotype_id number(19,0),
   administrative_site varchar2(255),
   genetic_manipulation varchar2(255),
   modification_description varchar2(255),
   parental_cell_line_name varchar2(255),
   name varchar2(255),
   atcc_number varchar2(255),
   cell_amount varchar2(255),
   harvest_date date,
   graft_type varchar2(255),
   graft_type_unctrl_vocab varchar2(255),
   tumor_code_id number(19,0),
   origin_species_id number(19,0),
   host_species_id number(19,0),
   organ_id number(19,0) unique,
   par_abs_can_model_id number(19,0),
   primary key (abs_cancer_model_id)
);
create table agent_agent_target (
   agent_target_id number(19,0) not null,
   env_factor_id number(19,0) not null
);
create table agent_biological_process (
   biological_process_id number(19,0) not null,
   env_factor_id number(19,0) not null
);
create table agent_chemical_class (
   chemical_class_id number(19,0) not null,
   env_factor_id number(19,0) not null
);
create table agent_target (
   agent_target_id number(19,0) not null,
   target_name varchar2(255),
   primary key (agent_target_id)
);
create table ani_ava_ani_distributor (
   animal_availability_id number(19,0) not null,
   animal_distributor_id number(19,0) not null
);
create table ani_mod_ani_availability (
   abs_cancer_model_id number(19,0) not null,
   animal_availability_id number(19,0) not null unique
);
create table ani_mod_cell_line (
   abs_cancer_model_id number(19,0) not null,
   cell_line_id number(19,0) not null unique
);
create table ani_mod_engineered_gene (
   abs_cancer_model_id number(19,0) not null,
   engineered_gene_id number(19,0) not null unique
);
create table ani_mod_env_factor (
   abs_cancer_model_id number(19,0) not null,
   env_factor_id number(19,0) not null unique
);
create table ani_mod_gen_delivery (
   abs_cancer_model_id number(19,0) not null,
   gene_delivery_id number(19,0) not null unique
);
create table ani_mod_histopathology (
   abs_cancer_model_id number(19,0) not null,
   histopathology_id number(19,0) not null unique
);
create table ani_mod_mic_array_data (
   abs_cancer_model_id number(19,0) not null,
   micro_array_data_id number(19,0) not null unique
);
create table ani_mod_spon_mutation (
   abs_cancer_model_id number(19,0) not null,
   spontaneous_mutation_id number(19,0) not null unique
);
create table animal_availability (
   animal_availability_id number(19,0) not null,
   name varchar2(255),
   stock_number varchar2(255),
   primary key (animal_availability_id)
);
create table animal_distributor (
   animal_distributor_id number(19,0) not null,
   name varchar2(255),
   primary key (animal_distributor_id)
);
create table animal_model_image (
   abs_cancer_model_id number(19,0) not null,
   image_id number(19,0) not null unique
);
create table animal_model_therapy (
   abs_cancer_model_id number(19,0) not null,
   therapy_id number(19,0) not null unique
);
create table availability (
   availability_id number(19,0) not null,
   entered_date date,
   visible_to varchar2(255),
   modified_date date,
   release_date date,
   primary key (availability_id)
);
create table biological_process (
   biological_process_id number(19,0) not null,
   process_name varchar2(255),
   primary key (biological_process_id)
);
create table cell_line (
   cell_line_id number(19,0) not null,
   comments varchar2(2000),
   experiment varchar2(2000),
   name varchar2(255),
   results varchar2(255),
   organ_id number(19,0),
   primary key (cell_line_id)
);
create table cell_line_publication (
   cell_line_id number(19,0) not null,
   publication_id number(19,0) not null unique
);
create table chemical_class (
   chemical_class_id number(19,0) not null,
   chemical_class_name varchar2(255),
   primary key (chemical_class_id)
);
create table clinical_marker (
   clinical_marker_id number(19,0) not null,
   name varchar2(255),
   value varchar2(255),
   primary key (clinical_marker_id)
);
create table comments (
   comments_id number(19,0) not null,
   remark varchar2(255),
   state varchar2(255),
   abs_cancer_model_id number(19,0),
   availability_id number(19,0) unique,
   model_section_id number(19,0),
   party_id number(19,0),
   primary key (comments_id)
);
create table conditionality (
   conditionality_id number(19,0) not null,
   conditioned_by varchar2(255),
   description varchar2(2000),
   primary key (conditionality_id)
);
create table contact_info (
   contact_info_id number(19,0) not null,
   city varchar2(255),
   state varchar2(255),
   address varchar2(255),
   zip varchar2(255),
   fax varchar2(255),
   lab varchar2(255),
   phone varchar2(255),
   email varchar2(255),
   institute varchar2(255),
   primary key (contact_info_id)
);
create table disease (
   disease_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   primary key (disease_id)
);
create table endpoint_code (
   endpoint_code_id number(19,0) not null,
   code varchar2(255),
   description varchar2(255),
   primary key (endpoint_code_id)
);
create table eng_gene_exp_feature (
   engineered_gene_id number(19,0) not null,
   expression_feature_id number(19,0) not null unique
);
create table engineered_gene (
   engineered_gene_id number(19,0) not null,
   engineered_gene_type varchar2(255) not null,
   cabio_id number(19,0),
   name varchar2(255),
   comments varchar2(2000),
   conditionality_id number(19,0),
   genotype_summary_id number(19,0),
   image_id number(19,0),
   mutation_identifier_id number(19,0) unique,
   location_of_integration varchar2(255),
   segment_size varchar2(255),
   clone_designator varchar2(255),
   gene_id varchar2(255),
   description varchar2(255),
   es_cell_line_name varchar2(255),
   blastocyst_name varchar2(255),
   mod_type_unctrl_vocab varchar2(255),
   primary key (engineered_gene_id)
);
create table env_fac_ind_mutation (
   engineered_gene_id number(19,0) not null,
   env_factor_id number(19,0),
   primary key (engineered_gene_id)
);
create table env_factor (
   env_factor_id number(19,0) not null,
   env_factor_type varchar2(255) not null,
   type varchar2(255),
   type_unctrl_vocab varchar2(255),
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   cas_number varchar2(255),
   nsc_number number(19,0),
   is_cmap_agent number(1,0),
   evs_id varchar2(255),
   comments varchar2(255),
   source varchar2(255),
   primary key (env_factor_id)
);
create table exp_level_desc (
   exp_level_desc_id number(19,0) not null,
   expression_level varchar2(255),
   primary key (exp_level_desc_id)
);
create table expression_feature (
   expression_feature_id number(19,0) not null,
   organ_id number(19,0),
   exp_level_desc_id number(19,0),
   primary key (expression_feature_id)
);
create table gen_seg_segment_type (
   engineered_gene_id number(19,0) not null,
   segment_type_id number(19,0) not null unique
);
create table gene_delivery (
   gene_delivery_id number(19,0) not null,
   viral_vector varchar2(255),
   viral_vector_unctrl_vocab varchar2(255),
   gene_in_virus varchar2(255),
   organ_id number(19,0) unique,
   treatment_id number(19,0) unique,
   primary key (gene_delivery_id)
);
create table gene_function (
   gene_function_id number(19,0) not null,
   function varchar2(255),
   engineered_gene_id number(19,0),
   primary key (gene_function_id)
);
create table genetic_alteration (
   genetic_alteration_id number(19,0) not null,
   observation varchar2(255) not null,
   method_of_observation varchar2(255),
   primary key (genetic_alteration_id)
);
create table genotype_summary (
   genotype_summary_id number(19,0) not null,
   summary varchar2(255),
   genotype varchar2(255),
   nomenclature_id number(19,0),
   primary key (genotype_summary_id)
);
create table his_clinical_marker (
   histopathology_id number(19,0) not null,
   clinical_marker_id number(19,0) not null unique
);
create table histopathology (
   histopathology_id number(19,0) not null,
   comments clob,
   gross_description varchar2(255),
   relational_operation varchar2(255),
   tumor_incidence_rate float,
   survival_info varchar2(255),
   age_of_onset varchar2(255),
   microscopic_description varchar2(2000),
   weight_of_tumor float,
   volume_of_tumor float,
   comparative_data varchar2(255),
   genetic_alteration_id number(19,0) unique,
   parent_histopathology_id number(19,0),
   primary key (histopathology_id)
);
create table histopathology_disease (
   disease_id number(19,0) not null,
   histopathology_id number(19,0) not null
);
create table image (
   image_id number(19,0) not null,
   title varchar2(255),
   description varchar2(4000),
   staining varchar2(255),
   staining_unctrl_vocab varchar2(255),
   file_server_location varchar2(255),
   availability_id number(19,0),
   primary key (image_id)
);
create table ind_mut_gen_alteration (
   engineered_gene_id number(19,0) not null,
   genetic_alteration_id number(19,0) not null unique
);
create table invivo_result (
   invivo_result_id number(19,0) not null,
   evaluationDay varchar2(255),
   toxicitySurvivors varchar2(255),
   toxicityEvalDay varchar2(255),
   percent_treated_control float,
   endpoint_code_id number(19,0),
   treatment_id number(19,0),
   agent_id number(19,0),
   primary key (invivo_result_id)
);
create table log (
   log_id number(19,0) not null,
   notes varchar2(255),
   type varchar2(255),
   sub_system varchar2(255),
   timestamp varchar2(255),
   party_id number(19,0),
   abs_cancer_model_id number(19,0),
   comments_id number(19,0) unique,
   primary key (log_id)
);
create table micro_array_data (
   micro_array_data_id number(19,0) not null,
   experiment_name varchar2(255),
   experiment_id number(19,0),
   other_location_url varchar2(255),
   availability_id number(19,0),
   primary key (micro_array_data_id)
);
create table model_section (
   model_section_id number(19,0) not null,
   name varchar2(255),
   primary key (model_section_id)
);
create table modification_type (
   modification_type_id number(19,0) not null,
   name varchar2(255),
   primary key (modification_type_id)
);
create table mutation_identifier (
   mutation_identifier_id number(19,0) not null,
   number_mgi number(19,0),
   primary key (mutation_identifier_id)
);
create table nomenclature (
   nomenclature_id number(19,0) not null,
   name varchar2(255),
   primary key (nomenclature_id)
);
create table organ (
   organ_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   primary key (organ_id)
);
create table organ_histopathology (
   histopathology_id number(19,0) not null,
   organ_id number(19,0),
   primary key (histopathology_id)
);
create table party (
   party_id number(19,0) not null,
   party_type varchar2(255) not null,
   first_name varchar2(255),
   middle_name varchar2(255),
   last_name varchar2(255),
   username varchar2(255),
   is_principle_investigator number(1,0),
   name varchar2(255),
   primary key (party_id)
);
create table party_contact_info (
   contact_info_id number(19,0) not null,
   party_id number(19,0) not null
);
create table party_role (
   party_id number(19,0) not null,
   role_id number(19,0) not null
);
create table phenotype (
   phenotype_id number(19,0) not null,
   breeding_notes varchar2(255),
   description varchar2(255),
   sex_distribution_id number(19,0),
   primary key (phenotype_id)
);
create table publication (
   publication_id number(19,0) not null,
   volume varchar2(255),
   end_page number(19,0),
   year number(19,0),
   title varchar2(255),
   pmid number(19,0),
   start_page number(19,0),
   journal varchar2(255),
   authors varchar2(255),
   first_time_reported number(1,0),
   publication_status_id number(19,0),
   primary key (publication_id)
);
create table publication_status (
   publication_status_id number(19,0) not null,
   name varchar2(255),
   primary key (publication_status_id)
);
create table reg_element_type (
   reg_element_type_id number(19,0) not null,
   name varchar2(255),
   primary key (reg_element_type_id)
);
create table regulatory_element (
   regulatory_element_id number(19,0) not null,
   name varchar2(255),
   reg_element_type_id number(19,0),
   taxon_id number(19,0),
   primary key (regulatory_element_id)
);
create table repository_info (
   repository_info_id number(19,0) not null,
   in_the_repository number(19,0),
   sent_email_content varchar2(255),
   suggest_submission number(19,0),
   primary key (repository_info_id)
);
create table role (
   role_id number(19,0) not null,
   name varchar2(255),
   primary key (role_id)
);
create table screening_result (
   screening_result_id number(19,0) not null,
   stage varchar2(255),
   diffinh float,
   aveinh float,
   inhibition_rate float,
   treatment_id number(19,0),
   agent_id number(19,0),
   primary key (screening_result_id)
);
create table segment_type (
   segment_type_id number(19,0) not null,
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   primary key (segment_type_id)
);
create table sex_distribution (
   sex_distribution_id number(19,0) not null,
   type varchar2(255),
   primary key (sex_distribution_id)
);
create table spon_mut_gen_alteration (
   spontaneous_mutation_id number(19,0) not null,
   genetic_alteration_id number(19,0) not null unique
);
create table spontaneous_mutation (
   spontaneous_mutation_id number(19,0) not null,
   name varchar2(255),
   comments varchar2(255),
   mutation_identifier_id number(19,0) unique,
   primary key (spontaneous_mutation_id)
);
create table tar_mod_modification_type (
   modification_type_id number(19,0) not null,
   engineered_gene_id number(19,0) not null
);
create table taxon (
   taxon_id number(19,0) not null,
   scientific_name varchar2(255),
   ethnicity_strain varchar2(255),
   abbreviation varchar2(255),
   common_name varchar2(255),
   primary key (taxon_id)
);
create table therapy (
   therapy_id number(19,0) not null,
   experiment varchar2(255),
   comments varchar2(255),
   results varchar2(255),
   toxicity_grade varchar2(255),
   biomarker varchar2(255),
   tumor_response varchar2(255),
   treatment_id number(19,0),
   env_factor_id number(19,0),
   therapeutic_experiment number(1,0),
   primary key (therapy_id)
);
create table therapy_publication (
   therapy_id number(19,0) not null,
   publication_id number(19,0) not null unique
);
create table transgene_reg_element (
   engineered_gene_id number(19,0) not null,
   regulatory_element_id number(19,0) not null unique
);
create table transgene_taxon (
   engineered_gene_id number(19,0) not null,
   taxon_id number(19,0) not null unique
);
create table treatment (
   treatment_id number(19,0) not null,
   regimen varchar2(255),
   dosage varchar2(255),
   administrative_route varchar2(255),
   admin_route_unctrl_vocab varchar2(255),
   age_at_treatment varchar2(255),
   sex_distribution_id number(19,0),
   primary key (treatment_id)
);
create table tumor_code (
   tumor_code_id number(19,0) not null,
   code varchar2(255),
   description varchar2(255),
   primary key (tumor_code_id)
);
create table xenograft_invivo_result (
   abs_cancer_model_id number(19,0) not null,
   invivo_result_id number(19,0) not null unique
);
create table yst_mdl_scrning_result (
   abs_cancer_model_id number(19,0) not null,
   screening_result_id number(19,0) not null unique
);
create table yst_mdl_trgtd_mod (
   abs_cancer_model_id number(19,0) not null,
   tar_modification_id number(19,0) not null unique
);
alter table abs_can_mod_publication add constraint FK9377C2B38258D935 foreign key (publication_id) references publication;
alter table abs_can_mod_publication add constraint FK9377C2B3496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table abs_cancer_model add constraint FKBC9267574D19DE94 foreign key (tumor_code_id) references tumor_code;
alter table abs_cancer_model add constraint FKBC926757DF44C4B5 foreign key (taxon_id) references taxon;
alter table abs_cancer_model add constraint FKBC9267576316BFA foreign key (host_species_id) references taxon;
alter table abs_cancer_model add constraint FKBC926757346CAD81 foreign key (principal_investigator_id) references party;
alter table abs_cancer_model add constraint FKBC9267573E7277BC foreign key (origin_species_id) references taxon;
alter table abs_cancer_model add constraint FKBC9267572175CA75 foreign key (phenotype_id) references phenotype;
alter table abs_cancer_model add constraint FKBC9267573D222B55 foreign key (organ_id) references organ;
alter table abs_cancer_model add constraint FKBC92675774AADA32 foreign key (submitter_id) references party;
alter table abs_cancer_model add constraint FKBC9267579BE9D993 foreign key (par_abs_can_model_id) references abs_cancer_model;
alter table abs_cancer_model add constraint FKBC92675765C8F094 foreign key (repository_info_id) references repository_info;
alter table abs_cancer_model add constraint FKBC926757290CE83F foreign key (availability_id) references availability;
alter table agent_agent_target add constraint FKCD3DA3054F764379 foreign key (env_factor_id) references env_factor;
alter table agent_agent_target add constraint FKCD3DA3053C5575C0 foreign key (agent_target_id) references agent_target;
alter table agent_biological_process add constraint FK5FEB6DAB4F764379 foreign key (env_factor_id) references env_factor;
alter table agent_biological_process add constraint FK5FEB6DAB42FAD9D2 foreign key (biological_process_id) references biological_process;
alter table agent_chemical_class add constraint FKD33177854F764379 foreign key (env_factor_id) references env_factor;
alter table agent_chemical_class add constraint FKD3317785BD096390 foreign key (chemical_class_id) references chemical_class;
alter table ani_ava_ani_distributor add constraint FKEFFCBFAEE35B7C58 foreign key (animal_availability_id) references animal_availability;
alter table ani_ava_ani_distributor add constraint FKEFFCBFAEFB3FC01C foreign key (animal_distributor_id) references animal_distributor;
alter table ani_mod_ani_availability add constraint FK6E85ECFEE35B7C58 foreign key (animal_availability_id) references animal_availability;
alter table ani_mod_ani_availability add constraint FK6E85ECFE1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_cell_line add constraint FK3EE58E91D2525AC4 foreign key (cell_line_id) references cell_line;
alter table ani_mod_cell_line add constraint FK3EE58E911CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_engineered_gene add constraint FK59308346D749BD3C foreign key (engineered_gene_id) references engineered_gene;
alter table ani_mod_engineered_gene add constraint FK593083461CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_env_factor add constraint FK273613016C3591A1 foreign key (env_factor_id) references env_factor;
alter table ani_mod_env_factor add constraint FK273613011CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_gen_delivery add constraint FK75A50A231CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_gen_delivery add constraint FK75A50A23BA3551AA foreign key (gene_delivery_id) references gene_delivery;
alter table ani_mod_histopathology add constraint FK2FA654D2D45FCF9F foreign key (histopathology_id) references histopathology;
alter table ani_mod_histopathology add constraint FK2FA654D21CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_mic_array_data add constraint FKC89D9CE81CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table ani_mod_mic_array_data add constraint FKC89D9CE8D086F613 foreign key (micro_array_data_id) references micro_array_data;
alter table ani_mod_spon_mutation add constraint FKEE64586C130E71D0 foreign key (spontaneous_mutation_id) references spontaneous_mutation;
alter table ani_mod_spon_mutation add constraint FKEE64586C1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table animal_model_image add constraint FK2BC31702BB186F15 foreign key (image_id) references image;
alter table animal_model_image add constraint FK2BC317021CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table animal_model_therapy add constraint FK84F30C50DB10995 foreign key (therapy_id) references therapy;
alter table animal_model_therapy add constraint FK84F30C501CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table cell_line add constraint FK61276CB13D222B55 foreign key (organ_id) references organ;
alter table cell_line_publication add constraint FKA8A4EB9E8258D935 foreign key (publication_id) references publication;
alter table cell_line_publication add constraint FKA8A4EB9ED2525AC4 foreign key (cell_line_id) references cell_line;
alter table comments add constraint FKDC17DDF495BE676C foreign key (model_section_id) references model_section;
alter table comments add constraint FKDC17DDF43595FF35 foreign key (party_id) references party;
alter table comments add constraint FKDC17DDF4496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table comments add constraint FKDC17DDF4290CE83F foreign key (availability_id) references availability;
alter table eng_gene_exp_feature add constraint FK6FC0F1EBBEC26304 foreign key (expression_feature_id) references expression_feature;
alter table eng_gene_exp_feature add constraint FK6FC0F1EBD749BD3C foreign key (engineered_gene_id) references engineered_gene;
alter table engineered_gene add constraint FK4ADB4966BB186F15 foreign key (image_id) references image;
alter table engineered_gene add constraint FK4ADB4966F9575982 foreign key (genotype_summary_id) references genotype_summary;
alter table engineered_gene add constraint FK4ADB496672CE5632 foreign key (mutation_identifier_id) references mutation_identifier;
alter table engineered_gene add constraint FK4ADB496666D7EBDF foreign key (conditionality_id) references conditionality;
alter table env_fac_ind_mutation add constraint FKDBCCD3F26C3591A1 foreign key (env_factor_id) references env_factor;
alter table env_fac_ind_mutation add constraint FKDBCCD3F22C7402E4 foreign key (engineered_gene_id) references engineered_gene;
alter table expression_feature add constraint FKCC1D9C4F3D222B55 foreign key (organ_id) references organ;
alter table expression_feature add constraint FKCC1D9C4FA17421A4 foreign key (exp_level_desc_id) references exp_level_desc;
alter table gen_seg_segment_type add constraint FK6E0AD7F53A0BB3C foreign key (segment_type_id) references segment_type;
alter table gen_seg_segment_type add constraint FK6E0AD7F11021D04 foreign key (engineered_gene_id) references engineered_gene;
alter table gene_delivery add constraint FK918699DE3D222B55 foreign key (organ_id) references organ;
alter table gene_delivery add constraint FK918699DE46872875 foreign key (treatment_id) references treatment;
alter table gene_function add constraint FKB2C0F1C2D749BD3C foreign key (engineered_gene_id) references engineered_gene;
alter table genotype_summary add constraint FK6ECA840B35AE2BF foreign key (nomenclature_id) references nomenclature;
alter table his_clinical_marker add constraint FK361904394E07A166 foreign key (clinical_marker_id) references clinical_marker;
alter table his_clinical_marker add constraint FK36190439D45FCF9F foreign key (histopathology_id) references histopathology;
alter table histopathology add constraint FK587A4AB23FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table histopathology add constraint FK587A4AB2BCA0AB4A foreign key (parent_histopathology_id) references histopathology;
alter table histopathology_disease add constraint FKE5510EF102F8CB5 foreign key (disease_id) references disease;
alter table histopathology_disease add constraint FKE5510EFD45FCF9F foreign key (histopathology_id) references histopathology;
alter table image add constraint FK5FAA95B290CE83F foreign key (availability_id) references availability;
alter table ind_mut_gen_alteration add constraint FKE110EE213FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table ind_mut_gen_alteration add constraint FKE110EE212C7402E4 foreign key (engineered_gene_id) references engineered_gene;
alter table invivo_result add constraint FKC187E8CB887C658A foreign key (endpoint_code_id) references endpoint_code;
alter table invivo_result add constraint FKC187E8CB46872875 foreign key (treatment_id) references treatment;
alter table invivo_result add constraint FKC187E8CB457316D5 foreign key (agent_id) references env_factor;
alter table log add constraint FK1A34477AB701F foreign key (comments_id) references comments;
alter table log add constraint FK1A344496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table log add constraint FK1A3443595FF35 foreign key (party_id) references party;
alter table micro_array_data add constraint FKC3D0BA2B290CE83F foreign key (availability_id) references availability;
alter table organ_histopathology add constraint FK49AEA2A0D45FCF9F foreign key (histopathology_id) references histopathology;
alter table organ_histopathology add constraint FK49AEA2A03D222B55 foreign key (organ_id) references organ;
alter table party_contact_info add constraint FK4B2B4226534EE516 foreign key (contact_info_id) references contact_info;
alter table party_contact_info add constraint FK4B2B42263595FF35 foreign key (party_id) references party;
alter table party_role add constraint FK1C92FE2FAC5A835F foreign key (role_id) references role;
alter table party_role add constraint FK1C92FE2F3595FF35 foreign key (party_id) references party;
alter table phenotype add constraint FKB24800288E609262 foreign key (sex_distribution_id) references sex_distribution;
alter table publication add constraint FKBFBBA22C9B29662E foreign key (publication_status_id) references publication_status;
alter table regulatory_element add constraint FKEA51B055DF44C4B5 foreign key (taxon_id) references taxon;
alter table regulatory_element add constraint FKEA51B055229D942B foreign key (reg_element_type_id) references reg_element_type;
alter table screening_result add constraint FKE30F148646872875 foreign key (treatment_id) references treatment;
alter table screening_result add constraint FKE30F1486457316D5 foreign key (agent_id) references env_factor;
alter table spon_mut_gen_alteration add constraint FKFBEC35E43FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table spon_mut_gen_alteration add constraint FKFBEC35E4130E71D0 foreign key (spontaneous_mutation_id) references spontaneous_mutation;
alter table spontaneous_mutation add constraint FKAE3E7B3B72CE5632 foreign key (mutation_identifier_id) references mutation_identifier;
alter table tar_mod_modification_type add constraint FK211C32E6C1E42178 foreign key (modification_type_id) references modification_type;
alter table tar_mod_modification_type add constraint FK211C32E6DE1A05A5 foreign key (engineered_gene_id) references engineered_gene;
alter table therapy add constraint FKAF8F6C694F764379 foreign key (env_factor_id) references env_factor;
alter table therapy add constraint FKAF8F6C6946872875 foreign key (treatment_id) references treatment;
alter table therapy_publication add constraint FK863317568258D935 foreign key (publication_id) references publication;
alter table therapy_publication add constraint FK86331756DB10995 foreign key (therapy_id) references therapy;
alter table transgene_reg_element add constraint FK32E60ECFB947BD44 foreign key (regulatory_element_id) references regulatory_element;
alter table transgene_reg_element add constraint FK32E60ECF9C4CA0C foreign key (engineered_gene_id) references engineered_gene;
alter table transgene_taxon add constraint FKCFB6A9C8DF44C4B5 foreign key (taxon_id) references taxon;
alter table transgene_taxon add constraint FKCFB6A9C89C4CA0C foreign key (engineered_gene_id) references engineered_gene;
alter table treatment add constraint FKFC3978788E609262 foreign key (sex_distribution_id) references sex_distribution;
alter table xenograft_invivo_result add constraint FKB62953C28FA16C42 foreign key (invivo_result_id) references invivo_result;
alter table xenograft_invivo_result add constraint FKB62953C2F1AE4034 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table yst_mdl_scrning_result add constraint FK737464356928CCA2 foreign key (screening_result_id) references screening_result;
alter table yst_mdl_scrning_result add constraint FK7374643556E4597B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table yst_mdl_trgtd_mod add constraint FK89FF9EAD75303515 foreign key (tar_modification_id) references engineered_gene;
alter table yst_mdl_trgtd_mod add constraint FK89FF9EAD56E4597B foreign key (abs_cancer_model_id) references abs_cancer_model;
create sequence hibernate_sequence;
