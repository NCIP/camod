

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**

   */

public  class ContactInfo 
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
	

	
	   
	   
	   
	      
			private java.util.Collection partyCollection = new java.util.HashSet();
			public java.util.Collection getPartyCollection(){
			try{
			   if(partyCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.ContactInfo thisIdSet = new gov.nih.nci.camod.domain.ContactInfo();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Party", thisIdSet);				 
				 	partyCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("ContactInfo:getPartyCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
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