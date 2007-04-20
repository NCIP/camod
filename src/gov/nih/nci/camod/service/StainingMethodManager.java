/**
 * 
 * $Id: StainingMethodManager.java,v 1.3 2007-04-20 17:51:35 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/24 16:46:03  pandyas
 * Converted StainingMethod to lookup - modified code to pull dropdown list from DB
 *
 * Revision 1.1  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.StainingMethod;
import java.util.List;

public interface StainingMethodManager
{

    public List getAll() throws Exception;

    public StainingMethod get(String id) throws Exception;

    public StainingMethod getOrCreate(String inConceptCode, String inStainingName) throws Exception;

}
