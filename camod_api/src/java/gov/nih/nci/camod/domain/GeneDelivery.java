

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Gene is delivered to specific organs or specific receptors within the animal model. using viral 
   * vectors 
   * 
   */

public  class GeneDelivery 
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
	
	   
	   public String viralVector;
	   public String getViralVector(){
	      return viralVector;
	   }
	   public void setViralVector(String viralVector){
	      this.viralVector = viralVector;
	   }
	
	   
	   public String geneInVirus;
	   public String getGeneInVirus(){
	      return geneInVirus;
	   }
	   public void setGeneInVirus(String geneInVirus){
	      this.geneInVirus = geneInVirus;
	   }
	
	   
	   public String viralVectorUnctrlVocab;
	   public String getViralVectorUnctrlVocab(){
	      return viralVectorUnctrlVocab;
	   }
	   public void setViralVectorUnctrlVocab(String viralVectorUnctrlVocab){
	      this.viralVectorUnctrlVocab = viralVectorUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.GeneDelivery thisIdSet = new gov.nih.nci.camod.domain.GeneDelivery();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     organ = (gov.nih.nci.camod.domain.Organ)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("GeneDelivery:getOrgan throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return organ;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Treatment treatment;
			public gov.nih.nci.camod.domain.Treatment getTreatment(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.GeneDelivery thisIdSet = new gov.nih.nci.camod.domain.GeneDelivery();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Treatment", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     treatment = (gov.nih.nci.camod.domain.Treatment)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("GeneDelivery:getTreatment throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return treatment;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.Treatment treatment){
		this.treatment = treatment;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneDelivery) {
				GeneDelivery c =(GeneDelivery)obj; 			 
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