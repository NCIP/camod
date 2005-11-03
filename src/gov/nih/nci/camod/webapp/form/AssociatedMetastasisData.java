/**
 * 
 * @author pandyas
 * 
 * $Id: AssociatedMetastasisData.java,v 1.1 2005-11-03 18:52:44 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface AssociatedMetastasisData extends HistopathologyData {
	
	public String getHistopathologyID();
	
	public void setHistopathologyID(String histopathologyID);
	

}
