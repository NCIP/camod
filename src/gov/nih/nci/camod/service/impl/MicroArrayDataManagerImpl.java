/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.service.MicroArrayDataManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MicroArrayDataManagerImpl extends BaseManager implements MicroArrayDataManager {
	
	public List getMicroArrayDatas() {		
		List microArrayDatas = null;		
		return microArrayDatas;
	}
	
	public MicroArrayData getMicroArrayData(String id) {
		MicroArrayData microArrayData = null;
		return microArrayData;
    }

    public void saveMicroArrayData(MicroArrayData microArrayData) {    	
    }

    public void removeMicroArrayData(String id) {    	
    }
}
