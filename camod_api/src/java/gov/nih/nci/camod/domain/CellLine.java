

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Cell lines generated from the animal model.
   */

public  class CellLine 
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
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	
	   
	   public String experiment;
	   public String getExperiment(){
	      return experiment;
	   }
	   public void setExperiment(String experiment){
	      this.experiment = experiment;
	   }
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	
	   
	   public String results;
	   public String getResults(){
	      return results;
	   }
	   public void setResults(String results){
	      this.results = results;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
			try{
			   if(publicationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.CellLine thisIdSet = new gov.nih.nci.camod.domain.CellLine();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Publication", thisIdSet);				 
				 	publicationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("CellLine:getPublicationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return publicationCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Organ organ;
			public gov.nih.nci.camod.domain.Organ getOrgan(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.CellLine thisIdSet = new gov.nih.nci.camod.domain.CellLine();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Organ", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                organ = (gov.nih.nci.camod.domain.Organ)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("CellLine:getOrgan throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return organ;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setOrgan(gov.nih.nci.camod.domain.Organ organ){
		this.organ = organ;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CellLine) {
				CellLine c =(CellLine)obj; 			 
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