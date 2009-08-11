/**
 *  @author dgeorge
 *  
 *  $Id: TOCQueryGroup.java,v 1.2 2006-05-08 13:34:07 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/20 19:28:58  georgeda
 *  Added TOC functionality
 *
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
    private List<TOCQuery> myQueries = new ArrayList<TOCQuery>();

    public TOCQueryGroup() {
    }

    public void setDescription(String inDescription) {
        myDescription = inDescription;
    }

    public String getDescription() {
        return myDescription;
    }

    public List<TOCQuery> getQueries() {
        return myQueries;
    }

    public void addQuery(TOCQuery inTOCQuery) {
        myQueries.add(inTOCQuery);
    }
}
