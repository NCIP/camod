/**
 * 
 * $Id: TransplantManager.java,v 1.1 2008-01-16 18:30:30 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/10/31 18:48:03  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.1  2007/07/31 12:03:05  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
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
import gov.nih.nci.camod.domain.Transplant;
import gov.nih.nci.camod.webapp.form.TransplantData;

import java.util.List;


public interface TransplantManager {

    public List getAll() throws Exception;

    public Transplant get(String id) throws Exception;

    public void save(Transplant transplant) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Transplant create(TransplantData inTransplantData, AnimalModel inAnimalModel) throws Exception;

    public void update(TransplantData inTransplantData, Transplant inTransplant, AnimalModel inAnimalModel)
            throws Exception;
}
