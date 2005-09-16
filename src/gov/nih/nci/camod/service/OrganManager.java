/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Organ;
import java.util.List;

public interface OrganManager {
	public List getAll();
	public Organ get(String id);
    public Organ getByName(String inType);
    public void save(Organ organ);
    public void remove(String id);
}
