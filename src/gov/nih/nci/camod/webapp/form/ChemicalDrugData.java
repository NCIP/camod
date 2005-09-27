/*
 * Created on Aug 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;


/**
 * @author dgeorge
 * 
 * $Id: ChemicalDrugData.java,v 1.2 2005-09-27 19:17:07 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/27 16:52:17  georgeda
 * Initial revision
 *
 * 
 */
public interface ChemicalDrugData  extends CarcinogenicInterventionData {

    public String getNSCNumber();

    public void setNSCNumber(String NSCNumber);

    public String getCASNumber();

    public void setCASNumber(String CASNumber);
}
