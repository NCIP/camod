/*
 * Created on Aug 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;


/**
 * @author dgeorge
 * 
 * $Id: ChemicalDrugData.java,v 1.3 2005-09-28 13:58:46 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/09/27 19:17:07  georgeda
 * Refactor of CI managers
 *
 * Revision 1.1  2005/09/27 16:52:17  georgeda
 * Initial revision
 *
 * 
 */

public interface ChemicalDrugData  extends NameData, DoseData, AgeGenderData, TreatmentData,
AdministrationData {

    public String getNSCNumber();

    public void setNSCNumber(String NSCNumber);

    public String getCASNumber();

    public void setCASNumber(String CASNumber);
}
