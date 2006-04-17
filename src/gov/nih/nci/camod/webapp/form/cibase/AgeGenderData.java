/**
 * @author dgeorge
 * 
 * $Id: AgeGenderData.java,v 1.2 2006-04-17 19:09:02 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 13:58:45  georgeda
 * More work on manager interface for CI
 *
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

    public String getAgeAtTreatmentUnit();

    public void setAgeAtTreatmentUnit(String ageAtTreatmentUnit);

    public String getAgeAtTreatment();

    public void setAgeAtTreatment(String ageAtTreatment);

    // Actually the gender
    public String getType();

    public void setType(String type);
}
