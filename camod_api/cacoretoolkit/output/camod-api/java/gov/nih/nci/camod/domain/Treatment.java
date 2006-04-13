

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Treatment schedule for the pre-clinical trial / therapeutic experiment describing the dosages 
   * of the drug and the times of the treatment. 
   * 
   */

public  class Treatment 
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
	
	   
	   public java.lang.String dosageUnit;
	   public  java.lang.String getDosageUnit(){
	      return dosageUnit;
	   }
	   public void setDosageUnit( java.lang.String dosageUnit){
	      this.dosageUnit = dosageUnit;
	   }
	
	   
	   public String administrativeRoute;
	   public String getAdministrativeRoute(){
	      return administrativeRoute;
	   }
	   public void setAdministrativeRoute(String administrativeRoute){
	      this.administrativeRoute = administrativeRoute;
	   }
	
	   
	   public String adminRouteUnctrlVocab;
	   public String getAdminRouteUnctrlVocab(){
	      return adminRouteUnctrlVocab;
	   }
	   public void setAdminRouteUnctrlVocab(String adminRouteUnctrlVocab){
	      this.adminRouteUnctrlVocab = adminRouteUnctrlVocab;
	   }
	
	   
	   public String ageAtTreatment;
	   public String getAgeAtTreatment(){
	      return ageAtTreatment;
	   }
	   public void setAgeAtTreatment(String ageAtTreatment){
	      this.ageAtTreatment = ageAtTreatment;
	   }
	
	   
	   public java.lang.String ageAtTreatmentUnit;
	   public  java.lang.String getAgeAtTreatmentUnit(){
	      return ageAtTreatmentUnit;
	   }
	   public void setAgeAtTreatmentUnit( java.lang.String ageAtTreatmentUnit){
	      this.ageAtTreatmentUnit = ageAtTreatmentUnit;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.SexDistribution sexDistribution;
			public gov.nih.nci.camod.domain.SexDistribution getSexDistribution(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Treatment thisIdSet = new gov.nih.nci.camod.domain.Treatment();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.SexDistribution", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     sexDistribution = (gov.nih.nci.camod.domain.SexDistribution)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Treatment:getSexDistribution throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return sexDistribution;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setSexDistribution(gov.nih.nci.camod.domain.SexDistribution sexDistribution){
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