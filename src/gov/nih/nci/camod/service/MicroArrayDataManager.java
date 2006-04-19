/*
 * $Id: MicroArrayDataManager.java,v 1.5 2006-04-19 17:37:51 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.MicroArrayData;
import java.util.List;

/**
 * @author rajputs
 */
public interface MicroArrayDataManager {
	public List getAll() throws Exception;
	public MicroArrayData get(String id) throws Exception;
    public void save(MicroArrayData microArrayData) throws Exception;
    public void remove(String id) throws Exception;
}
