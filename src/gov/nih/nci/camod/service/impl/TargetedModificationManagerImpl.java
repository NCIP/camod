/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.TargetedModificationManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TargetedModificationManagerImpl extends BaseManager implements TargetedModificationManager {
	
	public List getTargetedModifications() {		
		List targetedModifications = null;		
		return targetedModifications;
	}
	
	public TargetedModification getTargetedModification(String id) {
		TargetedModification targetedModification = null;
		return targetedModification;
    }

    public void saveTargetedModification(TargetedModification targetedModification) {    	
    }

    public void removeTargetedModification(String id) {    	
    }
}
