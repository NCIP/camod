/*
 * 
 * $Id: Image.java,v 1.11 2006-08-17 18:36:34 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.9  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.8  2005/11/08 16:47:49  georgeda
 * Changes for images
 *
 * Revision 1.7  2005/11/07 21:55:24  georgeda
 * Changes for images
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.Constants.CaImage;
import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Image extends BaseObject implements Comparable, Serializable, Duplicatable
{

    static private final Log log = LogFactory.getLog(Image.class);
    
    private static final long serialVersionUID = 3259255453799404851L;

    private String title;

    private String description;

    private String fileServerLocation;

    private StainingMethod stainingMethod;

    private AbstractCancerModel cancerModel;

    /**
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return fileServerLocation The fileServerLocation to set.
     */
    public String getFileServerLocation()
    {
        return fileServerLocation;
    }

    /**
     * @param fileServerLocation
     *            The fileServerLocation to set.
     */
    public void setFileServerLocation(String fileServerLocation)
    {
        this.fileServerLocation = fileServerLocation;
    }

    /**
     * @return Returns the stainingMethod.
     */
    public StainingMethod getStainingMethod()
    {
        return stainingMethod;
    }

    /**
     * @param stainingMethod
     *            The stainingMethod to set.
     */
    public void setStainingMethod(StainingMethod stainingMethod)
    {
        this.stainingMethod = stainingMethod;
    }

    /**
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel)
    {
        this.cancerModel = cancerModel;
    }

    public String getThumbUrl()
    {

        String theThumbUrl = "";
        if (fileServerLocation != null)
        {
            StringTokenizer theTokenizer = new StringTokenizer(fileServerLocation);
            theThumbUrl = theTokenizer.nextToken(";");
        }
        else
        {
            System.out.println("Unable to get thumb URL");
        }

        return theThumbUrl;
    }

    /**
     * @return Returns the ImageUrl.
     */
    public String getImageUrl()
    {
        String theUrl = "";
        try
        {
            // Retrieve ftp data from a resource bundle
    		Properties camodProperties = new Properties();
    		String camodPropertiesFileName = null;

    		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
    		
    		try {
			
    		FileInputStream in = new FileInputStream(camodPropertiesFileName);
    		camodProperties.load(in);
		
    		} 
    		catch (FileNotFoundException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		} catch (IOException e) {
    			log.error("Caught exception finding file for properties: ", e);
    			e.printStackTrace();			
    		}	        	

            String windowStart = camodProperties.getProperty("caimage.window.start");
            String windowEnd = camodProperties.getProperty("caimage.window.end");

            if (fileServerLocation != null)
            {
                StringTokenizer theTokenizer = new StringTokenizer(fileServerLocation);
                theUrl = theTokenizer.nextToken(CaImage.FILESEP);
                if (theTokenizer.hasMoreTokens())
                {
                    theUrl = theTokenizer.nextToken(CaImage.FILESEP);
                }
            }
            else
            {
                System.out.println("Unable to get thumb URL");
            }

            // It's in the old jsp format. Change to new format
            if (theUrl.indexOf(CaImage.LEGACYJSP) != -1)
            {

                // Pull out the image
                int index = theUrl.indexOf(CaImage.IMGTAG);
                String theImage = theUrl;
                if (index > 0)
                {
                    theImage = theUrl.substring(index + CaImage.IMGTAG.length());
                }

                // Setup the server location
                String sidUrlStart = camodProperties.getProperty("caimage.sidview.uri_start");
                String sidUrlEnd = camodProperties.getProperty("caimage.sidview.uri_end");
                String gencon = camodProperties.getProperty("caimage.gencon");
                String model = camodProperties.getProperty("caimage.model");
                String theType = "";
                if (fileServerLocation.indexOf(gencon) == -1)
                {
                    theType = model;
                }
                else
                {
                    theType = gencon;
                }
                theUrl = sidUrlStart + theType + theImage + sidUrlEnd;
            }
            theUrl = windowStart + theUrl + windowEnd;

        }
        catch (Exception e)
        {
            System.out.println("Exception getting url");
        }
        return theUrl;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getTitle() + " - " + this.getDescription();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Image obj = (Image) o;
        if (HashCodeUtil.notEqual(this.getTitle(), obj.getTitle()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getTitle());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof Image) && (this.getTitle() != null) && (((Image) o).getTitle() != null))
        {
            int result = this.getTitle().compareTo(((Image) o).getTitle());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
