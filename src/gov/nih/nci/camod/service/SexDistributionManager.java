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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SexDistributionManager {
	public List getSexDistributions();
	public SexDistribution getSexDistribution(String id);
    public void saveSexDistribution(SexDistribution sexDistribution);
    public void removeSexDistribution(String id);
}
