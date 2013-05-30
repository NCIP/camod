/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

public class MicroArrayDataManagerSingleton
{
    private static MicroArrayDataManagerImpl ourManager = new MicroArrayDataManagerImpl();

    /**
     * @return the global instance of the MicroArrayData
     */
    public static synchronized MicroArrayDataManagerImpl instance()
    {
        return ourManager;
    }
}
