

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Disease objects specify a disease name and ID; disease objects also provide access to: ontological 
   * relations to other diseases; clinical trial protocols treating the disease; and specific histologies 
   * associated with instances of the disease. 
   * 
   */

public  class Disease 
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
	
	   
	   public java.lang.Long name;
	   public  java.lang.Long getName(){
	      return name;
	   }
	   public void setName( java.lang.Long name){
	      this.name = name;
	   }
	
	   
	   public String conceptCode;
	   public String getConceptCode(){
	      return conceptCode;
	   }
	   public void setConceptCode(String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Disease) {
				Disease c =(Disease)obj; 			 
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