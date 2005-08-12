/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {
	
	public List getGeneDeliverys() {		
		List geneDeliverys = null;		
		return geneDeliverys;
	}
	
	public GeneDelivery getGeneDelivery(String id) {
		GeneDelivery geneDelivery = null;
		return geneDelivery;
    }

    public void saveGeneDelivery(GeneDelivery geneDelivery) {    	
    }

    public void removeGeneDelivery(String id) {    	
    }
}
