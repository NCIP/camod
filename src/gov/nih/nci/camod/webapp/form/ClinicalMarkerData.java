/**
 * 
 * @author pandyas
 * 
 * $Id: ClinicalMarkerData.java,v 1.2 2005-11-03 18:52:44 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface ClinicalMarkerData {
	
	public String getHistopathologyID();
	public void setHistopathologyID( String histopathologyID );
	public String getName();
    public void setName(String name);
    public String getValue();
    public void setValue(String value);	

}
