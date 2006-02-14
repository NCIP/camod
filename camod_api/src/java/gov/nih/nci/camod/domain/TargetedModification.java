

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Modification targeted to a specific gene or a specific part of the gene to either suppress the function 
   * of the gene or insert marker sequences like loxP sites. 
   * 
   */

public  class TargetedModification 
    extends EngineeredGene
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String esCellLineName;
	   public String getEsCellLineName(){
	      return esCellLineName;
	   }
	   public void setEsCellLineName(String esCellLineName){
	      this.esCellLineName = esCellLineName;
	   }
	
	   
	   public String blastocystName;
	   public String getBlastocystName(){
	      return blastocystName;
	   }
	   public void setBlastocystName(String blastocystName){
	      this.blastocystName = blastocystName;
	   }
	
	   
	   public String geneId;
	   public String getGeneId(){
	      return geneId;
	   }
	   public void setGeneId(String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public String modTypeUnctrlVocab;
	   public String getModTypeUnctrlVocab(){
	      return modTypeUnctrlVocab;
	   }
	   public void setModTypeUnctrlVocab(String modTypeUnctrlVocab){
	      this.modTypeUnctrlVocab = modTypeUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.YeastModel yeastModel;
			public gov.nih.nci.camod.domain.YeastModel getYeastModel(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.TargetedModification thisIdSet = new gov.nih.nci.camod.domain.TargetedModification();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.YeastModel", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                yeastModel = (gov.nih.nci.camod.domain.YeastModel)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("TargetedModification:getYeastModel throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return yeastModel;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setYeastModel(gov.nih.nci.camod.domain.YeastModel yeastModel){
		this.yeastModel = yeastModel;
	   }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection modificationTypeCollection = new java.util.HashSet();
			public java.util.Collection getModificationTypeCollection(){
			try{
			   if(modificationTypeCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.TargetedModification thisIdSet = new gov.nih.nci.camod.domain.TargetedModification();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ModificationType", thisIdSet);				 
				 	modificationTypeCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("TargetedModification:getModificationTypeCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return modificationTypeCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setModificationTypeCollection(java.util.Collection modificationTypeCollection){
	   		this.modificationTypeCollection = modificationTypeCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof TargetedModification) {
				TargetedModification c =(TargetedModification)obj; 			 
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