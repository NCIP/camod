

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class TargetedModification 
    extends EngineeredGene
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String esCellLineName;
	   public String getEsCellLineName(){
	      return esCellLineName;
	   }
	   
	   public void setEsCellLineName(String esCellLineName){
	      this.esCellLineName = esCellLineName;
	   }
	
	   
	   public String blastocystName;
	   public String getBlastocystName(){
	      return blastocystName;
	   }
	   
	   public void setBlastocystName(String blastocystName){
	      this.blastocystName = blastocystName;
	   }
	
	   
	   public String geneId;
	   public String getGeneId(){
	      return geneId;
	   }
	   
	   public void setGeneId(String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public java.lang.String constructSequence;
	   public  java.lang.String getConstructSequence(){
	      return constructSequence;
	   }
	   
	   public void setConstructSequence( java.lang.String constructSequence){
	      this.constructSequence = constructSequence;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.YeastModel yeastModel;
			public gov.nih.nci.camod.domain.ws.YeastModel getYeastModel(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setYeastModel(gov.nih.nci.camod.domain.ws.YeastModel yeastModel){
		this.yeastModel = yeastModel;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection modificationTypeCollection = new java.util.HashSet();
			public java.util.Collection getModificationTypeCollection(){
	              return modificationTypeCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setModificationTypeCollection(java.util.Collection modificationTypeCollection){
	   		this.modificationTypeCollection = modificationTypeCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof TargetedModification) {
				TargetedModification c =(TargetedModification)obj; 			 
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
