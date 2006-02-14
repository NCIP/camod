

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * The expression feature object describes expression pattern of the engineered gene in the cancer 
   * model. 
   * 
   */

public  class ExpressionFeature 
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
	
		   	   	   	      	   	   	  
	   
	      

			//private java.util.Collection organCollection = new java.util.HashSet();
			public java.util.Collection getOrganCollection(){
        java.util.Collection organCollection = new java.util.HashSet();
        gov.nih.nci.camod.domain.Organ organ = this.getOrgan();
        if (organ != null) {
          organCollection.add((gov.nih.nci.camod.domain.Organ)organ);
        }      
      /*
      
			try{
			   if(organCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      			      			         
				 	gov.nih.nci.camod.domain.ExpressionFeature thisIdSet = new gov.nih.nci.camod.domain.ExpressionFeature();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
				 	//organCollection = resultList;  
                if (resultList.size() > 0) {
                  organCollection.add(resultList.toArray()[0]);  
                }
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("ExpressionFeature:getOrganCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
       */
        
	       return organCollection;
	    }
			   
			  			   	      	               	   
	   	/*public void setOrganCollection(java.util.Collection organCollection){
	   		this.organCollection = organCollection;
	        }	
	   */
	   
	
	   	private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Organ thisIdSet = new gov.nih.nci.camod.domain.Organ();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                organ = (gov.nih.nci.camod.domain.Organ)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("ExpressionFeature:getOrgan throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return organ;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ExpressionLevelDesc expressionLevelDesc;
			public gov.nih.nci.camod.domain.ExpressionLevelDesc getExpressionLevelDesc(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.ExpressionFeature thisIdSet = new gov.nih.nci.camod.domain.ExpressionFeature();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.ExpressionLevelDesc", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                expressionLevelDesc = (gov.nih.nci.camod.domain.ExpressionLevelDesc)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("ExpressionFeature:getExpressionLevelDesc throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return expressionLevelDesc;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setExpressionLevelDesc(gov.nih.nci.camod.domain.ExpressionLevelDesc expressionLevelDesc){
		this.expressionLevelDesc = expressionLevelDesc;
	   }	
	   
     
     
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof ExpressionFeature) {
				ExpressionFeature c =(ExpressionFeature)obj; 			 
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