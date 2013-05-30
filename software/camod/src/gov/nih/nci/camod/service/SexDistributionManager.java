/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author
 * 
 * $Id: SexDistributionManager.java,v 1.5 2006-04-17 19:13:16 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/09/23 14:54:48  georgeda
 * Made SexDistribution a reference table
 *
 * 
 */
package gov.nih.nci.camod.service;

import java.util.List;

import gov.nih.nci.camod.domain.SexDistribution;

/**
 * See implementing classes for details
 */
public interface SexDistributionManager {

    //This is a getByName on type field in SexDistribution object
    public SexDistribution getByType(String inType);
    
    public List getAll() throws Exception;    
}
