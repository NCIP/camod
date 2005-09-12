package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.CurateableAction;

import java.util.List;

public class EmailActionImpl extends BaseCurateableAction {

    /**
     * Protected constructor. Use the create method to get an instance.
     * 
     */
    protected EmailActionImpl() {
    }

    /**
     * Create an instance of this curatable action
     * 
     * @param inArgs
     *            the arguments to the action
     * @param inObject
     *            the curatable object to get information from
     */
    public CurateableAction create() {
        return new EmailActionImpl();
    }

    /**
     * Execute the curatable action
     * 
     * @param inArgs
     *            the arguments to the action
     * @param inObject
     *            the curatable object to get information from
     */
    public void execute(List inArgs, Curateable inObject) {

        log.trace("Entering execute");

        log.debug("Arguments: " + inArgs);

        AnimalModel theAnimalModel = (AnimalModel) inObject;

        Log theLog = LogManagerSingleton.instance().getCurrentByModel(theAnimalModel);

        Person thePerson = (Person) theLog.getSubmitter();

        List theContactInfoList = thePerson.getContactInfoCollection();

        String[] theRecipients = new String[theContactInfoList.size()];

        for (int i = 0; i < theContactInfoList.size(); i++) {
            ContactInfo theContactInfo = (ContactInfo) theContactInfoList.get(i);
            theRecipients[i] = theContactInfo.getEmail();
        }

        try {
            if (theRecipients.length > 0) {
                MailUtilityImpl.sendMail(theRecipients, "Animal model assignment",
                        "You have been assigned an animal model", "caMod");
            } else {
                log.warn("No e-mail address assigned to user: " + thePerson.getUsername());
            }

        } catch (Exception e) {
            log.error("Error sending e-mail:", e);
        }

        log.trace("Exiting execute");
    }
}

/*
 * $Log: not supported by cvs2svn $
 */
