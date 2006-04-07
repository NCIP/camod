

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Chemical, radiation, hormone treatment or other environmental factor that initiates or supports 
   * development of neoplasias. 
   * 
   */

public  class EnvironmentalFactor 
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
	
	   
	   public String type;
	   public String getType(){
	      return type;
	   }
	   public void setType(String type){
	      this.type = type;
	   }
	
	   
	   public String typeUnctrlVocab;
	   public String getTypeUnctrlVocab(){
	      return typeUnctrlVocab;
	   }
	   public void setTypeUnctrlVocab(String typeUnctrlVocab){
	      this.typeUnctrlVocab = typeUnctrlVocab;
	   }
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String nameUnctrlVocab;
	   public String getNameUnctrlVocab(){
	      return nameUnctrlVocab;
	   }
	   public void setNameUnctrlVocab(String nameUnctrlVocab){
	      this.nameUnctrlVocab = nameUnctrlVocab;
	   }
	
	   
	   public String casNumber;
	   public String getCasNumber(){
	      return casNumber;
	   }
	   public void setCasNumber(String casNumber){
	      this.casNumber = casNumber;
	   }
	
	   
	   public java.lang.Float nscNumber;
	   public  java.lang.Float getNscNumber(){
	      return nscNumber;
	   }
	   public void setNscNumber( java.lang.Float nscNumber){
	      this.nscNumber = nscNumber;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof EnvironmentalFactor) {
				EnvironmentalFactor c =(EnvironmentalFactor)obj; 			 
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