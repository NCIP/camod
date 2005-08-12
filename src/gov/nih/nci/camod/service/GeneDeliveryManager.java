/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.GeneDelivery;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GeneDeliveryManager {
	public List getGeneDeliverys();
	public GeneDelivery getGeneDelivery(String id);
    public void saveGeneDelivery(GeneDelivery geneDelivery);
    public void removeGeneDelivery(String id);
}
