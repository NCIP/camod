

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * A Qualifier is  attached to associations and properties of a concept. 
   */


public  class Qualifier
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the name of a qualifier	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this Qualifier	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the name for this Qualifier	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * Specifies the value of a qualifier	   
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
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Qualifier) {
			Qualifier c =(Qualifier)obj; 
								
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
