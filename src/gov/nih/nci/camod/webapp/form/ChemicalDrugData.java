/**
 * @author dgeorge
 * 
 * $Id: ChemicalDrugData.java,v 1.5 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/10/20 20:31:06  pandyas
 * moved avadocs
 *
 * Revision 1.3  2005/09/28 13:58:46  georgeda
 * More work on manager interface for CI
 *
 * Revision 1.2  2005/09/27 19:17:07  georgeda
 * Refactor of CI managers
 *
 * Revision 1.1  2005/09/27 16:52:17  georgeda
 * Initial revision
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;


public interface ChemicalDrugData  extends NameData, DoseData, AgeGenderData, TreatmentData,
AdministrationData {

    public String getNscNumber();

    public void setNscNumber(String NscNumber);

    public String getCasNumber();

    public void setCasNumber(String casNumber);
}
