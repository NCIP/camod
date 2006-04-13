

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**

   */

public  class Comments 
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
	
	   
	   public String remark;
	   public String getRemark(){
	      return remark;
	   }
	   public void setRemark(String remark){
	      this.remark = remark;
	   }
	
	   
	   public java.lang.String state;
	   public  java.lang.String getState(){
	      return state;
	   }
	   public void setState( java.lang.String state){
	      this.state = state;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Availability availability;
			public gov.nih.nci.camod.domain.Availability getAvailability(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comments thisIdSet = new gov.nih.nci.camod.domain.Comments();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Availability", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     availability = (gov.nih.nci.camod.domain.Availability)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comments:getAvailability throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return availability;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Person submitter;
			public gov.nih.nci.camod.domain.Person getSubmitter(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comments thisIdSet = new gov.nih.nci.camod.domain.Comments();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Person", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                submitter = (gov.nih.nci.camod.domain.Person)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comments:getSubmitter throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return submitter;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ModelSection modelSection;
			public gov.nih.nci.camod.domain.ModelSection getModelSection(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comments thisIdSet = new gov.nih.nci.camod.domain.Comments();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.ModelSection", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                modelSection = (gov.nih.nci.camod.domain.ModelSection)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comments:getModelSection throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return modelSection;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setModelSection(gov.nih.nci.camod.domain.ModelSection modelSection){
		this.modelSection = modelSection;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.AbstractCancerModel cancerModel;
			public gov.nih.nci.camod.domain.AbstractCancerModel getCancerModel(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Comments thisIdSet = new gov.nih.nci.camod.domain.Comments();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.AbstractCancerModel", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                cancerModel = (gov.nih.nci.camod.domain.AbstractCancerModel)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Comments:getCancerModel throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return cancerModel;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setCancerModel(gov.nih.nci.camod.domain.AbstractCancerModel cancerModel){
		this.cancerModel = cancerModel;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Comments) {
				Comments c =(Comments)obj; 			 
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