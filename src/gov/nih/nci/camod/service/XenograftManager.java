/**
 * 
 * $Id: XenograftManager.java,v 1.7 2005-11-09 00:17:06 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/20 20:43:54  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.webapp.form.XenograftData;

import java.util.List;


public interface XenograftManager {

    public List getAll() throws Exception;

    public Xenograft get(String id) throws Exception;

    public void save(Xenograft xenograft) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Xenograft create(XenograftData inXenograftData, AnimalModel inAnimalModel) throws Exception;

    public void update(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception;
}
