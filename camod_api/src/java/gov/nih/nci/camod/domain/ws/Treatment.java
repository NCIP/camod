

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Treatment 
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
	
	   
	   public String regimen;
	   public String getRegimen(){
	      return regimen;
	   }
	   
	   public void setRegimen(String regimen){
	      this.regimen = regimen;
	   }
	
	   
	   public String dosage;
	   public String getDosage(){
	      return dosage;
	   }
	   
	   public void setDosage(String dosage){
	      this.dosage = dosage;
	   }
	
	   
	   public String administrativeRoute;
	   public String getAdministrativeRoute(){
	      return administrativeRoute;
	   }
	   
	   public void setAdministrativeRoute(String administrativeRoute){
	      this.administrativeRoute = administrativeRoute;
	   }
	
	   
	   public String ageAtTreatment;
	   public String getAgeAtTreatment(){
	      return ageAtTreatment;
	   }
	   
	   public void setAgeAtTreatment(String ageAtTreatment){
	      this.ageAtTreatment = ageAtTreatment;
	   }
	
	   
	   public String adminRouteUnctrlVocab;
	   public String getAdminRouteUnctrlVocab(){
	      return adminRouteUnctrlVocab;
	   }
	   
	   public void setAdminRouteUnctrlVocab(String adminRouteUnctrlVocab){
	      this.adminRouteUnctrlVocab = adminRouteUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.SexDistributionImpl sexDistribution;
			public gov.nih.nci.camod.domain.ws.SexDistributionImpl getSexDistribution(){
			  return sexDistribution;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setSexDistribution(gov.nih.nci.camod.domain.ws.SexDistributionImpl sexDistribution){
		this.sexDistribution = sexDistribution;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Treatment) {
				Treatment c =(Treatment)obj; 			 
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
