/**
 * @author dgeorge
 * 
 * $Id: DoseData.java,v 1.1 2005-09-28 13:58:44 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/27 19:17:08  georgeda
 * Refactor of CI managers
 *
 *
 */
package gov.nih.nci.camod.webapp.form.cibase;


/**
 Interface for most CarcinogenicIntervention screens
 */
public interface DoseData  {

    public String getDoseUnit();

    public void setDoseUnit(String d);

    public String getDosage();

    public void setDosage(String dosage);
}
