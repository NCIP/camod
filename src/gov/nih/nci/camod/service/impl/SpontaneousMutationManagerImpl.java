/*
 * $Id: SpontaneousMutationManagerImpl.java,v 1.13 2007-03-27 18:38:15 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/03/26 12:01:11  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/05/04 19:27:45  pandyas
 * Changed GeneticAlterationCollection to GeneticAlteration relationship from SpontaneousMutation and InducedMutation objects
 *
 * Revision 1.10  2006/04/20 18:12:11  pandyas
 * Modified save of Genetic Alteration
 *
 * Revision 1.9  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.SpontaneousMutationManager;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationData;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpontaneousMutationManagerImpl extends BaseManager implements SpontaneousMutationManager
{

    public List getAll() throws Exception
    {
        log.trace("In SpontaneousMutationManagerImpl.getAll");
        return super.getAll(SpontaneousMutation.class);
    }

    public SpontaneousMutation get(String id) throws Exception
    {
        log.trace("In SpontaneousMutationManagerImpl.get");
        return (SpontaneousMutation) super.get(id, SpontaneousMutation.class);
    }

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception
    {
        log.trace("In SpontaneousMutationManagerImpl.save");
        super.save(SpontaneousMutation);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        inAnimalModel.getSpontaneousMutationCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public SpontaneousMutation create(SpontaneousMutationData inSpontaneousMutationData) throws Exception
    {

        log.trace("Entering SpontaneousMutationManagerImpl.create");

        SpontaneousMutation theSpontaneousMutation = new SpontaneousMutation();

        log.trace("Exiting SpontaneousMutationManagerImpl.create");

        populateSpontaneousMutation(inSpontaneousMutationData, theSpontaneousMutation);

        return theSpontaneousMutation;
    }

    public void update(SpontaneousMutationData inSpontaneousMutationData,
                       SpontaneousMutation inSpontaneousMutation) throws Exception
    {

        log.trace("Entering SpontaneousMutationManagerImpl.update");
        log.debug("Updating SpontaneousMutationForm: " + inSpontaneousMutation.getId());

        // Populate w/ the new values and save
        populateSpontaneousMutation(inSpontaneousMutationData, inSpontaneousMutation);
        save(inSpontaneousMutation);

        log.trace("Exiting SpontaneousMutationManagerImpl.update");
    }

    private void populateSpontaneousMutation(SpontaneousMutationData inSpontaneousMutationData,
                                             SpontaneousMutation inSpontaneousMutation) throws Exception
    {

        log.trace("Entering populateSpontaneousMutation");
        //Name
        inSpontaneousMutation.setName(inSpontaneousMutationData.getName());

        //Comment
        inSpontaneousMutation.setComments(inSpontaneousMutationData.getComments());

        // GeneID
        inSpontaneousMutation.setGeneId(inSpontaneousMutationData.getGeneId());

        // Observation and Method of Observation
        // No genetic alteration in DB - data is entered from the GUI
        if (inSpontaneousMutation.getGeneticAlteration() == null && inSpontaneousMutationData.getObservation() != null && inSpontaneousMutationData.getObservation().length() > 0)
        {
            inSpontaneousMutation.setGeneticAlteration(new GeneticAlteration());
            log.info("Saving: inSpontaneousMutation.getGeneticAlteration() attributes ");

            inSpontaneousMutation.getGeneticAlteration().setObservation(inSpontaneousMutationData.getObservation());
            inSpontaneousMutation.getGeneticAlteration().setMethodOfObservation(inSpontaneousMutationData.getMethodOfObservation());
        }

        // Already have a genetic alteration in DB. Either blank it out or update it
        else
        {
            if (inSpontaneousMutationData.getObservation() != null && inSpontaneousMutationData.getObservation().length() > 0)
            {
                inSpontaneousMutation.getGeneticAlteration().setObservation(inSpontaneousMutationData.getObservation());
                inSpontaneousMutation.getGeneticAlteration().setMethodOfObservation(inSpontaneousMutationData.getMethodOfObservation());
            }
            else
            {
                inSpontaneousMutation.setGeneticAlteration(null);
            }
        }

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inSpontaneousMutation.getMutationIdentifier() != null)
            inMutationIdentifier = inSpontaneousMutation.getMutationIdentifier();
        else
            inMutationIdentifier = new MutationIdentifier();
        
		if (inSpontaneousMutationData.getMgiNumber() != null) {

			inMutationIdentifier.setMgiNumber(inSpontaneousMutationData
					.getMgiNumber().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}
		if (inSpontaneousMutationData.getZfinNumber() != null ) {
			inMutationIdentifier.setZfinNumber(inSpontaneousMutationData
					.getZfinNumber().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}
		if (inSpontaneousMutationData.getRgdNumber() != null) {

			inMutationIdentifier.setRgdNumber(inSpontaneousMutationData
					.getRgdNumber().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}



        log.trace("Exiting populateSpontaneousMutation");
    }
}
