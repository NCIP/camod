/**
 * @author dgeorge
 * 
 * $Id: GeneDeliveryData.java,v 1.1 2005-09-28 21:20:24 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.TreatmentData;

/**
 * Gene delivery interface for CI screen
 */
public interface GeneDeliveryData  extends TreatmentData {

	public String getOrganTissueName();
	
	public void setOrganTissueName( String o );
	
	public String getOrganTissueCode();
	
	public void setOrganTissueCode( String o );
	
	public String getOrgan();
	
	public void setOrgan( String o );
	

	public String getViralVector();
    
	public void setViralVector(String viralVector);
	
	public String getOtherViralVector();
    
	
	public void setOtherViralVector(String otherViralVector);
    
	public String getGeneInVirus();
    
	public void setGeneInVirus(String geneInVirus);
    
	public String getConceptCode();
    
	public void setConceptCode(String conceptCode);
}
