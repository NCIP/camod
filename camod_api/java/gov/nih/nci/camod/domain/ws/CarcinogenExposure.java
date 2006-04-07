

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class CarcinogenExposure 
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.EnvironmentalFactor environmentalFactor;
			public gov.nih.nci.camod.domain.ws.EnvironmentalFactor getEnvironmentalFactor(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setEnvironmentalFactor(gov.nih.nci.camod.domain.ws.EnvironmentalFactor environmentalFactor){
		this.environmentalFactor = environmentalFactor;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Treatment treatment;
			public gov.nih.nci.camod.domain.ws.Treatment getTreatment(){
			  return treatment;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.ws.Treatment treatment){
		this.treatment = treatment;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CarcinogenExposure) {
				CarcinogenExposure c =(CarcinogenExposure)obj; 			 
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
