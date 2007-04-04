/**
 * 
 * @author pandyas
 * 
 * $Id: TransientInterferenceData.java,v 1.4 2007-04-04 13:23:49 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.2  2006/10/23 14:17:26  pandyas
 * changed to conform to conceptCode format in all other classes
 *
 * Revision 1.1  2006/10/17 16:10:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.1  2006/05/03 20:05:25  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface TransientInterferenceData {

	public String getSource();

	public void setSource(String source);

	public String getOtherSource();

	public void setOtherSource(String otherSource);

	public String getType();

	public void setType(String type);

	public String getSequenceDirection();

	public void setSequenceDirection(String sequenceDirection);

	public String getTargetedRegion();

	public void setTargetedRegion(String targetedRegion);

	public String getConcentration();

	public void setConcentration(String concentration);

	public String getConcentrationUnit();

	public void setConcentrationUnit(String concentrationUnit);

	public String getDeliveryMethod();

	public void setDeliveryMethod(String deliveryMethod);

	public String getOtherDeliveryMethod();

	public void setOtherDeliveryMethod(String otherDeliveryMethod);

	public String getVisualLigand();

	public void setVisualLigand(String visualLigand);

	public String getOtherVisualLigand();

	public void setOtherVisualLigand(String otherVisualLigand);

	public String getConceptCode();

	public void setConceptCode(String conceptCode);

	public String getComments();

	public void setComments(String comments);
	
	public String getTargetSite();

	public void setTargetSite(String targetSite);
}
