

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


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
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.SegmentTypeImpl segmentType;
			public gov.nih.nci.camod.domain.ws.SegmentTypeImpl getSegmentType(){
			  return segmentType;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setSegmentType(gov.nih.nci.camod.domain.ws.SegmentTypeImpl segmentType){
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
