

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class Organ 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String conceptCode;
	   public String getConceptCode(){
	      return conceptCode;
	   }
	   
	   public void setConceptCode(String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection expressionLevelDescCollection = new java.util.HashSet();
			public java.util.Collection getExpressionLevelDescCollection(){
	              return expressionLevelDescCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setExpressionLevelDescCollection(java.util.Collection expressionLevelDescCollection){
	   		this.expressionLevelDescCollection = expressionLevelDescCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
	              return histopathologyCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection expressionFeatureCollection = new java.util.HashSet();
			public java.util.Collection getExpressionFeatureCollection(){
	              return expressionFeatureCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setExpressionFeatureCollection(java.util.Collection expressionFeatureCollection){
	   		this.expressionFeatureCollection = expressionFeatureCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Organ) {
				Organ c =(Organ)obj; 			 
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
