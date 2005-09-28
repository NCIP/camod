/**
 * @author schroedln
 * 
 * $Id: GeneDeliveryManagerImpl.java,v 1.4 2005-09-28 21:20:01 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/28 15:12:27  schroedn
 * Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryData;

import java.util.List;

public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {

    public List getAll() throws Exception {
        log.trace("In GeneDeliveryManagerImpl.getAll");
        return super.getAll(GeneDelivery.class);
    }

    public GeneDelivery get(String id) throws Exception {
        log.trace("In GeneDeliveryManagerImpl.get");
        return (GeneDelivery) super.get(id, GeneDelivery.class);
    }

    public void save(GeneDelivery geneDelivery) throws Exception {
        log.trace("In GeneDeliveryManagerImpl.save");
        super.save(geneDelivery);
    }

    public void remove(String id) throws Exception {
        log.trace("In GeneDeliveryManagerImpl.remove");
        super.remove(id, GeneDelivery.class);
    }

    public GeneDelivery create(GeneDeliveryData inGeneDeliveryForm) throws Exception {

        log.trace("Entering GeneDeliveryManagerImpl.create");

        GeneDelivery theGeneDelivery = new GeneDelivery();

        log.trace("Exiting GeneDeliveryManagerImpl.create");
        populateGeneDelivery(inGeneDeliveryForm, theGeneDelivery);
        
        return theGeneDelivery;
    }

    public void update(GeneDeliveryData inGeneDeliveryForm, GeneDelivery inGeneDelivery) throws Exception {

        log.trace("Entering GeneDeliveryManagerImpl.update");
        log.debug("Updating GeneDeliveryForm: " + inGeneDelivery.getId());

        // Populate w/ the new values and save
        populateGeneDelivery(inGeneDeliveryForm, inGeneDelivery);

        save(inGeneDelivery);

        log.trace("Exiting GeneDeliveryManagerImpl.update");
    }

    private void populateGeneDelivery(GeneDeliveryData inGeneDeliveryForm, GeneDelivery inGeneDelivery)
            throws Exception {

        log.trace("Entering populateGeneDelivery");

        if (inGeneDelivery.getTreatment() == null) {
            inGeneDelivery.setTreatment(new Treatment());
        }
        inGeneDelivery.getTreatment().setRegimen(inGeneDeliveryForm.getRegimen());

        inGeneDelivery.setViralVector(inGeneDeliveryForm.getViralVector());
        inGeneDelivery.setGeneInVirus(inGeneDeliveryForm.getGeneInVirus());

        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode (i.e.
         * Location of Delivery)
         */
        System.out.println("Saving: getOrgan=" + inGeneDeliveryForm.getOrgan() + " Not Saving organTissueName="
                + inGeneDeliveryForm.getOrganTissueName());

        if (inGeneDelivery.getOrgan() == null) {
            inGeneDelivery.setOrgan(new Organ());
        }
        inGeneDelivery.getOrgan().setName(inGeneDeliveryForm.getOrganTissueName());
        inGeneDelivery.getOrgan().setConceptCode(inGeneDeliveryForm.getOrganTissueCode());

        log.trace("Exiting populateGeneDelivery");
    }

}
