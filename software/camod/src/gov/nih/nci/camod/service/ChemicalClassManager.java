/**
 * @author
 * 
 * $Id: ChemicalClassManager.java,v 1.2 2005-11-07 20:43:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/23 14:54:56  georgeda
 * Made SexDistribution a reference table
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ChemicalClass;

/**
 * See implementing classes for details
 */
public interface ChemicalClassManager {

    public ChemicalClass getByName(String inName) throws Exception;
}
