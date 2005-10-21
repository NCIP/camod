package gov.nih.nci.camod.service.impl;

//import gov.nih.nci.camod.domain.EngineeredTransgene;
import gov.nih.nci.camod.Constants;
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
import gov.nih.nci.camod.util.FtpUtil;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

public class EngineeredTransgeneManagerImpl extends BaseManager implements
		EngineeredTransgeneManager {

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

	public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData, HttpServletRequest request)
			throws Exception {

		log.trace("Entering EngineeredTransgeneManagerImpl.create");

		Transgene inEngineeredTransgene = new Transgene();
		populateEngineeredTransgene(inEngineeredTransgeneData,
				inEngineeredTransgene, request);

		log.trace("Exiting EngineeredTransgeneManagerImpl.create");

		return inEngineeredTransgene;
	}

	public void update(EngineeredTransgeneData inEngineeredTransgeneData,
			Transgene inEngineeredTransgene, HttpServletRequest request) throws Exception {

		log.trace("Entering EngineeredTransgeneManagerImpl.update");
		log.debug("Updating EngineeredTransgeneForm: "
				+ inEngineeredTransgene.getId());

		// Populate w/ the new values and save
		populateEngineeredTransgene(inEngineeredTransgeneData,
				inEngineeredTransgene, request);
		save(inEngineeredTransgene);

		log.trace("Exiting EngineeredTransgeneManagerImpl.update");
	}

	private void populateEngineeredTransgene( EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene, HttpServletRequest request) 
		throws Exception {

		log.trace("Entering populateEngineeredTransgene");

		// Transgene Integration
		if (inEngineeredTransgeneData.getLocationOfIntegration().equals("Targeted"))
			inEngineeredTransgene.setLocationOfIntegration(inEngineeredTransgeneData.getOtherLocationOfIntegration());
		else
			inEngineeredTransgene.setLocationOfIntegration(inEngineeredTransgeneData.getLocationOfIntegration());

		//Transgene (coding sequence only)
		inEngineeredTransgene.setName( inEngineeredTransgeneData.getName() );
		
		Taxon tax = new Taxon();
		
		// Only add if there is no Taxon already
		if ( inEngineeredTransgene.getTaxonCollection().size() > 0 )
			inEngineeredTransgene.getTaxonCollection().removeAll( inEngineeredTransgene.getTaxonCollection() );

		if ( inEngineeredTransgeneData.getScientificName().equals( "Other") ) {			
			tax.setScientificName( inEngineeredTransgeneData.getOtherScientificName() );
			inEngineeredTransgene.addTaxon(tax);
		} else {
			tax.setScientificName( inEngineeredTransgeneData.getScientificName() );
			inEngineeredTransgene.addTaxon(tax);
		}
		
		// Transcriptional (Promoter)		
		if ( inEngineeredTransgene.getRegulatoryElementCollection().size() > 0 )
			inEngineeredTransgene.getRegulatoryElementCollection().removeAll( inEngineeredTransgene.getRegulatoryElementCollection() );

		// Transcriptional 1
		Taxon tax1 = new Taxon();
		RegulatoryElement regElement = new RegulatoryElement();
		RegulatoryElementType regElementType = new RegulatoryElementType();
		regElementType.setName("Transcriptional1");
		regElement.setRegulatoryElementType( regElementType );
		regElement.setName( inEngineeredTransgeneData.getTranscriptional1_name() );
		
		if ( inEngineeredTransgeneData.getTranscriptional1_species().equals( "Other" ) ) {
			tax1.setScientificName( inEngineeredTransgeneData.getTranscriptional1_otherSpecies() );
			regElement.setTaxon( tax1 );
			inEngineeredTransgene.addRegulatoryElement( regElement );
		} else {
			tax1.setScientificName( inEngineeredTransgeneData.getTranscriptional1_species() );
			regElement.setTaxon( tax1 );
			inEngineeredTransgene.addRegulatoryElement( regElement );
		}
				
		// Transcriptional 2
		if ( inEngineeredTransgeneData.getTranscriptional2_name() != null )	{
			if ( ! inEngineeredTransgeneData.getTranscriptional2_name().equals( "" ) ) {	
				Taxon tax2 = new Taxon();
				RegulatoryElement regElement2 = new RegulatoryElement();
				RegulatoryElementType regElementType2 = new RegulatoryElementType();
				regElementType2.setName("Transcriptional2");
				regElement2.setRegulatoryElementType( regElementType2 );
				regElement2.setName( inEngineeredTransgeneData.getTranscriptional2_name() );
				
				if ( inEngineeredTransgeneData.getTranscriptional2_species().equals( "Other" ) ) {
					tax2.setScientificName( inEngineeredTransgeneData.getTranscriptional2_otherSpecies() );
					regElement2.setTaxon( tax2 );
					inEngineeredTransgene.addRegulatoryElement( regElement2 );
				} else {
					tax2.setScientificName( inEngineeredTransgeneData.getTranscriptional2_species() );
					regElement2.setTaxon( tax2 );
					inEngineeredTransgene.addRegulatoryElement( regElement2 );
				}	
			}
		}
		
		// Transcriptional 3
		if ( inEngineeredTransgeneData.getTranscriptional3_name() != null )	{
			if ( ! inEngineeredTransgeneData.getTranscriptional3_name().equals( "" ) ) {
				Taxon tax3 = new Taxon();
				RegulatoryElement regElement3 = new RegulatoryElement();
				RegulatoryElementType regElementType3 = new RegulatoryElementType();
				regElementType3.setName("Transcriptional3");
				regElement3.setRegulatoryElementType( regElementType3 );
				regElement3.setName( inEngineeredTransgeneData.getTranscriptional3_name() );
				
				if ( inEngineeredTransgeneData.getTranscriptional3_species().equals( "Other" ) ) {
					tax3.setScientificName( inEngineeredTransgeneData.getTranscriptional3_otherSpecies() );
					regElement3.setTaxon( tax3 );
					inEngineeredTransgene.addRegulatoryElement( regElement3 );
				} else {
					tax3.setScientificName( inEngineeredTransgeneData.getTranscriptional3_species() );
					regElement3.setTaxon( tax3 );
					inEngineeredTransgene.addRegulatoryElement( regElement3 );
				}
			}
		}
		
		// PolyASignal
		if ( inEngineeredTransgeneData.getPolyASignal_name() != null )	{
			if ( ! inEngineeredTransgeneData.getPolyASignal_name().equals( "" ) )	{
				Taxon taxPolyASignal = new Taxon();
				RegulatoryElement regElementPolyASignal = new RegulatoryElement();
				RegulatoryElementType regElementTypePolyASignal = new RegulatoryElementType();
				regElementTypePolyASignal.setName("Poly A Signal");
				regElementPolyASignal.setRegulatoryElementType( regElementTypePolyASignal );
				regElementPolyASignal.setName( inEngineeredTransgeneData.getPolyASignal_name() );
				
				if ( inEngineeredTransgeneData.getPolyASignal_species().equals( "Other" ) ) {
					taxPolyASignal.setScientificName( inEngineeredTransgeneData.getPolyASignal_otherSpecies() );
					regElementPolyASignal.setTaxon( taxPolyASignal );
					inEngineeredTransgene.addRegulatoryElement( regElementPolyASignal );
				} else {
					taxPolyASignal.setScientificName( inEngineeredTransgeneData.getPolyASignal_species() );
					regElementPolyASignal.setTaxon( taxPolyASignal );
					inEngineeredTransgene.addRegulatoryElement( regElementPolyASignal );
				}
			}
		}
		
		// SpliceSites
		if ( inEngineeredTransgeneData.getSpliceSites_name() != null ) {
			if ( ! inEngineeredTransgeneData.getSpliceSites_name().equals( "" ) ) {
				Taxon taxSpliceSites = new Taxon();
				RegulatoryElement regElementSpliceSites = new RegulatoryElement();
				RegulatoryElementType regElementTypeSpliceSites = new RegulatoryElementType();
				regElementTypeSpliceSites.setName("Splice Site");
				regElementSpliceSites.setRegulatoryElementType( regElementTypeSpliceSites );
				regElementSpliceSites.setName( inEngineeredTransgeneData.getSpliceSites_name() );
				
				if ( inEngineeredTransgeneData.getSpliceSites_species().equals( "Other" ) ) {
					taxSpliceSites.setScientificName( inEngineeredTransgeneData.getSpliceSites_otherSpecies() );
					regElementSpliceSites.setTaxon( taxSpliceSites );
					inEngineeredTransgene.addRegulatoryElement( regElementSpliceSites );
				} else {
					taxSpliceSites.setScientificName( inEngineeredTransgeneData.getSpliceSites_species() );
					regElementSpliceSites.setTaxon( taxSpliceSites );
					inEngineeredTransgene.addRegulatoryElement( regElementSpliceSites );
				}
			}
		}
		
		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if (inEngineeredTransgene.getMutationIdentifier() != null)
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

		// Gene Functions
		Set geneList = null;
		if (inEngineeredTransgene.getGeneFunctionCollection() != null) {
			geneList = inEngineeredTransgene.getGeneFunctionCollection();
			// clear out the list
			geneList.removeAll(geneList);
		} else
			geneList = new HashSet();

		// Seperate by commas
		String geneFunctions = inEngineeredTransgeneData.getGeneFunctions();
		StringTokenizer st = new StringTokenizer(geneFunctions, ",");
		while (st.hasMoreTokens()) {
			GeneFunction inGeneFunctions = new GeneFunction();
			inGeneFunctions.setFunction(st.nextToken().trim());
			System.out.println("\tAdding GeneFunction:"	+ inGeneFunctions.getFunction());
			geneList.add(inGeneFunctions);
		}
		inEngineeredTransgene.setGeneFunctionCollection(geneList);

		// Conditional, Conditional Description
		Conditionality inConditionality = null;
		if (inEngineeredTransgene.getConditionality() != null)
			inConditionality = inEngineeredTransgene.getConditionality();
		else
			inConditionality = new Conditionality();

		inConditionality.setConditionedBy(inEngineeredTransgeneData.getConditionedBy());
		inConditionality.setDescription(inEngineeredTransgeneData.getDescription());
		inEngineeredTransgene.setConditionality(inConditionality);

		// Additional Features (i.e. Comments)
		inEngineeredTransgene.setComments(inEngineeredTransgeneData.getComments());

		if ( inEngineeredTransgene.getImage() != null )	{
			Image image = inEngineeredTransgene.getImage();
			image.setTitle( inEngineeredTransgeneData.getTitle());
			image.setDescription( inEngineeredTransgeneData.getDescriptionOfConstruct() );
			inEngineeredTransgene.setImage(image);
		}
		
		// Upload Construct File location, Title of Construct, Description of Construct
		// Check for exisiting Image for this TargetedModification		
		if( inEngineeredTransgeneData.getFileLocation() != null  ) 
		{
			System.out.println( "<EngineeredTransgeneManagerImpl populateEngineeredTransgene> Uploading a file" );
						
			//If this is a new Image, upload it to the server
			FormFile f = inEngineeredTransgeneData.getFileLocation();			
				
			Image image = new Image();
			
			//Retrieve the file type
			String fileType = null;
			StringTokenizer strToken = new StringTokenizer( f.getFileName(), "." );						
			
			while ( strToken.hasMoreTokens() ){				
				fileType = strToken.nextToken();
				System.out.println( "Token=" + fileType );
			}
			
			System.out.println( "<EngineeredTransgeneManagerImpl populateEngineeredTransgene> fileType is: " + fileType + " FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
			
			//Check the file type
			if ( fileType != null ) {
				if ( fileType.equals( "jpg" ) || fileType.equals( "jpeg" ) || fileType.equals( "gif" ) || fileType.equals( "tif" ) || fileType.equals( "sid" ) )
				{
					System.out.println( "<EngineeredTransgeneManagerImpl populateEngineeredTransgene> Valid file type " + fileType );
					System.out.println( "<EngineeredTransgeneManagerImpl populateEngineeredTransgene> FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
					
					InputStream in = null;
					OutputStream out = null;
					
					try {
						//Get an input stream on the form file
						in = f.getInputStream();
						
						//Create an output stream to a file
						//this file is stored on the jboss server
						//TODO: Set a max size for this file
						out = new BufferedOutputStream(new FileOutputStream( request.getSession().getServletContext().getRealPath("/config/temp.jpg") ));
						
						byte[] buffer = new byte[512];
						while ( in.read(buffer) != -1) {
							out.write(buffer);				
						}			
					} finally {
						if (out!=null) out.close();
						if (in!=null) in.close();
					}
					
		            String theFilename = request.getSession().getServletContext().getRealPath("/config/temp.jpg");
		            File uploadFile = new File( theFilename );
		            
		            //TODO: Retrieve list of files from server, create a unique file name, will require a more advanced FTPUtil
		            //TODO: Add ability to delete images from caIMAGE Ftp, requires more advanced FTPUtil
		            
		            //Get the current time and append the modelID, should be good enough to always be unique
		            long time = System.currentTimeMillis(); 
		            String uniqueFileName = time + "_" + request.getSession().getAttribute( Constants.MODELID ).toString() + "." + fileType;
		            
		            //Retrieve ftp data from a resource bundle
	                ResourceBundle theBundle = ResourceBundle.getBundle( "camod" );

	                // Iterate through all the reciepts in the config file
	                String ftpServer = 	theBundle.getString( Constants.Images.FTPSERVER );
	                String ftpUsername = theBundle.getString( Constants.Images.FTPUSERNAME );
                	String ftpPassword = theBundle.getString( Constants.Images.FTPPASSWORD );
	                String ftpStorageDirectory = theBundle.getString( Constants.Images.FTPSTORAGEDIRECTORY );
	                
	                //Upload the file to caIMAGE
		            FtpUtil ftpUtil = new FtpUtil();	           		            
		            ftpUtil.upload( ftpServer, ftpUsername, ftpPassword, ftpStorageDirectory + uniqueFileName, uploadFile );
		            
					image.setFileServerLocation( uniqueFileName );				
					image.setTitle(inEngineeredTransgeneData.getTitle());
					image.setDescription(inEngineeredTransgeneData.getDescriptionOfConstruct());
					inEngineeredTransgene.setImage(image);
					
				} else {
					//TODO: Add error for struts explaining that image is of an invalid type
					System.out.println( "Invalid file type! " + fileType );
				}
	 		}		
		}
		
		log.trace("Exiting populateEngineeredTransgene");
	}
}
