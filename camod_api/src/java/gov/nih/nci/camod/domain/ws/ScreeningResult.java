

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class ScreeningResult 
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
	
	   
	   public Float inhibitionRate;
	   public Float getInhibitionRate(){
	      return inhibitionRate;
	   }
	   
	   public void setInhibitionRate(Float inhibitionRate){
	      this.inhibitionRate = inhibitionRate;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TreatmentImpl treatment;
			public gov.nih.nci.camod.domain.ws.TreatmentImpl getTreatment(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.ws.TreatmentImpl treatment){
		this.treatment = treatment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.AgentImpl agent;
			public gov.nih.nci.camod.domain.ws.AgentImpl getAgent(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setAgent(gov.nih.nci.camod.domain.ws.AgentImpl agent){
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
