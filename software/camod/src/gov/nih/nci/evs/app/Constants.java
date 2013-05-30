/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.evs.app;

public class Constants {

    /**
     * The namespace to fetch the concepts from
     */
    public static final String NCI_SCHEMA = "NCI_Thesaurus";
    
    /**
     * The namespace to fetch the concepts from
     */
    public static final String ZEBRAFISH_SCHEMA = "Zebrafish"; 
    
    public static final String ERROR_NO_MATCH_FOUND = "No match found.";
    public static final String ERROR_NO_MATCH_FOUND_TRY_OTHER_ALGORITHMS = "No match found. Please try 'Begins With' or 'Contains' search instead.";
    
    public static final String CONTAIN_SEARCH_ALGORITHM = "contains";// "literalSubString";//"subString"; 
	public static final String EXACT_SEARCH_ALGORITHM = "exactMatch";// "literalSubString";//"subString";
	public static final String STARTWITH_SEARCH_ALGORITHM = "startsWith";// "literalSubString";//"subString";
	   
}
