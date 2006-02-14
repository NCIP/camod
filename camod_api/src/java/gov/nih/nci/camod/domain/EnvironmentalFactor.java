

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Chemical, radiation, hormone treatment or other environmental factor that initiates or supports 
   * development of neoplasias. 
   * 
   */

public  class EnvironmentalFactor 
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String CASNumber;
	   public String getCASNumber(){
	      return CASNumber;
	   }
	   public void setCASNumber(String CASNumber){
	      this.CASNumber = CASNumber;
	   }
	
	   
	   public String nameUnctrlVocab;
	   public String getNameUnctrlVocab(){
	      return nameUnctrlVocab;
	   }
	   public void setNameUnctrlVocab(String nameUnctrlVocab){
	      this.nameUnctrlVocab = nameUnctrlVocab;
	   }
	
	   
	   public String typeUnctrlVocab;
	   public String getTypeUnctrlVocab(){
	      return typeUnctrlVocab;
	   }
	   public void setTypeUnctrlVocab(String typeUnctrlVocab){
	      this.typeUnctrlVocab = typeUnctrlVocab;
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