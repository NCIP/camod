/**
 * $Id: TransInterferenceMethodManager.java,v 1.2 2006-10-18 18:09:25 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:14:18  pandyas
 * modified during development of caMOD 2.2 - various
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.TransientInterferenceMethod;

public interface TransInterferenceMethodManager
{

    public TransientInterferenceMethod getByConceptCode(String inConceptCode) throws Exception;


}
