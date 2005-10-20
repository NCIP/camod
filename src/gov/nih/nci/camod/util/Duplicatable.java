/*
 * Duplicatable.java
 *
 * Created on October 20, 2005, 10:07 AM
 */

package gov.nih.nci.camod.util;

/**
 * Duplicatable - Interface in support of DuplicateUtil Bean Deep-Copy Utility
 * <p>
 * This class provides an interface for demarkating bean objects to be duplicated
 * via a deep-copy.  Bean classes implementing this interface will be 
 * deep-copied during execution of the DuplicateUtil.duplicateBean function, 
 * rather than copied by reference.
 *
 * Any Sub-classes of bean classes which implmemnent this interface will also
 * be deep-copied as well.
 *
 * <p>
 * @author Marc Piparo
 * @version 1.0
 * @see DuplicateUtil
 */
public interface Duplicatable {
  // marker for bean classes to implement to perform
  // deep-copy during duplicate of itself and sub-classes.
}
