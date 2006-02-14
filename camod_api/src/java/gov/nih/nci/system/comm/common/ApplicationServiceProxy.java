package gov.nih.nci.system.comm.common;

import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface ApplicationServiceProxy
{

	public abstract void setSearchCaseSensitivity(String sessionKey, boolean caseSensitivity);

	public abstract int getQueryRowCount(String sessionKey, Object criteria, String targetClassName) throws ApplicationException;

	public abstract List search(String sessionKey, String path, List objList) throws ApplicationException;

	public abstract List search(String sessionKey, String path, Object obj) throws ApplicationException;

	public abstract List search(String sessionKey, Class targetClass, List objList) throws ApplicationException;

	public abstract List search(String sessionKey, Class targetClass, Object obj) throws ApplicationException;

	public abstract List query(String sessionKey, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;

	public abstract List query(String sessionKey, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(String sessionKey, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException;

	public abstract List evsSearch(String sessionKey, EVSQuery evsCriterion) throws ApplicationException;

	public abstract String authenticate(String userId, String password) throws ApplicationException;

	public abstract void logOut(String sessionKey);

	/*
	public abstract Object createObject(String sessionKey, Object domainobject) throws ApplicationException;
	*/
	
	/*
	public abstract Object updateObject(String sessionKey, Object domainobject) throws ApplicationException;
	*/
	
	/*
	public abstract void removeObject(String sessionKey, Object domainobject) throws ApplicationException;
	*/
	
	/*
	public abstract List getObjects(String sessionKey, Object domainobject) throws ApplicationException;
	*/
	
}