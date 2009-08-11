/*
 * HashCodeUtil.java
 *
 * Created on April 12, 2005, 11:16 PM
 *
 * $Id: HashCodeUtil.java,v 1.2 2006-04-17 19:10:50 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */

package gov.nih.nci.camod.util;

import java.lang.reflect.Array;

/**
* Collected methods which allow easy implementation of <code>hashCode</code>.
*
* Example use case:
* <pre>
*  public int hashCode(){
*    int result = HashCodeUtil.SEED;
*    result = HashCodeUtil.hash(result, object);
*    result = HashCodeUtil.hash(result, primitiveType);
*    result = HashCodeUtil.hash(result, arrayType);
*    return result;
*  }
* </pre>
*/
public final class HashCodeUtil {

  /**
  * An initial value for a <code>hashCode</code>, to which is added contributions
  * from fields. Using a non-zero value decreases collisons of <code>hashCode</code>
  * values.
  */
  public static final int SEED = 23;

  /**
  * booleans.
  */
  public static int hash( int aSeed, boolean aBoolean ) {    
    return firstTerm( aSeed ) + ( aBoolean ? 1 : 0 );
  }

  /**
  * chars.
  */
  public static int hash( int aSeed, char aChar ) {    
    return firstTerm( aSeed ) + (int)aChar;
  }

  /**
  * ints.
  */
  public static int hash( int aSeed , int aInt ) {
    /*
    * Implementation Note
    * Note that byte and short are handled by this method, through
    * implicit conversion.
    */    
    return firstTerm( aSeed ) + aInt;
  }

  /**
  * longs.
  */
  public static int hash( int aSeed , long aLong ) {    
    return firstTerm(aSeed)  + (int)( aLong ^ (aLong >>> 32) );
  }

  /**
  * floats.
  */
  public static int hash( int aSeed , float aFloat ) {
    return hash( aSeed, Float.floatToIntBits(aFloat) );
  }

  /**
  * doubles.
  */
  public static int hash( int aSeed , double aDouble ) {
    return hash( aSeed, Double.doubleToLongBits(aDouble) );
  }

  /**
  * <code>aObject</code> is a possibly-null object field, and possibly an array.
  *
  * If <code>aObject</code> is an array, then each element may be a primitive
  * or a possibly-null object.
  */
  public static int hash( int aSeed , Object aObject ) {
    int result = aSeed;
    if ( aObject == null) {
      result = hash(result, 0);
    }
    else if ( ! isArray(aObject) ) {
      result = hash(result, aObject.hashCode());
    }
    else {
      int length = Array.getLength(aObject);
      for ( int idx = 0; idx < length; ++idx ) {
        Object item = Array.get(aObject, idx);
        //recursive call!
        result = hash(result, item);
      }
    }
    return result;
  }

  
  public static boolean notEqual(Object self, Object tgt) {
      return (self != null ? !self.equals(tgt) : tgt != null);    
  }
      
  public static boolean notEqual(int self, int tgt) {
      return !(self == tgt);    
  }
       
  public static boolean notEqual(char self, char tgt) {
      return !(self == tgt);    
  }
         
  public static boolean notEqual(byte self, byte tgt) {
      return !(self == tgt);    
  }  
         
  public static boolean notEqual(short self, short tgt) {
      return !(self == tgt);    
  }
  
  public static boolean notEqual(long self, long tgt) {
      return !(self == tgt);    
  }
  
  public static boolean notEqual(float self, float tgt) {
      return !(self == tgt);    
  }
  
  public static boolean notEqual(double self, double tgt) {
      return !(self == tgt);    
  }
  
  public static boolean notEqual(boolean self, boolean tgt) {
      return !(self == tgt);    
  }

  /// PRIVATE ///
  private static final int fODD_PRIME_NUMBER = 37;

  private static int firstTerm( int aSeed ){
    return fODD_PRIME_NUMBER * aSeed;
  }

  private static boolean isArray(Object aObject){
    return aObject.getClass().isArray();
  }
} 

