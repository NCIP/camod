

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * Semantic type is a category defined in the semantic network that can be used to group similar concepts 
   * 
   */


public  class SemanticType
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the name 	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this SemanticType	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the specified name for this SemanticType	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * 	   
	 */
	   	
	
	private java.lang.String id;	   
	   
	   
	/**
	* 	   
	* @return - id
	*/
	public  java.lang.String getId(){
	      return id;
	      }   
	   		
	/**
	* 	   
	* @param - id
	*/
	public void setId( java.lang.String id){
	      this.id = id;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof SemanticType) {
			SemanticType c =(SemanticType)obj; 
								
				String thisKey =  getId();			
				if(thisKey!= null && thisKey.equals(c.getId())) {
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
