

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Party 
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
	

	
	   
	   
	   
	      
			private java.util.Collection roleCollection = new java.util.HashSet();
			public java.util.Collection getRoleCollection(){
	              return roleCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setRoleCollection(java.util.Collection roleCollection){
	   		this.roleCollection = roleCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection contactInfoCollection = new java.util.HashSet();
			public java.util.Collection getContactInfoCollection(){
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
