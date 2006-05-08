/*
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.11  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.10  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: Agent.java,v 1.13 2006-05-08 13:30:26 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.EvsTreeUtil;

import java.io.Serializable;
import java.util.*;

public class Agent extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 4259745453799404851L;

    private String name;
    private String conceptCode;
    private Long nscNumber;
    private String casNumber;    
    private Boolean isCMAPAgent;
    private String comments;
    private String source;
    private Set<BiologicalProcess> biologicalProcessCollection = new TreeSet<BiologicalProcess>();
    private Set<ChemicalClass> chemicalClassCollection = new TreeSet<ChemicalClass>();
    private Set<AgentTarget> agentTargetCollection = new TreeSet<AgentTarget>();

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @return Returns the EVS Preferred displayName
     */
    public String getEVSPreferredDescription()
    {
        return EvsTreeUtil.getEVSPreferedDescription(conceptCode);
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode)
    {
        this.conceptCode = conceptCode;
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
     * @return Returns the casNumber.
     */
    public String getCasNumber()
    {
        return casNumber;
    }

    /**
     * @param casNumber
     *            The casNumber to set.
     */
    public void setCasNumber(String casNumber)
    {
        this.casNumber = casNumber;
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
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
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
     * @return Returns the agentTargetCollection.
     */
    public Set<AgentTarget> getAgentTargetCollection() {            
      return agentTargetCollection;                    
    }

    /**
     * @param agentTargetCollection
     *            The agentTargetCollection to set.
     */
    public void setAgentTargetCollection(Set<AgentTarget> agentTargetCollection) {
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
    public Set<ChemicalClass> getChemicalClassCollection() {            
      return chemicalClassCollection;        
    }
    
    /**
     * @param chemicalClassCollection
     *            The chemicalClassCollection to set.
     */
    public void setChemicalClassCollection(Set<ChemicalClass> chemicalClassCollection) {
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
    public Set<BiologicalProcess> getBiologicalProcessCollection() {           
      return biologicalProcessCollection;                       
    }
   
    /**
     * @param biologicalProcessCollection
     *            The biologicalProcessCollection to set.
     */
    public void setBiologicalProcessCollection(Set<BiologicalProcess> biologicalProcessCollection) {
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getNscNumber();                
      return result;
    } 
    
    
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
