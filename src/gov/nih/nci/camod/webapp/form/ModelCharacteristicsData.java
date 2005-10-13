/**
 *  @author dgeorge
 *  
 *  $Id: ModelCharacteristicsData.java,v 1.2 2005-10-13 20:48:00 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/06 13:36:08  georgeda
 *  Changed ModelCharacteristics interface to be consistent w/ the rest of the interfaces
 *
 *  Revision 1.2  2005/09/28 15:12:22  schroedn
 *  Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 *  Revision 1.1  2005/09/16 15:52:58  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.form;

/**
 * 
 * Interface describing fields for an animal model
 *
 */
public interface ModelCharacteristicsData {

    public String getModelDescriptor();

    public void setModelDescriptor(String a);

    public String getOtherEthinicityStrain();

    public void setOtherEthinicityStrain(String a);

    public String getCalendarReleaseDate();

    public void setCalendarReleaseDate(String a);

    public String getName();

    public void setName(String a);

    public String getSummary();

    public void setSummary(String a);

    public String getPrincipalInvestigator();

    public void setPrincipalInvestigator(String a);

    public String getIsToolMouse();

    public void setIsToolMouse(String a);

    public String getScientificName();

    public void setScientificName(String a);

    public String getEthinicityStrain();

    public void setEthinicityStrain(String a);

    public String getExperimentDesign();

    public void setExperimentDesign(String a);

    public String getDescription();

    public void setDescription(String a);

    public String getType();

    public void setType(String a);

    public String getBreedingNotes();

    public void setBreedingNotes(String a);

    public String getUrl();

    public void setUrl(String a);

    public String getReleaseDate();

    public void setReleaseDate(String a);
    
    public void setEthnicityStrainUnctrlVocab( String a );
    
    public String getEthnicityStrainUnctrlVocab();
}
