/**
 * @author dgeorge
 * 
 * $Id: TransplantationData.java,v 1.3 2009-03-25 16:25:12 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/01/16 18:29:46  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/10/31 17:44:39  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.1  2007/07/31 12:02:03  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.9  2007/04/04 13:23:49  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.8  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
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
 * Transplantation interface
 */
public interface TransplantationData
{

    public String getDonorScientificName();

    public void setDonorScientificName(String donorScientificName);

    public String getOtherDonorScientificName();

    public void setOtherDonorScientificName(String otherDonorScientificName);

    public String getDonorEthinicityStrain();

    public void setDonorEthinicityStrain(String donorEthinicityStrain);

    public String getOtherDonorEthinicityStrain();

    public void setOtherDonorEthinicityStrain(String otherDonorEthinicityStrain);

    public String getName();

    public void setName(String name);

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

	public String getOtherSourceType();

	public void setOtherSourceType(String otherSourceType) ;

	public String getSourceType();

	public void setSourceType(String sourceType);

    public String getOrgan();

    public void setOrgan(String organ);

    public String getOrganTissueName();

    public void setOrganTissueName(String organTissueName);

    public String getOrganTissueCode();

    public void setOrganTissueCode(String organTissueCode);
    
	public String getConditioningRegimen();

	public void setConditioningRegimen(String conditioningRegimen);

	public String getOtherConditioningRegimen();

	public void setOtherConditioningRegimen(String otherConditioningRegimen);
	
	public String getComments();
	
	public void setComments(String comments);	
}
