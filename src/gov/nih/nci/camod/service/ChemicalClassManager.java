/**
 * @author
 * 
 * $Id: ChemicalClassManager.java,v 1.1 2005-09-23 14:54:56 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ChemicalClass;

/**
 * See implementing classes for details
 */
public interface ChemicalClassManager {

    public ChemicalClass getByName(String inName);
}
