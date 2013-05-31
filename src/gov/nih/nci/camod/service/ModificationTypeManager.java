/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: ModificationTypeManager.java,v 1.1 2005-10-31 16:34:58 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ModificationType;

public interface ModificationTypeManager {

    public ModificationType getByName(String inName) throws Exception;

}
