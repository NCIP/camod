/*
 * $Id: SpontaneousMutationManagerImpl.java,v 1.17 2008-01-16 18:30:22 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2007/10/31 19:13:27  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.15  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.14  2007/04/04 13:17:49  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.13  2007/03/27 18:38:15  pandyas
 * Modified code to trim identifiers - cleaner for display link
 *
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

		// GeneIdentifier
		GeneIdentifier inGeneIdentifier = null;
		if(inSpontaneousMutationData.getGeneIdentifier() != null && inSpontaneousMutationData.getGeneIdentifier().length() >0){
			log.info("inSpontaneousMutationData.getGeneIdentifier(): " + inSpontaneousMutationData.getGeneIdentifier());
			// Check for exisiting GeneIdentifier
			if (inSpontaneousMutation.getGeneIdentifier() != null) {
				log.info("getGeneIdentifier() != null loop");
				inGeneIdentifier = inSpontaneousMutation.getGeneIdentifier();
				inGeneIdentifier.setEntrezGeneID(inSpontaneousMutationData.getGeneIdentifier());
				inSpontaneousMutation.setGeneIdentifier(inGeneIdentifier);
				
				log.info("setEntrezGeneID");			
			} else {
				inGeneIdentifier = new GeneIdentifier();
				log.info("new GeneIdentifier loop");	
				inGeneIdentifier.setEntrezGeneID(inSpontaneousMutationData.getGeneIdentifier());
				inSpontaneousMutation.setGeneIdentifier(inGeneIdentifier);
				log.info("setEntrezGeneID");			
			}			
		}

        // Observation and Method of Observation
        // No genetic alteration in DB - data is entered from the GUI
        if (inSpontaneousMutation.getGeneticAlteration() == null && inSpontaneousMutationData.getObservation() != null && inSpontaneousMutationData.getObservation().length() > 0)
        {
            inSpontaneousMutation.setGeneticAlteration(new GeneticAlteration());
            log.debug("Saving: inSpontaneousMutation.getGeneticAlteration() attributes ");

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
        
		if (inSpontaneousMutationData.getMgiId() != null) {

			inMutationIdentifier.setMgiId(inSpontaneousMutationData
					.getMgiId().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}
		if (inSpontaneousMutationData.getZfinId() != null ) {
			inMutationIdentifier.setZfinId(inSpontaneousMutationData
					.getZfinId().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}
		if (inSpontaneousMutationData.getRgdId() != null) {

			inMutationIdentifier.setRgdId(inSpontaneousMutationData
					.getRgdId().trim());
			inSpontaneousMutation.setMutationIdentifier(inMutationIdentifier);
		}



        log.trace("Exiting populateSpontaneousMutation");
    }
}
