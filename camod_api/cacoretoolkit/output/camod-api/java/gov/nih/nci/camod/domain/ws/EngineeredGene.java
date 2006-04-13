

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class EngineeredGene 
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
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Conditionality conditionality;
			public gov.nih.nci.camod.domain.ws.Conditionality getConditionality(){
			  return conditionality;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setConditionality(gov.nih.nci.camod.domain.ws.Conditionality conditionality){
		this.conditionality = conditionality;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.MutationIdentifier mutationIdentifier;
			public gov.nih.nci.camod.domain.ws.MutationIdentifier getMutationIdentifier(){
			  return mutationIdentifier;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.ws.MutationIdentifier mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneFunctionCollection = new java.util.HashSet();
			public java.util.Collection getGeneFunctionCollection(){
	              return geneFunctionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneFunctionCollection(java.util.Collection geneFunctionCollection){
	   		this.geneFunctionCollection = geneFunctionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Image image;
			public gov.nih.nci.camod.domain.ws.Image getImage(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setImage(gov.nih.nci.camod.domain.ws.Image image){
		this.image = image;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection expressionFeatureCollection = new java.util.HashSet();
			public java.util.Collection getExpressionFeatureCollection(){
	              return expressionFeatureCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setExpressionFeatureCollection(java.util.Collection expressionFeatureCollection){
	   		this.expressionFeatureCollection = expressionFeatureCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.GenotypeSummary genotypeSummary;
			public gov.nih.nci.camod.domain.ws.GenotypeSummary getGenotypeSummary(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setGenotypeSummary(gov.nih.nci.camod.domain.ws.GenotypeSummary genotypeSummary){
		this.genotypeSummary = genotypeSummary;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof EngineeredGene) {
				EngineeredGene c =(EngineeredGene)obj; 			 
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
