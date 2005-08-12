/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneFunction extends BaseObject implements Serializable {
	private Long id;
	private String function;
	private EngineeredGene engineeredGene;
	
	/**
	 * @return Returns the engineeredGene.
	 */
	public EngineeredGene getEngineeredGene() {
		return engineeredGene;
	}
	/**
	 * @param engineeredGene The engineeredGene to set.
	 */
	public void setEngineeredGene(EngineeredGene engineeredGene) {
		this.engineeredGene = engineeredGene;
	}
	/**
	 * @return Returns the function.
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function The function to set.
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof GeneFunction)) {
			return false;
		}
		GeneFunction rhs = (GeneFunction) object;
		return new EqualsBuilder().append(
				this.engineeredGene, rhs.engineeredGene).append(this.function,
				rhs.function).append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1837155983, 150113011).append(this.engineeredGene).append(
				this.function).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"function", this.function).append("engineeredGene",
				this.engineeredGene).toString();
	}
}
