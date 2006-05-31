

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Persons of interest to the application, either through being a submitter of cancer models, the principal 
   * investigator for a cancer model or someone who administers the application. 
   * 
   */

public  class Person 
    extends Party
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String lastName;
	   public String getLastName(){
	      return lastName;
	   }
	   public void setLastName(String lastName){
	      this.lastName = lastName;
	   }
	
	   
	   public String firstName;
	   public String getFirstName(){
	      return firstName;
	   }
	   public void setFirstName(String firstName){
	      this.firstName = firstName;
	   }
	
	   
	   public String middleName;
	   public String getMiddleName(){
	      return middleName;
	   }
	   public void setMiddleName(String middleName){
	      this.middleName = middleName;
	   }
	
	   
	   public String username;
	   public String getUsername(){
	      return username;
	   }
	   public void setUsername(String username){
	      this.username = username;
	   }
	
	   
	   public Boolean isPrincipalInvestigator;
	   public Boolean getIsPrincipalInvestigator(){
	      return isPrincipalInvestigator;
	   }
	   public void setIsPrincipalInvestigator(Boolean isPrincipalInvestigator){
	      this.isPrincipalInvestigator = isPrincipalInvestigator;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Person) {
				Person c =(Person)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}