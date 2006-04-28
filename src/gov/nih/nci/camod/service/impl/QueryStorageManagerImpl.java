/**
 * @author schroedlni
 * 
 * $Id: QueryStorageManagerImpl.java,v 1.1 2006-04-28 19:20:14 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.QueryStorageManager;
import gov.nih.nci.camod.webapp.form.SearchData;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QueryStorageManagerImpl extends BaseManager implements QueryStorageManager
{
    /**
     * Saves a query with a name so that the user can later retrieve it and resubmit
     * 
     * @param SearchData - the query to be saved    
     * @return primary key of the saved query object created
     * @throws Exception
     */    
     public void saveQuery( SavedQuery inQuery ) throws Exception
     {
        log.trace( "Entering saveQuery" );
        
        // Save to db
        super.save(inQuery);
     }
     
     //TODO: Delete
     public long addQueryToHistory( SavedQuery inQuery ) throws Exception 
     {
         log.trace( "Entering addQueryToHistory" );
         return 1;
     }

     // From the set of search criteria, populate the SearchData
     public SearchForm buildSearchData( Set<SavedQueryAttribute> sqaList, SearchForm sData ) throws Exception 
     {       
         Iterator<SavedQueryAttribute> sqaIter = sqaList.iterator();       
         while ( sqaIter.hasNext() ){
             SavedQueryAttribute sqa = sqaIter.next();
             System.out.println( sqa.getAttributeName() + " = " + sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "PIName"))
                 sData.setPiName( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Model Descriptor"))
                 sData.setModelDescriptor( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Species"))
                 sData.setSpecies( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Phenotype"))
                 sData.setPhenotype( sqa.getAttributeValue() );

             if( sqa.getAttributeName().equals( "Cell Line"))
                 sData.setCellLine( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Organ Tissue Code"))
                 sData.setOrganTissueCode( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Organ Tissue Name"))
                 sData.setOrganTissueName( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Organ"))
                 sData.setOrgan( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Diagnosis Code"))
                 sData.setDiagnosisCode( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Diagnosis Name"))
                 sData.setDiagnosisName( sqa.getAttributeValue() );

             if( sqa.getAttributeName().equals( "Tumor Classification"))
                 sData.setTumorClassification( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "isSearchCarcinogenicInterventions")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setSearchCarcinogenicInterventions( true );
             }
             
             if( sqa.getAttributeName().equals( "Surgery"))
                 sData.setSurgery( sqa.getAttributeValue() );
              
             if( sqa.getAttributeName().equals( "Chemical Drug"))
                 sData.setChemicalDrug( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Hormone"))
                 sData.setHormone( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Viral"))
                 sData.setViral( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Growth Factor"))
                 sData.setGrowthFactor( sqa.getAttributeValue() );  
             
             if( sqa.getAttributeName().equals( "Radiation"))
                 sData.setRadiation( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "isEngineeredTransgene")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setEngineeredTransgene( true );
             }
             
             if( sqa.getAttributeName().equals( "isTargetedModification")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setTargetedModification( true );
             }

             if( sqa.getAttributeName().equals( "Gene Name"))
                 sData.setGeneName( sqa.getAttributeValue() );
                 
             if( sqa.getAttributeName().equals( "Genomic Seg Designator"))
                 sData.setGenomicSegDesignator( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "Induced Mutation Agent"))
                 sData.setInducedMutationAgent( sqa.getAttributeValue() );
               
             if( sqa.getAttributeName().equals( "isSearchTherapeuticApproaches")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setSearchTherapeuticApproaches( true );
             }
             
             if( sqa.getAttributeName().equals( "Therapeutic Approach"))
                 sData.setTherapeuticApproach( sqa.getAttributeValue() );
             
             if( sqa.getAttributeName().equals( "isSearchHistoMetastasis")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setSearchHistoMetastasis( true );
             }
             
             if( sqa.getAttributeName().equals( "isSearchMicroArrayData")) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setSearchMicroArrayData( true );
             }
             
             if( sqa.getAttributeName().equals( "isSearchXenograft") ) {
                 if (sqa.getAttributeValue().equals( "true") )
                     sData.setSearchXenograft( true );
             }
                        
         }
         return sData;
     }
     
     // Builds the set of search criteria from SearchData
     public Set<SavedQueryAttribute> buildCriteriaList( SearchData searchData ) throws Exception 
     {
         Set <SavedQueryAttribute>criteriaList = new HashSet <SavedQueryAttribute>();
         
         log.trace( "Entering addQueryToHistory" );
         
         // PI criteria         
         if (searchData.getPiName() != null && searchData.getPiName().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "PIName" );
             sqa.setAttributeValue( searchData.getPiName() );             
             criteriaList.add( sqa );                                   
         }
         
         // Model Secriptor criteria
         if (searchData.getModelDescriptor() != null && searchData.getModelDescriptor().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Model Descriptor" );
             sqa.setAttributeValue( searchData.getModelDescriptor() );             
             criteriaList.add( sqa );                                   
         }     
         
         // Species
         if (searchData.getSpecies() != null && searchData.getSpecies().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Species" );
             sqa.setAttributeValue( searchData.getSpecies() );             
             criteriaList.add( sqa );                                   
         }    
         
         // Phenotype
         if (searchData.getPhenotype() != null && searchData.getPhenotype().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Phenotype" );
             sqa.setAttributeValue( searchData.getPhenotype() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Cell Line
         if (searchData.getCellLine() != null && searchData.getCellLine().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Cell Line" );
             sqa.setAttributeValue( searchData.getCellLine() );             
             criteriaList.add( sqa );                                   
         }             
         
         // Organ (Tissue Code)
         if (searchData.getOrganTissueCode() != null && searchData.getOrganTissueCode().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Organ Tissue Code" );
             sqa.setAttributeValue( searchData.getOrganTissueCode() );             
             criteriaList.add( sqa );                                   
         }             

         // Organ (Tissue Name)
         if (searchData.getOrganTissueName() != null && searchData.getOrganTissueName().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Organ Tissue Name" );
             sqa.setAttributeValue( searchData.getOrganTissueName() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Organ
         if (searchData.getOrgan() != null && searchData.getOrgan().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Organ" );
             sqa.setAttributeValue( searchData.getOrgan() );             
             criteriaList.add( sqa );                                   
         }   

         // Diagnosis (Code)
         if (searchData.getDiagnosisCode() != null && searchData.getDiagnosisCode().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Diagnosis Code" );
             sqa.setAttributeValue( searchData.getDiagnosisCode() );             
             criteriaList.add( sqa );                                   
         }     

         // Diagnosis (Name)
         if (searchData.getDiagnosisName() != null && searchData.getDiagnosisName().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Diagnosis Name" );
             sqa.setAttributeValue( searchData.getDiagnosisName() );             
             criteriaList.add( sqa );                                   
         }    
         
         // Diagnosis
         
         if (searchData.getTumorClassification() != null && searchData.getTumorClassification().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Tumor Classification" );
             sqa.setAttributeValue( searchData.getTumorClassification() );             
             criteriaList.add( sqa );                                   
         }   
         // ///////////////////////////////////////
         // Carcinogenic interventions
         // ///////////////////////////////////////
         
         if (searchData.isSearchCarcinogenicInterventions() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isSearchCarcinogenicInterventions" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         // Surgery
         if (searchData.getSurgery() != null && searchData.getSurgery().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Surgery" );
             sqa.setAttributeValue( searchData.getSurgery() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Chemical / Drug
         if (searchData.getChemicalDrug() != null && searchData.getChemicalDrug().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Chemical Drug" );
             sqa.setAttributeValue( searchData.getChemicalDrug() );             
             criteriaList.add( sqa );                                   
         }
         
         // Hormone
         if (searchData.getHormone() != null && searchData.getHormone().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Hormone" );
             sqa.setAttributeValue( searchData.getHormone() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Growth Factor
         if (searchData.getGrowthFactor() != null && searchData.getGrowthFactor().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Growth Factor" );
             sqa.setAttributeValue( searchData.getGrowthFactor() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Radiation
         if (searchData.getRadiation() != null && searchData.getRadiation().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Radiation" );
             sqa.setAttributeValue( searchData.getRadiation() );             
             criteriaList.add( sqa );                                   
         }  
         
         // Viral
         if (searchData.getViral() != null && searchData.getViral().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Viral" );
             sqa.setAttributeValue( searchData.getViral() );             
             criteriaList.add( sqa );                                   
         }  

         // ///////////////////////////////////////
         // Genetic Description
         // ///////////////////////////////////////
         
         // is it an Engineered Transgene
         if (searchData.isEngineeredTransgene() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isEngineeredTransgene" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         // is it an Targeted Modification 
         if (searchData.isTargetedModification() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isTargetedModification" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
 
         // Gene Name
         if (searchData.getGeneName() != null && searchData.getGeneName().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Gene Name" );
             sqa.setAttributeValue( searchData.getGeneName() );             
             criteriaList.add( sqa );     
         }
         
         // Genomic Desginator
         if (searchData.getGenomicSegDesignator() != null && searchData.getGenomicSegDesignator().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Genomic Seg Designator" );
             sqa.setAttributeValue( searchData.getGenomicSegDesignator() );             
             criteriaList.add( sqa );     
         }
         
         // Induced Mutation Agent
         if (searchData.getInducedMutationAgent() != null && searchData.getInducedMutationAgent().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Induced Mutation Agent" );
             sqa.setAttributeValue( searchData.getInducedMutationAgent() );             
             criteriaList.add( sqa );     
         }
         
         // Therapeutic Approach
         if (searchData.getTherapeuticApproach() != null && searchData.getTherapeuticApproach().length() > 0) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "Therapeutic Approach" );
             sqa.setAttributeValue( searchData.getTherapeuticApproach() );             
             criteriaList.add( sqa );                                   
         }
         
         // Is it a therapeutic approaches 
         if (searchData.isSearchTherapeuticApproaches() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isSearchTherapeuticApproaches" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         // Is it a metastasis
         if (searchData.isSearchHistoMetastasis() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isSearchHistoMetastasis" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         // Is it a microarray data
         if (searchData.isSearchMicroArrayData() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isSearchMicroArrayData" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         // Is it a xenograft
         if (searchData.isSearchXenograft() == true ) {
             SavedQueryAttribute sqa = new SavedQueryAttribute();
             sqa.setAttributeName( "isSearchXenograft" );
             sqa.setAttributeValue( "true" );             
             criteriaList.add( sqa );     
         }
         
         return criteriaList;
     }
   
       public SavedQuery get( String id ) throws Exception 
       {        
           log.trace("In QueryStorageManagerImpl.get");           
           SavedQuery theSavedQuery = (SavedQuery) super.get(id, SavedQuery.class );
           return theSavedQuery;
       }       

       public List getAllByUsername( String userName ) throws Exception  
       {          
           List sqList = QueryManagerSingleton.instance().getQueriesByParty( userName );
           
           //Prune the list, any queries > 20
           for( int i=0; i < sqList.size(); i++ ) {
               if ( i >= 20 ) {
                   SavedQuery sq = (SavedQuery) sqList.get(i);
                   remove( sq.getId().toString() );
               }
           }
           
           return QueryManagerSingleton.instance().getQueriesByParty( userName );
       }
       
       public List getSavedQueriesByUsername( String userName ) throws Exception
       {          
           return QueryManagerSingleton.instance().getSavedQueriesByParty( userName );
       }
              
       // update
       public void update( SavedQuery inQuery ) throws Exception
       {
           // Save to db
           log.trace("In QueryStorageManagerImpl.update");
           super.save(inQuery);
       }

       public void remove( String id ) throws Exception
       {
           //delete from db
           log.trace("In QueryStorageManagerImpl.remove");
           super.remove( id, SavedQuery.class );                      
       }
       
}
