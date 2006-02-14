

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * Property is an attribute of a concept. Examples of properties are "Synonym", "Preferred_Name", 
   * "Semantic_Type" etc. 
   * 
   */


public  class Property
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the name of this property	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this Property	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the specified name for this Property	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * Specifies the value of this property	   
	 */
	   	
	
	private java.lang.String value;	   
	   
	   
	/**
	* Returns the value of this Qualifier	   
	* @return - value
	*/
	public  java.lang.String getValue(){
	      return value;
	      }   
	   		
	/**
	* Sets the specified value for this Qualifier	   
	* @param - value
	*/
	public void setValue( java.lang.String value){
	      this.value = value;
	   }	   
	
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector qualifierCollection = new java.util.Vector();
	/**
	* Returns the Qualifiers of this Property	   
	* @return - qualifierCollection
	*/

	public java.util.Vector getQualifierCollection(){
		return qualifierCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified Qualifiers for this Property	   
	* @return - qualifierCollection
	*/

	public void setQualifierCollection(java.util.Vector qualifierCollection){
	   	
	   	this.qualifierCollection = qualifierCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Property) {
			Property c =(Property)obj; 
								
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
