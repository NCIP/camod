/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Curateable;

import java.util.Map;


/**
 * This interface describes a realized/implementing CurationManager.
 */
public interface CurateableAction {
    public void execute(Map inArgs, Curateable inObject);
    public CurateableAction create();
}
