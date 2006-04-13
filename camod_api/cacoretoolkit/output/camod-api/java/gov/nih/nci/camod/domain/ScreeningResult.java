

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A Screening Result captures the results of the tens of thousands of compounds screened for their 
   * ability to inhibit the growth of selected yeast strains altered in DNA damage repair or cell cycle 
   * control. 
   * 
   */

public  class ScreeningResult 
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
	
	   
	   public String stage;
	   public String getStage(){
	      return stage;
	   }
	   public void setStage(String stage){
	      this.stage = stage;
	   }
	
	   
	   public Float diffinh;
	   public Float getDiffinh(){
	      return diffinh;
	   }
	   public void setDiffinh(Float diffinh){
	      this.diffinh = diffinh;
	   }
	
	   
	   public Float aveinh;
	   public Float getAveinh(){
	      return aveinh;
	   }
	   public void setAveinh(Float aveinh){
	      this.aveinh = aveinh;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Treatment treatment;
			public gov.nih.nci.camod.domain.Treatment getTreatment(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.ScreeningResult thisIdSet = new gov.nih.nci.camod.domain.ScreeningResult();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Treatment", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                treatment = (gov.nih.nci.camod.domain.Treatment)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("ScreeningResult:getTreatment throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return treatment;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.Treatment treatment){
		this.treatment = treatment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Agent agent;
			public gov.nih.nci.camod.domain.Agent getAgent(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.ScreeningResult thisIdSet = new gov.nih.nci.camod.domain.ScreeningResult();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Agent", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                agent = (gov.nih.nci.camod.domain.Agent)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("ScreeningResult:getAgent throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return agent;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAgent(gov.nih.nci.camod.domain.Agent agent){
		this.agent = agent;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ScreeningResult) {
				ScreeningResult c =(ScreeningResult)obj; 			 
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