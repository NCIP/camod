/*
 * 
 * $Id: Image.java,v 1.17 2008-10-16 13:55:00 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2008/08/15 15:06:54  schroedn
 * added getter/setter comments
 *
 * Revision 1.15  2008/08/14 19:00:57  schroedn
 * Changes for  altern_url to urlAlternEntry
 *
 * Revision 1.14  2008/08/14 06:23:15  schroedn
 * New features added
 *
 * Revision 1.13  2007/10/31 15:54:19  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.12  2007/07/31 12:03:28  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.11  2006/08/17 18:36:34  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
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

    private String url;
    
    private Long absCancerModelId;
    
    private String urlAlternEntry;
    
    private StainingMethod stainingMethod;
    
    private String comments;    

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
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
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
        if (url != null)
        {
            StringTokenizer theTokenizer = new StringTokenizer(url);
            theThumbUrl = theTokenizer.nextToken(";");
        }
        else
        {
        	log.debug("Unable to get thumb URL");
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

            if (url != null)
            {
                StringTokenizer theTokenizer = new StringTokenizer(url);
                theUrl = theTokenizer.nextToken(CaImage.FILESEP);
                if (theTokenizer.hasMoreTokens())
                {
                    theUrl = theTokenizer.nextToken(CaImage.FILESEP);
                }
            }
            else
            {
            	log.debug("Unable to get thumb URL");
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
                if (url.indexOf(gencon) == -1)
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
        	log.debug("Exception getting url");
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

    /**
     * @return Returns the url.
     */
	public String getUrl() {
		return url;
	}

    /**
     * @param url
     *            The url to set.
     */
	public void setUrl(String url) {
		this.url = url;
	}
	
    /**
     * @return Returns the urlAlternEntry.
     */
	public String getUrlAlternEntry() {
		return urlAlternEntry;
	}

    /**
     * @param urlAlternEntry
     *            The urlAlternEntry to set.
     */
	public void setUrlAlternEntry(String urlAlternEntry) {
		this.urlAlternEntry = urlAlternEntry;
	}

	/**
	 * @return the absCancerModelId
	 */
	public Long getAbsCancerModelId() {
		return absCancerModelId;
	}

	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(Long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}


}
