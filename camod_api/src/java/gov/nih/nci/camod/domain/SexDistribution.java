

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Object indicated if the phenotype was observed in both sexes or only in one.
   */

public  class SexDistribution 
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public String type;
	   public String getType(){
	      return type;
	   }
	   public void setType(String type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SexDistribution) {
				SexDistribution c =(SexDistribution)obj; 			 
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