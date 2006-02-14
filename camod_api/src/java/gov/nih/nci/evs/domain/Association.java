

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * An Association class relates a concept or a term to another concept or term. Association falls into 
   * 3 categories; concept association, term association and synonyms which are concept-term associations. 
   * 
   */


public  class Association
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * The name attribute holds the name of the association	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name for this Association	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the specified name for this Association	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * The value attribute holds the value of association	   
	 */
	   	
	
	private java.lang.String value;	   
	   
	   
	/**
	* Returns the value of this Association	   
	* @return - value
	*/
	public  java.lang.String getValue(){
	      return value;
	      }   
	   		
	/**
	* Sets the specified value for this Association	   
	* @param - value
	*/
	public void setValue( java.lang.String value){
	      this.value = value;
	   }	   
	
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector qualifierCollection = new java.util.Vector();
	/**
	* Returns the Qualifiers of this Association	   
	* @return - qualifierCollection
	*/

	public java.util.Vector getQualifierCollection(){
		return qualifierCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified Qualifiers for this Association	   
	* @return - qualifierCollection
	*/

	public void setQualifierCollection(java.util.Vector qualifierCollection){
	   	
	   	this.qualifierCollection = qualifierCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Association) {
			Association c =(Association)obj; 
								
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
