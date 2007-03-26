/**
 * @author dgeorge
 * 
 * $Id: XenograftData.java,v 1.8 2007-03-26 12:03:10 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/05/19 16:40:53  pandyas
 * Defect #249 - add other to species on the Xenograft screen
 *
 * Revision 1.6  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.5  2005/12/28 16:43:56  pandyas
 * removed harvest date - unused
 *
 * Revision 1.4  2005/11/28 22:49:58  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
 * Revision 1.3  2005/11/08 17:47:08  pandyas
 * modified Xenograft dropdown
 *
 * Revision 1.2  2005/09/29 18:31:59  georgeda
 * Removed mice age
 *
 * Revision 1.1  2005/09/28 21:20:25  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

/**
 * Xenograft interface
 */
public interface XenograftData
{

    public String getDonorScientificName();

    public void setDonorScientificName(String donorScientificName);

    public String getOtherDonorScientificName();

    public void setOtherDonorScientificName(String otherDonorScientificName);

    public String getDonorEthinicityStrain();

    public void setDonorEthinicityStrain(String donorEthinicityStrain);

    public String getOtherDonorEthinicityStrain();

    public void setOtherDonorEthinicityStrain(String otherDonorEthinicityStrain);

    public String getXenograftName();

    public void setXenograftName(String xenograftName);

    public String getAtccNumber();

    public void setAtccNumber(String atccNumber);

    public String getParentalCellLineName();

    public void setParentalCellLineName(String parentalCellLineName);

    public String getCellAmount();

    public void setCellAmount(String cellAmount);

    public String getModificationDescription();

    public void setModificationDescription(String modificationDescription);

    public String getGeneticManipulation();

    public void setGeneticManipulation(String geneticManipulation);

    public String getAdministrativeSite();

    public void setAdministrativeSite(String administrativeSite);

    public String getOtherAdministrativeSite();

    public void setOtherAdministrativeSite(String otherAdministrativeSite);

    public String getGrowthPeriod();

    public void setGrowthPeriod(String growthPeriod);

    public String getGraftType();

    public void setGraftType(String graftType);

    public String getOtherGraftType();

    public void setOtherGraftType(String otherGraftType);

    public String getOrgan();

    public void setOrgan(String organ);

    public String getOrganTissueName();

    public void setOrganTissueName(String organTissueName);

    public String getOrganTissueCode();

    public void setOrganTissueCode(String organTissueCode);
    
	public String getConditioningRegime();

	public void setConditioningRegime(String conditioningRegime);

	public String getOtherConditioningRegime();

	public void setOtherConditioningRegime(String otherConditioningRegime);
}
