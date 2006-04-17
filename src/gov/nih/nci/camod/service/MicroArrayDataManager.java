/*
 * $Id: MicroArrayDataManager.java,v 1.4 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.MicroArrayData;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface MicroArrayDataManager {
	public List getAll() throws Exception;
	public MicroArrayData get(String id) throws Exception;
    public void save(MicroArrayData microArrayData) throws Exception;
    public void remove(String id) throws Exception;
}
