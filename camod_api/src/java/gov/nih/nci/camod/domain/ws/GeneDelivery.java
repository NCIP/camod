

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.OrganImpl organ;
			public gov.nih.nci.camod.domain.ws.OrganImpl getOrgan(){
			  return organ;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.OrganImpl organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.TreatmentImpl treatment;
			public gov.nih.nci.camod.domain.ws.TreatmentImpl getTreatment(){
			  return treatment;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.ws.TreatmentImpl treatment){
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
