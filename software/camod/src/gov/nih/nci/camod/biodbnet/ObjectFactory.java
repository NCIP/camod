/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.biodbnet;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.biodbnet.ws.client package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.biodbnet.ws.client
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link DbOrthoParams }
	 * 
	 */
	public DbOrthoParams createDbOrthoParams() {
		return new DbOrthoParams();
	}

	/**
	 * Create an instance of {@link DbWalkParams }
	 * 
	 */
	public DbWalkParams createDbWalkParams() {
		return new DbWalkParams();
	}

	/**
	 * Create an instance of {@link DbReportParams }
	 * 
	 */
	public DbReportParams createDbReportParams() {
		return new DbReportParams();
	}

	/**
	 * Create an instance of {@link Db2DbParams }
	 * 
	 */
	public Db2DbParams createDb2DbParams() {
		return new Db2DbParams();
	}

	/**
	 * Create an instance of {@link DbFindParams }
	 * 
	 */
	public DbFindParams createDbFindParams() {
		return new DbFindParams();
	}

}
