/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.XenograftManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XenograftManagerImpl extends BaseManager implements XenograftManager {
	
	public List getXenografts() {		
		List xenografts = null;		
		return xenografts;
	}
	
	public Xenograft getXenograft(String id) {
		Xenograft xenograft = null;
		return xenograft;
    }

    public void saveXenograft(Xenograft xenograft) {    	
    }

    public void removeXenograft(String id) {    	
    }
}
