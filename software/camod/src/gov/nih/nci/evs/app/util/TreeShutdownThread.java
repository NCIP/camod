/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * TreeShutdown.java
 *
 * Created on September 1, 2006, 2:50 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gov.nih.nci.evs.app.util;

/**
 *
 * @author piparom
 */
public class TreeShutdownThread extends Thread {
  
  public void run() {
    try {
      TreeCacheManager.getInstance().shutdown();    
    } catch (Exception e) {
      System.err.println("Exception in Tree Cache Shutdown: "+e.getMessage());
    }
  }
  
  
}
