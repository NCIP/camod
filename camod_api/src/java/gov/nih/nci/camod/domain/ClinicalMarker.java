

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Biological markers are objectively measurable phenotypic parameters (e.g., specific enzyme 
   * concentration, specific hormone concentration, specific gene phenotype, presence of biological 
   * substances) that characterize an organism's state of health or disease, likelihood of developing 
   * a disease, or response to a particular therapeutic intervention. 
   * 
   */

public  class ClinicalMarker 
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String value;
	   public String getValue(){
	      return value;
	   }
	   public void setValue(String value){
	      this.value = value;
	   }
	

	
	   
	   
	   
	      
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ClinicalMarker) {
				ClinicalMarker c =(ClinicalMarker)obj; 			 
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