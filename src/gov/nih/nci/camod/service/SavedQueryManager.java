/**
 * @author schroedlni
 * 
 * $Id: SavedQueryManager.java,v 1.1 2006-05-10 14:14:24 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/28 19:13:01  schroedn
 * Defect #238, 261
 * Search Result Columns and Saving Queries managers
 *
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.webapp.form.SearchData;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.util.List;
import java.util.Set;

/**
 * SavedQueryManager. Creates/saves/updates the
 * ResultSettings based on the specific interface passed in
 */
public interface SavedQueryManager
{
     public void save(SavedQuery inSavedQuery ) throws Exception;
          
     public Set<SavedQueryAttribute> buildCriteriaList( SearchData inSearchData ) throws Exception;
     
     public void buildSearchData( Set<SavedQueryAttribute> inSavedQueryAttribute, SearchForm inSearchForm ) throws Exception;
      
     public SavedQuery get( String id ) throws Exception;
     
     public List getAllByUsername( String userName ) throws Exception;
     
     public List getSavedQueriesByUsername( String userName ) throws Exception;
     
     public void update( SavedQuery inSavedQuery ) throws Exception;
     
     public void remove( String id ) throws Exception;
}
