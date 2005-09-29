/**
 * @author dgeorge
 * 
 * $Id: XenograftData.java,v 1.2 2005-09-29 18:31:59 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/28 21:20:25  georgeda
 * Finished up converting to new manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

/**
 * Xenograft interface
 */
public interface XenograftData {

    public String getHostScientificName();

    public void setHostScientificName(String name);

    public String getHostEthinicityStrain();

    public void setHostEthinicityStrain(String name);

    public String getOtherHostEthinicityStrain();

    public void setOtherHostEthinicityStrain(String name);

    public String getName();

    public void setName(String name);

    public String getATCCNumber();

    public void setATCCNumber(String ATCCNumber);

    public String getParentalCellLineName();

    public void setParentalCellLineName(String parentalCellLineName);

    public String getCellAmount();

    public void setCellAmount(String cellAmount);

    public String getHarvestDate();

    public void setHarvestDate(String harvestDate);

    public String getModificationDescription();

    public void setModificationDescription(String modificationDescription);

    public String getGeneticManipulation();

    public void setGeneticManipulation(String geneticManipulation);

    public String getAdministrativeSite();

    public void setAdministrativeSite(String administrativeSite);

    public String getGraftType();

    public void setGraftType(String graftType);

    public String getOtherGraftType();

    public void setOtherGraftType(String otherGraftType);
}
