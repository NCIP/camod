

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * This class encapsulates the properties of an caBIO ExpressionLevel object. A client can retrieve 
   * information pertaining to a ExpressionLevel. 
   * 
   */

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
			try{
			   if(organCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.ExpressionLevelDesc thisIdSet = new gov.nih.nci.camod.domain.ExpressionLevelDesc();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
				 	organCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("ExpressionLevelDesc:getOrganCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return organCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setOrganCollection(java.util.Collection organCollection){
	   		this.organCollection = organCollection;
	        }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection expressionFeatureCollection = new java.util.HashSet();
			public java.util.Collection getExpressionFeatureCollection(){
			try{
			   if(expressionFeatureCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.ExpressionLevelDesc thisIdSet = new gov.nih.nci.camod.domain.ExpressionLevelDesc();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ExpressionFeature", thisIdSet);				 
				 	expressionFeatureCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("ExpressionLevelDesc:getExpressionFeatureCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
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