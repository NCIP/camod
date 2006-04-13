

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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.EnvironmentalFactor environmentalFactor;
			public gov.nih.nci.camod.domain.EnvironmentalFactor getEnvironmentalFactor(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.CarcinogenExposure thisIdSet = new gov.nih.nci.camod.domain.CarcinogenExposure();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.EnvironmentalFactor", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                environmentalFactor = (gov.nih.nci.camod.domain.EnvironmentalFactor)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("CarcinogenExposure:getEnvironmentalFactor throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return environmentalFactor;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setEnvironmentalFactor(gov.nih.nci.camod.domain.EnvironmentalFactor environmentalFactor){
		this.environmentalFactor = environmentalFactor;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Treatment treatment;
			public gov.nih.nci.camod.domain.Treatment getTreatment(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.CarcinogenExposure thisIdSet = new gov.nih.nci.camod.domain.CarcinogenExposure();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Treatment", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     treatment = (gov.nih.nci.camod.domain.Treatment)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("CarcinogenExposure:getTreatment throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return treatment;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.Treatment treatment){
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