/**
 * @author dgeorge
 * 
 * $Id: RadiationData.java,v 1.2 2005-09-28 13:58:47 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/27 19:17:09  georgeda
 * Refactor of CI managers
 *
 *
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;

/**
 * 
 * Same interface as RadiationData. Used for type checking
 *
 */
public interface RadiationData  extends NameData, DoseData, AgeGenderData, TreatmentData,
AdministrationData {

}
