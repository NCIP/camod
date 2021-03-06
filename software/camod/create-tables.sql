/*L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L*/

drop table abs_can_mod_publication cascade constraints;
drop table abs_cancer_model cascade constraints;
drop table agent cascade constraints;
drop table agent_agent_target cascade constraints;
drop table agent_biological_process cascade constraints;
drop table agent_chemical_class cascade constraints;
drop table agent_target cascade constraints;
drop table animal_availability cascade constraints;
drop table animal_distributor cascade constraints;
drop table availability cascade constraints;
drop table biological_process cascade constraints;
drop table carcinogen_exposure cascade constraints;
drop table cell_line cascade constraints;
drop table cell_line_publication cascade constraints;
drop table chemical_class cascade constraints;
drop table clinical_marker cascade constraints;
drop table comments cascade constraints;
drop table conditionality cascade constraints;
drop table contact_info cascade constraints;
drop table developmental_stage cascade constraints;
drop table disease cascade constraints;
drop table endpoint_code cascade constraints;
drop table engineered_gene cascade constraints;
drop table environmental_factor cascade constraints;
drop table exp_level_desc cascade constraints;
drop table expression_feature cascade constraints;
drop table gene_delivery cascade constraints;
drop table gene_function cascade constraints;
drop table genetic_alteration cascade constraints;
drop table genotype cascade constraints;
drop table genotype_summary cascade constraints;
drop table histopathology cascade constraints;
drop table image cascade constraints;
drop table invivo_result cascade constraints;
drop table log cascade constraints;
drop table micro_array_data cascade constraints;
drop table model_section cascade constraints;
drop table modification_type cascade constraints;
drop table mutation_identifier cascade constraints;
drop table nomenclature cascade constraints;
drop table organ cascade constraints;
drop table party cascade constraints;
drop table party_contact_info cascade constraints;
drop table party_role cascade constraints;
drop table phenotype cascade constraints;
drop table publication cascade constraints;
drop table publication_status cascade constraints;
drop table reg_element_type cascade constraints;
drop table regulatory_element cascade constraints;
drop table repository_info cascade constraints;
drop table result_settings cascade constraints;
drop table result_settings_columns cascade constraints;
drop table role cascade constraints;
drop table saved_query cascade constraints;
drop table saved_query_attribute cascade constraints;
drop table screening_result cascade constraints;
drop table segment_type cascade constraints;
drop table sex_distribution cascade constraints;
drop table species cascade constraints;
drop table spontaneous_mutation cascade constraints;
drop table staining_method cascade constraints;
drop table strain cascade constraints;
drop table tar_mod_modification_type cascade constraints;
drop table therapy cascade constraints;
drop table therapy_publication cascade constraints;
drop table transgene_reg_element cascade constraints;
drop table transient_interference cascade constraints;
drop table transient_interference_method cascade constraints;
drop table treatment cascade constraints;
drop table tumor_code cascade constraints;
drop table xenograft_invivo_result cascade constraints;
drop table yst_mdl_scrning_result cascade constraints;
drop table yst_mdl_trgtd_mod cascade constraints;
drop sequence hibernate_sequence;
create table abs_can_mod_publication (
   abs_cancer_model_id number(19,0) not null,
   publication_id number(19,0) not null unique,
   primary key (abs_cancer_model_id, publication_id)
);
create table abs_cancer_model (
   abs_cancer_model_id number(19,0) not null,
   abs_cancer_model_type varchar2(255) not null,
   experiment_design clob,
   model_descriptor varchar2(255),
   state varchar2(255),
   availability_id number(19,0) unique,
   strain_id number(19,0),
   submitter_id number(19,0),
   principal_investigator_id number(19,0),
   url varchar2(255),
   is_tool_strain number(1,0),
   external_source varchar2(255),
   external_source_identifier varchar2(255),
   developmental_stage varchar2(255),
   repository_info_id number(19,0),
   phenotype_id number(19,0),
   administrative_site varchar2(255),
   admin_site_unctrl_vocab varchar2(255),
   conditioning_regimen varchar2(255),
   cond_regimen_unctrl_vocab varchar2(255),
   genetic_manipulation varchar2(255),
   modification_description varchar2(255),
   parental_cell_line_name varchar2(255),
   xenograft_name varchar2(255),
   atcc_number varchar2(255),
   cell_amount varchar2(255),
   growth_period varchar2(255),
   graft_type varchar2(255),
   graft_type_unctrl_vocab varchar2(255),
   tumor_code_id number(19,0),
   donor_species_id number(19,0),
   organ_id number(19,0),
   par_abs_can_model_id number(19,0),
   primary key (abs_cancer_model_id)
);
create table agent (
   agent_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   nsc_number number(19,0),
   cas_number varchar2(255),
   is_cmap_agent number(1,0),
   comments varchar2(255),
   source varchar2(255),
   primary key (agent_id)
);
create table agent_agent_target (
   agent_id number(19,0) not null,
   agent_target_id number(19,0) not null,
   primary key (agent_id, agent_target_id)
);
create table agent_biological_process (
   agent_id number(19,0) not null,
   biological_process_id number(19,0) not null,
   primary key (agent_id, biological_process_id)
);
create table agent_chemical_class (
   agent_id number(19,0) not null,
   chemical_class_id number(19,0) not null,
   primary key (agent_id, chemical_class_id)
);
create table agent_target (
   agent_target_id number(19,0) not null,
   name varchar2(255),
   primary key (agent_target_id)
);
create table animal_availability (
   animal_availability_id number(19,0) not null,
   name varchar2(255),
   stock_number varchar2(255),
   animal_distributor_id number(19,0),
   abs_cancer_model_id number(19,0) not null,
   primary key (animal_availability_id)
);
create table animal_distributor (
   animal_distributor_id number(19,0) not null,
   name varchar2(255),
   primary key (animal_distributor_id)
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
   name varchar2(255),
   primary key (biological_process_id)
);
create table carcinogen_exposure (
   carcinogen_exposure_id number(19,0) not null,
   environmental_factor_id number(19,0),
   treatment_id number(19,0) unique,
   abs_cancer_model_id number(19,0) not null,
   primary key (carcinogen_exposure_id)
);
create table cell_line (
   cell_line_id number(19,0) not null,
   name varchar2(255),
   experiment varchar2(2000),
   results varchar2(2000),
   comments varchar2(2000),
   organ_id number(19,0),
   abs_cancer_model_id number(19,0) not null,
   primary key (cell_line_id)
);
create table cell_line_publication (
   cell_line_id number(19,0) not null,
   publication_id number(19,0) not null unique,
   primary key (cell_line_id, publication_id)
);
create table chemical_class (
   chemical_class_id number(19,0) not null,
   name varchar2(255),
   primary key (chemical_class_id)
);
create table clinical_marker (
   clinical_marker_id number(19,0) not null,
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   value varchar2(255),
   histopathology_id number(19,0) not null,
   primary key (clinical_marker_id)
);
create table comments (
   comments_id number(19,0) not null,
   remark varchar2(2000),
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
create table developmental_stage (
   developmental_stage_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   primary key (developmental_stage_id)
);
create table disease (
   disease_id number(19,0) not null,
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   concept_code varchar2(255),
   primary key (disease_id)
);
create table endpoint_code (
   endpoint_code_id number(19,0) not null,
   code varchar2(255),
   description varchar2(2000),
   primary key (endpoint_code_id)
);
create table engineered_gene (
   engineered_gene_id number(19,0) not null,
   engineered_gene_type varchar2(255) not null,
   name varchar2(255),
   comments varchar2(2000),
   conditionality_id number(19,0) unique,
   image_id number(19,0),
   mutation_identifier_id number(19,0) unique,
   location_of_integration varchar2(255),
   is_random number(1,0),
   segment_size varchar2(255),
   clone_designator varchar2(255),
   construct_sequence clob,
   segment_type_id number(19,0),
   gene_id varchar2(255),
   description varchar2(255),
   genetic_alteration_id number(19,0) unique,
   environmental_factor_id number(19,0),
   es_cell_line_name varchar2(255),
   blastocyst_name varchar2(255),
   species_id number(19,0) unique,
   abs_cancer_model_id number(19,0) not null,
   primary key (engineered_gene_id)
);
create table environmental_factor (
   environmental_factor_id number(19,0) not null,
   type varchar2(255),
   type_unctrl_vocab varchar2(255),
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   cas_number varchar2(255),
   nsc_number number(19,0),
   primary key (environmental_factor_id)
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
   engineered_gene_id number(19,0) not null,
   primary key (expression_feature_id)
);
create table gene_delivery (
   gene_delivery_id number(19,0) not null,
   viral_vector varchar2(255),
   viral_vector_unctrl_vocab varchar2(255),
   gene_in_virus varchar2(255),
   organ_id number(19,0) unique,
   treatment_id number(19,0) unique,
   abs_cancer_model_id number(19,0) not null,
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
   observation varchar2(255),
   method_of_observation varchar2(255),
   primary key (genetic_alteration_id)
);
create table genotype (
   genotype_id number(19,0) not null,
   name varchar2(255),
   abs_cancer_model_id number(19,0) not null,
   primary key (genotype_id)
);
create table genotype_summary (
   genotype_summary_id number(19,0) not null,
   summary varchar2(255),
   genotype varchar2(255),
   nomenclature_id number(19,0),
   primary key (genotype_summary_id)
);
create table histopathology (
   histopathology_id number(19,0) not null,
   comments clob,
   gross_description varchar2(2000),
   relational_operation varchar2(255),
   tumor_incidence_rate varchar2(255),
   survival_info varchar2(255),
   age_of_onset varchar2(255),
   age_of_onset_unit varchar2(255),
   age_of_detection varchar2(255),
   age_of_detection_unit varchar2(255),
   microscopic_description varchar2(2000),
   weight_of_tumor varchar2(255),
   volume_of_tumor varchar2(255),
   comparative_data varchar2(255),
   disease_id number(19,0),
   organ_id number(19,0),
   genetic_alteration_id number(19,0) unique,
   abs_cancer_model_id number(19,0),
   parent_histopathology_id number(19,0),
   primary key (histopathology_id)
);
create table image (
   image_id number(19,0) not null,
   title varchar2(255),
   description varchar2(4000),
   file_server_location varchar2(255),
   staining_method_id number(19,0),
   abs_cancer_model_id number(19,0),
   primary key (image_id)
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
   notes varchar2(2000),
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
   abs_cancer_model_id number(19,0) not null,
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
   name_unctrl_vocab varchar2(255),
   primary key (modification_type_id)
);
create table mutation_identifier (
   mutation_identifier_id number(19,0) not null,
   mgi_id varchar2(255),
   zfin_id varchar2(255),
   rgd_id varchar2(255),
   primary key (mutation_identifier_id)
);
create table nomenclature (
   nomenclature_id number(19,0) not null,
   name varchar2(255),
   abs_cancer_model_id number(19,0) not null,
   primary key (nomenclature_id)
);
create table organ (
   organ_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   primary key (organ_id)
);
create table party (
   party_id number(19,0) not null,
   party_type varchar2(255) not null,
   first_name varchar2(255),
   middle_name varchar2(255),
   last_name varchar2(255),
   username varchar2(255),
   is_principal_investigator number(1,0),
   name varchar2(255),
   primary key (party_id)
);
create table party_contact_info (
   contact_info_id number(19,0) not null,
   party_id number(19,0) not null,
   primary key (party_id, contact_info_id)
);
create table party_role (
   party_id number(19,0) not null,
   role_id number(19,0) not null,
   primary key (party_id, role_id)
);
create table phenotype (
   phenotype_id number(19,0) not null,
   breeding_notes varchar2(255),
   description varchar2(4000),
   sex_distribution_id number(19,0),
   primary key (phenotype_id)
);
create table publication (
   publication_id number(19,0) not null,
   volume varchar2(255),
   end_page number(19,0),
   year number(19,0),
   title varchar2(2000),
   pmid number(19,0),
   start_page number(19,0),
   journal varchar2(255),
   authors varchar2(255),
   first_time_reported number(1,0),
   j_number varchar2(255),
   zfin_pub_id varchar2(255),
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
   species_id number(19,0),
   primary key (regulatory_element_id)
);
create table repository_info (
   repository_info_id number(19,0) not null,
   in_the_repository number(19,0),
   sent_email_content varchar2(255),
   suggest_submission number(19,0),
   primary key (repository_info_id)
);
create table result_settings (
   result_settings_id number(19,0) not null,
   items_per_page number(10,0),
   party_id number(19,0),
   primary key (result_settings_id)
);
create table result_settings_columns (
   result_settings_columns_id number(19,0) not null,
   column_name varchar2(255),
   column_order number(10,0),
   result_settings_id number(19,0),
   primary key (result_settings_columns_id)
);
create table role (
   role_id number(19,0) not null,
   name varchar2(255),
   primary key (role_id)
);
create table saved_query (
   saved_query_id number(19,0) not null,
   query_name varchar2(255),
   query_elapsed_time number(19,0),
   query_execute_timestamp timestamp,
   is_saved number(19,0),
   number_results number(19,0),
   party_id number(19,0),
   primary key (saved_query_id)
);
create table saved_query_attribute (
   saved_query_attribute_id number(19,0) not null,
   attribute_name varchar2(255),
   attribute_value varchar2(255),
   saved_query_id number(19,0),
   primary key (saved_query_attribute_id)
);
create table screening_result (
   screening_result_id number(19,0) not null,
   stage varchar2(255),
   diffinh float,
   aveinh float,
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
create table species (
   species_id number(19,0) not null,
   scientific_name varchar2(255),
   scientific_name_unctrl_vocab varchar2(255),
   common_name varchar2(255),
   common_name_unctrl_vocab varchar2(255),
   abbreviation varchar2(255),
   concept_code varchar2(255),
   primary key (species_id)
);
create table spontaneous_mutation (
   spontaneous_mutation_id number(19,0) not null,
   name varchar2(255),
   gene_id varchar2(255),
   comments varchar2(255),
   genetic_alteration_id number(19,0) unique,
   mutation_identifier_id number(19,0),
   abs_cancer_model_id number(19,0),
   primary key (spontaneous_mutation_id)
);
create table staining_method (
   staining_method_id number(19,0) not null,
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   concept_code varchar2(255),
   primary key (staining_method_id)
);
create table strain (
   strain_id number(19,0) not null,
   name varchar2(255),
   name_unctrl_vocab varchar2(255),
   abbreviation varchar2(255),
   concept_code varchar2(255),
   species_id number(19,0) not null,
   mutation_identifier_id number(19,0) unique,
   primary key (strain_id)
);
create table tar_mod_modification_type (
   engineered_gene_id number(19,0) not null,
   modification_type_id number(19,0) not null,
   primary key (engineered_gene_id, modification_type_id)
);
create table therapy (
   therapy_id number(19,0) not null,
   experiment varchar2(255),
   results varchar2(255),
   toxicity_grade varchar2(255),
   biomarker varchar2(255),
   tumor_response varchar2(255),
   comments varchar2(255),
   agent_id number(19,0),
   developmental_stage_id number(19,0),
   treatment_id number(19,0) unique,
   abs_cancer_model_id number(19,0) not null,
   primary key (therapy_id)
);
create table therapy_publication (
   therapy_id number(19,0) not null,
   publication_id number(19,0) not null unique,
   primary key (therapy_id, publication_id)
);
create table transgene_reg_element (
   engineered_gene_id number(19,0) not null,
   regulatory_element_id number(19,0) not null unique,
   primary key (engineered_gene_id, regulatory_element_id)
);
create table transient_interference (
   transient_interference_id number(19,0) not null,
   source varchar2(255),
   source_unctr_vocab varchar2(255),
   type varchar2(255),
   sequence_direction varchar2(255),
   targeted_region varchar2(255),
   concentration varchar2(255),
   concentration_unit varchar2(255),
   delivery_method varchar2(255),
   delivery_method_unctrl_vocab varchar2(255),
   visual_ligand varchar2(255),
   visual_ligand_unctrl_vocab varchar2(255),
   comments clob,
   target_site varchar2(255),
   trans_interference_method_id number(19,0),
   abs_cancer_model_id number(19,0) not null,
   primary key (transient_interference_id)
);
create table transient_interference_method (
   trans_interference_method_id number(19,0) not null,
   name varchar2(255),
   concept_code varchar2(255),
   primary key (trans_interference_method_id)
);
create table treatment (
   treatment_id number(19,0) not null,
   regimen varchar2(255),
   dosage varchar2(255),
   dosage_unit varchar2(255),
   administrative_route varchar2(255),
   admin_route_unctrl_vocab varchar2(255),
   age_at_treatment varchar2(255),
   age_at_treatment_unit varchar2(255),
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
   invivo_result_id number(19,0) not null unique,
   primary key (abs_cancer_model_id, invivo_result_id)
);
create table yst_mdl_scrning_result (
   abs_cancer_model_id number(19,0) not null,
   screening_result_id number(19,0) not null unique,
   primary key (abs_cancer_model_id, screening_result_id)
);
create table yst_mdl_trgtd_mod (
   abs_cancer_model_id number(19,0) not null,
   tar_modification_id number(19,0) not null unique,
   primary key (abs_cancer_model_id, tar_modification_id)
);
alter table abs_can_mod_publication add constraint FK9377C2B38258D935 foreign key (publication_id) references publication;
alter table abs_can_mod_publication add constraint FK9377C2B3496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table abs_cancer_model add constraint FKBC9267574D19DE94 foreign key (tumor_code_id) references tumor_code;
alter table abs_cancer_model add constraint FKBC926757D490A9BF foreign key (strain_id) references strain;
alter table abs_cancer_model add constraint FKBC926757C104A02E foreign key (donor_species_id) references species;
alter table abs_cancer_model add constraint FKBC9267572D8B96DA foreign key (principal_investigator_id) references party;
alter table abs_cancer_model add constraint FKBC9267572175CA75 foreign key (phenotype_id) references phenotype;
alter table abs_cancer_model add constraint FKBC9267573D222B55 foreign key (organ_id) references organ;
alter table abs_cancer_model add constraint FKBC9267576DC9C38B foreign key (submitter_id) references party;
alter table abs_cancer_model add constraint FKBC9267579BE9D993 foreign key (par_abs_can_model_id) references abs_cancer_model;
alter table abs_cancer_model add constraint FKBC92675765C8F094 foreign key (repository_info_id) references repository_info;
alter table abs_cancer_model add constraint FKBC926757290CE83F foreign key (availability_id) references availability;
alter table agent_agent_target add constraint FKCD3DA3053C5575C0 foreign key (agent_target_id) references agent_target;
alter table agent_agent_target add constraint FKCD3DA305457316D5 foreign key (agent_id) references agent;
alter table agent_biological_process add constraint FK5FEB6DAB42FAD9D2 foreign key (biological_process_id) references biological_process;
alter table agent_biological_process add constraint FK5FEB6DAB457316D5 foreign key (agent_id) references agent;
alter table agent_chemical_class add constraint FKD3317785BD096390 foreign key (chemical_class_id) references chemical_class;
alter table agent_chemical_class add constraint FKD3317785457316D5 foreign key (agent_id) references agent;
alter table animal_availability add constraint FKD4698ABEFB3FC01C foreign key (animal_distributor_id) references animal_distributor;
alter table animal_availability add constraint FKD4698ABE1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table carcinogen_exposure add constraint FKDF2E871119EF4CF2 foreign key (environmental_factor_id) references environmental_factor;
alter table carcinogen_exposure add constraint FKDF2E87111CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table carcinogen_exposure add constraint FKDF2E871146872875 foreign key (treatment_id) references treatment;
alter table cell_line add constraint FK61276CB13D222B55 foreign key (organ_id) references organ;
alter table cell_line add constraint FK61276CB11CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table cell_line_publication add constraint FKA8A4EB9E8258D935 foreign key (publication_id) references publication;
alter table cell_line_publication add constraint FKA8A4EB9ED2525AC4 foreign key (cell_line_id) references cell_line;
alter table clinical_marker add constraint FK179B7EE6D45FCF9F foreign key (histopathology_id) references histopathology;
alter table comments add constraint FKDC17DDF495BE676C foreign key (model_section_id) references model_section;
alter table comments add constraint FKDC17DDF42EB4E88E foreign key (party_id) references party;
alter table comments add constraint FKDC17DDF4496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table comments add constraint FKDC17DDF4290CE83F foreign key (availability_id) references availability;
alter table engineered_gene add constraint FK4ADB496687D074B5 foreign key (species_id) references species;
alter table engineered_gene add constraint FK4ADB496653A0BB3C foreign key (segment_type_id) references segment_type;
alter table engineered_gene add constraint FK4ADB4966BB186F15 foreign key (image_id) references image;
alter table engineered_gene add constraint FK4ADB49663FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table engineered_gene add constraint FK4ADB496672CE5632 foreign key (mutation_identifier_id) references mutation_identifier;
alter table engineered_gene add constraint FK4ADB496619EF4CF2 foreign key (environmental_factor_id) references environmental_factor;
alter table engineered_gene add constraint FK4ADB49661CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table engineered_gene add constraint FK4ADB496666D7EBDF foreign key (conditionality_id) references conditionality;
alter table expression_feature add constraint FKCC1D9C4F3D222B55 foreign key (organ_id) references organ;
alter table expression_feature add constraint FKCC1D9C4FD749BD3C foreign key (engineered_gene_id) references engineered_gene;
alter table expression_feature add constraint FKCC1D9C4FA17421A4 foreign key (exp_level_desc_id) references exp_level_desc;
alter table gene_delivery add constraint FK918699DE3D222B55 foreign key (organ_id) references organ;
alter table gene_delivery add constraint FK918699DE1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table gene_delivery add constraint FK918699DE46872875 foreign key (treatment_id) references treatment;
alter table gene_function add constraint FKB2C0F1C2D749BD3C foreign key (engineered_gene_id) references engineered_gene;
alter table genotype add constraint FK6C7642D91CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table genotype_summary add constraint FK6ECA840B35AE2BF foreign key (nomenclature_id) references nomenclature;
alter table histopathology add constraint FK587A4AB2102F8CB5 foreign key (disease_id) references disease;
alter table histopathology add constraint FK587A4AB23FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table histopathology add constraint FK587A4AB2BCA0AB4A foreign key (parent_histopathology_id) references histopathology;
alter table histopathology add constraint FK587A4AB23D222B55 foreign key (organ_id) references organ;
alter table histopathology add constraint FK587A4AB21CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table image add constraint FK5FAA95BB86E2EDA foreign key (staining_method_id) references staining_method;
alter table image add constraint FK5FAA95B1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table invivo_result add constraint FKC187E8CB887C658A foreign key (endpoint_code_id) references endpoint_code;
alter table invivo_result add constraint FKC187E8CB46872875 foreign key (treatment_id) references treatment;
alter table invivo_result add constraint FKC187E8CB457316D5 foreign key (agent_id) references agent;
alter table log add constraint FK1A34477AB701F foreign key (comments_id) references comments;
alter table log add constraint FK1A3441CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table log add constraint FK1A344496C4E05 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table log add constraint FK1A3442EB4E88E foreign key (party_id) references party;
alter table micro_array_data add constraint FKC3D0BA2B1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table nomenclature add constraint FK356683771CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table party_contact_info add constraint FK4B2B4226534EE516 foreign key (contact_info_id) references contact_info;
alter table party_contact_info add constraint FK4B2B42263595FF35 foreign key (party_id) references party;
alter table party_role add constraint FK1C92FE2FAC5A835F foreign key (role_id) references role;
alter table party_role add constraint FK1C92FE2F3595FF35 foreign key (party_id) references party;
alter table phenotype add constraint FKB24800288E609262 foreign key (sex_distribution_id) references sex_distribution;
alter table publication add constraint FKBFBBA22C9B29662E foreign key (publication_status_id) references publication_status;
alter table regulatory_element add constraint FKEA51B05587D074B5 foreign key (species_id) references species;
alter table regulatory_element add constraint FKEA51B055229D942B foreign key (reg_element_type_id) references reg_element_type;
alter table result_settings add constraint FKA5ECF6052EB4E88E foreign key (party_id) references party;
alter table result_settings_columns add constraint FK67185FA3F638301A foreign key (result_settings_id) references result_settings;
alter table saved_query add constraint FK186CA9102EB4E88E foreign key (party_id) references party;
alter table saved_query_attribute add constraint FK71C13A2DCDAF32D0 foreign key (saved_query_id) references saved_query;
alter table screening_result add constraint FKE30F148646872875 foreign key (treatment_id) references treatment;
alter table screening_result add constraint FKE30F1486457316D5 foreign key (agent_id) references agent;
alter table spontaneous_mutation add constraint FKAE3E7B3B3FE1DDE8 foreign key (genetic_alteration_id) references genetic_alteration;
alter table spontaneous_mutation add constraint FKAE3E7B3B72CE5632 foreign key (mutation_identifier_id) references mutation_identifier;
alter table spontaneous_mutation add constraint FKAE3E7B3B1CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table strain add constraint FKCAD5417587D074B5 foreign key (species_id) references species;
alter table strain add constraint FKCAD5417572CE5632 foreign key (mutation_identifier_id) references mutation_identifier;
alter table tar_mod_modification_type add constraint FK211C32E6C1E42178 foreign key (modification_type_id) references modification_type;
alter table tar_mod_modification_type add constraint FK211C32E6DE1A05A5 foreign key (engineered_gene_id) references engineered_gene;
alter table therapy add constraint FKAF8F6C6936A94812 foreign key (developmental_stage_id) references developmental_stage;
alter table therapy add constraint FKAF8F6C691CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table therapy add constraint FKAF8F6C6946872875 foreign key (treatment_id) references treatment;
alter table therapy add constraint FKAF8F6C69457316D5 foreign key (agent_id) references agent;
alter table therapy_publication add constraint FK863317568258D935 foreign key (publication_id) references publication;
alter table therapy_publication add constraint FK86331756DB10995 foreign key (therapy_id) references therapy;
alter table transgene_reg_element add constraint FK32E60ECFB947BD44 foreign key (regulatory_element_id) references regulatory_element;
alter table transgene_reg_element add constraint FK32E60ECF9C4CA0C foreign key (engineered_gene_id) references engineered_gene;
alter table transient_interference add constraint FKE89F80171CC8B88B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table transient_interference add constraint FKE89F801778685E5B foreign key (trans_interference_method_id) references transient_interference_method;
alter table treatment add constraint FKFC3978788E609262 foreign key (sex_distribution_id) references sex_distribution;
alter table xenograft_invivo_result add constraint FKB62953C28FA16C42 foreign key (invivo_result_id) references invivo_result;
alter table xenograft_invivo_result add constraint FKB62953C2F1AE4034 foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table yst_mdl_scrning_result add constraint FK737464356928CCA2 foreign key (screening_result_id) references screening_result;
alter table yst_mdl_scrning_result add constraint FK7374643556E4597B foreign key (abs_cancer_model_id) references abs_cancer_model;
alter table yst_mdl_trgtd_mod add constraint FK89FF9EAD75303515 foreign key (tar_modification_id) references engineered_gene;
alter table yst_mdl_trgtd_mod add constraint FK89FF9EAD56E4597B foreign key (abs_cancer_model_id) references abs_cancer_model;
create sequence hibernate_sequence;
