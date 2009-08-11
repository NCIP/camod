/**
 * @author dgeorge
 * 
 * $Id: TreatmentData.java,v 1.2 2005-09-28 21:21:56 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 13:58:44  georgeda
 * More work on manager interface for CI
 *
 * Revision 1.1  2005/09/27 19:17:08  georgeda
 * Refactor of CI managers
 *
 *
 */
package gov.nih.nci.camod.webapp.form.cibase;

/**
 * Sub interface TreatmentData for therapy screens
 */
public interface TreatmentData  {

    public String getRegimen();

    public void setRegimen(String regimen);
}
