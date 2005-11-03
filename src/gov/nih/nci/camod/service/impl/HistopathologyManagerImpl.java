/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerImpl.java,v 1.1 2005-11-03 18:54:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import java.util.List;
import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisData;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerData;
import gov.nih.nci.camod.webapp.form.HistopathologyData;


public class HistopathologyManagerImpl extends BaseManager implements HistopathologyManager {

    public List getAll() throws Exception {
        log.trace("In HistopathologyManagerImpl.getAll");
        return super.getAll(Histopathology.class);
    }

    public Histopathology get(String id) throws Exception {
        log.trace("In HistopathologyManagerImpl.get");
        return (Histopathology) super.get(id, Histopathology.class);
    }

    public void save(Histopathology histopathology) throws Exception {
        log.trace("In HistopathologyManagerImpl.save");
        super.save(histopathology);
    }

    public void remove(String id) throws Exception {
        log.trace("In HistopathologyManagerImpl.save");
        super.remove(id, Histopathology.class);
    }

    public Histopathology createHistopathology(HistopathologyData inHistopathologyData)throws Exception{

        log.info("Entering HistopathologyManagerImpl.createHistopathology");

        Histopathology theHistopathology = new Histopathology();
        populateHistopathology(inHistopathologyData, theHistopathology);
        
        log.info("Exiting HistopathologyManagerImpl.createHistopathology");        

        return theHistopathology;    	
    }
    
    public void updateHistopathology(HistopathologyData inHistopathologyData, Histopathology inHistopathology) throws Exception{

    	log.info("Entering HistopathologyManagerImpl.updateHistopathology");
    	log.info("Updating HistopathologyData: " + inHistopathology.getId());

    	// Populate w/ the new values and save
    	editHistopathology(inHistopathologyData, inHistopathology);
    	save(inHistopathology);

    	log.info("Exiting HistopathologyManagerImpl.updateHistopathology");    	
    }
    
    private void populateHistopathology(HistopathologyData inHistopathologyData, Histopathology inHistopathology) {

		log.info( "<HistopathologyManagerImpl> Entering populateHistopathology" );
		
        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode 
         */		
		System.out.println("inHistopathology.getOrgan()" + inHistopathology.getOrgan());
        
        //new submission - organ will be null
        if (inHistopathology.getOrgan() == null) {
        	
        	System.out.println("Creating new Organ object");        	
        	inHistopathology.setOrgan(new Organ());
        }

        String newConceptCode = inHistopathologyData.getOrganTissueCode();
    	System.out.println("newConceptCode: " + newConceptCode);
    	
        String oldConceptCode = inHistopathology.getOrgan().getConceptCode();
    	System.out.println("oldConceptCode: " + oldConceptCode); 
    	
        if( !newConceptCode.equals(oldConceptCode) ) 
        {
       	System.out.println("Organ is new or was modified so retrieve attributes");
        /* Always get/store organ name through the concept code - never deal with converting name back and forth */
        String preferedOrganName = EvsTreeUtil.getEVSPreferedOrganDescription(inHistopathologyData.getOrganTissueCode());
        	
        System.out.println("preferedOrganName: " + preferedOrganName);
        inHistopathology.getOrgan().setName(preferedOrganName);
        
        System.out.println("populateMetastasis - getOrgan().setConceptCode - OrganTissueCode: " +inHistopathologyData.getOrganTissueCode());
        inHistopathology.getOrgan().setConceptCode(inHistopathologyData.getOrganTissueCode());            
        }

        /* Add histopathology to Organ */
        inHistopathology.getOrgan().addHistopathology(inHistopathology);        
        System.out.println("Added histopathology to Organ");
        
        /*
         * Add a Disease to AnimalModel with correct IDs, conceptCode  
        */
        Disease inDisease = null;   
        if (inHistopathology.getDiseaseCollection().size() > 0) {
        	System.out.println("Creating new Disease object");        	
        	inDisease = (Disease)inHistopathology.getDiseaseCollection().get(0);
        }	else {
        	inDisease = new Disease(); 
        }
    	inDisease.setName(inHistopathologyData.getDiagnosisName());
    	inDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());
    	
    	/* Add Disease object to Histopathology obejct */
    	inHistopathology.addDisease(inDisease);   
        
