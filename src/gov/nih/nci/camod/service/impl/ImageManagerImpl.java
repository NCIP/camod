/*
 * $Id: ImageManagerImpl.java,v 1.21 2006-08-17 18:26:17 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.20  2006/05/24 20:25:29  georgeda
 * Fixed staining methods
 *
 * Revision 1.19  2006/05/24 19:01:24  georgeda
 * Cleaned up
 *
 * Revision 1.18  2006/05/24 16:46:14  pandyas
 * Converted StainingMethod to lookup - modified code to pull dropdown list from DB
 *
 * Revision 1.17  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.StainingMethod;
import gov.nih.nci.camod.service.ImageManager;
import gov.nih.nci.camod.util.FtpUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.util.RandomGUID;
import gov.nih.nci.camod.webapp.form.ImageData;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.struts.upload.FormFile;

public class ImageManagerImpl extends BaseManager implements ImageManager {

	public List getAll() throws Exception {
		log.trace("In ImageManagerImpl.getAll");
		return super.getAll(Image.class);
	}

	public Image get(String id) throws Exception {
		log.trace("In ImageManagerImpl.get");
		return (Image) super.get(id, Image.class);
	}

	public void save(Image Image) throws Exception {
		log.trace("In ImageManagerImpl.save");
		super.save(Image);
	}

	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.trace("In ImageManagerImpl.remove");

		inAnimalModel.getImageCollection().remove(get(id));
		super.save(inAnimalModel);
	}

	public Image create(AnimalModel inAnimalModel, ImageData inImageData,
			String inPath, String inStorageDirKey) throws Exception {

		log.trace("Entering ImageManagerImpl.create");

		Image inImage = new Image();
		populateImage(inAnimalModel, inImageData, inImage, inPath,
				inStorageDirKey);

		log.trace("Exiting ImageManagerImpl.create");

		return inImage;
	}

	public void update(AnimalModel inAnimalModel, ImageData inImageData,
			Image inImage, String inPath, String inStorageDirKey)
			throws Exception {

		log.trace("Entering ImageManagerImpl.update");
		log.debug("Updating ImageForm: " + inImage.getId());

		// Populate w/ the new values and save
		populateImage(inAnimalModel, inImageData, inImage, inPath,
				inStorageDirKey);
		save(inImage);

		log.trace("Exiting ImageManagerImpl.update");
	}

	private void populateImage(AnimalModel inAnimalModel,
			ImageData inImageData, Image inImage, String inPath,
			String inStorageDirKey) throws Exception {

		log.info("Entering populateImage");

		if (inImageData.getStainingMethod() != null
				&& !inImageData.getStainingMethod().equals("")) {

			// Get/Create the StainingMethod
			StainingMethod stainingMethod = StainingMethodManagerSingleton
					.instance().getOrCreate(inImageData.getStainingMethod(),
							inImageData.getOtherStainingMethod());

			log.info("Entering populateImage stainingMethod:" + stainingMethod);

			if (stainingMethod.getName() == null
					&& stainingMethod.getNameUnctrlVocab() != null) {
				log.info("in other stainingMethod loop: " + stainingMethod);
				// Set staining method
				inImage.setStainingMethod(stainingMethod);

				// Send e-mail for other donor staining method
				sendEmail(inAnimalModel, stainingMethod.getNameUnctrlVocab(),
						"stainingMethod");

			} else {
				log.info("stainingMethod: " + stainingMethod);
				// Set staining method
				inImage.setStainingMethod(stainingMethod);
			}
		} else {
			// null staining method - covers editing
			inImage.setStainingMethod(null);
		}

		if (inImage != null) {
			inImage.setTitle(inImageData.getTitle());
			inImage.setDescription(inImageData.getDescriptionOfConstruct());
		}

		// Upload Construct File location, Title of Construct, Description of
		// Construct
		// Check for exisiting Image for this Image
		if (inImageData.getFileLocation() != null) {

			log.info("<ImageManagerImpl> Uploading a file");

			// If this is a new Image, upload it to the server
			FormFile f = inImageData.getFileLocation();

			// Retrieve the file type
			String fileType = null;
			StringTokenizer strToken = new StringTokenizer(f.getFileName(), ".");

			while (strToken.hasMoreTokens()) {
				fileType = strToken.nextToken();
				System.out.println("Token=" + fileType);
			}

			log.info("<ImageManagerImpl> fileType is: " + fileType
					+ " FileName is: " + f.getFileName() + " Type is: "
					+ f.getContentType());

			// Check the file type
			if (fileType != null) {
				// Supported file types. Sid is only supported for the normal
				// image upload
				if (fileType.toLowerCase().equals("jpg")
						|| fileType.toLowerCase().equals("jpeg")
						|| fileType.toLowerCase().equals("gif")
						|| (fileType.toLowerCase().equals("sid") && !inStorageDirKey
								.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY))
						|| fileType.toLowerCase().equals("png")) {
					InputStream in = null;
					OutputStream out = null;

					try {
						// Get an input stream on the form file
						in = f.getInputStream();

						// Create an output stream to a file
						// this file is stored on the jboss server
						// TODO: Set a max size for this file
						out = new BufferedOutputStream(new FileOutputStream(
								inPath));

						byte[] buffer = new byte[512];
						while (in.read(buffer) != -1) {
							out.write(buffer);
						}
					} finally {
						if (out != null)
							out.close();
						if (in != null)
							in.close();
					}

					String theFilename = inPath;
					File uploadFile = new File(theFilename);

					// TODO: Add ability to delete images from caIMAGE Ftp,
					// requires more advanced FTPUtil

					// Generate a random filename
					RandomGUID theGUID = new RandomGUID();
					String uniqueFileName = theGUID.toString() + "." + fileType;

					// Get the e-mail resource
					Properties camodProperties = new Properties();
					String camodPropertiesFileName = null;

					camodPropertiesFileName = System
							.getProperty("gov.nih.nci.camod.camodProperties");

					try {

						FileInputStream ins = new FileInputStream(
								camodPropertiesFileName);
						camodProperties.load(ins);

					} catch (FileNotFoundException e) {
						log
								.error(
										"Caught exception finding file for properties: ",
										e);
						e.printStackTrace();
					} catch (IOException e) {
						log
								.error(
										"Caught exception finding file for properties: ",
										e);
						e.printStackTrace();
					}

					// Iterate through all the reciepts in the config file
					String ftpServer = camodProperties
							.getProperty("caimage.ftp.server");
					String ftpUsername = camodProperties
							.getProperty("caimage.ftp.username");
					String ftpPassword = camodProperties
							.getProperty("caimage.ftp.password");

					String ftpStorageDirectory = camodProperties
							.getProperty("caimage.ftp.modelstoragedirectory");

					// Determine which path to do the view in
					String serverViewUrl = "";
					if (inStorageDirKey
							.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY)) {
						serverViewUrl = camodProperties
								.getProperty("caimage.genconview.uri");
					} else {
						serverViewUrl = camodProperties
								.getProperty("caimage.modelview.uri");
					}

					// Upload the file to caIMAGE
					FtpUtil ftpUtil = new FtpUtil();
					ftpUtil.upload(ftpServer, ftpUsername, ftpPassword,
							ftpStorageDirectory + uniqueFileName, uploadFile);

					log.error("File upload successful.  File name: "
							+ uniqueFileName);

					inImage.setTitle(inImageData.getTitle());

					inImage.setDescription(inImageData
							.getDescriptionOfConstruct());

					// Do something fancy for the sid images
					if (fileType.equals("sid")) {
						String theType = "";
						if (inStorageDirKey
								.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY)) {
							theType = camodProperties
									.getProperty("caimage.gencon");
						} else {
							theType = camodProperties
									.getProperty("caimage.model");
						}

						String sidThumbView = camodProperties
								.getProperty("caimage.sidthumbview.uri");
						String theThumbUrl = sidThumbView + theType
								+ uniqueFileName;
						String theViewUrl = Constants.CaImage.LEGACYJSP
								+ theType + uniqueFileName;
						inImage.setFileServerLocation(theThumbUrl
								+ Constants.CaImage.FILESEP + theViewUrl);
					} else {
						inImage.setFileServerLocation(serverViewUrl
								+ uniqueFileName);
					}
				} else {
					log.error("Unsupported file type: " + fileType);
					throw new IllegalArgumentException("Unknown file type: "
							+ fileType);
				}
			}
		}

		log.trace("Exiting populateImage");
	}

	private void sendEmail(AnimalModel inAnimalModel,
			String theUncontrolledVocab, String inType) {
		// Get the e-mail resource
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System
				.getProperty("gov.nih.nci.camod.camodProperties");

		try {

			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);

		} catch (FileNotFoundException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		}

		// Iterate through all the reciepts in the config file - modified
		String recipients = UserManagerSingleton.instance()
				.getEmailForCoordinator();

		StringTokenizer st = new StringTokenizer(recipients, ",");
		String inRecipients[] = new String[st.countTokens()];
		for (int i = 0; i < inRecipients.length; i++) {
			inRecipients[i] = st.nextToken();
		}

		String inSubject = camodProperties
				.getProperty("model.new_unctrl_vocab_subject");
		String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

		// gather message keys and variable values to build the e-mail
		String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
		Map<String, Object> values = new TreeMap<String, Object>();
		values.put("type", inType);
		values.put("value", theUncontrolledVocab);
		values.put("submitter", inAnimalModel.getSubmitter());
		values.put("model", inAnimalModel.getModelDescriptor());
		values.put("modelstate", inAnimalModel.getState());

		// Send the email
		try {
			MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys,
					values);
		} catch (Exception e) {
			log.error("Caught exception sending mail: ", e);
			e.printStackTrace();
		}
	}
}
