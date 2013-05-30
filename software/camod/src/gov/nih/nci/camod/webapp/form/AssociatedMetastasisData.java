/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * @author pandyas
 * 
 * $Id: AssociatedMetastasisData.java,v 1.2 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface AssociatedMetastasisData extends HistopathologyData
{

    public String getHistopathologyID();

    public void setHistopathologyID(String histopathologyID);

    public String getAbsCancerModelID();

    public void setAbsCancerModelID(String absCancerModelID);


}
