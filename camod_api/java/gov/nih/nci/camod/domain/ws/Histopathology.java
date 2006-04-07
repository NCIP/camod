

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Histopathology 
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
	
	   
	   public java.lang.String tumorIncidenceRate;
	   public  java.lang.String getTumorIncidenceRate(){
	      return tumorIncidenceRate;
	   }
	   
	   public void setTumorIncidenceRate( java.lang.String tumorIncidenceRate){
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
	
	   
	   public java.lang.String ageOfOnsetUnit;
	   public  java.lang.String getAgeOfOnsetUnit(){
	      return ageOfOnsetUnit;
	   }
	   
	   public void setAgeOfOnsetUnit( java.lang.String ageOfOnsetUnit){
	      this.ageOfOnsetUnit = ageOfOnsetUnit;
	   }
	
	   
	   public String microscopicDescription;
	   public String getMicroscopicDescription(){
	      return microscopicDescription;
	   }
	   
	   public void setMicroscopicDescription(String microscopicDescription){
	      this.microscopicDescription = microscopicDescription;
	   }
	
	   
	   public java.lang.String weightOfTumor;
	   public  java.lang.String getWeightOfTumor(){
	      return weightOfTumor;
	   }
	   
	   public void setWeightOfTumor( java.lang.String weightOfTumor){
	      this.weightOfTumor = weightOfTumor;
	   }
	
	   
	   public java.lang.String volumeOfTumor;
	   public  java.lang.String getVolumeOfTumor(){
	      return volumeOfTumor;
	   }
	   
	   public void setVolumeOfTumor( java.lang.String volumeOfTumor){
	      this.volumeOfTumor = volumeOfTumor;
	   }
	
	   
	   public String comparativeData;
	   public String getComparativeData(){
	      return comparativeData;
	   }
	   
	   public void setComparativeData(String comparativeData){
	      this.comparativeData = comparativeData;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.GeneticAlteration geneticAlteration;
			public gov.nih.nci.camod.domain.ws.GeneticAlteration getGeneticAlteration(){
			  return geneticAlteration;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setGeneticAlteration(gov.nih.nci.camod.domain.ws.GeneticAlteration geneticAlteration){
		this.geneticAlteration = geneticAlteration;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection clinicalMarkerCollection = new java.util.HashSet();
			public java.util.Collection getClinicalMarkerCollection(){
	              return clinicalMarkerCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setClinicalMarkerCollection(java.util.Collection clinicalMarkerCollection){
	   		this.clinicalMarkerCollection = clinicalMarkerCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Organ organ;
			public gov.nih.nci.camod.domain.ws.Organ getOrgan(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Disease disease;
			public gov.nih.nci.camod.domain.ws.Disease getDisease(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setDisease(gov.nih.nci.camod.domain.ws.Disease disease){
		this.disease = disease;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection metastasisCollection = new java.util.HashSet();
			public java.util.Collection getMetastasisCollection(){
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
