/**
 * 
 * $Id: SpontaneousMutationData.java,v 1.2 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

public interface SpontaneousMutationData
{

    public String getName();

    public void setName(String name);

    public String getMgiNumber();

    public void setMgiNumber(String mgiNumber);

    public String getComments();

    public void setComments(String comments);

    public String getObservation();

    public void setObservation(String observation);

    public String getMethodOfObservation();

    public void setMethodOfObservation(String methodOfObservation);

    public String getGeneId();

    public void setGeneId(String geneId);

}
