/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.ImageManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImageManagerImpl extends BaseManager implements ImageManager {
	
	public List getImages() {		
		List images = null;		
		return images;
	}
	
	public Image getImage(String id) {
		Image image = null;
		return image;
    }

    public void saveImage(Image image) {    	
    }

    public void removeImage(String id) {    	
    }
}
