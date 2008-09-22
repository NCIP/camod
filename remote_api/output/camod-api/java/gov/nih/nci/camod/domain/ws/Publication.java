

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Publication 
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
	
	   
	   public String volume;
	   public String getVolume(){
	      return volume;
	   }
	   
	   public void setVolume(String volume){
	      this.volume = volume;
	   }
	
	   
	   public Long endPage;
	   public Long getEndPage(){
	      return endPage;
	   }
	   
	   public void setEndPage(Long endPage){
	      this.endPage = endPage;
	   }
	
	   
	   public Long year;
	   public Long getYear(){
	      return year;
	   }
	   
	   public void setYear(Long year){
	      this.year = year;
	   }
	
	   
	   public String title;
	   public String getTitle(){
	      return title;
	   }
	   
	   public void setTitle(String title){
	      this.title = title;
	   }
	
	   
	   public Long pmid;
	   public Long getPmid(){
	      return pmid;
	   }
	   
	   public void setPmid(Long pmid){
	      this.pmid = pmid;
	   }
	
	   
	   public Long startPage;
	   public Long getStartPage(){
	      return startPage;
	   }
	   
	   public void setStartPage(Long startPage){
	      this.startPage = startPage;
	   }
	
	   
	   public String journal;
	   public String getJournal(){
	      return journal;
	   }
	   
	   public void setJournal(String journal){
	      this.journal = journal;
	   }
	
	   
	   public String authors;
	   public String getAuthors(){
	      return authors;
	   }
	   
	   public void setAuthors(String authors){
	      this.authors = authors;
	   }
	
	   
	   public Boolean firstTimeReported;
	   public Boolean getFirstTimeReported(){
	      return firstTimeReported;
	   }
	   
	   public void setFirstTimeReported(Boolean firstTimeReported){
	      this.firstTimeReported = firstTimeReported;
	   }
	
	   
	   public java.lang.String jaxJNumber;
	   public  java.lang.String getJaxJNumber(){
	      return jaxJNumber;
	   }
	   
	   public void setJaxJNumber( java.lang.String jaxJNumber){
	      this.jaxJNumber = jaxJNumber;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.PublicationStatus publicationStatus;
			public gov.nih.nci.camod.domain.ws.PublicationStatus getPublicationStatus(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setPublicationStatus(gov.nih.nci.camod.domain.ws.PublicationStatus publicationStatus){
		this.publicationStatus = publicationStatus;
	   }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Publication) {
				Publication c =(Publication)obj; 			 
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