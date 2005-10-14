/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RepositoryInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258705453799404851L;
    
    private Long inTheRepository;
    private String sentEmailContent;
    private Long suggestSubmission;
 

    /**
     * @return Returns the inTheRepository.
     */
    public Long getInTheRepository() {
        return inTheRepository;
    }

    /**
     * @param inTheRepository
     *            The inTheRepository to set.
     */
    public void setInTheRepository(Long inTheRepository) {
        this.inTheRepository = inTheRepository;
    }

    /**
     * @return Returns the sentEmailContent.
     */
    public String getSentEmailContent() {
        return sentEmailContent;
    }

    /**
     * @param sentEmailContent
     *            The sentEmailContent to set.
     */
    public void setSentEmailContent(String sentEmailContent) {
        this.sentEmailContent = sentEmailContent;
    }

    /**
     * @return Returns the suggestSubmission.
     */
    public Long getSuggestSubmission() {
        return suggestSubmission;
    }

    /**
     * @param suggestSubmission
     *            The suggestSubmission to set.
     */
    public void setSuggestSubmission(Long suggestSubmission) {
        this.suggestSubmission = suggestSubmission;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getSentEmailContent();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
