/**
 * 
 * $Id: XenograftForm.java,v 1.11 2005-11-28 22:49:58 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
     * 
     * TODO To change the template for this generated type comment go to Window -
     * Preferences - Java - Code Style - Code Templates
     */
    public XenograftForm() {
    }

    // This form does not include properties from the parent class of Xenograft,
    // AbstractCancerModel
    protected String name;
    protected String ATCCNumber;
    protected String parentalCellLineName;
    protected String cellAmount;
    protected String harvestDate;
    protected String modificationDescription;
    protected String geneticManipulation;
    protected String administrativeSite;
    protected String otherAdministrativeSite;    
    protected String graftType;
    protected String otherGraftType;

    protected String hostScientificName;
    protected String hostEthinicityStrain;
    protected String otherHostEthinicityStrain;
    
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;    

    /**
     * @return Returns the name.
     */
    public String getHostScientificName() {
        return hostScientificName;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setHostScientificName(String name) {
        this.hostScientificName = name;
    }

    /**
     * @return Returns the name.
     */
    public String getHostEthinicityStrain() {
        return hostEthinicityStrain;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setHostEthinicityStrain(String name) {
        this.hostEthinicityStrain = name;
    }

    /**
     * @return Returns the name.
     */
    public String getOtherHostEthinicityStrain() {
        return otherHostEthinicityStrain;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setOtherHostEthinicityStrain(String name) {
        this.otherHostEthinicityStrain = name;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the ATCCNumber.
     */
    public String getATCCNumber() {
        return ATCCNumber;
    }

    /**
     * @param ATCCNumber
     *            The ATCCNumber to set.
     */
    public void setATCCNumber(String ATCCNumber) {
        this.ATCCNumber = ATCCNumber;
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
     * @return Returns the harvestDate.
     */
    public String getHarvestDate() {
        return harvestDate;
    }

    /**
     * @param harvestDate
     *            The harvestDate to set.
     */
    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
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
        name = null;
        ATCCNumber = null;
        parentalCellLineName = null;
        cellAmount = null;
        harvestDate = null;
        modificationDescription = null;
        geneticManipulation = null;
        administrativeSite = null;
        graftType = null;
        otherGraftType = null;
        hostScientificName = null;
        hostEthinicityStrain = null;
        otherHostEthinicityStrain = null;
    }

}
