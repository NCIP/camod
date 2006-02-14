

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * AttributeSetDescriptor class specifies the set of concept attributes that should be retrieved 
   * by a given operation. 
   * 
   */


public  class AttributeSetDescriptor
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	public AttributeSetDescriptor(java.lang.String name){
	this.name = name;
};		
	
	   
	 /**
	 * Specifies the name for an AttributeSetDescriptor instance	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this AttributeSetDescriptor	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the name for this AttributeSetDescriptor	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * A special constant that specifies no concept attributes to be fetched.	   
	 */
	   
	public static final  int WITH_NO_ATTRIBUTES= 0; 
	   
	   
	 /**
	 * A special constant that specifies all concept attributes to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_ATTRIBUTES= 1; 
	   
	   
	 /**
	 * A special constant that specifies all roles of a concept to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_ROLES= 2; 
	   
	   
	 /**
	 * A special constant that specifies all properties of a concept to be fetched.	   
	 */
	   
	public static final  int WITH_ALL_PROPERTIES= 3; 
	   
	   
	 /**
	 * A special AttributeSetDescriptor that specifies all concept attributes to be fetched.	   
	 */
	   
	public static final  gov.nih.nci.evs.domain.AttributeSetDescriptor ALL_ATTRIBUTES = new AttributeSetDescriptor("All Attributes"); 
	   
	   
	 /**
	 * A special AttributeSetDescriptor that specifies no concept attributes to be fetched.	   
	 */
	   
	public static final  gov.nih.nci.evs.domain.AttributeSetDescriptor NO_ATTRIBUTES = new AttributeSetDescriptor("No Attributes");; 
	   
	   
	 /**
	 * A special AttributeSetDescriptor that specifies all properties to be fetched.	   
	 */
	   
	public static final  gov.nih.nci.evs.domain.AttributeSetDescriptor ALL_PROPERTIES = new AttributeSetDescriptor("All Properties");; 
	   
	   
	 /**
	 * A special AttributeSetDescriptor that specifies all roles to be fetched.	   
	 */
	   
	public static final  gov.nih.nci.evs.domain.AttributeSetDescriptor ALL_ROLES = new AttributeSetDescriptor("All Roles");; 
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector roleCollection = new java.util.Vector();
	/**
	* Returns the roles of this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public java.util.Vector getRoleCollection(){
		return roleCollection;
	}
	              	  
	      
	
	
	/** 
	* @deprecated - This method does not populate the role values. The preferred way to do this is by using the setRoleCollection method. 
	*/
	public void addRole(java.lang.String roleName){
		Role r = new Role();
		 r.setName(roleName);
		 roleCollection.add(r);
		
		}
	     
		   
	   	
	/**
	* Sets the specified roles for this AttributeSetDescriptor	   
	* @return - roleCollection
	*/

	public void setRoleCollection(java.util.Vector roleCollection){
	   	
	   	this.roleCollection = roleCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector propertyCollection = new java.util.Vector();
	/**
	* Returns the properties of this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public java.util.Vector getPropertyCollection(){
		return propertyCollection;
	}
	              	  
	      
	
	
	/** 
	* @deprecated - This method does not populate the property values. The preferred way to do this is by using the setPropertyCollection method.
	*/
	public void addProperty(java.lang.String propertyName){
		Property p = new Property();
		p.setName(propertyName);
		propertyCollection.add(p);
		
		}
	     
		   
	   	
	/**
	* Sets the specified properties for this AttributeSetDescriptor	   
	* @return - propertyCollection
	*/

	public void setPropertyCollection(java.util.Vector propertyCollection){
	   	
	   	this.propertyCollection = propertyCollection;
	        }	
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof AttributeSetDescriptor) {
			AttributeSetDescriptor c =(AttributeSetDescriptor)obj; 
								
				String thisKey =  getName();			
				if(thisKey!= null && thisKey.equals(c.getName())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getName() != null) {
			h += getName().hashCode();
		}
		return h;
	}
	
	
}
