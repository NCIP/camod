

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Availability 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public Date enteredDate;
	   public Date getEnteredDate(){
	      return enteredDate;
	   }
	   
	   public void setEnteredDate(Date enteredDate){
	      this.enteredDate = enteredDate;
	   }
	
	   
	   public String visibleTo;
	   public String getVisibleTo(){
	      return visibleTo;
	   }
	   
	   public void setVisibleTo(String visibleTo){
	      this.visibleTo = visibleTo;
	   }
	
	   
	   public Date modifiedDate;
	   public Date getModifiedDate(){
	      return modifiedDate;
	   }
	   
	   public void setModifiedDate(Date modifiedDate){
	      this.modifiedDate = modifiedDate;
	   }
	
	   
	   public Date releaseDate;
	   public Date getReleaseDate(){
	      return releaseDate;
	   }
	   
	   public void setReleaseDate(Date releaseDate){
	      this.releaseDate = releaseDate;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Availability) {
				Availability c =(Availability)obj; 			 
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
