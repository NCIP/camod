/**
 * @author dgeorge
 * 
 * $Id: AnimalModelSearchResult.java,v 1.9 2006-01-18 14:23:31 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.7  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.6  2005/11/15 19:10:23  schroedn
 * Defect #49
 *
 * Fixed misspelling of Metastasis in getMetastatisSites
 *
 * Revision 1.5  2005/10/17 18:19:28  georgeda
 * Added ability to sort
 *
 * Revision 1.4  2005/10/17 13:27:54  georgeda
 * Updates
 *
 * Revision 1.3  2005/10/12 18:18:48  georgeda
 * Small fix
 *
 * Revision 1.2  2005/10/12 18:09:29  georgeda
 * Update for metastatis organs
 *
 * Revision 1.1  2005/10/07 16:27:50  georgeda
 * Implemented paganation
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;

import java.util.*;

/**
 * Used as wrapper around animal model for speedy display during paganation.
 */
public class AnimalModelSearchResult implements Comparable
{

    private String myAnimalModelId;
    private String myTumorSites = null;
    private String mySpecies = null;
    private String myModelDescriptor = null;
    private String mySubmitterName = null;
    private String mySubmittedDate = null;
    private AnimalModel myAnimalModel = null;

    /**
     * Create the wraper object
     * 
     * @param inAnimalModel
     *            the animal model we will be wrapping. Saves only the id.
     */
    public AnimalModelSearchResult(AnimalModel inAnimalModel)
    {
        myAnimalModelId = inAnimalModel.getId().toString();
        myModelDescriptor = inAnimalModel.getModelDescriptor();
    }

    /**
     * Return the id for the associated model
     * 
     * @return the id for the model
     */
    public String getId()
    {

        return myAnimalModelId;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getModelDescriptor() throws Exception
    {
        return myModelDescriptor;
    }

    /**
     * Return the species. It will fetch the animal model from the DB if it
     * hasn't already happened.
     * 
     * @return the species for the associated model
     * @throws Exception
     */
    public String getSpecies() throws Exception
    {
        if (mySpecies == null)
        {
            fetchAnimalModel();
            mySpecies = myAnimalModel.getSpecies().getScientificName();
        }
        return mySpecies;
    }

    /**
     * Return the list of tumor sites. It will fetch the animal model from the
     * DB if it hasn't already happened.
     * 
     * @return the list of tumor sites for the associated model
     * @throws Exception
     */
    public String getTumorSites() throws Exception
    {

        Set<String> theOrgans = new TreeSet<String>();
        Hashtable<String, TreeSet<String>> theMetas = new Hashtable<String, TreeSet<String>>();

        if (myTumorSites == null)
        {
            fetchAnimalModel();

            myTumorSites = "";
            List<Histopathology> theHistopathologyList = myAnimalModel.getHistopathologyCollectionSorted();

            for (int i = 0, j = theHistopathologyList.size(); i < j; i++)
            {

                Histopathology theHistopathology = (Histopathology) theHistopathologyList.get(i);

                String theOrgan = theHistopathology.getOrgan().getEVSPreferredDescription();

                if (!theOrgans.contains(theOrgan))
                {
                    theOrgans.add(theOrgan);
                    theMetas.put(theOrgan, new TreeSet<String>());
                }

                TreeSet<String> theMetaSet = theMetas.get(theOrgan);
                List theMetastasisList = theHistopathology.getMetastatisCollectionSorted();

                for (int k = 0, l = theMetastasisList.size(); k < l; k++)
                {

                    Histopathology theMetastasis = (Histopathology) theMetastasisList.get(k);
                    String theMetaOrgan = theMetastasis.getOrgan().getEVSPreferredDescription();

                    if (!theMetaSet.contains(theMetaOrgan))
                    {
                        theMetaSet.add(theMetaOrgan);
                    }
                }
            }
        }

        Iterator theOrganIterator = theOrgans.iterator();

        while (theOrganIterator.hasNext())
        {

            String theOrgan = (String) theOrganIterator.next();

            myTumorSites += "<b>" + theOrgan + "</b><br/>";

            TreeSet theMetaSet = (TreeSet) theMetas.get(theOrgan);

            Iterator theMetaIterator = theMetaSet.iterator();

            while (theMetaIterator.hasNext())
            {
                String theMetaOrgan = (String) theMetaIterator.next();

                myTumorSites += theMetaOrgan + " (Metastasis)<br>";
            }

        }

        return myTumorSites;
    }

    /**
     * Gets the display name of the submitter in an html linked format
     * 
     * @return the display name of the submitter
     * @throws Exception
     */
    public String getSubmitterName() throws Exception
    {

        if (mySubmitterName == null)
        {
            fetchAnimalModel();

            String theEmailAddress = myAnimalModel.getSubmitter().getEmailAddress();

            if (theEmailAddress.length() > 0)
            {
                mySubmitterName = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myAnimalModel.getSubmitter().getDisplayName();
            }
            else
            {
                mySubmitterName = myAnimalModel.getSubmitter().getDisplayName();
            }
        }
        return mySubmitterName;
    }

    /**
     * Gets the date for which the model was submitted
     * 
     * @return the date the model was submitted
     * @throws Exception
     */
    public String getSubmittedDate() throws Exception
    {

        if (mySubmittedDate == null)
        {
            fetchAnimalModel();

            mySubmittedDate = myAnimalModel.getAvailability().getEnteredDate().toString();
        }
        return mySubmittedDate;
    }

    public int compareTo(Object inObject)
    {

        AnimalModelSearchResult theResult = (AnimalModelSearchResult) inObject;

        return this.myModelDescriptor.compareTo(theResult.myModelDescriptor);
    }

    // Fetch the animal model from the DB
    private void fetchAnimalModel() throws Exception
    {
        if (myAnimalModel == null)
        {
            myAnimalModel = AnimalModelManagerSingleton.instance().get(myAnimalModelId);
        }
    }
}
