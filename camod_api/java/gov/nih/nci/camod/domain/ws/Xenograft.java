

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Organ organ;
			public gov.nih.nci.camod.domain.ws.Organ getOrgan(){
			  return organ;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TumorCode tumorCode;
			public gov.nih.nci.camod.domain.ws.TumorCode getTumorCode(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setTumorCode(gov.nih.nci.camod.domain.ws.TumorCode tumorCode){
		this.tumorCode = tumorCode;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Species donorSpecies;
			public gov.nih.nci.camod.domain.ws.Species getDonorSpecies(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setDonorSpecies(gov.nih.nci.camod.domain.ws.Species donorSpecies){
		this.donorSpecies = donorSpecies;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection invivoResultCollection = new java.util.HashSet();
			public java.util.Collection getInvivoResultCollection(){
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
