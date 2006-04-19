/*
 * $Id: TransgeneManager.java,v 1.5 2006-04-19 17:37:51 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Transgene;
import java.util.List;

/**
 * @author rajputs
 */
public interface TransgeneManager {
	public List getAll() throws Exception;
	public Transgene get(String id) throws Exception;
    public void save(Transgene transgene) throws Exception;
    public void remove(String id) throws Exception;
}
