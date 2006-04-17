/**
 *  @author dgeorge
 *  
 *  $Id: ModelCharacteristicsData.java,v 1.4 2006-04-17 19:09:19 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.3  2005/10/24 13:28:30  georgeda
 *  Cleanup changes
 *
 *  Revision 1.2  2005/10/13 20:48:00  georgeda
 *  Correctly handle the PI
 *
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

    public void setModelDescriptor(String modelDescriptor);

    public String getOtherEthnicityStrain();

    public void setOtherEthnicityStrain(String otherEthnicityStrain);

    public String getCalendarReleaseDate();

    public void setCalendarReleaseDate(String calendarReleaseDate);
    
    public String getName();

    public void setName(String name);

    public String getSummary();

    public void setSummary(String summary);

    public String getPrincipalInvestigator();

    public void setPrincipalInvestigator(String principalInvestigator);

    public String getIsToolMouse();

    public void setIsToolMouse(String isToolMouse);

    public String getScientificName();

    public void setScientificName(String scientificName);
    
    public String getOtherScientificName();

    public void setOtherScientificName(String otherScientificName);

    public String getEthinicityStrain();

    public void setEthinicityStrain(String ethinicityStrain);

    public String getExperimentDesign();

    public void setExperimentDesign(String experimentDesign);

    public String getDescription();

    public void setDescription(String description);

    public String getType();

    public void setType(String type);

    public String getBreedingNotes();

    public void setBreedingNotes(String breedingNotes);

    public String getUrl();

    public void setUrl(String url);

    public String getReleaseDate();

    public void setReleaseDate(String releaseDate);
    

}
