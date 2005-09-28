/**
 * @author dgeorge
 * 
 * $Id: AdministrationData.java,v 1.1 2005-09-28 13:58:44 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/27 19:17:08  georgeda
 * Refactor of CI managers
 *
 *
 */
package gov.nih.nci.camod.webapp.form.cibase;


/**
 Interface for most AdministrationData screens
 */
public interface AdministrationData  {

    public String getAdministrativeRoute();

    public void setAdministrativeRoute(String administrativeRoute);

    public String getOtherAdministrativeRoute();

    public void setOtherAdministrativeRoute(String otherAdministrativeRoute);
}
