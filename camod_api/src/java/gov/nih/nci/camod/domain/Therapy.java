

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Pre-clinical trials experiment conducted on the animal model with the goal to reduce cancer burden. 
   * 
   */

public  class Therapy 
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
	
	   
	   public String experiment;
	   public String getExperiment(){
	      return experiment;
	   }
	   public void setExperiment(String experiment){
	      this.experiment = experiment;
	   }
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	
	   
	   public String results;
	   public String getResults(){
	      return results;
	   }
	   public void setResults(String results){
	      this.results = results;
	   }
	
	   
	   public String toxicityGrade;
	   public String getToxicityGrade(){
	      return toxicityGrade;
	   }
	   public void setToxicityGrade(String toxicityGrade){
	      this.toxicityGrade = toxicityGrade;
	   }
	
	   
	   public String biomarker;
	   public String getBiomarker(){
	      return biomarker;
	   }
	   public void setBiomarker(String biomarker){
	      this.biomarker = biomarker;
	   }
	
	   
	   public String tumorProgression;
	   public String getTumorProgression(){
	      return tumorProgression;
	   }
	   public void setTumorProgression(String tumorProgression){
	      this.tumorProgression = tumorProgression;
	   }
	
	   
	   public Boolean therapeuticExperiment;
	   public Boolean getTherapeuticExperiment(){
	      return therapeuticExperiment;
	   }
	   public void setTherapeuticExperiment(Boolean therapeuticExperiment){
	      this.therapeuticExperiment = therapeuticExperiment;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      

			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
			try{
			   if(publicationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Therapy thisIdSet = new gov.nih.nci.camod.domain.Therapy();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Publication", thisIdSet);				 
				 	publicationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Therapy:getPublicationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return publicationCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Treatment treatment;
			public gov.nih.nci.camod.domain.Treatment getTreatment(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Therapy thisIdSet = new gov.nih.nci.camod.domain.Therapy();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Treatment", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                treatment = (gov.nih.nci.camod.domain.Treatment)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Therapy:getTreatment throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return treatment;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setTreatment(gov.nih.nci.camod.domain.Treatment treatment){
		this.treatment = treatment;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Agent agent;
			public gov.nih.nci.camod.domain.Agent getAgent(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Therapy thisIdSet = new gov.nih.nci.camod.domain.Therapy();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Agent", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                agent = (gov.nih.nci.camod.domain.Agent)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Therapy:getAgent throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return agent;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAgent(gov.nih.nci.camod.domain.Agent agent){
		this.agent = agent;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Therapy) {
				Therapy c =(Therapy)obj; 			 
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