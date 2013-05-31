/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

public interface CurateableActionFactory {
    public CurateableAction getAction(String inActionName);
    public void registerAction(String inActionName, CurateableAction inAction);
}
