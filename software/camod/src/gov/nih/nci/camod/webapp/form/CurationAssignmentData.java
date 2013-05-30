/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 *   
 * $Id: CurationAssignmentData.java,v 1.1 2007-07-31 12:02:03 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $ 
 */

package gov.nih.nci.camod.webapp.form;

public interface CurationAssignmentData {

    public String getCurrentState();

    public void setCurrentState(String inCurrentState);

	public String getExternalSource();

	public void setExternalSource(String externalSource);

	public String getEditor();

	public void setEditor(String editor);

	public String getModelDescriptor();

	public void setModelDescriptor(String modelDescriptor);

	public String getModelId();

	public void setModelId(String modelId);

	public String getScreener();

	public void setScreener(String screener);
	
	public String getSpecies();

	public void setSpecies(String species);

	public String getPrincipalInvestigator();

	public void setPrincipalInvestigator(String principalInvestigator);	
	
}
