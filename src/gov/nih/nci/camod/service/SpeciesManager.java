/**
 * 
 * $Id: SpeciesManager.java,v 1.1 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
    
    public Species getOrCreate(String inSpeciesName) throws Exception;
}
