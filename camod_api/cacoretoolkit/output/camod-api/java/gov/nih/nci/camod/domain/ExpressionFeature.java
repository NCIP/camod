

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The expression feature object describes expression pattern of the engineered gene in the cancer 
   * model. 
   * 
   */

public  class ExpressionFeature 
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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.ExpressionFeature thisIdSet = new gov.nih.nci.camod.domain.ExpressionFeature();
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