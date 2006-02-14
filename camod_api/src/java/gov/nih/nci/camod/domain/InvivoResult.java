

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * An Invivo Result captures the results of anti-tumor drug screening.
   */

public  class InvivoResult 
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
	
	   
	   public String evaluationDay;
	   public String getEvaluationDay(){
	      return evaluationDay;
	   }
	   public void setEvaluationDay(String evaluationDay){
	      this.evaluationDay = evaluationDay;
	   }
	
	   
	   public String toxicitySurvivors;
	   public String getToxicitySurvivors(){
	      return toxicitySurvivors;
	   }
	   public void setToxicitySurvivors(String toxicitySurvivors){
	      this.toxicitySurvivors = toxicitySurvivors;
	   }
	
	   
	   public String toxicityEvalDay;
	   public String getToxicityEvalDay(){
	      return toxicityEvalDay;
	   }
	   public void setToxicityEvalDay(String toxicityEvalDay){
	      this.toxicityEvalDay = toxicityEvalDay;
	   }
	
	   
	   public Float percentTreatedControl;
	   public Float getPercentTreatedControl(){
	      return percentTreatedControl;
	   }
	   public void setPercentTreatedControl(Float percentTreatedControl){
	      this.percentTreatedControl = percentTreatedControl;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.EndpointCode endpointCode;
			public gov.nih.nci.camod.domain.EndpointCode getEndpointCode(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.InvivoResult thisIdSet = new gov.nih.nci.camod.domain.InvivoResult();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.EndpointCode", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                endpointCode = (gov.nih.nci.camod.domain.EndpointCode)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("InvivoResult:getEndpointCode throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return endpointCode;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setEndpointCode(gov.nih.nci.camod.domain.EndpointCode endpointCode){
		this.endpointCode = endpointCode;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Agent agent;
			public gov.nih.nci.camod.domain.Agent getAgent(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.InvivoResult thisIdSet = new gov.nih.nci.camod.domain.InvivoResult();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Agent", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                agent = (gov.nih.nci.camod.domain.Agent)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("InvivoResult:getAgent throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return agent;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAgent(gov.nih.nci.camod.domain.Agent agent){
		this.agent = agent;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Treatment treatmentSchedule;
			public gov.nih.nci.camod.domain.Treatment getTreatmentSchedule(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.InvivoResult thisIdSet = new gov.nih.nci.camod.domain.InvivoResult();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Treatment", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                treatmentSchedule = (gov.nih.nci.camod.domain.Treatment)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("InvivoResult:getTreatmentSchedule throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return treatmentSchedule;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setTreatmentSchedule(gov.nih.nci.camod.domain.Treatment treatmentSchedule){
		this.treatmentSchedule = treatmentSchedule;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof InvivoResult) {
				InvivoResult c =(InvivoResult)obj; 			 
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