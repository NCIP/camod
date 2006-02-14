

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class GeneticAlteration 
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
	
	   
	   public String observation;
	   public String getObservation(){
	      return observation;
	   }
	   
	   public void setObservation(String observation){
	      this.observation = observation;
	   }
	
	   
	   public String methodOfObservation;
	   public String getMethodOfObservation(){
	      return methodOfObservation;
	   }
	   
	   public void setMethodOfObservation(String methodOfObservation){
	      this.methodOfObservation = methodOfObservation;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneticAlteration) {
				GeneticAlteration c =(GeneticAlteration)obj; 			 
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
