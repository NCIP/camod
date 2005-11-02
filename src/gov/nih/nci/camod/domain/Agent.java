/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Agent extends EnvironmentalFactor {

    private static final long serialVersionUID = 4259745453799404851L;

    private Long nscNumber;
    private Boolean isCMAPAgent;
    private String evsId;
    private String comments;
    private String source;
    private List biologicalProcessCollection = new ArrayList();
    private List chemicalClassCollection = new ArrayList();
    private List agentTargetCollection = new ArrayList();

    /**
     * @return Returns the agentTargetCollection.
     */
    public List getAgentTargetCollection() {      
      if (agentTargetCollection != null) Collections.sort(agentTargetCollection);
      return agentTargetCollection;                    
    }

    /**
     * @param agentTargetCollection
     *            The agentTargetCollection to set.
     */
    public void setAgentTargetCollection(List agentTargetCollection) {
        this.agentTargetCollection = agentTargetCollection; 
    }

    /**
     * @param agentTarget
     *            The agentTarget to add.
     */
    public void addAgentTarget(AgentTarget agentTarget) {
        agentTarget.getAgentCollection().add(this);
        agentTargetCollection.add(agentTarget);
    }

    /**
     * @return Returns the chemicalClassCollection.
     */
    public List getChemicalClassCollection() {      
      if (chemicalClassCollection != null) Collections.sort(chemicalClassCollection);
      return chemicalClassCollection;        
    }

    /**
     * @param chemicalClassCollection
     *            The chemicalClassCollection to set.
     */
    public void setChemicalClassCollection(List chemicalClassCollection) {
        this.chemicalClassCollection = chemicalClassCollection;
    }

    /**
     * @param chemicalClass
     *            The chemicalClass to add.
     */
    public void addChemicalClass(ChemicalClass chemicalClass) {
        chemicalClass.getAgentCollection().add(this);
        chemicalClassCollection.add(chemicalClass);
    }

    /**
     * @return Returns the biologicalProcessCollection.
     */
    public List getBiologicalProcessCollection() {     
      if (biologicalProcessCollection != null) Collections.sort(biologicalProcessCollection);
      return biologicalProcessCollection;                       
    }

    /**
     * @param biologicalProcessCollection
     *            The biologicalProcessCollection to set.
     */
    public void setBiologicalProcessCollection(List biologicalProcessCollection) {
        this.biologicalProcessCollection = biologicalProcessCollection;
    }

    /**
     * @param biologicalProcess
     *            The biologicalProcess to add.
     */
    public void addBiologicalProcess(BiologicalProcess biologicalProcess) {
        biologicalProcess.getAgentCollection().add(this);
        biologicalProcessCollection.add(biologicalProcess);
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the evsId.
     */
    public String getEvsId() {
        return evsId;
    }

    /**
     * @param evsId
     *            The evsId to set.
     */
    public void setEvsId(String evsId) {
        this.evsId = evsId;
    }

    /**
     * @return Returns the isCMAPAgent.
     */
    public Boolean getIsCMAPAgent() {
        return isCMAPAgent;
    }

    /**
     * @param isCMAPAgent
     *            The isCMAPAgent to set.
     */
    public void setIsCMAPAgent(Boolean isCMAPAgent) {
        this.isCMAPAgent = isCMAPAgent;
    }

    /**
     * @return Returns the nscNumber.
     */
    public Long getNscNumber() {
        return nscNumber;
    }

    /**
     * @param nscNumber
     *            The nscNumber to set.
     */
    public void setNscNumber(Long nscNumber) {
        this.nscNumber = nscNumber;
    }

    /**
     * @return Returns the source.
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     *            The source to set.
     */
    public void setSource(String source) {
        this.source = source;
    }
  

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getEvsId();                
      return result;
    }       
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
