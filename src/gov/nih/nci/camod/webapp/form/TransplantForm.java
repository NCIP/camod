/**
 * 
 * $Id: TransplantForm.java,v 1.1 2008-01-16 18:29:46 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/10/31 17:59:39  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.1  2007/07/31 12:02:05  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.16  2007/04/04 13:23:49  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.15  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.14  2006/05/19 16:41:08  pandyas
 * Defect #249 - add other to species on the Xenograft screen
 *
 * Revision 1.13  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
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

public class TransplantForm extends BaseForm implements Serializable, TransplantData {

    private static final long serialVersionUID = 3257125453799404851L;

    /**
     * Default empty constructor
     * 
     * @author rajputs
     */
    public TransplantForm() {
    }

    // This form does not include properties from the parent class of Transplant,
    // AbstractCancerModel
    protected String name;
    protected String geneticManipulation;
    protected String modificationDescription;    
    protected String parentalCellLineName;    
    protected String atccNumber;
    protected String cellAmount;
    protected String growthPeriod;
    protected String sourceType;
    protected String otherSourceType;
    protected String administrativeSite;
    protected String otherAdministrativeSite;
    protected String conditioningRegimen;
    protected String otherConditioningRegimen;
    
    protected String donorScientificName;
    protected String otherDonorScientificName;
    protected String donorEthinicityStrain;
    protected String otherDonorEthinicityStrain;
    
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode; 
	protected String comments; 

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
     * @return Returns the otherDonorScientificName.
     */
    public String getOtherDonorScientificName() {
        return otherDonorScientificName;
    }

    /**
     * @param otherDonorScientificName
     *            The otherDonorScientificName to set.
     */
    public void setOtherDonorScientificName(String otherDonorScientificName) {
        this.otherDonorScientificName = otherDonorScientificName;
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
        name = null;
        atccNumber = null;
        parentalCellLineName = null;
        cellAmount = null;
        modificationDescription = null;
        geneticManipulation = null;
        administrativeSite = null;
        sourceType = null;
        otherSourceType = null;
        donorScientificName = null;
        donorEthinicityStrain = null;
        otherDonorEthinicityStrain = null;
    }
    
    public void resetOrgan() {
        organ = null;
    }

	public String getConditioningRegimen() {
		return conditioningRegimen;
	}

	public void setConditioningRegimen(String conditioningRegimen) {
		this.conditioningRegimen = conditioningRegimen;
	}

	public String getOtherConditioningRegimen() {
		return otherConditioningRegimen;
	}

	public void setOtherConditioningRegimen(String otherConditioningRegimen) {
		this.otherConditioningRegimen = otherConditioningRegimen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOtherSourceType() {
		return otherSourceType;
	}

	public void setOtherSourceType(String otherSourceType) {
		this.otherSourceType = otherSourceType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}    

}
