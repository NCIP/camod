

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A Person or group of persons (Organization) having a designated relationship (PartyRole) to parts 
   * of the MMHCC data with explicit access permissions. 
   * 
   */

public  class Party 
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
	

	
	   
	   
	   
	      
			private java.util.Collection roleCollection = new java.util.HashSet();
			public java.util.Collection getRoleCollection(){
			try{
			   if(roleCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Party thisIdSet = new gov.nih.nci.camod.domain.Party();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Role", thisIdSet);				 
				 	roleCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Party:getRoleCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return roleCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setRoleCollection(java.util.Collection roleCollection){
	   		this.roleCollection = roleCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection contactInfoCollection = new java.util.HashSet();
			public java.util.Collection getContactInfoCollection(){
			try{
			   if(contactInfoCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Party thisIdSet = new gov.nih.nci.camod.domain.Party();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ContactInfo", thisIdSet);				 
				 	contactInfoCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Party:getContactInfoCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return contactInfoCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setContactInfoCollection(java.util.Collection contactInfoCollection){
	   		this.contactInfoCollection = contactInfoCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Party) {
				Party c =(Party)obj; 			 
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