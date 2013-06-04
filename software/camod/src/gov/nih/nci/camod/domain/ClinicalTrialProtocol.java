package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.Collection;

public class ClinicalTrialProtocol  implements Serializable
{
    /**
    * An attribute to allow serialization of the domain objects
    */
    private static final long serialVersionUID = 1234567890L;

    /**
	* The current status of the clinical trial protocol object.
	**/
    
    public String currentStatus;
    /**
	* Retrieves the value of the currentStatus attribute
	* @return currentStatus
	**/

    public String getCurrentStatus(){
        return currentStatus;
    }

    /**
	* Sets the value of currentStatus attribute
	**/

    public void setCurrentStatus(String currentStatus){
        this.currentStatus = currentStatus;
    }

    /**
	* The name of the organization who lead the clinical trial.
	**/
    
    public String leadOrganizationName;
    /**
	* Retrieves the value of the leadOrganizationName attribute
	* @return leadOrganizationName
	**/

    public String getLeadOrganizationName(){
        return leadOrganizationName;
    }

    /**
	* Sets the value of leadOrganizationName attribute
	**/

    public void setLeadOrganizationName(String leadOrganizationName){
        this.leadOrganizationName = leadOrganizationName;
    }
    
    /**
	* The NCI PDQ identification code for the clinical trial.
	**/
    
    public String PDQIdentifier;
    /**
	* Retrieves the value of the PDQIdentifier attribute
	* @return PDQIdentifier
	**/

    public String getPDQIdentifier(){
        return PDQIdentifier;
    }

    /**
	* Sets the value of PDQIdentifier attribute
	**/

    public void setPDQIdentifier(String PDQIdentifier){
        this.PDQIdentifier = PDQIdentifier;
    }
    /**
	* The phase code for the clinical trial.
	**/
    
    public String phase;
    /**
	* Retrieves the value of the phase attribute
	* @return phase
	**/

    public String getPhase(){
        return phase;
    }

    /**
	* Sets the value of phase attribute
	**/

    public void setPhase(String phase){
        this.phase = phase;
    }
    /**
	* The principal investigator's name for the clinical trial.
	**/
    
    public String PIName;
    /**
	* Retrieves the value of the PIName attribute
	* @return PIName
	**/

    public String getPIName(){
        return PIName;
    }

    /**
	* Sets the value of PIName attribute
	**/

    public void setPIName(String PIName){
        this.PIName = PIName;
    }
    /**
	* The title of the clinical trial.
	**/
    
    public String title;
    /**
	* Retrieves the value of the title attribute
	* @return title
	**/

    public String getTitle(){
        return title;
    }

    /**
	* Sets the value of title attribute
	**/

    public void setTitle(String title){
        this.title = title;
    }
    
    public String NCTIdentifier;
    /**
	* Retrieves the value of the NCTIdentifier attribute
	* @return NCTIdentifier
	**/

    public String getNCTIdentifier(){
        return NCTIdentifier;
    }

    /**
	* Sets the value of phase attribute
	**/

    public void setNCTIdentifier(String NCTIdentifier){
        this.NCTIdentifier = NCTIdentifier;
    }
    
}
