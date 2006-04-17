/**
 * 
 * $Id: AssociatedExpressionData.java,v 1.2 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

public interface AssociatedExpressionData {

	public String getName();

	public void setName(String name);
	
	public String getExpressionLevel();
	
	public void setExpressionLevel(String expressionLevel);

	public String getEngineeredGeneID();
	
	public void setEngineeredGeneID(String engineeredGeneID);
	
	public String getOrgan();
	
	public void setOrgan(String organ);
	
	public String getOrganTissueCode();
	
	public void setOrganTissueCode(String organTissueCode);
	
	public String getOrganTissueName();
	
	public void setOrganTissueName(String organTissueName);
	
}
