package gov.nih.nci.camod.biodbnet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for dbOrthoParams complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dbOrthoParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="input" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="output" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inputTaxon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outputTaxon" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "dbOrthoParams", propOrder = {

})
public class DbOrthoParams {

	@XmlElement(required = true)
	protected String input;
	@XmlElement(required = true)
	protected String output;
	@XmlElement(required = true)
	protected String inputTaxon;
	@XmlElement(required = true)
	protected String outputTaxon;
	@XmlElement(required = true)
	protected String inputValues;

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
	 * Gets the value of the inputTaxon property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInputTaxon() {
		return inputTaxon;
	}

	/**
	 * Sets the value of the inputTaxon property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInputTaxon(String value) {
		this.inputTaxon = value;
	}

	/**
	 * Gets the value of the outputTaxon property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutputTaxon() {
		return outputTaxon;
	}

	/**
	 * Sets the value of the outputTaxon property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutputTaxon(String value) {
		this.outputTaxon = value;
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
