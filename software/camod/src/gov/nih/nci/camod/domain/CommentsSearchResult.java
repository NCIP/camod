/**
 * @author dgeorge
 * 
 * $Id: CommentsSearchResult.java,v 1.4 2007-07-31 12:03:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.2  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.1  2005/10/17 13:25:52  georgeda
 * Initial revision
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

import gov.nih.nci.camod.service.impl.CommentsManagerSingleton;

/**
 * Used as wrapper around comments for speedy display during paganation.
 */
public class CommentsSearchResult
{
    private String myCommentsId;
    private String myModelId;
    private String myModelDescriptor = null;
    private String myModelSection = null;
    private String mySubmitterName = null;
    private String mySubmittedDate = null;
    private Comments myComments = null;

    /**
     * Create the wraper object
     * 
     * @param inAnimalModel
     *            the animal model we will be wrapping. Saves only the id.
     */
    public CommentsSearchResult(Comments inComments)
    {
        myCommentsId = inComments.getId().toString();
    }

    /**
     * Return the id for the associated comments
     * 
     * @return the id for the model
     */
    public String getId()
    {
        return myCommentsId;
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

        if (myModelDescriptor == null)
        {
            fetchComments();
            myModelDescriptor = myComments.getAbstractCancerModel().getModelDescriptor();
        }
        return myModelDescriptor;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getModelId() throws Exception
    {

        if (myModelId == null)
        {
            fetchComments();
            myModelId = myComments.getAbstractCancerModel().getId().toString();
        }
        return myModelId;
    }

    /**
     * Return the model descriptor. It will fetch the animal model from the DB
     * if it hasn't already happened.
     * 
     * @return the model descriptor for the associated model
     * 
     * @throws Exception
     */
    public String getModelSection() throws Exception
    {

        if (myModelSection == null)
        {
            fetchComments();
            myModelSection = myComments.getModelSection().getName();
        }
        return myModelSection;
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
            fetchComments();

            String theEmailAddress = myComments.getSubmitter().getEmailAddress();

            if (theEmailAddress.length() > 0)
            {
                mySubmitterName = "<a href=\"mailto:" + theEmailAddress + "\"/>" + myComments.getSubmitter().getDisplayName();
            }
            else
            {
                mySubmitterName = myComments.getSubmitter().getDisplayName();
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
            fetchComments();
            mySubmittedDate = myComments.getAvailability().getEnteredDate().toString();
        }
        return mySubmittedDate;
    }

    // Fetch the animal model from the DB
    private void fetchComments() throws Exception
    {
        if (myComments == null)
        {
            myComments = CommentsManagerSingleton.instance().get(myCommentsId);
        }
    }
}
