/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * @author pandyas
 * 
 * $Id: AnimalDistributorManager.java,v 1.1 2005-10-26 20:16:07 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalDistributor;

public interface AnimalDistributorManager {
	
    public AnimalDistributor getByName(String id) throws Exception;	

}
