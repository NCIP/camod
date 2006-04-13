

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class YeastModel 
    extends AbstractCancerModel
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	

	
	   
	   
	   
	      
			private java.util.Collection targetedModificationCollection = new java.util.HashSet();
			public java.util.Collection getTargetedModificationCollection(){
	              return targetedModificationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetedModificationCollection(java.util.Collection targetedModificationCollection){
	   		this.targetedModificationCollection = targetedModificationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection screeningResultCollection = new java.util.HashSet();
			public java.util.Collection getScreeningResultCollection(){
	              return screeningResultCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setScreeningResultCollection(java.util.Collection screeningResultCollection){
	   		this.screeningResultCollection = screeningResultCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof YeastModel) {
				YeastModel c =(YeastModel)obj; 			 
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
