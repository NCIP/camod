

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


public  class RepositoryInfo 
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
	
	   
	   public Long inTheRepository;
	   public Long getInTheRepository(){
	      return inTheRepository;
	   }
	   
	   public void setInTheRepository(Long inTheRepository){
	      this.inTheRepository = inTheRepository;
	   }
	
	   
	   public String sentEmailContent;
	   public String getSentEmailContent(){
	      return sentEmailContent;
	   }
	   
	   public void setSentEmailContent(String sentEmailContent){
	      this.sentEmailContent = sentEmailContent;
	   }
	
	   
	   public Long suggestSubmission;
	   public Long getSuggestSubmission(){
	      return suggestSubmission;
	   }
	   
	   public void setSuggestSubmission(Long suggestSubmission){
	      this.suggestSubmission = suggestSubmission;
	   }
	

	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof RepositoryInfo) {
				RepositoryInfo c =(RepositoryInfo)obj; 			 
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
