package gov.nih.nci.system.comm.server;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.comm.common.ApplicationServiceProxy;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationServiceServerImpl implements ApplicationServiceProxy
{

	private ApplicationService applicationService;
	private SecurityEnabler securityEnabler;

	/**
	 * Default Constructor it takes in 
	 */
	public ApplicationServiceServerImpl()
	{
		securityEnabler = new SecurityEnabler(Constant.APPLICATION_NAME);
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.APPLICATION_SERVICE_FILE_NAME);
		applicationService = (ApplicationService) ctx.getBean(Constant.APPLICATION_SERVICE);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#authenticate(java.lang.String, java.lang.String)
	 */
	public String authenticate(String userId, String password) throws ApplicationException
	{
		return securityEnabler.authenticate(userId, password);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#logOut(java.lang.String)
	 */
	public void logOut(String sessionKey)
	{
		securityEnabler.logOut(sessionKey);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(java.lang.String, java.lang.String, java.util.List)
	 */
	public List search(String sessionKey, String path, List objList) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = new String(path);
			if (objList.size() != 0)
				newPath = newPath.concat("," + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return applicationService.search(path, objList);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public List search(String sessionKey, String path, Object obj) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = new String(path);
			if (obj != null)
				newPath = newPath.concat("," + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return applicationService.search(path, obj);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(java.lang.String, java.lang.Class, java.util.List)
	 */
	public List search(String sessionKey, Class targetClass, List objList) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = new String(targetClass.getName());
			if (objList.size() != 0)
				newPath = newPath.concat("," + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return applicationService.search(targetClass, objList);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(java.lang.String, java.lang.Class, java.lang.Object)
	 */
	public List search(String sessionKey, Class targetClass, Object obj) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = new String(targetClass.getName());
			if (obj != null)
				newPath = newPath.concat("," + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return applicationService.search(targetClass, obj);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(java.lang.String, java.lang.Object, int, int, java.lang.String)
	 */
	public List query(String sessionKey, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		List list = applicationService.query(criteria, firstRow, resultsPerQuery, targetClassName);

		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat("," + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return list;

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(java.lang.String, java.lang.Object, java.lang.String)
	 */
	public List query(String sessionKey, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException
	{
		List list = applicationService.query(detachedCriteria, targetClassName);
		
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat("," + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return list;

	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(java.lang.String, gov.nih.nci.common.util.HQLCriteria, java.lang.String)
	 */
	public List query(String sessionKey, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException
	{
		List list = applicationService.query(hqlCriteria, targetClassName);
		
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat("," + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(sessionKey,domainObjectName, "READ"))
					throw new ApplicationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}

		return list;

	}	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#setSearchCaseSensitivity(java.lang.String, boolean)
	 */
	public void setSearchCaseSensitivity(String sessionKey, boolean caseSensitivity)
	{
		applicationService.setSearchCaseSensitivity(caseSensitivity);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getQueryRowCount(java.lang.String, java.lang.Object, java.lang.String)
	 */
	public int getQueryRowCount(String sessionKey, Object criteria, String targetClassName) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (!securityEnabler.hasAuthorization(sessionKey,targetClassName, "READ"))
				throw new ApplicationException("User does not have privilege to perform a READ on " + targetClassName+ " object");
		}
		
		return applicationService.getQueryRowCount(criteria, targetClassName);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#evsSearch(java.lang.String, gov.nih.nci.evs.query.EVSQuery)
	 */
	public List evsSearch(String sessionKey, EVSQuery evsCriterion) throws ApplicationException
	{
		
		List list = applicationService.evsSearch(evsCriterion);
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String returnObjectName = "";
			if (list.size() != 0)
				returnObjectName = list.get(0).getClass().getName();
			if (!securityEnabler.hasAuthorization(sessionKey,returnObjectName, "READ"))
				throw new ApplicationException("User does not have privilege to perform a READ on " + returnObjectName+ " object");
		}

		return list;

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#createObject(java.lang.String, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(String sessionKey, Object domainobject) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{

			String domainObjectName = domainobject.getClass().getName();
			try
			{
				if (!securityEnabler.hasAuthorization(sessionKey, domainObjectName, "CREATE"))
				{
					throw new ApplicationException("User does not have privilege to CREATE " + domainObjectName + " object");
				}
			}
			catch (ApplicationException e)
			{
				throw new ApplicationException(e.getMessage());
			}
		}

		return applicationService.createObject(domainobject);

	}
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#updateObject(java.lang.String, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(String sessionKey, Object domainobject) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			try
			{
				String domainObjectName = domainobject.getClass().getName();
				if (!securityEnabler.hasAuthorization(sessionKey, domainObjectName, "UPDATE"))
				{
					throw new ApplicationException("User does not have privilege to CREATE " + domainObjectName + " object");
				}
			}
			catch (ApplicationException e)
			{
				throw new ApplicationException(e.getMessage());
			}
		}

		return applicationService.updateObject(domainobject);

	}
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#removeObject(java.lang.String, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(String sessionKey, Object domainobject) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			try
			{
				String domainObjectName = domainobject.getClass().getName();
				if (!securityEnabler.hasAuthorization(sessionKey, domainObjectName, "DELETE"))
				{
					throw new ApplicationException("User does not have privilege to DELETE " + domainObjectName + " object");
				}
			}
			catch (ApplicationException e)
			{
				throw new ApplicationException(e.getMessage());
			}
		}

		applicationService.removeObject(domainobject);

	}
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getObjects(java.lang.String, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(String sessionKey, Object domainobject) throws ApplicationException
	{

		if (securityEnabler.getSecurityLevel() > 0)
		{
			try
			{
				String domainObjectName = domainobject.getClass().getName();
				if (!securityEnabler.hasAuthorization(sessionKey, domainObjectName, "READ"))
				{
					throw new ApplicationException("User does not have privilege to CREATE " + domainObjectName + " object");
				}
			}
			catch (ApplicationException e)
			{
				throw new ApplicationException(e.getMessage());
			}
		}

		return applicationService.getObjects(domainobject);

	}
	*/

}