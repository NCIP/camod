

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * This object describes to xenografts (implanting tissue or cell lines from a different species) 
   * and allografts (implanting tissue or cell lines form the same species). The grafts are transplanted 
   * in immuno-compromised animals where they grow and, depending 
   * 
   */

public  class Xenograft 
    extends AbstractCancerModel
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String xenograftName;
	   public String getXenograftName(){
	      return xenograftName;
	   }
	   public void setXenograftName(String xenograftName){
	      this.xenograftName = xenograftName;
	   }
	
	   
	   public String geneticManipulation;
	   public String getGeneticManipulation(){
	      return geneticManipulation;
	   }
	   public void setGeneticManipulation(String geneticManipulation){
	      this.geneticManipulation = geneticManipulation;
	   }
	
	   
	   public String modificationDescription;
	   public String getModificationDescription(){
	      return modificationDescription;
	   }
	   public void setModificationDescription(String modificationDescription){
	      this.modificationDescription = modificationDescription;
	   }
	
	   
	   public String parentalCellLineName;
	   public String getParentalCellLineName(){
	      return parentalCellLineName;
	   }
	   public void setParentalCellLineName(String parentalCellLineName){
	      this.parentalCellLineName = parentalCellLineName;
	   }
	
	   
	   public String atccNumber;
	   public String getAtccNumber(){
	      return atccNumber;
	   }
	   public void setAtccNumber(String atccNumber){
	      this.atccNumber = atccNumber;
	   }
	
	   
	   public String cellAmount;
	   public String getCellAmount(){
	      return cellAmount;
	   }
	   public void setCellAmount(String cellAmount){
	      this.cellAmount = cellAmount;
	   }
	
	   
	   public java.lang.String growthPeriod;
	   public  java.lang.String getGrowthPeriod(){
	      return growthPeriod;
	   }
	   public void setGrowthPeriod( java.lang.String growthPeriod){
	      this.growthPeriod = growthPeriod;
	   }
	
	   
	   public String graftType;
	   public String getGraftType(){
	      return graftType;
	   }
	   public void setGraftType(String graftType){
	      this.graftType = graftType;
	   }
	
	   
	   public String graftTypeUnctrlVocab;
	   public String getGraftTypeUnctrlVocab(){
	      return graftTypeUnctrlVocab;
	   }
	   public void setGraftTypeUnctrlVocab(String graftTypeUnctrlVocab){
	      this.graftTypeUnctrlVocab = graftTypeUnctrlVocab;
	   }
	
	   
	   public String administrativeSite;
	   public String getAdministrativeSite(){
	      return administrativeSite;
	   }
	   public void setAdministrativeSite(String administrativeSite){
	      this.administrativeSite = administrativeSite;
	   }
	
	   
	   public java.lang.String adminSiteUnctrlVocab;
	   public  java.lang.String getAdminSiteUnctrlVocab(){
	      return adminSiteUnctrlVocab;
	   }
	   public void setAdminSiteUnctrlVocab( java.lang.String adminSiteUnctrlVocab){
	      this.adminSiteUnctrlVocab = adminSiteUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Xenograft thisIdSet = new gov.nih.nci.camod.domain.Xenograft();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     organ = (gov.nih.nci.camod.domain.Organ)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Xenograft:getOrgan throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return organ;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.TumorCode tumorCode;
			public gov.nih.nci.camod.domain.TumorCode getTumorCode(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Xenograft thisIdSet = new gov.nih.nci.camod.domain.Xenograft();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.TumorCode", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                tumorCode = (gov.nih.nci.camod.domain.TumorCode)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Xenograft:getTumorCode throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return tumorCode;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setTumorCode(gov.nih.nci.camod.domain.TumorCode tumorCode){
		this.tumorCode = tumorCode;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Species donorSpecies;
			public gov.nih.nci.camod.domain.Species getDonorSpecies(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Xenograft thisIdSet = new gov.nih.nci.camod.domain.Xenograft();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Species", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                donorSpecies = (gov.nih.nci.camod.domain.Species)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Xenograft:getDonorSpecies throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return donorSpecies;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setDonorSpecies(gov.nih.nci.camod.domain.Species donorSpecies){
		this.donorSpecies = donorSpecies;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection invivoResultCollection = new java.util.HashSet();
			public java.util.Collection getInvivoResultCollection(){
			try{
			   if(invivoResultCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Xenograft thisIdSet = new gov.nih.nci.camod.domain.Xenograft();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.InvivoResult", thisIdSet);				 
				 	invivoResultCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Xenograft:getInvivoResultCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return invivoResultCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setInvivoResultCollection(java.util.Collection invivoResultCollection){
	   		this.invivoResultCollection = invivoResultCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Xenograft) {
				Xenograft c =(Xenograft)obj; 			 
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