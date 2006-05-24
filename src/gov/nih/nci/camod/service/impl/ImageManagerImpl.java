/*
 * $Id: ImageManagerImpl.java,v 1.19 2006-05-24 19:01:24 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.struts.upload.FormFile;

public class ImageManagerImpl extends BaseManager implements ImageManager
{

    public List getAll() throws Exception
    {
        log.trace("In ImageManagerImpl.getAll");
        return super.getAll(Image.class);
    }

    public Image get(String id) throws Exception
    {
        log.trace("In ImageManagerImpl.get");
        return (Image) super.get(id, Image.class);
    }

    public void save(Image Image) throws Exception
    {
        log.trace("In ImageManagerImpl.save");
        super.save(Image);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.trace("In ImageManagerImpl.remove");

        inAnimalModel.getImageCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public Image create(AnimalModel inAnimalModel,
                        ImageData inImageData,
                        String inPath,
                        String inStorageDirKey) throws Exception
    {

        log.trace("Entering ImageManagerImpl.create");

        Image inImage = new Image();
        populateImage(inAnimalModel, inImageData, inImage, inPath, inStorageDirKey);

        log.trace("Exiting ImageManagerImpl.create");

        return inImage;
    }

    public void update(AnimalModel inAnimalModel,
                       ImageData inImageData,
                       Image inImage,
                       String inPath,
                       String inStorageDirKey) throws Exception
    {

        log.trace("Entering ImageManagerImpl.update");
        log.debug("Updating ImageForm: " + inImage.getId());

        // Populate w/ the new values and save
        populateImage(inAnimalModel, inImageData, inImage, inPath, inStorageDirKey);
        save(inImage);

        log.trace("Exiting ImageManagerImpl.update");
    }

    private void populateImage(AnimalModel inAnimalModel,
                               ImageData inImageData,
                               Image inImage,
                               String inPath,
                               String inStorageDirKey) throws Exception
    {

        log.info("Entering populateImage");

        if (inImageData.getStainingMethod() != null && !inImageData.getStainingMethod().equals(""))
        {

            // Get/Create the StainingMethod
            StainingMethod stainingMethod = StainingMethodManagerSingleton.instance().getOrCreate(inImageData.getStainingMethod(),
                                                                                                  inImageData.getOtherStainingMethod());

            log.info("Entering populateImage stainingMethod:" + stainingMethod);

            if (stainingMethod.getName().equals(Constants.Dropdowns.OTHER_OPTION))
            {
                log.info("in other stainingMethod loop: " + stainingMethod);
                //Set staining method
                inImage.setStainingMethod(stainingMethod);

                // Send e-mail for other donor staining method
                sendEmail(inAnimalModel, stainingMethod.getNameUnctrlVocab(), "stainingMethod");

            }
            else
            {
                log.info("stainingMethod: " + stainingMethod);
                //Set staining method
                inImage.setStainingMethod(stainingMethod);
            }
        }
        else
        {
            // null staining method - covers editing
            inImage.setStainingMethod(null);
        }

        if (inImage != null)
        {
            inImage.setTitle(inImageData.getTitle());
            inImage.setDescription(inImageData.getDescriptionOfConstruct());
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this Image
        if (inImageData.getFileLocation() != null)
        {

            log.info("<ImageManagerImpl> Uploading a file");

            // If this is a new Image, upload it to the server
            FormFile f = inImageData.getFileLocation();

            // Retrieve the file type
            String fileType = null;
            StringTokenizer strToken = new StringTokenizer(f.getFileName(), ".");

            while (strToken.hasMoreTokens())
            {
                fileType = strToken.nextToken();
                System.out.println("Token=" + fileType);
            }

            log.info("<ImageManagerImpl> fileType is: " + fileType + " FileName is: " + f.getFileName() + " Type is: " + f.getContentType());

            // Check the file type
            if (fileType != null)
            {
                // Supported file types.  Sid is only supported for the normal image upload
                if (fileType.toLowerCase().equals("jpg") ||
                    fileType.toLowerCase().equals("jpeg") || 
                    fileType.toLowerCase().equals("gif") || (
                    fileType.toLowerCase().equals("sid") && !inStorageDirKey.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY)) || 
                    fileType.toLowerCase().equals("png"))
                {
                    InputStream in = null;
                    OutputStream out = null;

                    try
                    {
                        // Get an input stream on the form file
                        in = f.getInputStream();

                        // Create an output stream to a file
                        // this file is stored on the jboss server
                        // TODO: Set a max size for this file
                        out = new BufferedOutputStream(new FileOutputStream(inPath));

                        byte[] buffer = new byte[512];
                        while (in.read(buffer) != -1)
                        {
                            out.write(buffer);
                        }
                    }
                    finally
                    {
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

                    // Retrieve ftp data from a resource bundle
                    ResourceBundle theBundle = ResourceBundle.getBundle("camod");

                    // Iterate through all the reciepts in the config file
                    String ftpServer = theBundle.getString(Constants.CaImage.FTPSERVER);
                    String ftpUsername = theBundle.getString(Constants.CaImage.FTPUSERNAME);
                    String ftpPassword = theBundle.getString(Constants.CaImage.FTPPASSWORD);
                    String ftpStorageDirectory = theBundle.getString(inStorageDirKey);

                    // Determine which path to do the view in
                    String serverViewUrl = "";
                    if (inStorageDirKey.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY))
                    {
                        serverViewUrl = theBundle.getString(Constants.CaImage.CAIMAGEGENCONSERVERVIEW);
                    }
                    else
                    {
                        serverViewUrl = theBundle.getString(Constants.CaImage.CAIMAGEMODELSERVERVIEW);
                    }

                    // Upload the file to caIMAGE
                    FtpUtil ftpUtil = new FtpUtil();
                    ftpUtil.upload(ftpServer, ftpUsername, ftpPassword, ftpStorageDirectory + uniqueFileName, uploadFile);

                    log.error("File upload successful.  File name: " + uniqueFileName);

                    inImage.setTitle(inImageData.getTitle());

                    inImage.setDescription(inImageData.getDescriptionOfConstruct());

                    // Do something fancy for the sid images
                    if (fileType.equals("sid"))
                    {
                        String theType = "";
                        if (inStorageDirKey.equals(Constants.CaImage.FTPGENCONSTORAGEDIRECTORY))
                        {
                            theType = theBundle.getString(Constants.CaImage.CAIMAGEGENCON);
                        }
                        else
                        {
                            theType = theBundle.getString(Constants.CaImage.CAIMAGEMODEL);
                        }

                        String sidThumbView = theBundle.getString(Constants.CaImage.CAIMAGESIDTHUMBVIEW);
                        String theThumbUrl = sidThumbView + theType + uniqueFileName;
                        String theViewUrl = Constants.CaImage.LEGACYJSP + theType + uniqueFileName;
                        inImage.setFileServerLocation(theThumbUrl + Constants.CaImage.FILESEP + theViewUrl);
                    }
                    else
                    {
                        inImage.setFileServerLocation(serverViewUrl + uniqueFileName);
                    }
                }
                else
                {
                    log.error("Unsupported file type: " + fileType);
                    throw new IllegalArgumentException("Unknown file type: " + fileType);
                }
            }
        }

        log.trace("Exiting populateImage");
    }

    private void sendEmail(AnimalModel inAnimalModel,
                           String theUncontrolledVocab,
                           String inType)
    {
        // Get the e-mail resource
        ResourceBundle theBundle = ResourceBundle.getBundle("camod");

        // Iterate through all the reciepts in the config file
        String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
        StringTokenizer st = new StringTokenizer(recipients, ",");
        String inRecipients[] = new String[st.countTokens()];
        for (int i = 0; i < inRecipients.length; i++)
        {
            inRecipients[i] = st.nextToken();
        }

        String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
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
        try
        {
            MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);
        }
        catch (Exception e)
        {
            log.error("Caught exception sending mail: ", e);
            e.printStackTrace();
        }
    }
}
