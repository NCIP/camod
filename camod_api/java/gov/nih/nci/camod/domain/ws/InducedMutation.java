

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class InducedMutation 
    extends EngineeredGene
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String geneId;
	   public String getGeneId(){
	      return geneId;
	   }
	   
	   public void setGeneId(String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public String description;
	   public String getDescription(){
	      return description;
	   }
	   
	   public void setDescription(String description){
	      this.description = description;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.EnvironmentalFactor environmentalFactor;
			public gov.nih.nci.camod.domain.ws.EnvironmentalFactor getEnvironmentalFactor(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setEnvironmentalFactor(gov.nih.nci.camod.domain.ws.EnvironmentalFactor environmentalFactor){
		this.environmentalFactor = environmentalFactor;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneticAlterationCollection = new java.util.HashSet();
			public java.util.Collection getGeneticAlterationCollection(){
	              return geneticAlterationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneticAlterationCollection(java.util.Collection geneticAlterationCollection){
	   		this.geneticAlterationCollection = geneticAlterationCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof InducedMutation) {
				InducedMutation c =(InducedMutation)obj; 			 
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
