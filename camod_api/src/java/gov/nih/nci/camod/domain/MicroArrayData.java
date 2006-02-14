

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Data of microarray experiments generated from the animal model.
   */

public  class MicroArrayData 
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
	
	   
	   public String experimentName;
	   public String getExperimentName(){
	      return experimentName;
	   }
	   public void setExperimentName(String experimentName){
	      this.experimentName = experimentName;
	   }
	
	   
	   public Long experimentId;
	   public Long getExperimentId(){
	      return experimentId;
	   }
	   public void setExperimentId(Long experimentId){
	      this.experimentId = experimentId;
	   }
	
	   
	   public String otherLocationURL;
	   public String getOtherLocationURL(){
	      return otherLocationURL;
	   }
	   public void setOtherLocationURL(String otherLocationURL){
	      this.otherLocationURL = otherLocationURL;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Availability availability;
			public gov.nih.nci.camod.domain.Availability getAvailability(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.MicroArrayData thisIdSet = new gov.nih.nci.camod.domain.MicroArrayData();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Availability", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                availability = (gov.nih.nci.camod.domain.Availability)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("MicroArrayData:getAvailability throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return availability;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof MicroArrayData) {
				MicroArrayData c =(MicroArrayData)obj; 			 
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