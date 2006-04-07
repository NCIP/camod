

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.Organ organ;
			public gov.nih.nci.camod.domain.ws.Organ getOrgan(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.ws.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.ExpressionLevelDesc expressionLevelDesc;
			public gov.nih.nci.camod.domain.ws.ExpressionLevelDesc getExpressionLevelDesc(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setExpressionLevelDesc(gov.nih.nci.camod.domain.ws.ExpressionLevelDesc expressionLevelDesc){
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
