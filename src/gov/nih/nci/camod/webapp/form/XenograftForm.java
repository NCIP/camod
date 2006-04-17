/**
 * 
 * $Id: XenograftForm.java,v 1.13 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2005/12/28 16:43:48  pandyas
 * removed harvest date - unused
 *
 * Revision 1.11  2005/11/28 22:49:58  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
 * Revision 1.10  2005/11/11 16:28:36  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class XenograftForm extends BaseForm implements Serializable, XenograftData {

    private static final long serialVersionUID = 3257125453799404851L;

    /**
     * Default empty constructor
     * 
     * @author rajputs
     */
    public XenograftForm() {
    }

    // This form does not include properties from the parent class of Xenograft,
    // AbstractCancerModel
    protected String xenograftName;
    protected String geneticManipulation;
    protected String modificationDescription;    
    protected String parentalCellLineName;    
    protected String atccNumber;
    protected String cellAmount;
    protected String growthPeriod;
    protected String graftType;
    protected String otherGraftType;
    protected String administrativeSite;
    protected String otherAdministrativeSite;
    
    protected String donorScientificName;
    protected String donorEthinicityStrain;
    protected String otherDonorEthinicityStrain;
    
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;    

    /**
     * @return Returns the donorScientificName.
     */
    public String getDonorScientificName() {
        return donorScientificName;
    }

    /**
     * @param donorScientificName
     *            The donorScientificName to set.
     */
    public void setDonorScientificName(String donorScientificName) {
        this.donorScientificName = donorScientificName;
    }

    /**
     * @return Returns the donorEthinicityStrain.
     */
    public String getDonorEthinicityStrain() {
        return donorEthinicityStrain;
    }

    /**
     * @param donorEthinicityStrain
     *            The donorEthinicityStrain to set.
     */
    public void setDonorEthinicityStrain(String donorEthinicityStrain) {
        this.donorEthinicityStrain = donorEthinicityStrain;
    }

    /**
     * @return Returns the otherDonorEthinicityStrain.
     */
    public String getOtherDonorEthinicityStrain() {
        return otherDonorEthinicityStrain;
    }

    /**
     * @param otherDonorEthinicityStrain
     *            The otherDonorEthinicityStrain to set.
     */
    public void setOtherDonorEthinicityStrain(String otherDonorEthinicityStrain) {
        this.otherDonorEthinicityStrain = otherDonorEthinicityStrain;
    }

    /**
     * @return Returns the xenograftName.
     */
    public String getXenograftName() {
        return xenograftName;
    }

    /**
     * @param xenograftName
     *            The xenograftName to set.
     */
    public void setXenograftName(String xenograftName) {
        this.xenograftName = xenograftName;
    }

    /**
     * @return Returns the atccNumber.
     */
    public String getAtccNumber() {
        return atccNumber;
    }

    /**
     * @param atccNumber
     *            The atccNumber to set.
     */
    public void setAtccNumber(String atccNumber) {
        this.atccNumber = atccNumber;
    }

    /**
     * @return Returns the parentalCellLineName.
     */
    public String getParentalCellLineName() {
        return parentalCellLineName;
    }

    /**
     * @param parentalCellLineName
     *            The parentalCellLineName to set.
     */
    public void setParentalCellLineName(String parentalCellLineName) {
        this.parentalCellLineName = parentalCellLineName;
    }

    /**
     * @return Returns the cellAmount.
     */
    public String getCellAmount() {
        return cellAmount;
    }

    /**
     * @param cellAmount
     *            The cellAmount to set.
     */
    public void setCellAmount(String cellAmount) {
        this.cellAmount = cellAmount;
    }

    /**
     * @return Returns the modificationDescription.
     */
    public String getModificationDescription() {
        return modificationDescription;
    }

    /**
     * @param modificationDescription
     *            The modificationDescription to set.
     */
    public void setModificationDescription(String modificationDescription) {
        this.modificationDescription = modificationDescription;
    }

    /**
     * @return Returns the geneticManipulation.
     */
    public String getGeneticManipulation() {
        return geneticManipulation;
    }

    /**
     * @param geneticManipulation
     *            The geneticManipulation to set.
     */
    public void setGeneticManipulation(String geneticManipulation) {
        this.geneticManipulation = geneticManipulation;
    }

    /**
     * @return Returns the administrativeSite.
     */
    public String getAdministrativeSite() {
        return administrativeSite;
    }

    /**
     * @param administrativeSite
     *            The administrativeSite to set.
     */
    public void setAdministrativeSite(String administrativeSite) {
        this.administrativeSite = administrativeSite;
    }
    /**
     * @return Returns the otherAdministrativeSite.
     */
    public String getOtherAdministrativeSite() {
        return otherAdministrativeSite;
    }

    /**
     * @param otherAdministrativeSite
     *            The otherAdministrativeSite to set.
     */
    public void setOtherAdministrativeSite(String otherAdministrativeSite) {
        this.otherAdministrativeSite = otherAdministrativeSite;
    }
    
    /**
     * @return Returns the graftType.
     */
    public String getGraftType() {
        return graftType;
    }

    /**
     * @param graftType
     *            The graftType to set.
     */
    public void setGraftType(String graftType) {
        this.graftType = graftType;
    }

    /**
     * @return Returns the otherGraftType.
     */
    public String getOtherGraftType() {
        return otherGraftType;
    }

    /**
     * @param otherGraftType
     *            The otherGraftType to set.
     */
    public void setOtherGraftType(String otherGraftType) {
        this.otherGraftType = otherGraftType;
    }
    /**
     * @return Returns the growthPeriod.
     */
    public String getGrowthPeriod() {
        return growthPeriod;
    }

    /**
     * @param growthPeriod
     *            The growthPeriod to set.
     */
    public void setGrowthPeriod(String growthPeriod) {
        this.growthPeriod = growthPeriod;
    }    
	/**
	 * @return Returns the organ.
	 */	
	public String getOrgan() {
		return organ;
	}
	/**
	 * @param organ The organ to set.
	 */	
	public void setOrgan( String organ ) {
		this.organ = organ;
	}
	/**
	 * @return Returns the organTissueName.
	 */	
	public String getOrganTissueName() {
		return organTissueName;
	}
	/**
	 * @param organTissueName The organTissueName to set.
	 */	
	public void setOrganTissueName( String organTissueName ) {
		this.organTissueName = organTissueName;
	}
	/**
	 * @return Returns the organTissueCode (concept code).
	 */	
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	/**
	 * @param organTissueCode The organTissueCode (concept code) to set .
	 */	
	public void setOrganTissueCode( String organTissueCode ) {
		this.organTissueCode = organTissueCode;
	}	    

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        xenograftName = null;
        atccNumber = null;
        parentalCellLineName = null;
        cellAmount = null;
        modificationDescription = null;
        geneticManipulation = null;
        administrativeSite = null;
        graftType = null;
        otherGraftType = null;
        donorScientificName = null;
        donorEthinicityStrain = null;
        otherDonorEthinicityStrain = null;
    }
    
    public void resetOrgan() {
        organ = null;
    }    

}
