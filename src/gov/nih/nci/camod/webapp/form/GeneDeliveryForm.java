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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GeneDeliveryForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 3257275453799404851L;

    /**
     * Default empty constructor
     * 
     * @author rajputs
     * 
     * TODO To change the template for this generated type comment go to Window -
     * Preferences - Java - Code Style - Code Templates
     */
    public GeneDeliveryForm() {
    }

    protected String viralVector;
    protected String otherViralVector;
    protected String geneInVirus;
    protected String regimen;
    protected String conceptCode;

    /**
     * @return Returns the viralVector.
     */
    public String getViralVector() {
        return viralVector;
    }

    /**
     * @param viralVector
     *            The viralVector to set.
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
     * @param otherViralVector
     *            The otherViralVector to set.
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
     * @param viralVector
     *            The viralVector to set.
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
     * @param regimen
     *            The regimen to set.
     */
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode() {
        return conceptCode;
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }
}
