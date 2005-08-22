/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.ImageManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImageManagerImpl extends BaseManager implements ImageManager {
	
	public List getAll() {		
		List images = null;
		
		try {
			images = Search.query(Image.class);
		} catch (Exception e) {
			System.out.println("Exception in ImageManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return images;
	}
	
	public Image get(String id) {
		Image image = null;
		
		try {
			image = (Image) Search.queryById(Image.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in ImageManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in ImageManagerImpl.get");
			e.printStackTrace();
		}
		
		return image;
    }

    public void save(Image image) {    	
    	try {
			Persist.save(image);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in ImageManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in ImageManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(Image.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in ImageManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in ImageManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
