/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: RegulatoryElementTypeManager.java,v 1.1 2005-10-24 14:15:52 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.RegulatoryElementType;

/**
 * See implementing classes for details
 */
public interface RegulatoryElementTypeManager {

    public RegulatoryElementType getByType(String inType) throws Exception;
}
