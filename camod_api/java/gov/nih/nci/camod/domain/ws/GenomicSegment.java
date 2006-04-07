

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
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
	
	   
	   public java.lang.Boolean isRandom;
	   public  java.lang.Boolean getIsRandom(){
	      return isRandom;
	   }
	   
	   public void setIsRandom( java.lang.Boolean isRandom){
	      this.isRandom = isRandom;
	   }
	
	   
	   public java.lang.String constructSequence;
	   public  java.lang.String getConstructSequence(){
	      return constructSequence;
	   }
	   
	   public void setConstructSequence( java.lang.String constructSequence){
	      this.constructSequence = constructSequence;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.SegmentType segmentType;
			public gov.nih.nci.camod.domain.ws.SegmentType getSegmentType(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSegmentType(gov.nih.nci.camod.domain.ws.SegmentType segmentType){
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
