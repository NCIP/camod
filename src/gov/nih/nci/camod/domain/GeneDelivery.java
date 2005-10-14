/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GeneDelivery extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259385453799404851L;
    
    private String viralVector;
    private String viralVectorUnctrlVocab;
    private String geneInVirus;
    private Organ organ;
    private Treatment treatment;

    /**
     * @return Returns the treatment.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * @param treatment
     *            The treatment to set.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan() {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    /**
     * @return Returns the geneInVirus.
     */
    public String getGeneInVirus() {
        return geneInVirus;
    }

    /**
     * @param geneInVirus
     *            The geneInVirus to set.
     */
    public void setGeneInVirus(String geneInVirus) {
        this.geneInVirus = geneInVirus;
    }

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
     * @return Returns the viralVectorUnctrlVocab.
     */
    public String getViralVectorUnctrlVocab() {
        return viralVectorUnctrlVocab;
    }

    /**
     * @param viralVectorUnctrlVocab
     *            The viralVectorUnctrlVocab to set.
     */
    public void setViralVectorUnctrlVocab(String viralVectorUnctrlVocab) {
        this.viralVectorUnctrlVocab = viralVectorUnctrlVocab;
    }

  
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
       String result = super.toString() + " - ";      
       result += this.getViralVector() + " - " + this.getViralVectorUnctrlVocab() + " - " + this.getGeneInVirus();
       return result;
     }    
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
