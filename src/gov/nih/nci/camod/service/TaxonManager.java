/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Taxon;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaxonManager {
	public List getAll();
	public Taxon get(String id);
    public void save(Taxon taxon);
    public void remove(String id);
    public List getStrains(Species species);
}
