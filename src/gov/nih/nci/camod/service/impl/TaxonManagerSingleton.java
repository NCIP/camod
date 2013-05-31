/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

public class TaxonManagerSingleton {

    private static TaxonManagerImpl ourManager = new TaxonManagerImpl();

    /**
     * @return the global instance of the TaxonManager
     */
    public static synchronized TaxonManagerImpl instance() {
        return ourManager;
    }
}
