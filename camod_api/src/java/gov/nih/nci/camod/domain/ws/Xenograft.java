

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Xenograft 
    extends AbstractCancerModel
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String administrativeSite;
	   public String getAdministrativeSite(){
	      return administrativeSite;
	   }
	   
	   public void setAdministrativeSite(String administrativeSite){
	      this.administrativeSite = administrativeSite;
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String ATCCNumber;
	   public String getATCCNumber(){
	      return ATCCNumber;
	   }
	   
	   public void setATCCNumber(String ATCCNumber){
	      this.ATCCNumber = ATCCNumber;
	   }
	
	   
	   public String cellAmount;
	   public String getCellAmount(){
	      return cellAmount;
	   }
	   
	   public void setCellAmount(String cellAmount){
	      this.cellAmount = cellAmount;
	   }
	
	   
	   public Date harvestDate;
	   public Date getHarvestDate(){
	      return harvestDate;
	   }
	   
	   public void setHarvestDate(Date harvestDate){
	      this.harvestDate = harvestDate;
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
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.OrganImpl organ;
			public gov.nih.nci.camod.domain.ws.OrganImpl getOrgan(){
			  return organ;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.OrganImpl organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TaxonImpl hostSpecies;
			public gov.nih.nci.camod.domain.ws.TaxonImpl getHostSpecies(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setHostSpecies(gov.nih.nci.camod.domain.ws.TaxonImpl hostSpecies){
		this.hostSpecies = hostSpecies;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TaxonImpl originSpecies;
			public gov.nih.nci.camod.domain.ws.TaxonImpl getOriginSpecies(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setOriginSpecies(gov.nih.nci.camod.domain.ws.TaxonImpl originSpecies){
		this.originSpecies = originSpecies;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TumorCodeImpl tumorCode;
			public gov.nih.nci.camod.domain.ws.TumorCodeImpl getTumorCode(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setTumorCode(gov.nih.nci.camod.domain.ws.TumorCodeImpl tumorCode){
		this.tumorCode = tumorCode;
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
