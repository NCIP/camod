/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.SexDistribution;
import java.util.List;

/**
 * @author rajputs
 *
 * See implementing classes for details
 */
public interface SexDistributionManager {
	public List getAll();
	public SexDistribution get(String id);
    public SexDistribution getByType(String inType);
    public void save(SexDistribution sexDistribution);
    public void remove(String id);
}