        System.out.println("Saving: Histopathology object attributes ");
        inHistopathology.setComments( inHistopathologyData.getComments() );
        inHistopathology.setGrossDescription(inHistopathologyData.getGrossDescription());
        
        String theTumorIncidenceRate = inHistopathologyData.getTumorIncidenceRate().trim();
        if (theTumorIncidenceRate != null && theTumorIncidenceRate.length() > 0) {
            try {
            	inHistopathology.setTumorIncidenceRate(Float.valueOf(theTumorIncidenceRate));
            } catch (NumberFormatException e) {
                log.error("Bad Tumor Incidence Rate: " + theTumorIncidenceRate);
            }
        }        
        
        inHistopathology.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
        inHistopathology.setMicroscopicDescription(inHistopathologyData.getMicroscopicDescription());        
        inHistopathology.setComparativeData( inHistopathologyData.getComparativeData() );
        System.out.println("inHistopathologyData.getComparativeData():  " +inHistopathologyData.getComparativeData());

        String theWeightOfTumor = inHistopathologyData.getWeightOfTumor().trim();
        if (theWeightOfTumor != null && theWeightOfTumor.length() > 0) {
            try {
            	inHistopathology.setWeightOfTumor(Float.valueOf(theWeightOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Weight Of Tumor: " + theWeightOfTumor);
            }
        } 
        
        String theVolumeOfTumor = inHistopathologyData.getVolumeOfTumor().trim();
        if (theVolumeOfTumor != null && theVolumeOfTumor.length() > 0) {
            try {
            	inHistopathology.setVolumeOfTumor(Float.valueOf(theVolumeOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Volume Of Tumor: " + theVolumeOfTumor);
            }
        }        

        //Histopathology attributes that need units appended
        inHistopathology.setAgeOfOnset(inHistopathologyData.getAgeOfOnset() + " " + inHistopathologyData.getAgeUnit());
       
        /* Add GeneticAlteration attributes */
        if (inHistopathology.getGeneticAlteration() == null) {
        	inHistopathology.setGeneticAlteration(new GeneticAlteration());        	
			System.out.println("Saving: inHistopathology.getGeneticAlteration() attributes ");
			
			inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
			inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
        } else {
			inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
			inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
        }

		
		log.info("<HistopathologyManagerImpl> Exiting populateHistopathology");
        }
    
    private void editHistopathology(HistopathologyData inHistopathologyData, Histopathology inHistopathology) {

		log.info( "<HistopathologyManagerImpl> Entering editHistopathology" );
		
        /*
         * Edit Organ only if new conceptCode 
         */		
		System.out.println("inHistopathology.getOrgan()" + inHistopathology.getOrgan());

        String newConceptCode = inHistopathologyData.getOrganTissueCode();
    	System.out.println("newConceptCode: " + newConceptCode);
    	
        String oldConceptCode = inHistopathology.getOrgan().getConceptCode();
    	System.out.println("oldConceptCode: " + oldConceptCode); 
    	
        if( !newConceptCode.equals(oldConceptCode) ) 
        {
       	System.out.println("Organ is new or was modified so retrieve attributes");
        /* Always get/store organ name through the concept code - never deal with converting name back and forth */
        String preferedOrganName = EvsTreeUtil.getEVSPreferedOrganDescription(inHistopathologyData.getOrganTissueCode());
        	
        System.out.println("preferedOrganName: " + preferedOrganName);
        inHistopathology.getOrgan().setName(preferedOrganName);
        
        System.out.println("populateMetastasis - getOrgan().setConceptCode - OrganTissueCode: " +inHistopathologyData.getOrganTissueCode());
        inHistopathology.getOrgan().setConceptCode(inHistopathologyData.getOrganTissueCode());            
        }

        /* Add histopathology to Organ */
        //inHistopathology.getOrgan().addHistopathology(inHistopathology);        
        System.out.println("Not needed: Added histopathology to Organ");
        
        /*
        * TODO: Update Disease with correct IDs, Name 
        

        Disease inDisease = DiseaseManagerSingleton.instance().getByName( inHistopathologyData.getDiseaseName());        		
        System.out.println("inDisease object: " +inDisease);
        		
        inDisease.setName(inHistopathologyData.getDiagnosisName());
        inDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());
        		
        // Add Disease object to Histopathology obejct 
        inHistopathology.addDisease(inDisease);
        System.out.println("Updated Disease object ");
        
    	// Add Disease object to Histopathology obejct 
    	//inHistopathology.addDisease(inDisease); 
    	System.out.println("Not needed: Add Disease object to Histopathology obejct");
    	*/
        System.out.println("Saving: Histopathology object attributes ");
        inHistopathology.setComments( inHistopathologyData.getComments() );
        inHistopathology.setGrossDescription(inHistopathologyData.getGrossDescription());
        
        String theTumorIncidenceRate = inHistopathologyData.getTumorIncidenceRate().trim();
        if (theTumorIncidenceRate != null && theTumorIncidenceRate.length() > 0) {
            try {
            	inHistopathology.setTumorIncidenceRate(Float.valueOf(theTumorIncidenceRate));
            } catch (NumberFormatException e) {
                log.error("Bad Tumor Incidence Rate: " + theTumorIncidenceRate);
            }
        }        
        
        inHistopathology.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
        inHistopathology.setMicroscopicDescription(inHistopathologyData.getMicroscopicDescription());        
        inHistopathology.setComparativeData( inHistopathologyData.getComparativeData() );
        System.out.println("inHistopathologyData.getComparativeData():  " +inHistopathologyData.getComparativeData());

        String theWeightOfTumor = inHistopathologyData.getWeightOfTumor().trim();
        if (theWeightOfTumor != null && theWeightOfTumor.length() > 0) {
            try {
            	inHistopathology.setWeightOfTumor(Float.valueOf(theWeightOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Weight Of Tumor: " + theWeightOfTumor);
            }
        } 
        
        String theVolumeOfTumor = inHistopathologyData.getVolumeOfTumor().trim();
        if (theVolumeOfTumor != null && theVolumeOfTumor.length() > 0) {
            try {
            	inHistopathology.setVolumeOfTumor(Float.valueOf(theVolumeOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Volume Of Tumor: " + theVolumeOfTumor);
            }
        }        

        //Histopathology attributes that need units appended
        inHistopathology.setAgeOfOnset(inHistopathologyData.getAgeOfOnset() + " " + inHistopathologyData.getAgeUnit());
       
        /* Add GeneticAlteration attributes */
        if (inHistopathology.getGeneticAlteration() == null) {
        	inHistopathology.setGeneticAlteration(new GeneticAlteration());        	
			System.out.println("Saving: inHistopathology.getGeneticAlteration() attributes ");
			
			inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
			inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
        } else {
			inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
			inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
        }
		
		log.info("<HistopathologyManagerImpl> Exiting editHistopathology");
    }
    
    public void createAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inHistopathology)throws Exception{

        log.info("Entering HistopathologyManagerImpl.createMetastasis");        

        //Histopathology theHistopathology = new Histopathology();     
        
        populateAssociatedMetastasis(inHistopathology, inAssociatedMetastasisData);
        
        log.info("Exiting HistopathologyManagerImpl.createMetastasis");        

        //return theHistopathology;    	
    }
    
    public void updateAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inHistopathology, String inAssociatedMetastasisID) throws Exception{

    	log.info("Entering HistopathologyManagerImpl.updateAssociatedMetastasis");
    	log.info("Updating HistopathologyData: " + inHistopathology.getId());

    	// Populate w/ the new values and save
    	editAssociatedMetastasis(inHistopathology, inAssociatedMetastasisData, inAssociatedMetastasisID);
    	save(inHistopathology);

    	log.info("Exiting HistopathologyManagerImpl.updateAssociatedMetastasis");    	
    }
    
    private void populateAssociatedMetastasis(Histopathology inHistopathology, AssociatedMetastasisData inAssociatedMetastasisData) {

		log.info( "<HistopathologyManagerImpl> Entering populateMetastasis" );
		
		//create new Histopathology to be used as the current associated metastasis
		Histopathology associatedMetastasis = new Histopathology();
		
        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode 
         */		
        /* Always get/store organ name through the concept code - never deal with converting name back and forth */
        String preferedOrganName = EvsTreeUtil.getEVSPreferedOrganDescription(inAssociatedMetastasisData.getOrganTissueCode());
        log.trace("preferedOrganName: " +preferedOrganName);
        
        //new submission - organ will be null
        System.out.println("Creating new Organ object");        	
        associatedMetastasis.setOrgan(new Organ());
		
        System.out.println("preferedOrganName: " + preferedOrganName);
        associatedMetastasis.getOrgan().setName(preferedOrganName);
        System.out.println("inAssociatedMetastasisData.getOrganTissueCode(): " + inAssociatedMetastasisData.getOrganTissueCode());
        associatedMetastasis.getOrgan().setConceptCode( inAssociatedMetastasisData.getOrganTissueCode());
        
        /* Add histopathology to Organ, if needed */
        associatedMetastasis.getOrgan().addHistopathology(associatedMetastasis);
        System.out.println("Added histopathology to Organ");
        
        /*
         * Add a Disease to AnimalModel with correct IDs, conceptCode 
         */		
        /* Always get/store organ name through the concept code - never deal with converting name back and forth */
        String preferedDiseaseName = EvsTreeUtil.getEVSPreferedOrganDescription(inAssociatedMetastasisData.getDiagnosisCode());        
        log.trace("preferedDiseaseName: " +preferedDiseaseName);

        Disease inDisease = null;   
        if (associatedMetastasis.getDiseaseCollection().size() > 0) {
        	System.out.println("Creating new Disease object");        	
        	inDisease = (Disease)associatedMetastasis.getDiseaseCollection().get(0);
        }	else {
        	inDisease = new Disease(); 
        }
    	inDisease.setName(inAssociatedMetastasisData.getDiagnosisName());
    	inDisease.setConceptCode(inAssociatedMetastasisData.getDiagnosisCode());
    	
    	/* Add Disease object to Histopathology obejct */
    	associatedMetastasis.addDisease(inDisease);   
        
        System.out.println("Saving: Histopathology object attributes ");
        associatedMetastasis.setComments( inAssociatedMetastasisData.getComments() );
        associatedMetastasis.setGrossDescription(inAssociatedMetastasisData.getGrossDescription());
        
        String theTumorIncidenceRate = inAssociatedMetastasisData.getTumorIncidenceRate().trim();
        if (theTumorIncidenceRate != null && theTumorIncidenceRate.length() > 0) {
            try {
            	associatedMetastasis.setTumorIncidenceRate(Float.valueOf(theTumorIncidenceRate));
            } catch (NumberFormatException e) {
                log.error("Bad Tumor Incidence Rate: " + theTumorIncidenceRate);
            }
        }        
        
        associatedMetastasis.setSurvivalInfo(inAssociatedMetastasisData.getSurvivalInfo());
        associatedMetastasis.setMicroscopicDescription(inAssociatedMetastasisData.getMicroscopicDescription());        
        associatedMetastasis.setComparativeData( inAssociatedMetastasisData.getComparativeData() );
        System.out.println("inHistopathologyData.getComparativeData():  " +inAssociatedMetastasisData.getComparativeData());

        String theWeightOfTumor = inAssociatedMetastasisData.getWeightOfTumor().trim();
        if (theWeightOfTumor != null && theWeightOfTumor.length() > 0) {
            try {
            	associatedMetastasis.setWeightOfTumor(Float.valueOf(theWeightOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Weight Of Tumor: " + theWeightOfTumor);
            }
        }  
        
        String theVolumeOfTumor = inAssociatedMetastasisData.getVolumeOfTumor().trim();
        if (theVolumeOfTumor != null && theVolumeOfTumor.length() > 0) {
            try {
            	associatedMetastasis.setVolumeOfTumor(Float.valueOf(theVolumeOfTumor));
            } catch (NumberFormatException e) {
                log.error("Bad Volume Of Tumor: " + theVolumeOfTumor);
            }
        }        

        //Histopathology attributes that need units appended
        associatedMetastasis.setAgeOfOnset(inAssociatedMetastasisData.getAgeOfOnset() + " " + inAssociatedMetastasisData.getAgeUnit());
       
        /* Add GeneticAlteration attributes */
        if (associatedMetastasis.getGeneticAlteration() == null) {
        	associatedMetastasis.setGeneticAlteration(new GeneticAlteration());
        }		
		System.out.println("Saving: inHistopathology.getGeneticAlteration() attributes ");
		
		associatedMetastasis.getGeneticAlteration().setObservation(inAssociatedMetastasisData.getObservation());
		associatedMetastasis.getGeneticAlteration().setMethodOfObservation(inAssociatedMetastasisData.getMethodOfObservation());
		
		/* Add Metastasis to Histopathology - ParentHistopathology_id not null */
		inHistopathology.addHistopathology(associatedMetastasis);
		System.out.println("HistopathologyManagerImpl> Added Metastasis to Histopathology ");
		
		
		log.info("<HistopathologyManagerImpl> Exiting populateMetastasis");
    } 
    


    private void editAssociatedMetastasis(Histopathology inHistopathology, AssociatedMetastasisData inAssociatedMetastasisData, String inAssociatedMetastasisID) {

		log.info( "<HistopathologyManagerImpl> Entering editAssociatedMetastasis" );
		
        List assocMetastasisList = inHistopathology.getMetastatisCollection();
        
        for( int i=0; i < assocMetastasisList.size(); i++ ) 
        {
        	Histopathology associatedMetastasis = (Histopathology) assocMetastasisList.get(i);

        	/* Match histopathology_id in DB to the curent id (inAssociatedMetastasisID) or  
        	 * a duplicate histopathology entry will be added to the DB
        	 */
        	if( associatedMetastasis.getId().toString().equals( inAssociatedMetastasisID )) {
        		
        		System.out.println("Editing: AssociatedMetastasis object attributes ");        		

                /*
                 * Edit Organ only if new conceptCode 
                 */		
        		System.out.println("inAssociatedMetastasisData.getOrgan()" + inHistopathology.getOrgan());

                String newConceptCode = inAssociatedMetastasisData.getOrganTissueCode();
            	System.out.println("newConceptCode: " + newConceptCode);
            	
                String oldConceptCode = inHistopathology.getOrgan().getConceptCode();
            	System.out.println("oldConceptCode: " + oldConceptCode); 
            	
                if( !newConceptCode.equals(oldConceptCode) ) 
                {
               	System.out.println("Organ is new or was modified so retrieve attributes");
               	
                /* Always get/store organ name through the concept code - never deal with converting name back and forth */
                String preferedOrganName = EvsTreeUtil.getEVSPreferedOrganDescription(inAssociatedMetastasisData.getOrganTissueCode());
                	
                System.out.println("preferedOrganName: " + preferedOrganName);
                associatedMetastasis.getOrgan().setName(preferedOrganName);
                
                System.out.println("populateMetastasis - getOrgan().setConceptCode - OrganTissueCode: " +inAssociatedMetastasisData.getOrganTissueCode());
                associatedMetastasis.getOrgan().setConceptCode(inAssociatedMetastasisData.getOrganTissueCode());            
                }
                /*
                 * TODO: Update Disease with correct IDs, Name 
                       		
        		Disease inDisease = DiseaseManagerSingleton.instance().getByName( inAssociatedMetastasisData.getDiseaseName());        		
        		System.out.println("inDisease object: " +inDisease);
        		
        		inDisease.setName(inAssociatedMetastasisData.getDiagnosisName());
        		inDisease.setConceptCode(inAssociatedMetastasisData.getDiagnosisCode());
        		
            	// Add Disease object to Histopathology obejct 
        		associatedMetastasis.addDisease(inDisease);
        		*/ 
                System.out.println("Saving: Histopathology object attributes ");
                associatedMetastasis.setComments( inAssociatedMetastasisData.getComments() );
                associatedMetastasis.setGrossDescription(inAssociatedMetastasisData.getGrossDescription());
                
                String theTumorIncidenceRate = inAssociatedMetastasisData.getTumorIncidenceRate().trim();
                if (theTumorIncidenceRate != null && theTumorIncidenceRate.length() > 0) {
                    try {
                    	associatedMetastasis.setTumorIncidenceRate(Float.valueOf(theTumorIncidenceRate));
                    } catch (NumberFormatException e) {
                        log.error("Bad Tumor Incidence Rate: " + theTumorIncidenceRate);
                    }
                } 
                
                associatedMetastasis.setSurvivalInfo(inAssociatedMetastasisData.getSurvivalInfo());
                associatedMetastasis.setMicroscopicDescription(inAssociatedMetastasisData.getMicroscopicDescription());        
                associatedMetastasis.setComparativeData( inAssociatedMetastasisData.getComparativeData() );
                System.out.println("inHistopathologyData.getComparativeData():  " +inAssociatedMetastasisData.getComparativeData());

                String theWeightOfTumor = inAssociatedMetastasisData.getWeightOfTumor().trim();
                if (theWeightOfTumor != null && theWeightOfTumor.length() > 0) {
                    try {
                    	associatedMetastasis.setWeightOfTumor(Float.valueOf(theWeightOfTumor));
                    } catch (NumberFormatException e) {
                        log.error("Bad Weight Of Tumor: " + theWeightOfTumor);
                    }
                }
                
                String theVolumeOfTumor = inAssociatedMetastasisData.getVolumeOfTumor().trim();
                if (theVolumeOfTumor != null && theVolumeOfTumor.length() > 0) {
                    try {
                    	associatedMetastasis.setVolumeOfTumor(Float.valueOf(theVolumeOfTumor));
                    } catch (NumberFormatException e) {
                        log.error("Bad Volume Of Tumor: " + theVolumeOfTumor);
                    }
                }               
                
                //Histopathology attributes that need units appended
                associatedMetastasis.setAgeOfOnset(inAssociatedMetastasisData.getAgeOfOnset() + " " + inAssociatedMetastasisData.getAgeUnit());
               
                /* Add GeneticAlteration attributes */
                if (associatedMetastasis.getGeneticAlteration() == null) {
                	associatedMetastasis.setGeneticAlteration(new GeneticAlteration());
                }		
        			System.out.println("Saving: inHistopathology.getGeneticAlteration() attributes ");
        		
        		associatedMetastasis.getGeneticAlteration().setObservation(inAssociatedMetastasisData.getObservation());
        		associatedMetastasis.getGeneticAlteration().setMethodOfObservation(inAssociatedMetastasisData.getMethodOfObservation());
        	}        		
        }
		
		/* Add Metastasis to Histopathology - ParentHistopathology_id not null */
		//inHistopathology.addHistopathology(associatedMetastasis);
		System.out.println("Not needed in edit> Added Metastasis to Histopathology ");
		
		log.info("<HistopathologyManagerImpl> Exiting editAssociatedMetastasis");
        }  
 
    
    public Histopathology createClinicalMarker(ClinicalMarkerData inClinicalMarkerData)throws Exception{

        log.info("Entering HistopathologyManagerImpl.createClinicalMarker");

        Histopathology theHistopathology = new Histopathology();
        populateClinicalMarker(inClinicalMarkerData, theHistopathology);
        
        log.info("Exiting HistopathologyManagerImpl.createClinicalMarker");        

        return theHistopathology; 
        
    }    

    public void updateClinicalMarker(ClinicalMarkerData inClinicalMarkerData, Histopathology inHistopathology) throws Exception{

    	log.info("Entering HistopathologyManagerImpl.updateClinicalMarker");
    	log.info("Updating HistopathologyData: " + inHistopathology.getId());

    	// Populate w/ the new values and save
    	populateClinicalMarker(inClinicalMarkerData, inHistopathology);
    	save(inHistopathology);

    	log.info("Exiting HistopathologyManagerImpl.updateClinicalMarker");    	
    }    
    
    private void populateClinicalMarker(ClinicalMarkerData inClinicalMarkerData, Histopathology inHistopathology) {

		log.info( "<HistopathologyManagerImpl> Entering populateClinicalMarker" );
		
		//create new ClinicalMarker to be used as the current ClinicalMarker
		ClinicalMarker theClinicalMarker = new ClinicalMarker();		
		
        theClinicalMarker.setName(inClinicalMarkerData.getName());
        theClinicalMarker.setValue(inClinicalMarkerData.getValue());
    	
		/* Add ClinicalMarker object to Histopathology obejct  */
		
		inHistopathology.addClinicalMarker(theClinicalMarker);
		System.out.println("HistopathologyManagerImpl> Added ClinicalMarker to Histopathology ");    	
    	
		log.info( "<HistopathologyManagerImpl> Exiting populateClinicalMarker" );    	
    }     

}
