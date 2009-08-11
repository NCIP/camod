/**
 * @author dgeorge
 * 
 * $Id: GrowthFactorData.java,v 1.2 2005-10-19 19:26:06 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 21:20:23  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;

/**
 * Growth factor interface for CI screen
 */
public interface GrowthFactorData  extends NameData, DoseData, AgeGenderData, TreatmentData, AdministrationData {
}
