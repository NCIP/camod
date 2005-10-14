package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * Base class for Model objects.  Child objects should implement toString(), 
 * equals() and hashCode();
 *
 * <p>
 * <a href="BaseObject.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {    
  
  private Long id = null;
  
  private void setId(Long id) { this.id = id; }
  
  public Long getId() { return id; }    
    
  public boolean equals(Object o) {
    if (this == o) return true;        
    if ((o == null) || (!(o instanceof BaseObject))) return false;
    
    final BaseObject obj = (BaseObject) o;
    if (HashCodeUtil.notEqual(this.getId(), obj.getId())) return false;
    
    return true;
  }
  
   public int hashCode() {
    int result = HashCodeUtil.SEED;        
    result = HashCodeUtil.hash(result, this.getId());     
    return result;
  }

  public String toString() {
    String result = "ID: ";
    if (id != null) {
      result += id.toString();
    } else {
      result += "Null";
    }    
    return result;
  } 
  
}
