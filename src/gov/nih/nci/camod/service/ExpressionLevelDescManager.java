/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service;

import java.util.List;

import gov.nih.nci.camod.domain.ExpressionLevelDesc;

public interface ExpressionLevelDescManager {

	public List getAll() throws Exception;
	
    public ExpressionLevelDesc getByName(String inName) throws Exception;

}
