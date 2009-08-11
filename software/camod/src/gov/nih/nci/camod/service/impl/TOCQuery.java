/**
 *  @author dgeorge
 *  
 *  $Id: TOCQuery.java,v 1.1 2005-10-20 19:28:58 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.5  2005/09/19 13:09:00  georgeda
 *  Slight change to interface
 *
 *  
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.webapp.form.SearchData;

import java.util.List;

/**
 * CurationManager implementation.
 */
public class TOCQuery {

    private String myDescription;
    private String myKey;
    private SearchData mySearchData;
    int mySize;
    List myResults;

    public TOCQuery(String inDescription, String inKey, SearchData inSearchData, int inSize) {
        myDescription = inDescription;
        myKey = inKey;
        mySearchData = inSearchData;
        mySize = inSize;
    }

    public String getDescription() {
        return myDescription;
    }

    public String getKey() {
        return myKey;
    }

    public int getSize() {
        return mySize;
    }

    public List getResults() throws Exception {
        if (myResults == null) {
            myResults = AnimalModelManagerSingleton.instance().search(mySearchData);
        }
        return myResults;
    }
}
