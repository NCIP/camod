/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.webapp.form.XenograftData;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface XenograftManager {

    public List getAll() throws Exception;

    public Xenograft get(String id) throws Exception;

    public void save(Xenograft xenograft) throws Exception;

    public void remove(String id) throws Exception;

    public Xenograft create(XenograftData inXenograftData, AnimalModel inAnimalModel) throws Exception;

    public void update(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception;
}
