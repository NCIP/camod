/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ExpressionLevelDescManager;

/**
 * @author schroedln
 * 
 * Singleton class for the ExpressionLevelDescManagerSingleton
 */
public class ExpressionLevelDescManagerSingleton {

	
    private static ExpressionLevelDescManager ourManager = new ExpressionLevelDescManagerImpl();

    /**
     * @return the global instance of the ExpressionLevelDescManager
     */
    public static synchronized ExpressionLevelDescManager instance() {
        return ourManager;
    }	
}
