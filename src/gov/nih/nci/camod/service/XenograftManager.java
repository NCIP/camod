/**
 * 
 * $Id: XenograftManager.java,v 1.6 2005-10-20 20:43:54 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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

    public void remove(String id) throws Exception;

    public Xenograft create(XenograftData inXenograftData, AnimalModel inAnimalModel) throws Exception;

    public void update(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception;
}
