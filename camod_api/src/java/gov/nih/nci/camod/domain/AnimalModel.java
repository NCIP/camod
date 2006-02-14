

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * An animal model which develops cancer or can be used to generate a model that develops cancer. 
   * 
   */

public  class AnimalModel 
    extends AbstractCancerModel
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String url;
	   public String getUrl(){
	      return url;
	   }
	   public void setUrl(String url){
	      this.url = url;
	   }
	
	   
	   public Boolean isToolMouse;
	   public Boolean getIsToolMouse(){
	      return isToolMouse;
	   }
	   public void setIsToolMouse(Boolean isToolMouse){
	      this.isToolMouse = isToolMouse;
	   }
	

	
	   
	   
	   
	      

			private java.util.Collection spontaneousMutationCollection = new java.util.HashSet();
			public java.util.Collection getSpontaneousMutationCollection(){
			try{
			   if(spontaneousMutationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.SpontaneousMutation", thisIdSet);				 
				 	spontaneousMutationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getSpontaneousMutationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return spontaneousMutationCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setSpontaneousMutationCollection(java.util.Collection spontaneousMutationCollection){
	   		this.spontaneousMutationCollection = spontaneousMutationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection geneDeliveryCollection = new java.util.HashSet();
			public java.util.Collection getGeneDeliveryCollection(){
			try{
			   if(geneDeliveryCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.GeneDelivery", thisIdSet);				 
				 	geneDeliveryCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getGeneDeliveryCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return geneDeliveryCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setGeneDeliveryCollection(java.util.Collection geneDeliveryCollection){
	   		this.geneDeliveryCollection = geneDeliveryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection microArrayDataCollection = new java.util.HashSet();
			public java.util.Collection getMicroArrayDataCollection(){
			try{
			   if(microArrayDataCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.MicroArrayData", thisIdSet);				 
				 	microArrayDataCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getMicroArrayDataCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return microArrayDataCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setMicroArrayDataCollection(java.util.Collection microArrayDataCollection){
	   		this.microArrayDataCollection = microArrayDataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.RepositoryInfo repositoryInfo;
			public gov.nih.nci.camod.domain.RepositoryInfo getRepositoryInfo(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.RepositoryInfo", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                repositoryInfo = (gov.nih.nci.camod.domain.RepositoryInfo)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AnimalModel:getRepositoryInfo throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return repositoryInfo;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setRepositoryInfo(gov.nih.nci.camod.domain.RepositoryInfo repositoryInfo){
		this.repositoryInfo = repositoryInfo;
	   }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection xenograftCollection = new java.util.HashSet();
			public java.util.Collection getXenograftCollection(){
			try{
			   if(xenograftCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Xenograft", thisIdSet);				 
				 	xenograftCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getXenograftCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return xenograftCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setXenograftCollection(java.util.Collection xenograftCollection){
	   		this.xenograftCollection = xenograftCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection animalAvailabilityCollection = new java.util.HashSet();
			public java.util.Collection getAnimalAvailabilityCollection(){
			try{
			   if(animalAvailabilityCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.AnimalAvailability", thisIdSet);				 
				 	animalAvailabilityCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getAnimalAvailabilityCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return animalAvailabilityCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setAnimalAvailabilityCollection(java.util.Collection animalAvailabilityCollection){
	   		this.animalAvailabilityCollection = animalAvailabilityCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Phenotype phenotype;
			public gov.nih.nci.camod.domain.Phenotype getPhenotype(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Phenotype", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                phenotype = (gov.nih.nci.camod.domain.Phenotype)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AnimalModel:getPhenotype throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return phenotype;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setPhenotype(gov.nih.nci.camod.domain.Phenotype phenotype){
		this.phenotype = phenotype;
	   }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
			try{
			   if(histopathologyCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Histopathology", thisIdSet);				 
				 	histopathologyCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getHistopathologyCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return histopathologyCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection therapyCollection = new java.util.HashSet();
			public java.util.Collection getTherapyCollection(){
			try{
			   if(therapyCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Therapy", thisIdSet);				 
				 	therapyCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getTherapyCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return therapyCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setTherapyCollection(java.util.Collection therapyCollection){
	   		this.therapyCollection = therapyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection cellLineCollection = new java.util.HashSet();
			public java.util.Collection getCellLineCollection(){
			try{
			   if(cellLineCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.CellLine", thisIdSet);				 
				 	cellLineCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getCellLineCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return cellLineCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setCellLineCollection(java.util.Collection cellLineCollection){
	   		this.cellLineCollection = cellLineCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection engineeredGeneCollection = new java.util.HashSet();
			public java.util.Collection getEngineeredGeneCollection(){
			try{
			   if(engineeredGeneCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.EngineeredGene", thisIdSet);				 
				 	engineeredGeneCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getEngineeredGeneCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return engineeredGeneCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setEngineeredGeneCollection(java.util.Collection engineeredGeneCollection){
	   		this.engineeredGeneCollection = engineeredGeneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection imageCollection = new java.util.HashSet();
			public java.util.Collection getImageCollection(){
			try{
			   if(imageCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AnimalModel thisIdSet = new gov.nih.nci.camod.domain.AnimalModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Image", thisIdSet);				 
				 	imageCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AnimalModel:getImageCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return imageCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setImageCollection(java.util.Collection imageCollection){
	   		this.imageCollection = imageCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnimalModel) {
				AnimalModel c =(AnimalModel)obj; 			 
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