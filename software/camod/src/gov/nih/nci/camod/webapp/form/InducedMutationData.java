/**
 * 
 * $Id: InducedMutationData.java,v 1.6 2007-10-31 17:57:26 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.4  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

public interface InducedMutationData {
	
	public String getType();
	
	public void setType(String type);
	
	public String getOtherType();
	
	public void setOtherType(String otherType);
	
	public String getCasNumber();
	
	public void setCasNumber(String casNumber);
	
	public String getGeneIdentifier();
	
	public void setGeneIdentifier(String geneIdentifier);
	
	public String getName();
	
	public void setName(String name);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getObservation();
	
	public void setObservation(String observation);
	
	public String getMethodOfObservation();
	
	public void setMethodOfObservation(String methodOfObservation);
	
	public String getMgiId();
	
	public void setMgiId(String mgiId);

    public String getComments();
    
    public void setComments(String comments);
    
	public String getRgdId();
	
	public void setRgdId(String rgdId);
	
	public String getZfinId();
	
	public void setZfinId(String zfinId);
    
}