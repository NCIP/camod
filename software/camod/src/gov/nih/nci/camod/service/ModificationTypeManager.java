/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: ModificationTypeManager.java,v 1.2 2006-04-19 17:37:51 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/31 16:34:58  georgeda
 * Initial revision
 *
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ModificationType;

public interface ModificationTypeManager {

    //Sima TODO:  we may be able to remove this one
    public ModificationType getByName(String inName) throws Exception;
    
    public ModificationType getOrCreate(String inName, String inOtherName) throws Exception;    

}
