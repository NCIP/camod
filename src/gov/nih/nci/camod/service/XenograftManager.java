/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Xenograft;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface XenograftManager {
	public List getXenografts();
	public Xenograft getXenograft(String id);
    public void saveXenograft(Xenograft xenograft);
    public void removeXenograft(String id);
}
