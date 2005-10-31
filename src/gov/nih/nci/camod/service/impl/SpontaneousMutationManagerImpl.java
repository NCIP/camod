/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.SpontaneousMutationManager;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationData;

import java.util.List;

public class SpontaneousMutationManagerImpl extends BaseManager implements SpontaneousMutationManager {

    public List getAll() throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.getAll");
        return super.getAll(SpontaneousMutation.class);
    }

    public SpontaneousMutation get(String id) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.get");
        return (SpontaneousMutation) super.get(id, SpontaneousMutation.class);
    }

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.save");
        super.save(SpontaneousMutation);
    }

    public void remove(String id) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.remove");
        super.remove(id, SpontaneousMutation.class);
    }

    public SpontaneousMutation create(SpontaneousMutationData inSpontaneousMutationData) throws Exception {

        log.trace("Entering SpontaneousMutationManagerImpl.create");

        SpontaneousMutation theSpontaneousMutation = new SpontaneousMutation();

        log.trace("Exiting SpontaneousMutationManagerImpl.create");

        populateSpontaneousMutation(inSpontaneousMutationData, theSpontaneousMutation);

        return theSpontaneousMutation;
    }

    public void update(SpontaneousMutationData inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation)
            throws Exception {

        log.trace("Entering SpontaneousMutationManagerImpl.update");
        log.debug("Updating SpontaneousMutationForm: " + inSpontaneousMutation.getId());

        // Populate w/ the new values and save
        populateSpontaneousMutation(inSpontaneousMutationData, inSpontaneousMutation);
        save(inSpontaneousMutation);

        log.trace("Exiting SpontaneousMutationManagerImpl.update");
    }

    private void populateSpontaneousMutation(SpontaneousMutationData inSpontaneousMutationData,
            SpontaneousMutation inSpontaneousMutation) throws Exception {

        log.trace("Entering populateSpontaneousMutation");

        inSpontaneousMutation.setName(inSpontaneousMutationData.getName());
        inSpontaneousMutation.setComments(inSpontaneousMutationData.getComments());

        List geneticList = inSpontaneousMutation.getGeneticAlterationCollection();

        if (geneticList.size() > 0) {
            GeneticAlteration inGeneticAlteration = (GeneticAlteration) geneticList.get(0);
            inGeneticAlteration.setObservation(inSpontaneousMutationData.getObservation());
            inGeneticAlteration.setMethodOfObservation(inSpontaneousMutationData.getMethodOfObservation());
        } else {
            if (inSpontaneousMutationData.getObservation() != null) {
                if (!inSpontaneousMutationData.getObservation().equals("")) {
                    GeneticAlteration inGeneticAlteration = new GeneticAlteration();
                    inGeneticAlteration.setObservation(inSpontaneousMutationData.getObservation());
                    inGeneticAlteration.setMethodOfObservation(inSpontaneousMutationData.getMethodOfObservation());
                    inSpontaneousMutation.addGeneticAlteration(inGeneticAlteration);
                }
            }
        }

        MutationIdentifier inMutationIdentifier = new MutationIdentifier();

        inMutationIdentifier.setNumberMGI(Long.valueOf(inSpontaneousMutationData.getNumberMGI().trim()));
        inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);

        log.trace("Exiting populateSpontaneousMutation");
    }
}
