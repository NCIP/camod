

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Comments 
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
	
	   
	   public String remark;
	   public String getRemark(){
	      return remark;
	   }
	   
	   public void setRemark(String remark){
	      this.remark = remark;
	   }
	
	   
	   public java.lang.String state;
	   public  java.lang.String getState(){
	      return state;
	   }
	   
	   public void setState( java.lang.String state){
	      this.state = state;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.Availability availability;
			public gov.nih.nci.camod.domain.ws.Availability getAvailability(){
			  return availability;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.ws.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Person submitter;
			public gov.nih.nci.camod.domain.ws.Person getSubmitter(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.ws.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ModelSection modelSection;
			public gov.nih.nci.camod.domain.ws.ModelSection getModelSection(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setModelSection(gov.nih.nci.camod.domain.ws.ModelSection modelSection){
		this.modelSection = modelSection;
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
			if(obj instanceof Comments) {
				Comments c =(Comments)obj; 			 
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
