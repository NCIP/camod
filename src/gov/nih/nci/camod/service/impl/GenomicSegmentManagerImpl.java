/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SegmentType;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.util.FtpUtil;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

public class GenomicSegmentManagerImpl extends BaseManager implements GenomicSegmentManager {
	
	  public List getAll() throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.getAll");
	        return super.getAll(GenomicSegment.class);
	    }

	    public GenomicSegment get(String id) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.get");
	        return (GenomicSegment) super.get(id, GenomicSegment.class);
	    }

	    public void save(GenomicSegment GenomicSegment) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.save");
	        super.save(GenomicSegment);
	    }

	    public void remove(String id) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.remove");
	        super.remove(id, GenomicSegment.class);
	    }

	    public GenomicSegment create(GenomicSegmentData inGenomicSegmentData, HttpServletRequest request) throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.create");

	        GenomicSegment inGenomicSegment= new GenomicSegment();
	        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment, request);
	       
	        log.trace("Exiting GenomicSegmentManagerImpl.create");
	        
	        return inGenomicSegment;
	    }

	    public void update(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment, HttpServletRequest request)
	            throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.update");
	        log.debug("Updating GenomicSegmentForm: " + inGenomicSegment.getId());

	        // Populate w/ the new values and save
	        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment, request);
	        save(inGenomicSegment);

	        log.trace("Exiting GenomicSegmentManagerImpl.update");
	    }

	    private void populateGenomicSegment(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment, HttpServletRequest request)
	            throws Exception {
	    	
	        log.trace("Entering populateGenomicSegment");	       
	        
	        if ( inGenomicSegmentData.getLocationOfIntegration().equals( "Targeted" ) )
	        	inGenomicSegment.setLocationOfIntegration( inGenomicSegmentData.getOtherLocationOfIntegration() );
	        else
	        	inGenomicSegment.setLocationOfIntegration( inGenomicSegmentData.getLocationOfIntegration() );
	    	        	        
	        inGenomicSegment.setComments( inGenomicSegmentData.getComments() );
	        inGenomicSegment.setSegmentSize( inGenomicSegmentData.getSegmentSize() );
	        inGenomicSegment.setCloneDesignator( inGenomicSegmentData.getCloneDesignator() );

			// SegmentType
			// TODO: Find matching existing segment type
	        SegmentType inSegmentType = null;	        
	        if( inGenomicSegment.getSegmentTypeCollection().size() > 0 )
	        	inSegmentType = (SegmentType) inGenomicSegment.getSegmentTypeCollection().get(0);
	        else
	        	inSegmentType = new SegmentType();
	        
	        inSegmentType.setName( inGenomicSegmentData.getSegmentName() );	       	        
	        
	        // TODO: Send Email
	        if ( inGenomicSegmentData.getOtherSegmentName() != null ) {
	        	inSegmentType.setNameUnctrlVocab( inGenomicSegmentData.getOtherSegmentName() );
	        }
	        
	        inGenomicSegment.addSegmentType( inSegmentType );
	        
			if ( inGenomicSegment.getImage() != null )	{
				Image image = inGenomicSegment.getImage();
				image.setTitle( inGenomicSegmentData.getTitle());
				image.setDescription( inGenomicSegmentData.getDescriptionOfConstruct() );
				inGenomicSegment.setImage(image);
			}
			
			// Upload Construct File location, Title of Construct, Description of Construct
			// Check for exisiting Image for this GenomicSegment		
			if( inGenomicSegmentData.getFileLocation() != null  ) 
			{
				System.out.println( "<GenomicSegmentDataManagerImpl> Uploading a file" );
				
				Image image = new Image();
				
				//If this is a new Image, upload it to the server
				FormFile f = inGenomicSegmentData.getFileLocation();			
								
				//Retrieve the file type
				String fileType = null;
				StringTokenizer strToken = new StringTokenizer( f.getFileName(), "." );						
				
				while ( strToken.hasMoreTokens() ){				
					fileType = strToken.nextToken();
					System.out.println( "Token=" + fileType );
				}
				
				System.out.println( "<GenomicSegmentDataManagerImpl> fileType is: " + fileType + " FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
				
				//Check the file type
				if ( fileType != null ) {
					if ( fileType.equals( "jpg" ) || fileType.equals( "jpeg" ) || fileType.equals( "gif" ) || fileType.equals( "tif" ) || fileType.equals( "sid" ) )
					{
						System.out.println( "<GenomicSegmentDataManagerImpl> Valid file type " + fileType );
						System.out.println( "<GenomicSegmentDataManagerImpl> FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
						
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
		                String ftpServer = 	theBundle.getString( Constants.CaImage.FTPSERVER );
		                String ftpUsername = theBundle.getString( Constants.CaImage.FTPUSERNAME );
	                	String ftpPassword = theBundle.getString( Constants.CaImage.FTPPASSWORD );
		                String ftpStorageDirectory = theBundle.getString( Constants.CaImage.FTPSTORAGEDIRECTORY );
		                
		                //Upload the file to caIMAGE
			            FtpUtil ftpUtil = new FtpUtil();	           		            
			            ftpUtil.upload( ftpServer, ftpUsername, ftpPassword, ftpStorageDirectory + uniqueFileName, uploadFile );
			            
						image.setTitle(inGenomicSegmentData.getTitle());
						image.setFileServerLocation( uniqueFileName );
						image.setDescription(inGenomicSegmentData.getDescriptionOfConstruct());
						inGenomicSegment.setImage(image);
						
					} else {
						//TODO: Add error for struts explaining that image is of an invalid type
						System.out.println( "Invalid file type! " + fileType );
					}
		 		}		
			}
					
			// MGI Number
			// Check for exisiting MutationIdentifier
			MutationIdentifier inMutationIdentifier = null;
			if (inGenomicSegment.getMutationIdentifier() != null)
				inMutationIdentifier = inGenomicSegment.getMutationIdentifier();
			else
				inMutationIdentifier = new MutationIdentifier();

			String strNumberMGI = inGenomicSegmentData.getNumberMGI().trim();
			Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
			Matcher m = p.matcher(strNumberMGI);
			if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
				inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
				inGenomicSegment.setMutationIdentifier(inMutationIdentifier);
			}
			
	        log.trace("Exiting populateGenomicSegment");
	    }	    
}
