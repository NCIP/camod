

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Log 
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
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.CommentImpl comment;
			public gov.nih.nci.camod.domain.ws.CommentImpl getComment(){
			  return comment;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setComment(gov.nih.nci.camod.domain.ws.CommentImpl comment){
		this.comment = comment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.PersonImpl submitter;
			public gov.nih.nci.camod.domain.ws.PersonImpl getSubmitter(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.ws.PersonImpl submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.AbstractCancerModelImpl cancerModel;
			public gov.nih.nci.camod.domain.ws.AbstractCancerModelImpl getCancerModel(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setCancerModel(gov.nih.nci.camod.domain.ws.AbstractCancerModelImpl cancerModel){
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
