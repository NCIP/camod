

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The method used for staining an image
   */

public  class StainingMethod 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.String nameUnctrlVocab;
	   public  java.lang.String getNameUnctrlVocab(){
	      return nameUnctrlVocab;
	   }
	   public void setNameUnctrlVocab( java.lang.String nameUnctrlVocab){
	      this.nameUnctrlVocab = nameUnctrlVocab;
	   }
	
	   
	   public java.lang.String conceptCode;
	   public  java.lang.String getConceptCode(){
	      return conceptCode;
	   }
	   public void setConceptCode( java.lang.String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof StainingMethod) {
				StainingMethod c =(StainingMethod)obj; 			 
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