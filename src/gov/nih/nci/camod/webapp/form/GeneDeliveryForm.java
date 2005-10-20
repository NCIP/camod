/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneDeliveryForm extends BaseForm implements Serializable, GeneDeliveryData {

    private static final long serialVersionUID = 3257355453799404851L;	
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public GeneDeliveryForm() {}
	
	protected String viralVector;
	protected String otherViralVector;
	protected String geneInVirus;
	protected String regimen;
    protected String type;
    protected String ageAtTreatment;    
    protected String ageUnit;	

	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;

	
	/**
	 * @return Returns the viralVector.
	 */
	public String getViralVector() {
		return viralVector;
	}
	/**
	 * @param viralVector The viralVector to set.
	 */
	public void setViralVector(String viralVector) {
		this.viralVector = viralVector;
	}
	/**
	 * @return Returns the otherViralVector.
	 */
	public String getOtherViralVector() {
		return otherViralVector;
	}
	/**
	 * @param otherViralVector The otherViralVector to set.
	 */
	public void setOtherViralVector(String otherViralVector) {
		this.otherViralVector = otherViralVector;
	}	
	/**
	 * @return Returns the geneInVirus.
	 */
	public String getGeneInVirus() {
		return geneInVirus;
	}
	/**
	 * @param viralVector The viralVector to set.
	 */
	public void setGeneInVirus(String geneInVirus) {
		this.geneInVirus = geneInVirus;
	}	
	/**
	 * @return Returns the regimen.
	 */
	public String getRegimen() {
		return regimen;
	}
	/**
	 * @param regimen The regimen to set.
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the ageAtTreatment.
     */
    public String getAgeAtTreatment() {
        return ageAtTreatment;
    }

    /**
     * @param ageAtTreatment
     *            The ageAtTreatment to set.
     */
    public void setAgeAtTreatment(String ageAtTreatment) {
        this.ageAtTreatment = ageAtTreatment;
    }    
    /**
     * @return Returns the ageUnit.
     */    
    public String getAgeUnit() {
        return ageUnit;
    }
    /**
     * @param ageUnit
     *            The ageUnit to set.
     */
    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }    
	
	public String getOrgan() {
		return organ;
	}	
	public void setOrgan( String organ ) {
		this.organ = organ;
	}	

	public String getOrganTissueName() {
		return organTissueName;
	}
	
	public void setOrganTissueName( String organTissueName ) {
		this.organTissueName = organTissueName;
	}
	
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	
	public void setOrganTissueCode( String organTissueCode ) {
		this.organTissueCode = organTissueCode;
	}
	

	

	
}
