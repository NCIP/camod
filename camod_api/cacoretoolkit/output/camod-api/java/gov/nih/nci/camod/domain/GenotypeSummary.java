

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Listing of the genetic changes made deliberately in order to generate the animal model. 
   * 
   */

public  class GenotypeSummary 
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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Nomenclature nomenclature;
			public gov.nih.nci.camod.domain.Nomenclature getNomenclature(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.GenotypeSummary thisIdSet = new gov.nih.nci.camod.domain.GenotypeSummary();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Nomenclature", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                nomenclature = (gov.nih.nci.camod.domain.Nomenclature)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("GenotypeSummary:getNomenclature throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return nomenclature;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setNomenclature(gov.nih.nci.camod.domain.Nomenclature nomenclature){
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