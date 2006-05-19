/**
 * 
 * $Id: StrainManager.java,v 1.2 2006-05-19 16:38:53 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Strain;
import java.util.List;


public interface StrainManager
{
    public List getAll() throws Exception;

    public Strain get(String id) throws Exception;

    public Strain getByName(String inName) throws Exception;

    public Strain getOrCreate(String inStrainName,
                              String inOtherStrainName,
                              String inSpeciesName) throws Exception;

    public Strain getOrCreate(String inStrainName,
                              String inOtherStrainName,
                              String inSpeciesName,
                              String inOtherSpeciesName) throws Exception;

}
