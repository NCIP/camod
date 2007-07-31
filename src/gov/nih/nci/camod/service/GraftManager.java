/**
 * 
 * $Id: GraftManager.java,v 1.1 2007-07-31 12:03:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/11/09 00:17:06  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.6  2005/10/20 20:43:54  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Graft;
import gov.nih.nci.camod.webapp.form.GraftData;

import java.util.List;


public interface GraftManager {

    public List getAll() throws Exception;

    public Graft get(String id) throws Exception;

    public void save(Graft graft) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Graft create(GraftData inGraftData, AnimalModel inAnimalModel) throws Exception;

    public void update(GraftData inGraftData, Graft inGraft, AnimalModel inAnimalModel)
            throws Exception;
}
