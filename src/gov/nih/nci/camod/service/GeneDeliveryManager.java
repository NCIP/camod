/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GeneDeliveryManager {
	public List getAll() throws Exception;
	public GeneDelivery get(String id) throws Exception;
	public GeneDelivery create(GeneDeliveryForm inGeneDeliveryForm, String inUsername, AnimalModel inAnimalModel ) throws Exception;
	public void update(GeneDeliveryForm inGeneDeliveryForm, GeneDelivery theGeneDelivery, AnimalModel inAnimalModel) throws Exception;
    public void remove(String id) throws Exception;
}
