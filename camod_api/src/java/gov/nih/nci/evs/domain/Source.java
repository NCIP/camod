

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * The source is a knowledge base.
   */


public  class Source
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the source abbreviation	   
	 */
	   	
	
	private java.lang.String abbreviation;	   
	   
	   
	/**
	* Returns the abbreviation of this Source	   
	* @return - abbreviation
	*/
	public  java.lang.String getAbbreviation(){
	      return abbreviation;
	      }   
	   		
	/**
	* Sets the specified abbreviation  for this Source	   
	* @param - abbreviation
	*/
	public void setAbbreviation( java.lang.String abbreviation){
	      this.abbreviation = abbreviation;
	   }	   
	
	   
	 /**
	 * Textual description of the source	   
	 */
	   	
	
	private java.lang.String description;	   
	   
	   
	/**
	* Returns the description of this Source	   
	* @return - description
	*/
	public  java.lang.String getDescription(){
	      return description;
	      }   
	   		
	/**
	* Sets the specified description for this Source	   
	* @param - description
	*/
	public void setDescription( java.lang.String description){
	      this.description = description;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof Source) {
			Source c =(Source)obj; 
								
				String thisKey =  getAbbreviation();			
				if(thisKey!= null && thisKey.equals(c.getAbbreviation())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getAbbreviation() != null) {
			h += getAbbreviation().hashCode();
		}
		return h;
	}
	
	
}
