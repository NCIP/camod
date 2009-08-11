/**
 * 
 * $Id: SpeciesManager.java,v 1.2 2006-04-20 18:11:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Species;

import java.util.List;

public interface SpeciesManager
{    
    public List getAll() throws Exception;

    public Species get(String id) throws Exception;
    
    public Species getByName(String inName) throws Exception;    
    
    public Species getOrCreate(String inSpeciesName, String inOtherName) throws Exception;
}
