package gov.nih.nci.camod.biodbnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for dbFindParams complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dbFindParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="output" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "dbFindParams", propOrder = {

})
public class DbFindParams {

	@XmlElement(required = true)
	protected String output;
	@XmlElement(required = true)
	protected String taxonId;
	@XmlElement(required = true)
	protected String inputValues;

	/**
	 * Gets the value of the output property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * Sets the value of the output property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutput(String value) {
		this.output = value;
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
