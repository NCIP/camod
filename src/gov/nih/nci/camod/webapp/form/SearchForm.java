/*
 * LogonForm.java
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SearchForm extends BaseForm implements Serializable, SearchData {
    
    private static final long serialVersionUID = 3257045453799404851L;
    
	/**
	 * Default empty constructor
	 * @author nschroedl
	 *
	 */
	public SearchForm() {}
	
	protected String keyword;
	protected String piName;
	protected String modelDescriptor;
	protected String organ;
	protected String species;
	protected String chemicalDrug;
	protected String hormone;
	protected String growthFactor;
	protected String radiation;
	protected String viral;
	protected String surgery;
	protected String phenotype;
	protected String disease;
	protected String cellLine;
	
	public void setHormone(String hormone) {
		this.hormone = hormone;
	}

	public void setGrowthFactor(String growthFactor) {
		this.growthFactor = growthFactor;
	}

	public String getChemicalDrug() {
		return chemicalDrug;
	}

	public void setChemicalDrug(String chemicalDrug) {
		this.chemicalDrug = chemicalDrug;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getModelDescriptor() {
		return modelDescriptor;
	}

	public void setModelDescriptor(String modelDescriptor) {
		this.modelDescriptor = modelDescriptor;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getPiName() {
		return piName;
	}

	public void setPiName(String piName) {
		this.piName = piName;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getGrowthFactor() {
		return growthFactor;
	}

	public String getHormone() {
		return hormone;
	}

	public String getRadiation() {
		return radiation;
	}

	public void setRadiation(String radiation) {
		this.radiation = radiation;
	}

	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getViral() {
		return viral;
	}

	public void setViral(String viral) {
		this.viral = viral;
	}

	public String getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getCellLine() {
		return cellLine;
	}

	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}
}
