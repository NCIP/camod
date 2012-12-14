package gov.nih.nci.camod.biodbnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for db2dbParams complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="db2dbParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="input" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxonId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputValues" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outputs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "db2dbParams", propOrder = {

})
public class Db2DbParams {

	@XmlElement(required = true)
	protected String input;
	@XmlElement(required = true)
	protected String taxonId;
	@XmlElement(required = true)
	protected String inputValues;
	@XmlElement(required = true)
	protected String outputs;

	/**
	 * Gets the value of the input property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Sets the value of the input property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInput(String value) {
		this.input = value;
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

	/**
	 * Gets the value of the outputs property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutputs() {
		return outputs;
	}

	/**
	 * Sets the value of the outputs property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutputs(String value) {
		this.outputs = value;
	}

}
