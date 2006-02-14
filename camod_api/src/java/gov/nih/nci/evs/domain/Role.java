

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * Defines a relationship between two concepts. 
   */


public  class Role
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the name of the role	   
	 */
	   	
	
	private java.lang.String Name;	   
	   
	   
	/**
	* Returns the name of this Role	   
	* @return - Name
	*/
	public  java.lang.String getName(){
	      return Name;
	      }   
	   		
	/**
	* Sets the specified name for this role	   
	* @param - Name
	*/
	public void setName( java.lang.String Name){
	      this.Name = Name;
	   }	   
	
	   
	 /**
	 * Specifies the value	   
	 */
	   	
	
	private java.lang.String value;	   
	   
	   
	/**
	* Returns the value of this Role	   
	* @return - value
	*/
	public  java.lang.String getValue(){
	      return value;
	      }   
	   		
	/**
	* Sets the specified value for this Role	   
	* @param - value
	*/
	public void setValue( java.lang.String value){
	      this.value = value;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Role) {
			Role c =(Role)obj; 
								
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
