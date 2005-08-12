/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.service.InducedMutationManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager {
	
	public List getInducedMutations() {		
		List inducedMutations = null;		
		return inducedMutations;
	}
	
	public InducedMutation getInducedMutation(String id) {
		InducedMutation inducedMutation = null;
		return inducedMutation;
    }

    public void saveInducedMutation(InducedMutation inducedMutation) {    	
    }

    public void removeInducedMutation(String id) {    	
    }
}
