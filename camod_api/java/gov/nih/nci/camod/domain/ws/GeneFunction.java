

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class GeneFunction 
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
	
	   
	   public String function;
	   public String getFunction(){
	      return function;
	   }
	   
	   public void setFunction(String function){
	      this.function = function;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.EngineeredGene engineeredGene;
			public gov.nih.nci.camod.domain.ws.EngineeredGene getEngineeredGene(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setEngineeredGene(gov.nih.nci.camod.domain.ws.EngineeredGene engineeredGene){
		this.engineeredGene = engineeredGene;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneFunction) {
				GeneFunction c =(GeneFunction)obj; 			 
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
