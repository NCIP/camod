/**
 * 
 * @author pandyas
 * 
 * $Id: ClinicalMarkerData.java,v 1.4 2007-10-31 17:27:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.2  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface ClinicalMarkerData
{

    public String getHistopathologyID();

    public void setHistopathologyID(String histopathologyID);

    public String getName();

    public void setName(String name);

    public String getOtherName();

    public void setOtherName(String otherName);

    public String getValue();

    public void setValue(String value);
    
	public String getComments();
	
	public void setComments(String comments);    

}
