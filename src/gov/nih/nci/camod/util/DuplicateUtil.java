/*
 * DuplicateUtil.java
 *
 * Created on October 19, 2005, 3:44 PM
 *
 * support for executing deep copy of bean objects.
 * to implement..classes choosing to perform deep copy of themselves must
 * implement 2 methods as follows.  
 *
 * For example to make MyBean deep copyable:
 *
 *  public MyBean duplicate() throws Exception { 
 *    return this.duplicateImpl(null);
 *  }  
 *
 *  public MyBean duplicateImpl(Collection history) throws Exception {   
 *    return (MyBean) DuplicateUtil.duplicateBean(this, history);      
 *  } 
 *
 *  usage:
 *
 *  MyBean copyBean = sourceBean.duplicate();
 *
 */

package gov.nih.nci.camod.util;

import java.util.*;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.*;
import org.apache.commons.logging.*;

/**
 *
 * @author piparom
 */
public class DuplicateUtil {
  
  private static Log log = LogFactory.getLog(DuplicateUtil.class);   
   
  public static Object duplicateBean(Object src, Collection history) throws Exception {    
    Object duplicate = null;    
     
    try {
      // reset history collection on root duplicate
      if (history == null) {
        history = new HashSet();
      }   
           
      // check if we've already duplicated this object
      if (!history.contains(src)) {
        history.add(src);       
        
        // instantiate a new instance of this class
        duplicate = (Object) src.getClass().newInstance();        

        Map beanProps = PropertyUtils.describe(src);
        Iterator props = beanProps.entrySet().iterator();

        // loop thru bean properties
        while (props.hasNext()) {          
          Map.Entry entry = (Map.Entry) props.next();    
          String propName = (String) entry.getKey();  
          Object propValue = entry.getValue();          
          log.debug("duplicating property: "+propName);  
          
          // exclude built-in getClass property
          if (!propName.equals("class")) {
            Class propertyType = PropertyUtils.getPropertyType(duplicate, propName); 

            // check if property is a collection
            if (Class.forName("java.util.Collection").isInstance(propValue)) {    
              Collection collectionProperty = (Collection) propValue;
              if (!collectionProperty.isEmpty()) {       
                // get collection property - *bean class MUST instatiate collection*
                Collection duplicateCollection = (Collection) PropertyUtils.getProperty(duplicate, propName);
                if (duplicateCollection != null) {
                  // iterate thru collection, duplicate elements and add to collection
                  for (Iterator iter = collectionProperty.iterator(); iter.hasNext();) {                    
                    Object collectionEntry = iter.next();                                           
                    duplicateCollection.add(duplicateProperty(collectionEntry, history));                                      
                  }
                }                             
              }           
            } else {
              // set member property in duplicate object             
              try {          
                BeanUtils.setProperty(duplicate, propName, duplicateProperty(propValue, history));                
              } catch (Exception ex) {
                // do nothing. skip and move on. property value may be null, or no set method found.           
                log.info("property "+propName+" not duplicated.  Either no set method, or value not set or null.");
              }
            } // collection condition                
          }
        } // loop end
      } else {
        // this object has already been duplicated, simply return reference
        // to the duplicate rather than re-duplicate        
        duplicate = src;
      }
    } catch (Exception ex) {
      throw new Exception("Error during Bean Duplicate: "+ex);
    } 
    
    return duplicate;                
  }
  
  
  private static Object duplicateProperty(Object obj, Collection history) throws Exception {      
     Method duplicateMethod = null; 
     try {                   
       duplicateMethod = obj.getClass().getMethod("duplicateImpl", new Class[] {Collection.class});
     } catch (NoSuchMethodException e) {
       duplicateMethod = null;
     }
    
     // if object has a duplicate method implemented, invoke it.
     // otherwise, return by reference
     if (duplicateMethod != null) {           
       return duplicateMethod.invoke(obj, new Object[] {history});    
     } else {       
       return obj;
     }               
  }  
      
}
