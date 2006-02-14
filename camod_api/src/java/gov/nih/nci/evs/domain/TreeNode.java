

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * The TreeNode class specifies the relationshop between a concept and it's immediate parent when 
   * a TREE is generated using the getTree method. 
   * 
* @deprecated @deprecated - The name of this object is misleading. The preferred class sudgested by the EVSTeam is EdgeProperties.
   */


public  class TreeNode
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * Specifes the isa value for this concept. If a relationship type between this concept and the parent is a "isa" this value is set to true.	   
	 */
	   	
	
	private boolean isA;	   
	   
	   
	/**
	* @depricated -  Returns the is-a value	   
	* @return - isA
	*/
	public boolean getIsA(){
	      return isA;
	      }   
	   		
	/**
	* @depricated - Sets the specified isa value	   
	* @param - isA
	*/
	public void setIsA(boolean isA){
	      this.isA = isA;
	   }	   
	
	   
	 /**
	 * The direction specified when the concept tree was generated. 	   
	 */
	   	
	
	private boolean traverseDown;	   
	   
	   
	/**
	* @depricated - Returns the traverseDown value	   
	* @return - traverseDown
	*/
	public boolean getTraverseDown(){
	      return traverseDown;
	      }   
	   		
	/**
	* @depricated - Sets the traverseDown value	   
	* @param - traverseDown
	*/
	public void setTraverseDown(boolean traverseDown){
	      this.traverseDown = traverseDown;
	   }	   
	
	   
	 /**
	 * Specifes the name for this instance	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* @depricated - Returns the name for this TreeNode	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* @depricated - Sets the name for this TreeNode	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * Specifes the link information used  to generate the concept tree	   
	 */
	   	
	
	private java.util.HashSet links;	   
	   
	   
	/**
	* 	   
	* @return - links
	*/
	public  java.util.HashSet getLinks(){
	      return links;
	      }   
	   		
	/**
	* @depricated - sets the links for this TreeNode	   
	* @param - links
	*/
	public void setLinks( java.util.HashSet links){
	      this.links = links;
	   }	   
	
	
	   
	   	
	   
	   
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof TreeNode) {
			TreeNode c =(TreeNode)obj; 
								
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
