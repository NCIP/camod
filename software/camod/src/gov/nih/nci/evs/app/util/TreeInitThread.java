/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.evs.app.util;

import gov.nih.nci.evs.app.webtree.EvsWebTreeImpl;

public class TreeInitThread extends Thread {
	public TreeInitThread(String name) {
		super(name);		
	}

	public void run() {
		System.out.println("Entered TreeIntThread run method: ");
		try {
			System.out.println("TreeIntThread Thread: " + this.getName() + " - Executing");
			//Thread.sleep(100);
      
			EvsWebTreeImpl evsWebTree = new EvsWebTreeImpl(); 
			evsWebTree.cacheTrees(false); 
			      
		} catch (Exception e) {
			System.err.println("Error in TreeInitThread - loading tree thread: "
					+ this.getName());
			e.printStackTrace();
		}
	}

}
