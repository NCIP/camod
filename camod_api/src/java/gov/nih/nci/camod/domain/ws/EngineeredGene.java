

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class EngineeredGene 
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
	
	   
	   public Long cabioId;
	   public Long getCabioId(){
	      return cabioId;
	   }
	   
	   public void setCabioId(Long cabioId){
	      this.cabioId = cabioId;
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ConditionalityImpl conditionality;
			public gov.nih.nci.camod.domain.ws.ConditionalityImpl getConditionality(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setConditionality(gov.nih.nci.camod.domain.ws.ConditionalityImpl conditionality){
		this.conditionality = conditionality;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.MutationIdentifierImpl mutationIdentifier;
			public gov.nih.nci.camod.domain.ws.MutationIdentifierImpl getMutationIdentifier(){
			  return mutationIdentifier;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.ws.MutationIdentifierImpl mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneFunctionCollection = new java.util.HashSet();
			public java.util.Collection getGeneFunctionCollection(){
	              return geneFunctionCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneFunctionCollection(java.util.Collection geneFunctionCollection){
	   		this.geneFunctionCollection = geneFunctionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ImageImpl image;
			public gov.nih.nci.camod.domain.ws.ImageImpl getImage(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setImage(gov.nih.nci.camod.domain.ws.ImageImpl image){
		this.image = image;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection expressionFeatureCollection = new java.util.HashSet();
			public java.util.Collection getExpressionFeatureCollection(){
	              return expressionFeatureCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setExpressionFeatureCollection(java.util.Collection expressionFeatureCollection){
	   		this.expressionFeatureCollection = expressionFeatureCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.GenotypeSummaryImpl genotypeSummary;
			public gov.nih.nci.camod.domain.ws.GenotypeSummaryImpl getGenotypeSummary(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setGenotypeSummary(gov.nih.nci.camod.domain.ws.GenotypeSummaryImpl genotypeSummary){
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
