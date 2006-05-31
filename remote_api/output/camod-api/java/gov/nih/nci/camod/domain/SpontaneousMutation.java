

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * As a type of mutation, one that has occurred in the absence of any experimental mutagenic treatment, 
   * such as irradiation or treatment with chemical mutagens. 
   * 
   */

public  class SpontaneousMutation 
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
	
	   
	   public java.lang.String geneId;
	   public  java.lang.String getGeneId(){
	      return geneId;
	   }
	   public void setGeneId( java.lang.String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration;
			public gov.nih.nci.camod.domain.GeneticAlteration getGeneticAlteration(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.SpontaneousMutation thisIdSet = new gov.nih.nci.camod.domain.SpontaneousMutation();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.GeneticAlteration", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     geneticAlteration = (gov.nih.nci.camod.domain.GeneticAlteration)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("SpontaneousMutation:getGeneticAlteration throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return geneticAlteration;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setGeneticAlteration(gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration){
		this.geneticAlteration = geneticAlteration;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier;
			public gov.nih.nci.camod.domain.MutationIdentifier getMutationIdentifier(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.SpontaneousMutation thisIdSet = new gov.nih.nci.camod.domain.SpontaneousMutation();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.MutationIdentifier", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                mutationIdentifier = (gov.nih.nci.camod.domain.MutationIdentifier)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("SpontaneousMutation:getMutationIdentifier throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return mutationIdentifier;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.MutationIdentifier mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SpontaneousMutation) {
				SpontaneousMutation c =(SpontaneousMutation)obj; 			 
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