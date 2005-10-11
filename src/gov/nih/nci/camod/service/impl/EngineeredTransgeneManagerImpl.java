package gov.nih.nci.camod.service.impl;

//import gov.nih.nci.camod.domain.EngineeredTransgene;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GeneFunction;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.RegulatoryElement;
import gov.nih.nci.camod.domain.RegulatoryElementType;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.EngineeredTransgeneManager;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineeredTransgeneManagerImpl extends BaseManager implements EngineeredTransgeneManager {
	 
	public List getAll() throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.getAll");
        return super.getAll(EngineeredGene.class);
    }

    public Transgene get(String id) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.get");
        return (Transgene) super.get(id, Transgene.class);
    }

    public void save(Transgene engineeredGene) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.save");
        super.save(engineeredGene);
    }

    public void remove(String id) throws Exception {
        log.trace("In EngineeredTransgeneManagerImpl.remove");
        super.remove(id, EngineeredGene.class);
    }

    public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData) throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.create");

        Transgene inEngineeredTransgene= new Transgene();
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene);
       
        log.trace("Exiting EngineeredTransgeneManagerImpl.create");
        
        return inEngineeredTransgene;
    }

    public void update(EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene)
            throws Exception {

        log.trace("Entering EngineeredTransgeneManagerImpl.update");
        log.debug("Updating EngineeredTransgeneForm: " + inEngineeredTransgene.getId());

        // Populate w/ the new values and save
        populateEngineeredTransgene(inEngineeredTransgeneData, inEngineeredTransgene);
        save(inEngineeredTransgene);

        log.trace("Exiting EngineeredTransgeneManagerImpl.update");
    }
    
    private void populateEngineeredTransgene(EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene)
    	throws Exception {

    	log.trace("Entering populateEngineeredTransgene");
    	
    	//Transgene Integration
    	if (inEngineeredTransgeneData.getLocationOfIntegration().equals( "Targeted" ))
    		inEngineeredTransgene.setLocationOfIntegration( inEngineeredTransgeneData.getOtherLocationOfIntegration() );
    	else
    		inEngineeredTransgene.setLocationOfIntegration( inEngineeredTransgeneData.getLocationOfIntegration() );
		
    	//Find the matching taxon in the db and reuse it
		Taxon inTaxon = new Taxon();
		
    	//Transgene (coding sequence only)
    	inEngineeredTransgene.setName( inEngineeredTransgeneData.getName() );
    	
		if( inEngineeredTransgeneData.getOtherScientificName() != null ) {
			inTaxon.setScientificName( inEngineeredTransgeneData.getOtherScientificName() );
			//TODO: Send an Email
		} else {
			List taxonList = (List) TaxonManagerSingleton.instance().getAll();
		    
		    for( int i=0; i < taxonList.size(); i++ ) {
		    	inTaxon = (Taxon) taxonList.get(i);
		    	if ( inTaxon.getScientificName() != null ) {
		        	if ( inTaxon.getScientificName().equals( inEngineeredTransgeneData.getScientificName() ) )
		        		break;
		    	}
		    }	
		}    	
    	inEngineeredTransgene.addTaxon( inTaxon );
    	
    	//Transcriptional 1 (Promoter)
		RegulatoryElement trans1 = new RegulatoryElement();
		trans1.setName( inEngineeredTransgeneData.getTranscriptional1_name() );
		
		RegulatoryElementType regType = new RegulatoryElementType();
		regType.setName( "Transcriptional1" );
		trans1.setRegulatoryElementType( regType );

		if( inEngineeredTransgeneData.getTranscriptional1_otherSpecies() != null ) {
			inTaxon.setScientificName( inEngineeredTransgeneData.getTranscriptional1_otherSpecies() );
			//TODO: Send an Email
		} else {
			List taxonList = (List) TaxonManagerSingleton.instance().getAll();
		    
		    for( int i=0; i < taxonList.size(); i++ ) {
		    	inTaxon = (Taxon) taxonList.get(i);
		    	if ( inTaxon.getScientificName() != null ) {
		        	if ( inTaxon.getScientificName().equals( inEngineeredTransgeneData.getTranscriptional1_species() ))
		        		break;
		    	}
		    }	
		}
		trans1.setTaxon( inTaxon );	
		inEngineeredTransgene.addRegulatoryElement( trans1 );
        
    	//Transcriptional 2 (Promoter)
    	
    	//Transcriptional 3 (Promoter)
    	
    	//Poly A Signal
    	
    	//Splice Sites / Intron
    	
    	//MGI Number
		//Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if ( inEngineeredTransgene.getMutationIdentifier() != null )
			inMutationIdentifier = inEngineeredTransgene.getMutationIdentifier();
		else
			inMutationIdentifier = new MutationIdentifier();

		String strNumberMGI = inEngineeredTransgeneData.getNumberMGI().trim();
		Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
		Matcher m = p.matcher(strNumberMGI);
		if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
			inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
			inEngineeredTransgene.setMutationIdentifier(inMutationIdentifier);
		}
		
    	//Gene Functions
		Set geneList = null;
		if( inEngineeredTransgene.getGeneFunctionCollection() != null ) {
			geneList = inEngineeredTransgene.getGeneFunctionCollection();
			//clear out the list
			geneList.removeAll(geneList);
		} else
			geneList = new HashSet();
    	
      	//Seperate by commas    	
    	String geneFunctions = inEngineeredTransgeneData.getGeneFunctions();
    	StringTokenizer st = new StringTokenizer( geneFunctions, ",");
    	while( st.hasMoreTokens() ) {
    		GeneFunction inGeneFunctions = new GeneFunction();
    		inGeneFunctions.setFunction( st.nextToken().trim() );
    		System.out.println( "\tAdding GeneFunction:" + inGeneFunctions.getFunction() );	
    		geneList.add( inGeneFunctions );
    	}    	    
        inEngineeredTransgene.setGeneFunctionCollection( geneList );
    	
    	//Conditional, Conditional Description
    	Conditionality inConditionality = null;    	
    	if ( inEngineeredTransgene.getConditionality() != null )
    		inConditionality = inEngineeredTransgene.getConditionality();
    	else
    		inConditionality = new Conditionality();
 
		inConditionality.setConditionedBy( inEngineeredTransgeneData.getConditionedBy() );
		inConditionality.setDescription( inEngineeredTransgeneData.getDescription() );		
    	inEngineeredTransgene.setConditionality(inConditionality);
    	
    	//Additional Features (i.e. Comments)
    	inEngineeredTransgene.setComments( inEngineeredTransgeneData.getComments() );
    	
    	//Upload Construct File location, Title of Construct, Description of construct
		//Check for exisiting Image for this TargetedModification
		Image image = null;
		if (inEngineeredTransgene.getImage() != null)
			image = inEngineeredTransgene.getImage();
		else
			image = new Image();

		image.setFileServerLocation(inEngineeredTransgeneData.getFileServerLocation());
		image.setTitle(inEngineeredTransgeneData.getTitle());
		image.setDescription(inEngineeredTransgeneData.getDescriptionOfConstruct());
		inEngineeredTransgene.setImage(image);

    	log.trace("Exiting populateEngineeredTransgene");    
    }
}
