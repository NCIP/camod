/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

public class AssociatedExpressionManagerSingleton {

    private static AssociatedExpressionManagerImpl ourManager = new AssociatedExpressionManagerImpl();

    /**
     * @return the global instance of the AssociatedExpressionManager
     */
    public static synchronized AssociatedExpressionManagerImpl instance() {
        return ourManager;
    }	
	
}
