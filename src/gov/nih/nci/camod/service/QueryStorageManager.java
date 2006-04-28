/**
 * @author schroedlni
 * 
 * $Id: QueryStorageManager.java,v 1.1 2006-04-28 19:13:01 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.webapp.form.SearchData;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.util.List;
import java.util.Set;

public interface QueryStorageManager
{
     public void saveQuery(SavedQuery inQuery ) throws Exception;
     
     public long addQueryToHistory( SavedQuery inQuery ) throws Exception;
     
     public Set<SavedQueryAttribute> buildCriteriaList( SearchData searchData ) throws Exception;
     
     public SearchForm buildSearchData( Set<SavedQueryAttribute> sqaList, SearchForm sData ) throws Exception;
      
     public SavedQuery get( String id ) throws Exception;
     
     public List getAllByUsername( String userName ) throws Exception;
     
     public List getSavedQueriesByUsername( String userName ) throws Exception;
     
     public void update( SavedQuery inQuery ) throws Exception;
     
     public void remove( String id ) throws Exception;
}
