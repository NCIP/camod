/**
 * @author dgeorge
 * 
 * $Id: AgeGenderData.java,v 1.1 2005-09-28 13:58:45 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/27 19:17:08  georgeda
 * Refactor of CI managers
 *
 *
 */
package gov.nih.nci.camod.webapp.form.cibase;

/**
 * Interface for most AgeGenderData screens
 */
public interface AgeGenderData {

    public String getAgeUnit();

    public void setAgeUnit(String ageUnit);

    public String getAgeAtTreatment();

    public void setAgeAtTreatment(String ageAtTreatment);

    // Actually the gender
    public String getType();

    public void setType(String type);
}
