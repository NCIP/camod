

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class ContactInfo 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String city;
	   public String getCity(){
	      return city;
	   }
	   
	   public void setCity(String city){
	      this.city = city;
	   }
	
	   
	   public String state;
	   public String getState(){
	      return state;
	   }
	   
	   public void setState(String state){
	      this.state = state;
	   }
	
	   
	   public String address;
	   public String getAddress(){
	      return address;
	   }
	   
	   public void setAddress(String address){
	      this.address = address;
	   }
	
	   
	   public String zip;
	   public String getZip(){
	      return zip;
	   }
	   
	   public void setZip(String zip){
	      this.zip = zip;
	   }
	
	   
	   public String fax;
	   public String getFax(){
	      return fax;
	   }
	   
	   public void setFax(String fax){
	      this.fax = fax;
	   }
	
	   
	   public String lab;
	   public String getLab(){
	      return lab;
	   }
	   
	   public void setLab(String lab){
	      this.lab = lab;
	   }
	
	   
	   public String phone;
	   public String getPhone(){
	      return phone;
	   }
	   
	   public void setPhone(String phone){
	      this.phone = phone;
	   }
	
	   
	   public String email;
	   public String getEmail(){
	      return email;
	   }
	   
	   public void setEmail(String email){
	      this.email = email;
	   }
	
	   
	   public String institute;
	   public String getInstitute(){
	      return institute;
	   }
	   
	   public void setInstitute(String institute){
	      this.institute = institute;
	   }
	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   
	   public void setId(Long id){
	      this.id = id;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection partyCollection = new java.util.HashSet();
			public java.util.Collection getPartyCollection(){
	              return partyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPartyCollection(java.util.Collection partyCollection){
	   		this.partyCollection = partyCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ContactInfo) {
				ContactInfo c =(ContactInfo)obj; 			 
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
