

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * A yeast model captures the yeast strains altered in the NCI Yeast Anticancer Drug Screen. 
   * 
   */

public  class YeastModel 
    extends AbstractCancerModel
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      

			private java.util.Collection targetedModificationCollection = new java.util.HashSet();
			public java.util.Collection getTargetedModificationCollection(){
			try{
			   if(targetedModificationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.YeastModel thisIdSet = new gov.nih.nci.camod.domain.YeastModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.TargetedModification", thisIdSet);				 
				 	targetedModificationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("YeastModel:getTargetedModificationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return targetedModificationCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setTargetedModificationCollection(java.util.Collection targetedModificationCollection){
	   		this.targetedModificationCollection = targetedModificationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection screeningResultCollection = new java.util.HashSet();
			public java.util.Collection getScreeningResultCollection(){
			try{
			   if(screeningResultCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.YeastModel thisIdSet = new gov.nih.nci.camod.domain.YeastModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ScreeningResult", thisIdSet);				 
				 	screeningResultCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("YeastModel:getScreeningResultCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return screeningResultCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setScreeningResultCollection(java.util.Collection screeningResultCollection){
	   		this.screeningResultCollection = screeningResultCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof YeastModel) {
				YeastModel c =(YeastModel)obj; 			 
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