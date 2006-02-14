

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Disease objects specify a disease name and ID; disease objects also provide access to: ontological 
   * relations to other diseases; clinical trial protocols treating the disease; and specific histologies 
   * associated with instances of the disease. 
   * 
   */

public  class Disease 
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
	

	
	   
	   
	   
	      

			private java.util.Collection histopathologyCollection = new java.util.HashSet();
			public java.util.Collection getHistopathologyCollection(){
			try{
			   if(histopathologyCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Disease thisIdSet = new gov.nih.nci.camod.domain.Disease();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Histopathology", thisIdSet);				 
				 	histopathologyCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Disease:getHistopathologyCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return histopathologyCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setHistopathologyCollection(java.util.Collection histopathologyCollection){
	   		this.histopathologyCollection = histopathologyCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Disease) {
				Disease c =(Disease)obj; 			 
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