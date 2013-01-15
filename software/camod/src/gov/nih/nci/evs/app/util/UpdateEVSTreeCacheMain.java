/*
 * Main.java
 *
 * Created on August 15, 2006, 10:31 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gov.nih.nci.evs.app.util;

import gov.nih.nci.evs.app.webtree.EvsWebTreeImpl;

/**
 *
 * @author piparom
 */
public class UpdateEVSTreeCacheMain {
  
  /** Creates a new instance of Main */
  public UpdateEVSTreeCacheMain() {
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
      System.out.println("UpdateEVSTreeCacheMain entered main method" );
      System.out.println("Re-freshing EVS Tree Caches...");
  	try
    {
      //TreeShutdownThread treeShutdown = new TreeShutdownThread();      
      //Runtime.getRuntime().addShutdownHook(treeShutdown);
      
      EvsWebTreeImpl evsWebTree = new EvsWebTreeImpl(); 
      evsWebTree.cacheTrees(true);              
  	}
	  catch(Exception e)
    {
  		System.out.println("Error in caching EVS trees!");
    	e.printStackTrace();     
      System.exit(-1);
    }     
      
    System.exit(0);  
  }
  
}
