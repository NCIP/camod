/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.service.EnvironmentalFactorManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnvironmentalFactorManagerImpl extends BaseManager implements EnvironmentalFactorManager {
	
	public List getEnvironmentalFactors() {		
		List environmentalFactors = null;		
		return environmentalFactors;
	}
	
	public EnvironmentalFactor getEnvironmentalFactor(String id) {
		EnvironmentalFactor environmentalFactor = null;
		return environmentalFactor;
    }

    public void saveEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {    	
    }

    public void removeEnvironmentalFactor(String id) {    	
    }
}
