

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class CellLine 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String experiment;
	   public String getExperiment(){
	      return experiment;
	   }
	   
	   public void setExperiment(String experiment){
	      this.experiment = experiment;
	   }
	
	   
	   public String results;
	   public String getResults(){
	      return results;
	   }
	   
	   public void setResults(String results){
	      this.results = results;
	   }
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
	              return publicationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Organ organ;
			public gov.nih.nci.camod.domain.ws.Organ getOrgan(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CellLine) {
				CellLine c =(CellLine)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
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