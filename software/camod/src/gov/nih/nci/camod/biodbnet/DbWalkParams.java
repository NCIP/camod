/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.biodbnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for dbWalkParams complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dbWalkParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="dbPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxonId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputValues" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dbWalkParams", propOrder = {

})
public class DbWalkParams {

	@XmlElement(required = true)
	protected String dbPath;
	@XmlElement(required = true)
	protected String taxonId;
	@XmlElement(required = true)
	protected String inputValues;

	/**
	 * Gets the value of the dbPath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDbPath() {
		return dbPath;
	}

	/**
	 * Sets the value of the dbPath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDbPath(String value) {
		this.dbPath = value;
	}

	/**
	 * Gets the value of the taxonId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxonId() {
		return taxonId;
	}

	/**
	 * Sets the value of the taxonId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaxonId(String value) {
		this.taxonId = value;
	}

	/**
	 * Gets the value of the inputValues property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInputValues() {
		return inputValues;
	}

	/**
	 * Sets the value of the inputValues property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInputValues(String value) {
		this.inputValues = value;
	}

}
