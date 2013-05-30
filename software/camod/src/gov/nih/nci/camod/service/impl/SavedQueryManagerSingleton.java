/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SavedQueryManager;

public class SavedQueryManagerSingleton {

    private static SavedQueryManager ourManager = new SavedQueryManagerImpl();

    public static synchronized SavedQueryManager instance() {
        return ourManager;
    }
}
