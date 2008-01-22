/**
 * 
 * $Id: TransplantationManager.java,v 1.1 2007-10-31 18:48:03 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.domain.Transplantation;
import gov.nih.nci.camod.webapp.form.TransplantationData;

import java.util.List;


public interface TransplantationManager {

    public List getAll() throws Exception;

    public Transplantation get(String id) throws Exception;

    public void save(Transplantation transplantation) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Transplantation create(TransplantationData inTransplantationData, AnimalModel inAnimalModel) throws Exception;

    public void update(TransplantationData inTransplantationData, Transplantation inTransplantation, AnimalModel inAnimalModel)
            throws Exception;
}
