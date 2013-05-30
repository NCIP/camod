/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/*
 * TreeCacheManager.java
 *
 * Created on September 1, 2006, 3:07 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gov.nih.nci.evs.app.util;

import net.sf.ehcache.*;

/**
 *
 * @author piparom
 */
public class TreeCacheManager {
    
  static private TreeCacheManager instance = null;
  
  private CacheManager cacheManager = null;
  
  private Cache webTreesCache;    
  
  private String cacheConfigFile = "evsTrees_ehcache.xml";
  
  protected TreeCacheManager() {
    if (cacheManager == null) { 
      cacheManager = new CacheManager(this.getClass().getResourceAsStream(cacheConfigFile)); 
      webTreesCache = cacheManager.getCache("webTreesCache");  
      }                 
  }
      
  public CacheManager getCacheManager() {  
    return cacheManager; 
  }

  public Cache getWebTreesCache() { 	  
    return webTreesCache; 
  }    
  
  public static TreeCacheManager getInstance() { 
    if (instance == null) {
      instance = new TreeCacheManager();
    }
    return instance; 
  }

  protected void finalize() throws Throwable {  
    try {
        this.shutdown();
    } finally {
        super.finalize();
    }
  }
  
  public void shutdown() throws Exception { 
    if (cacheManager != null) {
        System.out.println("Shutting down Tree Cache Manager...");
      	Thread.sleep(1000);		
        cacheManager.shutdown();
        Thread.sleep(1000);		
    }
  }
  
}
