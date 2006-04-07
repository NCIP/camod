

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class ClinicalMarker 
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public java.lang.String nameUnctrlVocab;
	   public  java.lang.String getNameUnctrlVocab(){
	      return nameUnctrlVocab;
	   }
	   
	   public void setNameUnctrlVocab( java.lang.String nameUnctrlVocab){
	      this.nameUnctrlVocab = nameUnctrlVocab;
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
