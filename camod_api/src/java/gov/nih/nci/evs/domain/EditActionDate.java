

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   *  xx DOCUMENT ME!
* @deprecated @deprecated - Please use History.java
   */


public  class EditActionDate
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifies the edit action	   
	 */
	   	
	
	private int action;	   
	   
	   
	/**
	* 	   
	* @return - action
	*/
	public int getAction(){
	      return action;
	      }   
	   		
	/**
	* 	   
	* @param - action
	*/
	public void setAction(int action){
	      this.action = action;
	   }	   
	
	   
	 /**
	 * Specifies the edit action date.	   
	 */
	   	
	
	private java.util.Date editDate;	   
	   
	   
	/**
	* 	   
	* @return - editDate
	*/
	public  java.util.Date getEditDate(){
	      return editDate;
	      }   
	   		
	/**
	* 	   
	* @param - editDate
	*/
	public void setEditDate( java.util.Date editDate){
	      this.editDate = editDate;
	   }	   
	
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof EditActionDate) {
			EditActionDate c =(EditActionDate)obj; 
								
				Date thisKey =  getEditDate();			
				if(thisKey!= null && thisKey.equals(c.getEditDate())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getEditDate() != null) {
			h += getEditDate().hashCode();
		}
		return h;
	}
	
	
}
