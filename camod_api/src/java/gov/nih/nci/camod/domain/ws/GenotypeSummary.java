

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class GenotypeSummary 
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
	
	   
	   public String summary;
	   public String getSummary(){
	      return summary;
	   }
	   
	   public void setSummary(String summary){
	      this.summary = summary;
	   }
	
	   
	   public String genotype;
	   public String getGenotype(){
	      return genotype;
	   }
	   
	   public void setGenotype(String genotype){
	      this.genotype = genotype;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.NomenclatureImpl nomenclature;
			public gov.nih.nci.camod.domain.ws.NomenclatureImpl getNomenclature(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setNomenclature(gov.nih.nci.camod.domain.ws.NomenclatureImpl nomenclature){
		this.nomenclature = nomenclature;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GenotypeSummary) {
				GenotypeSummary c =(GenotypeSummary)obj; 			 
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
