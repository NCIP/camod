

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Manipulated Gene introduced in the animal model.
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Conditionality conditionality;
			public gov.nih.nci.camod.domain.Conditionality getConditionality(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Conditionality", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     conditionality = (gov.nih.nci.camod.domain.Conditionality)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("EngineeredGene:getConditionality throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return conditionality;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setConditionality(gov.nih.nci.camod.domain.Conditionality conditionality){
		this.conditionality = conditionality;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier;
			public gov.nih.nci.camod.domain.MutationIdentifier getMutationIdentifier(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.MutationIdentifier", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     mutationIdentifier = (gov.nih.nci.camod.domain.MutationIdentifier)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("EngineeredGene:getMutationIdentifier throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return mutationIdentifier;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneFunctionCollection = new java.util.HashSet();
			public java.util.Collection getGeneFunctionCollection(){
			try{
			   if(geneFunctionCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.GeneFunction", thisIdSet);				 
				 	geneFunctionCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("EngineeredGene:getGeneFunctionCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return geneFunctionCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setGeneFunctionCollection(java.util.Collection geneFunctionCollection){
	   		this.geneFunctionCollection = geneFunctionCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Image image;
			public gov.nih.nci.camod.domain.Image getImage(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Image", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                image = (gov.nih.nci.camod.domain.Image)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("EngineeredGene:getImage throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return image;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setImage(gov.nih.nci.camod.domain.Image image){
		this.image = image;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection expressionFeatureCollection = new java.util.HashSet();
			public java.util.Collection getExpressionFeatureCollection(){
			try{
			   if(expressionFeatureCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ExpressionFeature", thisIdSet);				 
				 	expressionFeatureCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("EngineeredGene:getExpressionFeatureCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return expressionFeatureCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setExpressionFeatureCollection(java.util.Collection expressionFeatureCollection){
	   		this.expressionFeatureCollection = expressionFeatureCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.GenotypeSummary genotypeSummary;
			public gov.nih.nci.camod.domain.GenotypeSummary getGenotypeSummary(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.EngineeredGene thisIdSet = new gov.nih.nci.camod.domain.EngineeredGene();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.GenotypeSummary", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                genotypeSummary = (gov.nih.nci.camod.domain.GenotypeSummary)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("EngineeredGene:getGenotypeSummary throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return genotypeSummary;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setGenotypeSummary(gov.nih.nci.camod.domain.GenotypeSummary genotypeSummary){
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