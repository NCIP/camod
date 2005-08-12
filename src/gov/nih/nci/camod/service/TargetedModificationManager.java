/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.TargetedModification;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TargetedModificationManager {
	public List getTargetedModifications();
	public TargetedModification getTargetedModification(String id);
    public void saveTargetedModification(TargetedModification targetedModification);
    public void removeTargetedModification(String id);
}
