

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Log 
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
	
	   
	   public String notes;
	   public String getNotes(){
	      return notes;
	   }
	   
	   public void setNotes(String notes){
	      this.notes = notes;
	   }
	
	   
	   public String type;
	   public String getType(){
	      return type;
	   }
	   
	   public void setType(String type){
	      this.type = type;
	   }
	
	   
	   public String subsystem;
	   public String getSubsystem(){
	      return subsystem;
	   }
	   
	   public void setSubsystem(String subsystem){
	      this.subsystem = subsystem;
	   }
	
	   
	   public String timestamp;
	   public String getTimestamp(){
	      return timestamp;
	   }
	   
	   public void setTimestamp(String timestamp){
	      this.timestamp = timestamp;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Comments comment;
			public gov.nih.nci.camod.domain.ws.Comments getComment(){
			  return comment;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setComment(gov.nih.nci.camod.domain.ws.Comments comment){
		this.comment = comment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Person submitter;
			public gov.nih.nci.camod.domain.ws.Person getSubmitter(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.ws.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.AbstractCancerModel cancerModel;
			public gov.nih.nci.camod.domain.ws.AbstractCancerModel getCancerModel(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setCancerModel(gov.nih.nci.camod.domain.ws.AbstractCancerModel cancerModel){
		this.cancerModel = cancerModel;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Log) {
				Log c =(Log)obj; 			 
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
