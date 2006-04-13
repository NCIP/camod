

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
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
	

	
	   
	   
	   
	      
			private java.util.Collection geneDeliveryCollection = new java.util.HashSet();
			public java.util.Collection getGeneDeliveryCollection(){
	              return geneDeliveryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneDeliveryCollection(java.util.Collection geneDeliveryCollection){
	   		this.geneDeliveryCollection = geneDeliveryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.RepositoryInfo repositoryInfo;
			public gov.nih.nci.camod.domain.ws.RepositoryInfo getRepositoryInfo(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setRepositoryInfo(gov.nih.nci.camod.domain.ws.RepositoryInfo repositoryInfo){
		this.repositoryInfo = repositoryInfo;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection xenograftCollection = new java.util.HashSet();
			public java.util.Collection getXenograftCollection(){
	              return xenograftCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setXenograftCollection(java.util.Collection xenograftCollection){
	   		this.xenograftCollection = xenograftCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection carcinogenExposureCollection = new java.util.HashSet();
			public java.util.Collection getCarcinogenExposureCollection(){
	              return carcinogenExposureCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCarcinogenExposureCollection(java.util.Collection carcinogenExposureCollection){
	   		this.carcinogenExposureCollection = carcinogenExposureCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection therapyCollection = new java.util.HashSet();
			public java.util.Collection getTherapyCollection(){
	              return therapyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTherapyCollection(java.util.Collection therapyCollection){
	   		this.therapyCollection = therapyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection cellLineCollection = new java.util.HashSet();
			public java.util.Collection getCellLineCollection(){
	              return cellLineCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCellLineCollection(java.util.Collection cellLineCollection){
	   		this.cellLineCollection = cellLineCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection engineeredGeneCollection = new java.util.HashSet();
			public java.util.Collection getEngineeredGeneCollection(){
	              return engineeredGeneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setEngineeredGeneCollection(java.util.Collection engineeredGeneCollection){
	   		this.engineeredGeneCollection = engineeredGeneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection spontaneousMutationCollection = new java.util.HashSet();
			public java.util.Collection getSpontaneousMutationCollection(){
	              return spontaneousMutationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSpontaneousMutationCollection(java.util.Collection spontaneousMutationCollection){
	   		this.spontaneousMutationCollection = spontaneousMutationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection microArrayDataCollection = new java.util.HashSet();
			public java.util.Collection getMicroArrayDataCollection(){
	              return microArrayDataCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setMicroArrayDataCollection(java.util.Collection microArrayDataCollection){
	   		this.microArrayDataCollection = microArrayDataCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Phenotype phenotype;
			public gov.nih.nci.camod.domain.ws.Phenotype getPhenotype(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setPhenotype(gov.nih.nci.camod.domain.ws.Phenotype phenotype){
		this.phenotype = phenotype;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection animalAvailabilityCollection = new java.util.HashSet();
			public java.util.Collection getAnimalAvailabilityCollection(){
	              return animalAvailabilityCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setAnimalAvailabilityCollection(java.util.Collection animalAvailabilityCollection){
	   		this.animalAvailabilityCollection = animalAvailabilityCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
	              return histopathologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection imageCollection = new java.util.HashSet();
			public java.util.Collection getImageCollection(){
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
