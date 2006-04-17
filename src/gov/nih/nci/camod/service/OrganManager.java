/**
 * 
 * $Id: OrganManager.java,v 1.4 2006-04-17 19:13:16 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/11/07 20:43:29  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions
 *
 * Revision 1.2  2005/10/20 20:42:43  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Organ;

import java.util.List;

public interface OrganManager
{
    public List getAll() throws Exception;

    public Organ get(String id) throws Exception;

    public Organ getByName(String inType) throws Exception;
    
    public Organ getOrCreate(String inConceptCode) throws Exception;    

}
