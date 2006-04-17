/**
 * 
 * $Id: StainingMethodManager.java,v 1.1 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.StainingMethod;
import java.util.List;

public interface StainingMethodManager
{
    
    public List getAll() throws Exception;

    public StainingMethod get(String id) throws Exception;

    public StainingMethod getByName(String inType) throws Exception;    

}
