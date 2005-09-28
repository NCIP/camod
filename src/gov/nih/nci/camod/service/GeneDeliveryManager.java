/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface GeneDeliveryManager {

    public List getAll() throws Exception;

    public GeneDelivery get(String id) throws Exception;

    public GeneDelivery create(GeneDeliveryData inGeneDeliveryForm) throws Exception;

    public void update(GeneDeliveryData inGeneDeliveryForm, GeneDelivery theGeneDelivery) throws Exception;

    public void remove(String id) throws Exception;
}
