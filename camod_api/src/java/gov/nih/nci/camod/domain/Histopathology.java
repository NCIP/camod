

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * An object representing anatomical changes in a diseased tissue sample associated with an expression 
   * experiment; captures the relationship between organ and disease. 
   * 
   */

public  class Histopathology 
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
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	
	   
	   public String grossDescription;
	   public String getGrossDescription(){
	      return grossDescription;
	   }
	   public void setGrossDescription(String grossDescription){
	      this.grossDescription = grossDescription;
	   }
	
	   
	   public String relationalOperation;
	   public String getRelationalOperation(){
	      return relationalOperation;
	   }
	   public void setRelationalOperation(String relationalOperation){
	      this.relationalOperation = relationalOperation;
	   }
	
	   
	   public Float tumorIncidenceRate;
	   public Float getTumorIncidenceRate(){
	      return tumorIncidenceRate;
	   }
	   public void setTumorIncidenceRate(Float tumorIncidenceRate){
	      this.tumorIncidenceRate = tumorIncidenceRate;
	   }
	
	   
	   public String survivalInfo;
	   public String getSurvivalInfo(){
	      return survivalInfo;
	   }
	   public void setSurvivalInfo(String survivalInfo){
	      this.survivalInfo = survivalInfo;
	   }
	
	   
	   public String ageOfOnset;
	   public String getAgeOfOnset(){
	      return ageOfOnset;
	   }
	   public void setAgeOfOnset(String ageOfOnset){
	      this.ageOfOnset = ageOfOnset;
	   }
	
	   
	   public String microscopicDescription;
	   public String getMicroscopicDescription(){
	      return microscopicDescription;
	   }
	   public void setMicroscopicDescription(String microscopicDescription){
	      this.microscopicDescription = microscopicDescription;
	   }
	
	   
	   public Float weightOfTumor;
	   public Float getWeightOfTumor(){
	      return weightOfTumor;
	   }
	   public void setWeightOfTumor(Float weightOfTumor){
	      this.weightOfTumor = weightOfTumor;
	   }
	
	   
	   public Float volumeOfTumor;
	   public Float getVolumeOfTumor(){
	      return volumeOfTumor;
	   }
	   public void setVolumeOfTumor(Float volumeOfTumor){
	      this.volumeOfTumor = volumeOfTumor;
	   }
	
	   
	   public String comparativeData;
	   public String getComparativeData(){
	      return comparativeData;
	   }
	   public void setComparativeData(String comparativeData){
	      this.comparativeData = comparativeData;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration;
			public gov.nih.nci.camod.domain.GeneticAlteration getGeneticAlteration(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Histopathology thisIdSet = new gov.nih.nci.camod.domain.Histopathology();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.GeneticAlteration", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     geneticAlteration = (gov.nih.nci.camod.domain.GeneticAlteration)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Histopathology:getGeneticAlteration throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return geneticAlteration;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setGeneticAlteration(gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration){
		this.geneticAlteration = geneticAlteration;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection clinicalMarkerCollection = new java.util.HashSet();
			public java.util.Collection getClinicalMarkerCollection(){
			try{
			   if(clinicalMarkerCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Histopathology thisIdSet = new gov.nih.nci.camod.domain.Histopathology();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ClinicalMarker", thisIdSet);				 
				 	clinicalMarkerCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Histopathology:getClinicalMarkerCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return clinicalMarkerCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setClinicalMarkerCollection(java.util.Collection clinicalMarkerCollection){
	   		this.clinicalMarkerCollection = clinicalMarkerCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Histopathology thisIdSet = new gov.nih.nci.camod.domain.Histopathology();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                organ = (gov.nih.nci.camod.domain.Organ)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Histopathology:getOrgan throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return organ;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection diseaseCollection = new java.util.HashSet();
			public java.util.Collection getDiseaseCollection(){
			try{
			   if(diseaseCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Histopathology thisIdSet = new gov.nih.nci.camod.domain.Histopathology();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Disease", thisIdSet);				 
				 	diseaseCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Histopathology:getDiseaseCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return diseaseCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setDiseaseCollection(java.util.Collection diseaseCollection){
	   		this.diseaseCollection = diseaseCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection metastasisCollection = new java.util.HashSet();
			public java.util.Collection getMetastasisCollection(){
			try{
			   if(metastasisCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Histopathology thisIdSet = new gov.nih.nci.camod.domain.Histopathology();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Histopathology", thisIdSet);				 
				 	metastasisCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Histopathology:getMetastasisCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return metastasisCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setMetastasisCollection(java.util.Collection metastasisCollection){
	   		this.metastasisCollection = metastasisCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Histopathology) {
				Histopathology c =(Histopathology)obj; 			 
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