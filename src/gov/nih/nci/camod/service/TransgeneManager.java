/*
 * $Id: TransgeneManager.java,v 1.4 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Transgene;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TransgeneManager {
	public List getAll() throws Exception;
	public Transgene get(String id) throws Exception;
    public void save(Transgene transgene) throws Exception;
    public void remove(String id) throws Exception;
}
