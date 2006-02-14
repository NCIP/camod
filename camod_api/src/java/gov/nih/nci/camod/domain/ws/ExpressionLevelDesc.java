

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class ExpressionLevelDesc 
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
	
	   
	   public String expressionLevel;
	   public String getExpressionLevel(){
	      return expressionLevel;
	   }
	   
	   public void setExpressionLevel(String expressionLevel){
	      this.expressionLevel = expressionLevel;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection organCollection = new java.util.HashSet();
			public java.util.Collection getOrganCollection(){
	              return organCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setOrganCollection(java.util.Collection organCollection){
	   		this.organCollection = organCollection;
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
			if(obj instanceof ExpressionLevelDesc) {
				ExpressionLevelDesc c =(ExpressionLevelDesc)obj; 			 
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
