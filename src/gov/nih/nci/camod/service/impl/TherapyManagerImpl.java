/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TherapyManagerImpl extends BaseManager implements TherapyManager {
	
	public List getTherapys() {		
		List therapys = null;		
		return therapys;
	}
	
	public Therapy getTherapy(String id) {
		Therapy therapy = null;
		return therapy;
    }

    public void saveTherapy(Therapy therapy) {    	
    }

    public void removeTherapy(String id) {    	
    }
}
