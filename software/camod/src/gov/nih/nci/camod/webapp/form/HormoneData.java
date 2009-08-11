/**
 * @author dgeorge
 * 
 * $Id: HormoneData.java,v 1.2 2005-10-18 21:59:00 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 21:20:26  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;

/**
 * Interface for hormone data
 */
public interface HormoneData extends NameData, DoseData, AgeGenderData, TreatmentData,
AdministrationData {
}
