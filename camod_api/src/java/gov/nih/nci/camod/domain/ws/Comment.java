

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Comment 
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
	
	   
	   public String comment;
	   public String getComment(){
	      return comment;
	   }
	   
	   public void setComment(String comment){
	      this.comment = comment;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.AvailabilityImpl availability;
			public gov.nih.nci.camod.domain.ws.AvailabilityImpl getAvailability(){
			  return availability;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.ws.AvailabilityImpl availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.PersonImpl submitter;
			public gov.nih.nci.camod.domain.ws.PersonImpl getSubmitter(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.ws.PersonImpl submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ModelSectionImpl modelSection;
			public gov.nih.nci.camod.domain.ws.ModelSectionImpl getModelSection(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setModelSection(gov.nih.nci.camod.domain.ws.ModelSectionImpl modelSection){
		this.modelSection = modelSection;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Comment) {
				Comment c =(Comment)obj; 			 
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
