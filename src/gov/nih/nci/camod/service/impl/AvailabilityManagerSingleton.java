/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

public class AvailabilityManagerSingleton {

    private static AvailabilityManagerImpl ourManager = new AvailabilityManagerImpl();

    /**
     * @return the global instance of the AvailabilityManager
     */
    public static synchronized AvailabilityManagerImpl instance() {
        return ourManager;
    }	
	
}
