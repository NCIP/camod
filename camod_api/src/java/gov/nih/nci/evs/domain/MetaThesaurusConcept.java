

package gov.nih.nci.evs.domain;


import java.util.*;
  /**
   * MetaThesaurusConcept is the fundermental vocabulary entity in the NCI MetaThesaurus. 
   * 
   */


public  class MetaThesaurusConcept
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

		
	
	   
	 /**
	 * CUI is the Concept Unique Identifier within the MetaThesaurus.	   
	 */
	   	
	
	private java.lang.String cui;	   
	   
	   
	/**
	* Returns the cui for this MetaThesaurusConcept	   
	* @return - cui
	*/
	public  java.lang.String getCui(){
	      return cui;
	      }   
	   		
	/**
	* Sets the specified  cui for this MetaThesaurusConcept	   
	* @param - cui
	*/
	public void setCui( java.lang.String cui){
	      this.cui = cui;
	   }	   
	
	   
	 /**
	 * Specifies the concept name	   
	 */
	   	
	
	private java.lang.String name;	   
	   
	   
	/**
	* Returns the name for this MetaThesaurusConcept	   
	* @return - name
	*/
	public  java.lang.String getName(){
	      return name;
	      }   
	   		
	/**
	* Sets the name for this MetaThesaurusConcept	   
	* @param - name
	*/
	public void setName( java.lang.String name){
	      this.name = name;
	   }	   
	
	   
	 /**
	 * Specifies sysnonyms for the concept	   
	 */
	   	
	
	private java.util.ArrayList synonymCollection;	   
	   
	   
	/**
	* Returns the sysnonymCollection for this MetaThesaurusConcept	   
	* @return - synonymCollection
	*/
	public  java.util.ArrayList getSynonymCollection(){
	      return synonymCollection;
	      }   
	   		
	/**
	* Sets the spcified synonymCollection for this MetaThesaurusConcept	   
	* @param - synonymCollection
	*/
	public void setSynonymCollection( java.util.ArrayList synonymCollection){
	      this.synonymCollection = synonymCollection;
	   }	   
	
	
	   
	   	
	   
	   
	   
	      	      		
	      			      				
	private java.util.ArrayList definitionCollection = new java.util.ArrayList();
	
	/**
	* Returns the Definitions of this MetaThesaurusConcept	   
	* @return - definitionCollection
	*/

	public java.util.ArrayList getDefinitionCollection(){
	      	return definitionCollection;
	}
	      		  
	      
	     
		   
	   	
	/**
	* Sets the specified Definitions for this MetaThesaurusConcept	   
	* @param - definitionCollection
	*/

	public void setDefinitionCollection(java.util.ArrayList definitionCollection){
	   	
	   	this.definitionCollection = definitionCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      			      				
	private java.util.ArrayList sourceCollection = new java.util.ArrayList();
	
	/**
	* Returns Sources of this MetaThesaurusConcept	   
	* @return - sourceCollection
	*/

	public java.util.ArrayList getSourceCollection(){
	      	return sourceCollection;
	}
	      		  
	      
	     
		   
	   	
	/**
	* Sets the specified Sources for this MetaThesaurusConcept	   
	* @param - sourceCollection
	*/

	public void setSourceCollection(java.util.ArrayList sourceCollection){
	   	
	   	this.sourceCollection = sourceCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      			      				
	private java.util.ArrayList semanticTypeCollection = new java.util.ArrayList();
	
	/**
	* Returns the Semantic Types of this MetaThesaurusConcept	   
	* @return - semanticTypeCollection
	*/

	public java.util.ArrayList getSemanticTypeCollection(){
	      	return semanticTypeCollection;
	}
	      		  
	      
	     
		   
	   	
	/**
	* Sets the Semantic Types for this MetaThesaurusConcept	   
	* @param - semanticTypeCollection
	*/

	public void setSemanticTypeCollection(java.util.ArrayList semanticTypeCollection){
	   	
	   	this.semanticTypeCollection = semanticTypeCollection;
	        }	
	   
	
	   
	   	
	   
	   
	   
	      	      		
	      			      				
	private java.util.ArrayList atomCollection = new java.util.ArrayList();
	
	/**
	* Returns the Atoms of this MetaThesaurusConcept	   
	* @return - atomCollection
	*/

	public java.util.ArrayList getAtomCollection(){
	      	return atomCollection;
	}
	      		  
	      
	     
		   
	   	
	/**
	* Sets the specified Atoms for this MetaThesaurusConcept	   
	* @param - atomCollection
	*/

	public void setAtomCollection(java.util.ArrayList atomCollection){
	   	
	   	this.atomCollection = atomCollection;
	        }	
	   
	
	public boolean equals(Object obj){
		boolean eq = false;
		if(obj instanceof MetaThesaurusConcept) {
			MetaThesaurusConcept c =(MetaThesaurusConcept)obj; 
								
				String thisKey =  getCui();			
				if(thisKey!= null && thisKey.equals(c.getCui())) {
					eq = true;
				}		
				
			}
			return eq;
		}
		

	public int hashCode(){
		int h = 0;					
		if(getCui() != null) {
			h += getCui().hashCode();
		}
		return h;
	}
	
	
}
