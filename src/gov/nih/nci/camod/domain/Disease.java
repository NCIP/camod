/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.*;

import java.io.Serializable;
import java.util.*;

/**
 * @author rajputs TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class Disease extends BaseObject implements Comparable, Serializable, Duplicatable {

	private static final long serialVersionUID = 3259515453799404851L;
	

	private String name;

	private String conceptCode;

	private List histopathologyCollection = new ArrayList();

	/**
	 * @return Returns the histopathologyCollection.
	 */
	public List getHistopathologyCollection() {
             if (histopathologyCollection != null) Collections.sort(histopathologyCollection);    
            return histopathologyCollection;                 
	}

	/**
	 * @param histopathologyCollection
	 *            The histopathologyCollection to set.
	 */
	public void setHistopathologyCollection(List histopathologyCollection) {
		this.histopathologyCollection = histopathologyCollection;
	}

	/**
	 * @return Returns the conceptCode.
	 */
	public String getConceptCode() {
		return conceptCode;
	}

	/**
	 * @return Returns the EVS Preferred displayName
	 * 		if the conceptCode = 000000, then return the name
	 */
	public String getEVSPreferredDescription() {
		String thePreferedDesc = null;
		if ("000000".equals(conceptCode)) {
			thePreferedDesc = name;
		} else {
			thePreferedDesc = EvsTreeUtil.getEVSPreferedOrganDescription(conceptCode);
		}
		return thePreferedDesc;
	}

	/**
	 * @param conceptCode
	 *            The conceptCode to set.
	 */
	public void setConceptCode(String conceptCode) {
		this.conceptCode = conceptCode;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
       String result = super.toString() + " - ";      
       result += this.getName();  
       return result;
     }    
    
           
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final Disease obj = (Disease) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof Disease) && (this.getName() != null) && (((Disease)o).getName() != null)) {   
        int result = this.getName().compareTo( ((Disease)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
