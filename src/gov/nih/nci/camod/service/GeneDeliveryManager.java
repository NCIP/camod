/**
 * 
 * $Id: GeneDeliveryManager.java,v 1.6 2005-11-02 19:03:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/20 20:23:17  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;

import java.util.List;

public interface GeneDeliveryManager {

    public List getAll() throws Exception;

    public GeneDelivery get(String id) throws Exception;

    public GeneDelivery create(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryForm) throws Exception;

    public void update(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryForm, GeneDelivery theGeneDelivery) throws Exception;

    public void remove(String id) throws Exception;
}
