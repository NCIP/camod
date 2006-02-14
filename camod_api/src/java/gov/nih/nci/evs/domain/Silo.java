

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * Silo is a repository of customized concept terminology data from a knowledgebase. There can be a 
   * single silo or multiple silos, each consisting of semantically related concepts and extracted 
   * character strings associated with those concepts. 
   * 
   */


public  class Silo
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * The  identifier of this Silo	   
	 */
	   	
	
	private int id;	   
	   
	   
	/**
	* Returns the id for this Silo	   
	* @return - id
	*/
	public int getId(){
	      return id;
	      }   
	   		
	/**
	* Sets the specified id for this Silo	   
	* @param - id
	*/
	public void setId(int id){
	      this.id = id;
	   }	   
	
	   
	 /**
	 * This is the name of this Silo	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name of this Silo	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the specified name for this Silo	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Silo) {
			Silo c =(Silo)obj; 
								
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
