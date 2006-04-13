

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Data of microarray experiments generated from the animal model.
   */

public  class MicroArrayData 
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
	
	   
	   public String experimentName;
	   public String getExperimentName(){
	      return experimentName;
	   }
	   public void setExperimentName(String experimentName){
	      this.experimentName = experimentName;
	   }
	
	   
	   public Long experimentId;
	   public Long getExperimentId(){
	      return experimentId;
	   }
	   public void setExperimentId(Long experimentId){
	      this.experimentId = experimentId;
	   }
	
	   
	   public String otherLocationURL;
	   public String getOtherLocationURL(){
	      return otherLocationURL;
	   }
	   public void setOtherLocationURL(String otherLocationURL){
	      this.otherLocationURL = otherLocationURL;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof MicroArrayData) {
				MicroArrayData c =(MicroArrayData)obj; 			 
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