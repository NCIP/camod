/*
 * LogonForm.java
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

public interface SearchData {

	public String getKeyword();

	public void setKeyword(String k);

	public void setPiName(String p);

	public String getPiName();

	public String getModelDescriptor();

	public void setModelDescriptor(String m);

	public String getOrgan();

	public void setOrgan(String o);

	public String getDisease();

	public void setDisease(String o);
	
	public String getSpecies();

	public void setSpecies(String s);
	
	public String getPhenotype();
	
	public void setPhenotype(String s);
	
	public String getCellLine();
	
	public void setCellLine(String s);
	
	
	// Carciogenic interventions
	public String getChemicalDrug();

	public void setChemicalDrug(String s);
	
	public String getGrowthFactor();

	public void setGrowthFactor(String s);
	
	public String getHormone();

	public void setHormone(String s);
	
	public String getRadiation();

	public void setRadiation(String s);
	
	public String getViral();

	public void setViral(String s);
	
	public String getSurgery();

	public void setSurgery(String s);
}
