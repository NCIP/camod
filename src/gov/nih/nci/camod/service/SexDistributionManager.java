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
 * $Id: SexDistributionManager.java,v 1.4 2005-09-23 14:54:48 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.SexDistribution;

/**
 * See implementing classes for details
 */
public interface SexDistributionManager {

    public SexDistribution getByType(String inType);
}
