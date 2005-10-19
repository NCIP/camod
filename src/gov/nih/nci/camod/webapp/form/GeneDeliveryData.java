/**
 * @author dgeorge
 * 
 * $Id: GeneDeliveryData.java,v 1.2 2005-10-19 18:07:56 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 21:20:24  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.AgeGenderData;
import gov.nih.nci.camod.webapp.form.cibase.TreatmentData;

/**
 * Gene delivery interface for CI screen
 */
public interface GeneDeliveryData  extends TreatmentData, AgeGenderData {

	public String getViralVector();
    
	public void setViralVector(String viralVector);
	
	public String getOtherViralVector();    
	
	public void setOtherViralVector(String otherViralVector);
    
	public String getGeneInVirus();
    
	public void setGeneInVirus(String geneInVirus);
	
	public String getOrgan();
	
	public void setOrgan( String organ );
	
	public String getOrganName();
	
	public void setOrganName( String organName );
	
	public String getOrganTissueName();
	
	public void setOrganTissueName( String organTissueName );
	
	public String getOrganTissueCode();
	
	public void setOrganTissueCode( String organTissueCode );	
    
}
