

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class SpontaneousMutation 
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
	
	   
	   public java.lang.String geneId;
	   public  java.lang.String getGeneId(){
	      return geneId;
	   }
	   
	   public void setGeneId( java.lang.String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.MutationIdentifier mutationIdentifier;
			public gov.nih.nci.camod.domain.ws.MutationIdentifier getMutationIdentifier(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setMutationIdentifier(gov.nih.nci.camod.domain.ws.MutationIdentifier mutationIdentifier){
		this.mutationIdentifier = mutationIdentifier;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneticAlterationCollection = new java.util.HashSet();
			public java.util.Collection getGeneticAlterationCollection(){
	              return geneticAlterationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneticAlterationCollection(java.util.Collection geneticAlterationCollection){
	   		this.geneticAlterationCollection = geneticAlterationCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SpontaneousMutation) {
				SpontaneousMutation c =(SpontaneousMutation)obj; 			 
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
