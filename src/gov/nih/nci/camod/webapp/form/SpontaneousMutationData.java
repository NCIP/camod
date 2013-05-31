/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.form;

public interface SpontaneousMutationData {
    
	public String getName();
	
	public void setName(String name);
	
	public String getNumberMGI();

	public void setNumberMGI(String numberMGI);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public String getObservation();
	
	public void setObservation(String observation);
	
	public String getMethodOfObservation();
	
	public void setMethodOfObservation(String methodOfObservation);
	
}
