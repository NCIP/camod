

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class AbstractCancerModel 
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
	
	   
	   public java.lang.String modelDescriptor;
	   public  java.lang.String getModelDescriptor(){
	      return modelDescriptor;
	   }
	   
	   public void setModelDescriptor( java.lang.String modelDescriptor){
	      this.modelDescriptor = modelDescriptor;
	   }
	
	   
	   public java.lang.String experimentDesign;
	   public  java.lang.String getExperimentDesign(){
	      return experimentDesign;
	   }
	   
	   public void setExperimentDesign( java.lang.String experimentDesign){
	      this.experimentDesign = experimentDesign;
	   }
	
	   
	   public java.lang.String state;
	   public  java.lang.String getState(){
	      return state;
	   }
	   
	   public void setState( java.lang.String state){
	      this.state = state;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Person principalInvestigator;
			public gov.nih.nci.camod.domain.ws.Person getPrincipalInvestigator(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setPrincipalInvestigator(gov.nih.nci.camod.domain.ws.Person principalInvestigator){
		this.principalInvestigator = principalInvestigator;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Strain strain;
			public gov.nih.nci.camod.domain.ws.Strain getStrain(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setStrain(gov.nih.nci.camod.domain.ws.Strain strain){
		this.strain = strain;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
	              return publicationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
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
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection logCollection = new java.util.HashSet();
			public java.util.Collection getLogCollection(){
	              return logCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLogCollection(java.util.Collection logCollection){
	   		this.logCollection = logCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AbstractCancerModel) {
				AbstractCancerModel c =(AbstractCancerModel)obj; 			 
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
