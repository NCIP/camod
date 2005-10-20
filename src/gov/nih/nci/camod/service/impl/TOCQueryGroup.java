/**
 *  @author dgeorge
 *  
 *  $Id: TOCQueryGroup.java,v 1.1 2005-10-20 19:28:58 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.5  2005/09/19 13:09:00  georgeda
 *  Slight change to interface
 *
 *  
 */
package gov.nih.nci.camod.service.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * CurationManager implementation.
 */
public class TOCQueryGroup {

    private String myDescription;
    private List myQueries = new ArrayList();

    public TOCQueryGroup() {
    }

    public void setDescription(String inDescription) {
        myDescription = inDescription;
    }

    public String getDescription() {
        return myDescription;
    }

    public List getQueries() {
        return myQueries;
    }

    public void addQuery(TOCQuery inTOCQuery) {
        myQueries.add(inTOCQuery);
    }
}
