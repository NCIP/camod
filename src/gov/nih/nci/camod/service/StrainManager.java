/**
 * 
 * $Id: StrainManager.java,v 1.1 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
    
    public Strain getOrCreate(String inStrainName, String inOtherStrainName, String inSpecies) throws Exception;    
    
}
