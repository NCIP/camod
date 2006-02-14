

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Genomic segment extracted from a library e.g. BAC or YAC library to be used in the genetic manipulation 
   * of the animal model. 
   * 
   */

public  class GenomicSegment 
    extends EngineeredGene
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String locationOfIntegration;
	   public String getLocationOfIntegration(){
	      return locationOfIntegration;
	   }
	   public void setLocationOfIntegration(String locationOfIntegration){
	      this.locationOfIntegration = locationOfIntegration;
	   }
	
	   
	   public String segmentSize;
	   public String getSegmentSize(){
	      return segmentSize;
	   }
	   public void setSegmentSize(String segmentSize){
	      this.segmentSize = segmentSize;
	   }
	
	   
	   public String cloneDesignator;
	   public String getCloneDesignator(){
	      return cloneDesignator;
	   }
	   public void setCloneDesignator(String cloneDesignator){
	      this.cloneDesignator = cloneDesignator;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.SegmentType segmentType;
			public gov.nih.nci.camod.domain.SegmentType getSegmentType(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.GenomicSegment thisIdSet = new gov.nih.nci.camod.domain.GenomicSegment();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.SegmentType", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     segmentType = (gov.nih.nci.camod.domain.SegmentType)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("GenomicSegment:getSegmentType throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return segmentType;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setSegmentType(gov.nih.nci.camod.domain.SegmentType segmentType){
		this.segmentType = segmentType;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GenomicSegment) {
				GenomicSegment c =(GenomicSegment)obj; 			 
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