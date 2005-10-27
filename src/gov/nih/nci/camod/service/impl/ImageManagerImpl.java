/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.ImageManager;
import gov.nih.nci.camod.util.FtpUtil;
import gov.nih.nci.camod.util.RandomGUID;
import gov.nih.nci.camod.webapp.form.ImageData;

import java.io.*;
import java.util.*;

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

    public void remove(String id) throws Exception {
        log.trace("In ImageManagerImpl.remove");
        super.remove(id, Image.class);
    }

    public Image create(ImageData inImageData, String inPath) throws Exception {

        log.trace("Entering ImageManagerImpl.create");

        Image inImage = new Image();
        populateImage(inImageData, inImage, inPath);

        log.trace("Exiting ImageManagerImpl.create");

        return inImage;
    }

    public void update(ImageData inImageData, Image inImage, String inPath) throws Exception {

        log.trace("Entering ImageManagerImpl.update");
        log.debug("Updating ImageForm: " + inImage.getId());

        // Populate w/ the new values and save
        populateImage(inImageData, inImage, inPath);
        save(inImage);

        log.trace("Exiting ImageManagerImpl.update");
    }

    private void populateImage(ImageData inImageData, Image inImage, String inPath) throws Exception {

        log.trace("Entering populateImage");

        if (inImage != null) {
            // Image image = inImage.getImage();
            inImage.setTitle(inImageData.getTitle());
            inImage.setDescription(inImageData.getDescriptionOfConstruct());
            // inImage.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this Image
        if (inImageData.getFileLocation() != null) {
            System.out.println("<ImageDataManagerImpl> Uploading a file");
           
            // If this is a new Image, upload it to the server
            FormFile f = inImageData.getFileLocation();

            // Retrieve the file type
            String fileType = null;
            StringTokenizer strToken = new StringTokenizer(f.getFileName(), ".");

            while (strToken.hasMoreTokens()) {
                fileType = strToken.nextToken();
                System.out.println("Token=" + fileType);
            }

            System.out.println("<ImageDataManagerImpl> fileType is: " + fileType + " FileName is: " + f.getFileName()
                    + " Type is: " + f.getContentType());

            // Check the file type
            if (fileType != null) {
                if (fileType.equals("jpg") || fileType.equals("jpeg") || fileType.equals("gif")
                        || fileType.equals("tif") || fileType.equals("sid")) {
                    System.out.println("<ImageDataManagerImpl> Valid file type " + fileType);
                    System.out.println("<ImageDataManagerImpl> FileName is: " + f.getFileName() + " Type is: "
                            + f.getContentType());

                    InputStream in = null;
                    OutputStream out = null;

                    try {
                        // Get an input stream on the form file
                        in = f.getInputStream();

                        // Create an output stream to a file
                        // this file is stored on the jboss server
                        // TODO: Set a max size for this file
                        out = new BufferedOutputStream(new FileOutputStream(inPath));

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
                    String uniqueFileName = theGUID.toString();

                    // Retrieve ftp data from a resource bundle
                    ResourceBundle theBundle = ResourceBundle.getBundle("camod");

                    // Iterate through all the reciepts in the config file
                    String ftpServer = theBundle.getString(Constants.CaImage.FTPSERVER);
                    String ftpUsername = theBundle.getString(Constants.CaImage.FTPUSERNAME);
                    String ftpPassword = theBundle.getString(Constants.CaImage.FTPPASSWORD);
                    String ftpStorageDirectory = theBundle.getString(Constants.CaImage.FTPSTORAGEDIRECTORY);

                    // Upload the file to caIMAGE
                    FtpUtil ftpUtil = new FtpUtil();
                    ftpUtil.upload(ftpServer, ftpUsername, ftpPassword, ftpStorageDirectory + uniqueFileName,
                            uploadFile);

                    inImage.setTitle(inImageData.getTitle());
                    inImage.setFileServerLocation(uniqueFileName);
                    inImage.setDescription(inImageData.getDescriptionOfConstruct());

                    // inImage.setImage(image);

                } else {
                    // TODO: Add error for struts explaining that image is of an
                    // invalid type
                    System.out.println("Invalid file type! " + fileType);
                }
            }
        }

        log.trace("Exiting populateImage");
    }

}
