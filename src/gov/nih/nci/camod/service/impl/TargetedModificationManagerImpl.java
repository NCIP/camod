/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.util.FtpUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.TargetedModificationData;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

public class TargetedModificationManagerImpl extends BaseManager implements
		TargetedModificationManager {

	public List getAll() throws Exception {
		log.trace("In TargetedModificationManagerImpl.getAll");
		return super.getAll(TargetedModification.class);
	}

	public TargetedModification get(String id) throws Exception {
		log.trace("In TargetedModificationManagerImpl.get");
		return (TargetedModification) super.get(id, TargetedModification.class);
	}

	public void save(TargetedModification TargetedModification)
			throws Exception {
		log.trace("In TargetedModificationManagerImpl.save");
		super.save(TargetedModification);
	}

	public void remove(String id) throws Exception {
		log.trace("In TargetedModificationManagerImpl.remove");
		super.remove(id, TargetedModification.class);
	}

	public TargetedModification create(	TargetedModificationData inTargetedModificationData, HttpServletRequest request)
			throws Exception {

		log.trace("Entering TargetedModificationManagerImpl.create");

		TargetedModification theTargetedModification = new TargetedModification();

		populateTargetedModification(inTargetedModificationData, theTargetedModification, request);
		
		log.trace("Exiting TargetedModificationManagerImpl.create");

		return theTargetedModification;
	}

	public void update(TargetedModificationData inTargetedModificationData,	TargetedModification theTargetedModification, HttpServletRequest request) 
		throws Exception {

		log.trace("Entering TargetedModificationManagerImpl.update");
		log.debug("Updating TargetedModificationForm: "
				+ theTargetedModification.getId());

		// Populate w/ the new values and save
		populateTargetedModification(inTargetedModificationData, theTargetedModification, request);
		

		save(theTargetedModification);

		log.trace("Exiting TargetedModificationManagerImpl.update");
	}

	private void populateTargetedModification( TargetedModificationData inTargetedModificationData,	TargetedModification theTargetedModification, HttpServletRequest request) 
		throws Exception {

		log.trace("Entering populateTargetedModification");

		// set Targeted Gene/Locus
		theTargetedModification.setName( inTargetedModificationData.getName() );

		// Type of Modification
		ModificationType inModificationType = null;
		if ( theTargetedModification.getModificationTypeCollection().size() > 0 )
			inModificationType = (ModificationType) theTargetedModification.getModificationTypeCollection().get(0);
		else
			inModificationType = new ModificationType();
		
		inModificationType.setName(inTargetedModificationData.getModificationType());
		
		if ( theTargetedModification.getModificationTypeCollection().size() < 1 )			
			theTargetedModification.addModificationType(inModificationType);

		// Other - Type of Modification
		if (inTargetedModificationData.getOtherModificationType() != null) {
			if (!inTargetedModificationData.getOtherModificationType().equals("")) {

				log.trace("Sending Notification eMail - new Targeted Modification added");
				ResourceBundle theBundle = ResourceBundle.getBundle("camod");

				// Iterate through all the reciepts in the config file
				String recipients = theBundle.getString(Constants.EmailMessage.RECIPIENTS);
				StringTokenizer st = new StringTokenizer(recipients, ",");
				String inRecipients[] = new String[st.countTokens()];

				for (int i = 0; i < inRecipients.length; i++)
					inRecipients[i] = st.nextToken();
				
				String inSubject = theBundle
						.getString(Constants.EmailMessage.SUBJECT);
				String inMessage = theBundle
						.getString(Constants.EmailMessage.MESSAGE)
				 		+ " Targeted Modification added ( "
						+ inTargetedModificationData.getOtherModificationType()
						+ " ) and is awaiting your approval.";
				String inFrom = theBundle.getString(Constants.EmailMessage.FROM);
				// theBundle.getString(Constants.EmailMessage.SENDER);

				// Send the email
				try {
					log	.trace("Sending Notification eMail - new Targeted Modification added");

                    // gather message keys and variable values to build the e-mail content with
                    String[] messageKeys = {Constants.Admin.INDUCED_MUTATION_AGENT_ADDED};
                    TreeMap values = new TreeMap();
                    values.put(Constants.Admin.TARGETED_MODIFICATION_NAME, inTargetedModificationData.getName());
                    values.put(Constants.Admin.TARGETED_MODIFICATION_TYPE,
                            inTargetedModificationData.getOtherModificationType());

                    // Send the email
					MailUtil.sendMail(inRecipients, inSubject, inMessage, inFrom, messageKeys, values);
					log.trace("Notification eMail sent");
				} catch (Exception e) {
					log.trace("Caught exception " + e);
					// System.out.println("Caught exception" + e);
					e.printStackTrace();
				}

				// 2. Set flag, this Strain will need to be approved before
				// being added the list
				theTargetedModification.setModTypeUnctrlVocab(inTargetedModificationData.getOtherModificationType());
			}
		}

		// Gene Id
		theTargetedModification.setGeneId(inTargetedModificationData.getGeneId());

		// Genetic Background - Donor
		theTargetedModification.setEsCellLineName(inTargetedModificationData.getEsCellLineName());

		// Genetic Background - Recipient
		theTargetedModification.setBlastocystName(inTargetedModificationData.getBlastocystName());

		// Conditional?
		// Check for exisiting Conditionality for this TargetedModification
		Conditionality inConditionality = null;
		if (theTargetedModification.getConditionality() != null)
			inConditionality = theTargetedModification.getConditionality();
		else
			inConditionality = new Conditionality();

		inConditionality.setConditionedBy(inTargetedModificationData.getConditionedBy());
		inConditionality.setDescription(inTargetedModificationData.getDescription());
		theTargetedModification.setConditionality(inConditionality);

		// Upload Construct Map
		// Check for exisiting Image for this TargetedModification
		if ( theTargetedModification.getImage() != null )	{
			Image image = theTargetedModification.getImage();
			image.setTitle( inTargetedModificationData.getTitle());
			image.setDescription( inTargetedModificationData.getDescriptionOfConstruct() );
			theTargetedModification.setImage(image);
		}
		
		// Upload Construct File location, Title of Construct, Description of Construct
		// Check for exisiting Image for this TargetedModification		
		if( inTargetedModificationData.getFileLocation() != null  ) 
		{
			System.out.println( "<TargetedModificationManagerImpl> Uploading a file" );
						
			//If this is a new Image, upload it to the server
			FormFile f = inTargetedModificationData.getFileLocation();			
				
			Image image = new Image();
			
			//Retrieve the file type
			String fileType = null;
			StringTokenizer strToken = new StringTokenizer( f.getFileName(), "." );						
			
			while ( strToken.hasMoreTokens() ){				
				fileType = strToken.nextToken();
				System.out.println( "Token=" + fileType );
			}
			
			System.out.println( "<TargetedModificationManagerImpl> fileType is: " + fileType + " FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
			
			//Check the file type
			if ( fileType != null ) {
				if ( fileType.equals( "jpg" ) || fileType.equals( "jpeg" ) || fileType.equals( "gif" ) || fileType.equals( "tif" ) || fileType.equals( "sid" ) )
				{
					System.out.println( "<TargetedModificationManagerImpl> Valid file type " + fileType );
					System.out.println( "<TargetedModificationManagerImpl> FileName is: " + f.getFileName() + " Type is: " + f.getContentType() );
					
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
					image.setTitle(inTargetedModificationData.getTitle());
					image.setDescription(inTargetedModificationData.getDescriptionOfConstruct());
					theTargetedModification.setImage(image);
					
				} else {
					//TODO: Add error for struts explaining that image is of an invalid type
					System.out.println( "Invalid file type! " + fileType );
				}
	 		}		
		}
		
		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if (theTargetedModification.getMutationIdentifier() != null)
			inMutationIdentifier = theTargetedModification.getMutationIdentifier();
		else
			inMutationIdentifier = new MutationIdentifier();

		String strNumberMGI = inTargetedModificationData.getNumberMGI().trim();
		Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
		Matcher m = p.matcher(strNumberMGI);
		if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
			inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
			theTargetedModification.setMutationIdentifier(inMutationIdentifier);
		}

		theTargetedModification.setComments(inTargetedModificationData.getComments());

		log.trace("Exiting populateTargetedModification");
	}
}