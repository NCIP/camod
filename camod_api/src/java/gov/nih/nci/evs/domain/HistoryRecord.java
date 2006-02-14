

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * The HistoryRecord holds history information for the specifed concept
   */


public  class HistoryRecord
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * This is an unique code associated with a concept within the specified vocabulary. 	   
	 */
	   	
	
	private java.lang.String descLogicConceptCode;	   
	   
	   
	/**
	* 	   
	* @return - descLogicConceptCode
	*/
	public  java.lang.String getDescLogicConceptCode(){
	      return descLogicConceptCode;
	      }   
	   		
	/**
	* 	   
	* @param - descLogicConceptCode
	*/
	public void setDescLogicConceptCode( java.lang.String descLogicConceptCode){
	      this.descLogicConceptCode = descLogicConceptCode;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      		
	private java.util.Vector historyCollection = new java.util.Vector();
	/**
	* Returns history information of this HistoryRecord	   
	* @return - historyCollection
	*/

	public java.util.Vector getHistoryCollection(){
		return historyCollection;
	}
	              	  
	      
	     
		   
	   	
	/**
	* Sets the specified history information for this HistroryRecord	   
	* @return - historyCollection
	*/

	public void setHistoryCollection(java.util.Vector historyCollection){
	   	
	   	this.historyCollection = historyCollection;
	        }	
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof HistoryRecord) {
			HistoryRecord c =(HistoryRecord)obj; 
								
				String thisKey =  getDescLogicConceptCode();			
				if(thisKey!= null && thisKey.equals(c.getDescLogicConceptCode())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getDescLogicConceptCode() != null) {
			h += getDescLogicConceptCode().hashCode();
		}
		return h;
	}
	
	
}
