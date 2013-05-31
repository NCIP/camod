/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

public class EngineeredTransgeneManagerSingleton {

    private static EngineeredTransgeneManagerImpl ourManager = new EngineeredTransgeneManagerImpl();

    /**
     * @return the global instance of the GeneDelivery
     */
    public static synchronized EngineeredTransgeneManagerImpl instance() {
        return ourManager;
    }
}
