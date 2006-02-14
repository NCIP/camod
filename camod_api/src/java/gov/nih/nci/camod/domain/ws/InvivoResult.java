

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.EndpointCodeImpl endpointCode;
			public gov.nih.nci.camod.domain.ws.EndpointCodeImpl getEndpointCode(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setEndpointCode(gov.nih.nci.camod.domain.ws.EndpointCodeImpl endpointCode){
		this.endpointCode = endpointCode;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.AgentImpl agent;
			public gov.nih.nci.camod.domain.ws.AgentImpl getAgent(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setAgent(gov.nih.nci.camod.domain.ws.AgentImpl agent){
		this.agent = agent;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TreatmentImpl treatmentSchedule;
			public gov.nih.nci.camod.domain.ws.TreatmentImpl getTreatmentSchedule(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setTreatmentSchedule(gov.nih.nci.camod.domain.ws.TreatmentImpl treatmentSchedule){
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
